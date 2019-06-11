package com.example.u1m6summative.dao;

import com.example.u1m6summative.model.InvoiceItem;
import com.example.u1m6summative.model.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ItemDaoJdbcTemplateImpl implements ItemDao {
    @Override
    public Item addItem(InvoiceItem invoiceItem) {
        return null;
    }

    @Override
    public List<Item> getAllItem() {
        return null;
    }

    @Override
    public Item getItem(int id) {
        return null;
    }

    @Override
    public Item updateItem(InvoiceItem invoiceItem) {
        return null;
    }

    @Override
    public void deleteItem(int id) {

    }
}
