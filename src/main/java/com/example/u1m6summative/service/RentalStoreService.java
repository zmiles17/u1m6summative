package com.example.u1m6summative.service;

import com.example.u1m6summative.dao.CustomerDao;
import com.example.u1m6summative.dao.InvoiceDao;
import com.example.u1m6summative.dao.InvoiceItemDao;
import com.example.u1m6summative.dao.ItemDao;
import com.example.u1m6summative.model.Address;
import com.example.u1m6summative.model.Customer;
import com.example.u1m6summative.model.InvoiceItem;
import com.example.u1m6summative.viewmodel.RentalStoreViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RentalStoreService {

    private CustomerDao customerDao;
    private InvoiceDao invoiceDao;
    private ItemDao itemDao;
    private InvoiceItemDao invoiceItemDao;

    @Autowired
    public RentalStoreService(CustomerDao customerDao, InvoiceDao invoiceDao, ItemDao itemDao, InvoiceItemDao invoiceItemDao) {
        this.customerDao = customerDao;
        this.invoiceDao = invoiceDao;
        this.itemDao = itemDao;
        this.invoiceItemDao = invoiceItemDao;
    }

    @Transactional
    public RentalStoreViewModel saveStore(RentalStoreViewModel viewModel) {
        return null;

    }

//    public Customer findCustomer(int id) {
//        return customerDao.getCustomer(id);
//    }
//
//    public Customer saveCustomer(Customer customer) {
//        return customerDao.addCustomer(customer);
//    }
//
//    public List<Customer> findAllCustomers() {
//        return customerDao.getAllCustomer();
//    }
//
//    public Customer updateCustomer(Customer customer) {
//        return customerDao.updateCustomer(customer);
//    }
//
//    public void deleteCustomer(int id) {
//        customerDao.deleteCustomer(id);
//    }
//
//    private RentalStoreViewModel buildStoreViewModel(Address address) {
//        return null;
//    }
//
//    //Invoice Item
//
//    public InvoiceItem getInvoiceItem(int id) {
//        return invoiceItemDao.getInvoiceItem(id);
//    }
//
//    public Customer saveCustomer(Customer customer) {
//        return customerDao.addCustomer(customer);
//    }
//
//    public List<Customer> findAllCustomers() {
//        return customerDao.getAllCustomer();
//    }
//
//    public Customer updateCustomer(Customer customer) {
//        return customerDao.updateCustomer(customer);
//    }
//
//    public void deleteCustomer(int id) {
//        customerDao.deleteCustomer(id);
//    }

}
