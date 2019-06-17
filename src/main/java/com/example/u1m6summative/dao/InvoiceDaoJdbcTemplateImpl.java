package com.example.u1m6summative.dao;

import com.example.u1m6summative.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_INVOICE_SQL =
            "insert into invoice (customer_Id, order_Date, pickup_Date, return_Date, late_Fee) values (?, ?, ?, ?, ?)";

    private static final String SELECT_INVOICE_SQL =
            "select * from invoice where invoice_Id = ?";

    private static final String SELECT_ALL_INVOICES_SQL =
            "select * from invoice";

    private static final String UPDATE_INVOICE_SQL =
            "update invoice set customer_Id = ?, order_Date = ?, pickup_Date = ?, return_Date = ?, late_Fee = ? where invoice_Id = ?";

    private static final String DELETE_INVOICE_SQL =
            "delete from invoice where invoice_Id = ?";

    private static final String SELECT_INVOICES_BY_CUSTOMER =
            "select * from invoice" +
                    "  inner join customer on invoice.customer_id = customer.customer_id" +
                    "  where LOWER(customer.first_name) like LOWER(?) or LOWER(customer.last_name) like LOWER(?)";
    private static final String SELECT_INVOICES_BY_CUSTOMERID =
            "select * from invoice where customer_id = ?";


    @Autowired
    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Invoice addInvoice(Invoice invoice) {
        try{
            jdbcTemplate.update(INSERT_INVOICE_SQL, invoice.getCustomerId(), invoice.getOrderDate(), invoice.getPickUpDate(), invoice.getReturndate(), invoice.getLateFee());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        invoice.setInvoiceId(id);

        return invoice;
        } catch (NullPointerException e) {
            return null;
        }

    }

    @Override
    public Invoice getInvoice(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_SQL, this::mapRowToInvoice, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Invoice> getAllInvoice() {

        return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL, this::mapRowToInvoice);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice){

        jdbcTemplate.update(UPDATE_INVOICE_SQL, invoice.getInvoiceId(), invoice.getCustomerId(), invoice.getOrderDate(),
                invoice.getPickUpDate(), invoice.getReturndate(), invoice.getLateFee());

        return jdbcTemplate.queryForObject(SELECT_INVOICE_SQL, this::mapRowToInvoice, invoice.getInvoiceId());
    }

    @Override
    public int deleteInvoice(int id) {

     return jdbcTemplate.update(DELETE_INVOICE_SQL, id);
    }

    @Override
    public List<Invoice> getInvoicesByCustomer(String firstName, String lastName) {
        try {
            return jdbcTemplate.query(SELECT_INVOICES_BY_CUSTOMER, this::mapRowToInvoice, firstName + "%", lastName + "%");
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Invoice> getInvoiceByCustomerId(int id) {
        try {
            return jdbcTemplate.query(SELECT_INVOICES_BY_CUSTOMERID, this::mapRowToInvoice, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(rs.getInt("invoice_id"));
        invoice.setCustomerId(rs.getInt("customer_id"));
        java.sql.Date mySqlDate = rs.getDate("order_date");
        LocalDate myLocalDate = mySqlDate.toLocalDate();
        invoice.setOrderDate(myLocalDate);
        java.sql.Date mySqlDate2 = rs.getDate("pickup_date");
        LocalDate myLocalDate2 = mySqlDate2.toLocalDate();
        invoice.setPickUpDate(myLocalDate2);
        java.sql.Date mySqlDate3 = rs.getDate("return_date");
        LocalDate myLocalDate3 = mySqlDate3.toLocalDate();
        invoice.setReturndate(myLocalDate3);
        invoice.setLateFee(rs.getDouble("late_fee"));

        return invoice;
    }

}
