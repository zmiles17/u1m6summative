package com.example.u1m6summative.dao;

import com.example.u1m6summative.model.Customer;


import java.util.List;

public interface CustomerDao {
    Customer addCustomer(Customer customer);

    List<Customer> getAllCustomer();

    Customer getCustomer(int id);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(int id);

}
