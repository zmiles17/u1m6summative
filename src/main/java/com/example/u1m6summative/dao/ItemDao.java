package com.example.u1m6summative.dao;

import com.example.u1m6summative.model.InvoiceItem;
import com.example.u1m6summative.model.Item;

import java.util.List;

public interface ItemDao {
    Item addItem(InvoiceItem invoiceItem);

    List<Item> getAllItem();

    Item getItem(int id);

    Item updateItem(InvoiceItem invoiceItem);

    void deleteItem(int id);
}
