package com.example.u1m6summative.service;

import com.example.u1m6summative.dao.CustomerDao;
import com.example.u1m6summative.dao.InvoiceDao;
import com.example.u1m6summative.dao.InvoiceItemDao;
import com.example.u1m6summative.dao.ItemDao;
import com.example.u1m6summative.model.Address;
import com.example.u1m6summative.model.Customer;
import com.example.u1m6summative.model.InvoiceItem;
import com.example.u1m6summative.viewmodel.PurchaseViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ServiceLayer {

    private CustomerDao customerDao;
    private InvoiceDao invoiceDao;
    private ItemDao itemDao;
    private InvoiceItemDao invoiceItemDao;

    @Autowired
    public ServiceLayer(CustomerDao customerDao, InvoiceDao invoiceDao, ItemDao itemDao, InvoiceItemDao invoiceItemDao) {
        this.customerDao = customerDao;
        this.invoiceDao = invoiceDao;
        this.itemDao = itemDao;
        this.invoiceItemDao = invoiceItemDao;
    }

    @Transactional
    public PurchaseViewModel saveInvoiceItem(PurchaseViewModel viewModel) {

        //Persist InvoiceItem
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(viewModel.getInvoiceId());


        // Add invoice id to invoiceitems on the order and persist to database
        List<InvoiceItem> invoiceItems = viewModel.getInvoiceItemList();

        invoiceItem.stream()
                .forEach(invoiceItem ->
                {
                    invoiceItem.setInvoiceId(viewModel.getInvoiceId());
                    invoiceDao.addInvoice(invoiceItem);
                });

                invoiceItems = invoiceDao.get

        return viewModel;

        /*
    private int invoiceItemId;
    private int invoiceId;
    private int itemId;
    private int quantity;
    private BigDecimal unitRate;
    private BigDecimal discount;
         */

    }

    public Customer findCustomer(int id) {
        return customerDao.getCustomer(id);
    }

    public Customer saveCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    public List<Customer> findAllCustomers() {
        return customerDao.getAllCustomer();
    }

    public Customer updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    public void deleteCustomer(int id) {
        customerDao.deleteCustomer(id);
    }

    private PurchaseViewModel buildStoreViewModel(Address address) {
        Customer customer = customerDao.getCustomer(customer.getCustomerId())
        return null;
    }

}
