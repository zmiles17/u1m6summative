package com.example.u1m6summative.viewmodel;

import com.example.u1m6summative.model.Customer;
import com.example.u1m6summative.model.Invoice;
import com.example.u1m6summative.model.InvoiceItem;

import java.util.List;
import java.util.Map;

public class CustomerViewModel {
    private List<Invoice> invoiceList;
    private Customer customer;
    private Map<Integer, List<InvoiceItem>> invoiceItemMap;

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Integer, List<InvoiceItem>> getInvoiceItemMap() {
        return invoiceItemMap;
    }

    public void setInvoiceItemMap(Map<Integer, List<InvoiceItem>> invoiceItemMap) {
        this.invoiceItemMap = invoiceItemMap;
    }
}
