package com.example.u1m6summative.dao;

import com.example.u1m6summative.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao {
    @Override
    public Customer addCustomer(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return null;
    }

    @Override
    public Customer getCustomer(int id) {
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public void deleteCustomer(int id) {

    }
}
