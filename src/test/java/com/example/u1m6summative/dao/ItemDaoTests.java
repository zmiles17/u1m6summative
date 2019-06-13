package com.example.u1m6summative.dao;

import com.example.u1m6summative.model.InvoiceItem;
import com.example.u1m6summative.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemDaoTests {
    @Autowired
    ItemDao dao;

    @Before
public void setUp() throws Exception{

    }

    @Test
    public Item addItem(Item item) {

        return null;
    }

    @Test
    public List<Item> getAllItem() {
        return null;
    }

    @Test
    public Item getItem(int id) {
        return null;
    }

    @Test
    public Item updateItem(Item item) {
        return null;
    }

    @Test
    public void deleteItem(int id) {

    }
}
