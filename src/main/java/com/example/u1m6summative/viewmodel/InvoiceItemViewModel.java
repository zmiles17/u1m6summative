package com.example.u1m6summative.viewmodel;

import com.example.u1m6summative.model.Invoice;
import com.example.u1m6summative.model.Item;

import java.util.List;

public class InvoiceItemViewModel {
    private Invoice invoice;
    private Item item;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
