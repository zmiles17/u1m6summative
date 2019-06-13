package com.example.u1m6summative.dao;

import com.example.u1m6summative.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoTests {
    @Autowired
    CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        List<Customer> customers = customerDao.getAllCustomer();
        customers.forEach(customer -> customerDao.deleteCustomer(customer.getCustomerId()));
    }

    @Test
    public void getAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setCompany("Cognizant");
        customer1.setEmail("someemail@gmail.com");
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setPhone("313-231-5342");
        customerDao.addCustomer(customer1);

        Customer customer2 = new Customer();
        customer2.setCompany("Cognizant");
        customer2.setEmail("someemail@gmail.com");
        customer2.setFirstName("John");
        customer2.setLastName("Doe");
        customer2.setPhone("313-231-5342");
        customerDao.addCustomer(customer2);

        List<Customer> customerList = customerDao.getAllCustomer();
        assertEquals(customerList.size(), 2);
    }

    @Test
    public void addCustomer() {
        Customer customerToAdd = new Customer();
        customerToAdd.setCompany("Cognizant");
        customerToAdd.setEmail("someemail@gmail.com");
        customerToAdd.setFirstName("John");
        customerToAdd.setLastName("Doe");
        customerToAdd.setPhone("313-231-5342");
        customerToAdd = customerDao.addCustomer(customerToAdd);
        Customer customerFromDatabase = customerDao.getCustomer(customerToAdd.getCustomerId());

        assertEquals(customerToAdd, customerFromDatabase);
    }

    @Test
    public void deleteCustomer() {
        Customer customerToDelete = new Customer();
        customerToDelete.setCompany("Cognizant");
        customerToDelete.setEmail("someemail@gmail.com");
        customerToDelete.setFirstName("John");
        customerToDelete.setLastName("Doe");
        customerToDelete.setPhone("313-231-5342");
        customerToDelete = customerDao.addCustomer(customerToDelete);
        customerDao.deleteCustomer(customerToDelete.getCustomerId());
        customerToDelete = customerDao.getCustomer(customerToDelete.getCustomerId());

        assertNull(customerToDelete);
    }

    @Test
    public void updateCustomer() {
        Customer customerToUpdate = new Customer();
        customerToUpdate.setCompany("Cognizant");
        customerToUpdate.setEmail("someemail@gmail.com");
        customerToUpdate.setFirstName("John");
        customerToUpdate.setLastName("Doe");
        customerToUpdate.setPhone("313-231-5342");
        customerToUpdate = customerDao.addCustomer(customerToUpdate);

        customerToUpdate.setCompany("Trilogy");
        customerToUpdate = customerDao.updateCustomer(customerToUpdate);

        Customer customerFromDatabase = customerDao.getCustomer(customerToUpdate.getCustomerId());

        assertEquals(customerFromDatabase, customerToUpdate);
    }
}
