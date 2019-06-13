package com.example.u1m6summative.dao;

import com.example.u1m6summative.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_CUSTOMER_SQL =
            "INSERT INTO customer (first_name, last_name, email, company, phone) VALUES (?, ?, ?, ?, ?)";

    private static final String SELECT_CUSTOMER_SQL =
            "SELECT * FROM customer WHERE customer_id = ?";

    private static final String SELECT_ALL_CUSTOMERS_SQL =
            "SELECT * FROM customer";

    private static final String DELETE_CUSTOMER_SQL =
            "DELETE FROM customer WHERE customer_id = ?";

    private static final String UPDATE_CUSTOMER_SQL =
            "UPDATE customer set first_name = ?, last_name = ?, email = ?, company = ?, phone = ? where customer_id = ?";

    @Autowired
    public CustomerDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        jdbcTemplate.update(INSERT_CUSTOMER_SQL,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getCompany(),
                customer.getPhone());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        customer.setCustomerId(id);
        return customer;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return jdbcTemplate.query(SELECT_ALL_CUSTOMERS_SQL, this::mapRowToCustomer);
    }

    @Override
    public Customer getCustomer(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_CUSTOMER_SQL, this::mapRowToCustomer, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        jdbcTemplate.update(UPDATE_CUSTOMER_SQL, customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getCompany(),customer.getPhone(), customer.getCustomerId());
        return jdbcTemplate.queryForObject(SELECT_CUSTOMER_SQL, this::mapRowToCustomer, customer.getCustomerId());
    }

    @Override
    public void deleteCustomer(int id) {
        jdbcTemplate.update(DELETE_CUSTOMER_SQL, id);
    }

    private Customer mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setEmail(rs.getString("email"));
        customer.setCompany(rs.getString("company"));
        customer.setPhone(rs.getString("phone"));

        return customer;
    }
}
