package com.example.u1m6summative.service;

import com.example.u1m6summative.dao.CustomerDao;
import com.example.u1m6summative.dao.InvoiceDao;
import com.example.u1m6summative.dao.InvoiceItemDao;
import com.example.u1m6summative.dao.ItemDao;
import com.example.u1m6summative.model.Customer;
import com.example.u1m6summative.model.Invoice;
import com.example.u1m6summative.model.InvoiceItem;
import com.example.u1m6summative.model.Item;
import com.example.u1m6summative.viewmodel.CustomerViewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class CustomerServiceLayer {
    private CustomerDao customerDao;
    private InvoiceDao invoiceDao;
    private ItemDao itemDao;
    private InvoiceItemDao invoiceItemDao;

    @Autowired
    public CustomerServiceLayer(CustomerDao customerDao, InvoiceDao invoiceDao, ItemDao itemDao, InvoiceItemDao invoiceItemDao) {
        this.customerDao = customerDao;
        this.invoiceDao = invoiceDao;
        this.itemDao = itemDao;
        this.invoiceItemDao = invoiceItemDao;
    }

    // TODO fix this stuff
    public CustomerViewModel findCustomer(int id) {
        return buildCustomerViewModel(customerDao.getCustomer(id));
    }

    public Customer addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    public CustomerViewModel saveCustomer(CustomerViewModel customerViewModel) {
        Customer customer = new Customer();
        customer.setFirstName(customerViewModel.getCustomer().getFirstName());
        customer.setLastName(customerViewModel.getCustomer().getLastName());
        customer.setEmail(customerViewModel.getCustomer().getEmail());
        customer.setCompany(customerViewModel.getCustomer().getCompany());
        customer.setPhone(customerViewModel.getCustomer().getPhone());
        customer = customerDao.addCustomer(customer);
        customerViewModel.setCustomer(customer);

        List<Invoice> invoices = customerViewModel.getInvoiceList();
        invoices.forEach(invoice -> {
            invoice.setCustomerId(customerViewModel.getCustomer().getCustomerId());
            invoiceDao.addInvoice(invoice);
        });

        invoices = invoiceDao.getInvoiceByCustomerId(customerViewModel.getCustomer().getCustomerId());
        customerViewModel.setInvoiceList(invoices);

        List<Item> itemList = customerViewModel.getItemList();
        itemList.forEach(item -> itemDao.addItem(item));

        List<Invoice> invoiceList = invoiceDao.getInvoiceByCustomerId(customer.getCustomerId());
        List<InvoiceItem> invoiceItemList;
        List<Item> items = new ArrayList<>();
        Map<Integer, List<InvoiceItem>> invoiceItemMap = customerViewModel.getInvoiceItemMap();
        for (Invoice invoice : invoiceList) {
            invoiceItemList = invoiceItemDao.getInvoiceItemsByInvoiceId(invoice.getInvoiceId());
            for(InvoiceItem invoiceItem: invoiceItemList) {
                items.add(itemDao.getItem(invoiceItem.getItemId()));
            }
            invoiceItemMap.put(invoice.getInvoiceId(), invoiceItemList);
        }


        //   return buildCustomerViewModel(customerDao.addCustomer(customer));
        return customerViewModel;
    }

    public List<CustomerViewModel> findAllCustomers() {
        List<Customer> customerList = customerDao.getAllCustomer();

        List<CustomerViewModel> customerViewModelList = new ArrayList<>();

        for (Customer customer : customerList) {
            CustomerViewModel customerViewModel = buildCustomerViewModel(customer);
            customerViewModelList.add(customerViewModel);
        }
        return customerViewModelList;
    }

    public void updateCustomer(CustomerViewModel customerViewModel) {
        Customer customer = customerViewModel.getCustomer();
//        customer.setCustomerId(purchaseViewModel.getCustomerId());
        customer.setFirstName(customer.getFirstName());
        customer.setLastName(customer.getLastName());
        customer.setEmail(customer.getEmail());
        customer.setCompany(customer.getCompany());
        customer.setPhone(customer.getPhone());
        customerDao.updateCustomer(customer);
    }

    public void deleteCustomer(int id) {
        List<Invoice> invoiceList = invoiceDao.getInvoiceByCustomerId(id);
        invoiceList.stream()
                .forEach(item -> invoiceItemDao.deleteInvoiceItem(item.getInvoiceId()));
        invoiceList.stream()
                .forEach(item -> invoiceDao.deleteInvoice(item.getInvoiceId()));
        customerDao.deleteCustomer(id);
    }

    public Item addItem(Item item) {
        return itemDao.addItem(item);
    }

    public List<Item> findAllItem() {
        return itemDao.getAllItem();
    }

    public Item findItem(int id) {
        return itemDao.getItem(id);
    }

    public void updateItem(Item item) {
        itemDao.updateItem(item);
    }

    @Transactional
    public int removeItem(int id) throws DataIntegrityViolationException  {
        return itemDao.deleteItem(id);
    }

    @Transactional
     public Invoice addInvoice(Invoice invoice) {
         return  invoiceDao.addInvoice(invoice);
     }

     public List<Invoice> getInvoicesByCustomer(String firstName, String lastName) {
        return invoiceDao.getInvoicesByCustomer(firstName, lastName);
     }

    @Transactional
     public int deleteInvoice(int invoiceId) throws DataIntegrityViolationException {
         List<InvoiceItem> invoiceItemList = invoiceItemDao.getInvoiceItemsByInvoiceId(invoiceId);
         for(InvoiceItem invoiceItem:invoiceItemList){
             invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId());
         }
       return invoiceDao.deleteInvoice(invoiceId);
     }

    private CustomerViewModel buildCustomerViewModel(Customer customer) {
        List<Invoice> invoiceList = invoiceDao.getInvoiceByCustomerId(customer.getCustomerId());
        List<InvoiceItem> invoiceItemList;
        List<Item> items = new ArrayList<>();
        // <Integer invoiceId, InvoiceItem InvoiceItemList>
        Map<Integer, List<InvoiceItem>> invoiceItemMap = new HashMap<>();
        for (Invoice invoice : invoiceList) {
            invoiceItemList = invoiceItemDao.getInvoiceItemsByInvoiceId(invoice.getInvoiceId());
            for(InvoiceItem invoiceItem: invoiceItemList) {
                items.add(itemDao.getItem(invoiceItem.getItemId()));
            }
            invoiceItemMap.put(invoice.getInvoiceId(), invoiceItemList);
        }
        CustomerViewModel customerViewModel = new CustomerViewModel();
        customerViewModel.setCustomer(customer);
        customerViewModel.setItemList(items);
        customerViewModel.setInvoiceList(invoiceList);
        customerViewModel.setInvoiceItemMap(invoiceItemMap);
        return customerViewModel;
    }
}