package com.example.u1m6summative.dao;

import com.example.u1m6summative.model.Invoice;

import java.util.List;

public interface InvoiceDao {
    Invoice addInvoice(Invoice invoice);

    List<Invoice> getAllInvoice();

    Invoice getInvoice(int id);

    Invoice updateInvoice(Invoice invoice);

    int deleteInvoice(int id);

    List<Invoice> getInvoicesByCustomer(String firstName, String lastName);
    List<Invoice> getInvoiceByCustomerId(int id);
}
