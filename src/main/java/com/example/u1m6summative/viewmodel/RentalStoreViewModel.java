package com.example.u1m6summative.viewmodel;

import com.example.u1m6summative.model.*;

import java.util.List;
import java.util.Objects;

public class RentalStoreViewModel {
    private int id;
    private String storeName;
    private Address address;
    private String phone;
    private List<Customer> customers;
    private List<Item> items;
    private List<Invoice> invoices;
    private List<InvoiceItem> invoiceItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalStoreViewModel that = (RentalStoreViewModel) o;
        return id == that.id &&
                storeName.equals(that.storeName) &&
                address.equals(that.address) &&
                phone.equals(that.phone) &&
                customers.equals(that.customers) &&
                items.equals(that.items) &&
                invoices.equals(that.invoices) &&
                invoiceItems.equals(that.invoiceItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, storeName, address, phone, customers, items, invoices, invoiceItems);
    }
}
