package com.example.u1m6summative.service;

import com.example.u1m6summative.dao.CustomerDao;
import com.example.u1m6summative.dao.InvoiceDao;
import com.example.u1m6summative.dao.InvoiceItemDao;
import com.example.u1m6summative.dao.ItemDao;
import com.example.u1m6summative.model.Customer;
import com.example.u1m6summative.model.Invoice;
import com.example.u1m6summative.model.InvoiceItem;
import com.example.u1m6summative.viewmodel.CustomerViewModel;
import com.example.u1m6summative.viewmodel.PurchaseViewModel;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public CustomerViewModel saveCustomer(CustomerViewModel customerViewModel) {
        Customer customer = new Customer();
        customer.setFirstName(customerViewModel.getCustomer().getFirstName());
        customer.setLastName(customerViewModel.getCustomer().getLastName());
        customer.setEmail(customerViewModel.getCustomer().getEmail());
        customer.setCompany(customerViewModel.getCustomer().getCompany());
        customer.setPhone(customerViewModel.getCustomer().getPhone());
        return buildCustomerViewModel(customerDao.addCustomer(customer));
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
        List<Invoice> invoiceList = invoiceDao.getInvoiceByCustomer(id);
        invoiceList.stream()
                .forEach(item -> invoiceItemDao.deleteInvoiceItem(item.getInvoiceId()));
        invoiceList.stream()
                .forEach(item -> invoiceDao.deleteInvoice(item.getInvoiceId()));
        customerDao.deleteCustomer(id);
    }

    private CustomerViewModel buildCustomerViewModel(Customer customer) {
        List<Invoice> invoiceList = invoiceDao.getInvoiceByCustomer(customer.getCustomerId());
        List<InvoiceItem> invoiceItemList;
        // <Integer invoiceId, InvoiceItem InvoiceItemList>
        Map<Integer,List<InvoiceItem>> invoiceItemMap =  new HashMap<>();
        for(Invoice invoice:invoiceList){
            invoiceItemList=invoiceItemDao.getInvoiceItemsByInvoiceId(invoice.getInvoiceId());
            invoiceItemMap.put(invoice.getInvoiceId(),invoiceItemList);
        }

        CustomerViewModel customerViewModel = new CustomerViewModel();
        customerViewModel.setCustomer(customer);
        customerViewModel.setInvoiceList(invoiceList);
        customerViewModel.setInvoiceItemMap(invoiceItemMap);
        return customerViewModel;
    }
}