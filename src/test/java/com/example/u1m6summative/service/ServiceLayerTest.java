package com.example.u1m6summative.service;


import com.example.u1m6summative.dao.CustomerDao;
import com.example.u1m6summative.dao.InvoiceDao;
import com.example.u1m6summative.dao.InvoiceItemDao;
import com.example.u1m6summative.dao.ItemDao;
import com.example.u1m6summative.model.Address;
import com.example.u1m6summative.model.Customer;
import com.example.u1m6summative.viewmodel.PurchaseViewModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    PurchaseServiceLayer service;
    CustomerDao customerDao;
    InvoiceDao invoiceDao;
    ItemDao itemDao;
    InvoiceItemDao invoiceItemDao;

    @Before
    public void setUp() throws Exception {
        setUpCustomerMock();
        service = new PurchaseServiceLayer(customerDao, invoiceDao, itemDao, invoiceItemDao);
    }

    @Test
    public void addStore() {
        PurchaseViewModel rsvm = new PurchaseViewModel();
        rsvm.setAddress(new Address("street", "city", "state", "zip"));
        rsvm.setStoreName("store name");
        rsvm.setPhone("phone number");

        Customer customer = new Customer();
        customer.setFirstName("Zack");
        customer.setLastName("Miles");
        customer.setEmail("email@gmail.com");
        customer.setCompany("Google");
        customer.setPhone("543-543-2313");
       // customer = service.saveCustomer(customer);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        rsvm.setCustomers(customers);

    }

    private void setUpCustomerMock() {
        customerDao = mock(CustomerDao.class);
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("Zack");
        customer.setLastName("Miles");
        customer.setEmail("email@gmail.com");
        customer.setCompany("Google");
        customer.setPhone("543-543-2313");

        Customer customer1 = new Customer();
        customer1.setCustomerId(1);
        customer1.setFirstName("Jack");
        customer1.setLastName("Smiles");
        customer1.setEmail("email@yahoo.com");
        customer1.setCompany("Yahoo");
        customer1.setPhone("423-763-8976");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        customerList.add(customer1);

        doReturn(customer).when(customerDao).addCustomer(customer);
        doReturn(customerList).when(customerDao).getAllCustomer();
    }
}
