package com.example.u1m6summative.dao;

import com.example.u1m6summative.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {
    InvoiceItem addInvoiceItem(InvoiceItem invoiceItem);

    List<InvoiceItem> getAllInvoiceItem();

    InvoiceItem getInvoiceItem(int id);

    InvoiceItem updateInvoiceItem(InvoiceItem invoiceItem);

    void deleteInvoiceItem(int id);
}
