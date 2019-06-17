package com.example.u1m6summative.service;

import com.example.u1m6summative.dao.*;
import com.example.u1m6summative.model.Customer;
import com.example.u1m6summative.model.Item;
import com.example.u1m6summative.viewmodel.CustomerViewModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ItemServiceLayerTest {
    CustomerServiceLayer service;
    CustomerDao customerDao;
    InvoiceItemDao invoiceItemDao;
    ItemDao itemDao;
    InvoiceDao invoiceDao;

    @Before
    public void setUp() throws Exception {
        setUpItemDaoMock();

        service = new CustomerServiceLayer(customerDao, invoiceDao, itemDao, invoiceItemDao);

    }

    @Test
    public void saveItem() {

        Item item = new Item();
        item.setName("Table");
        item.setDescription("A great table.");
        item.setDailyRate(33.20);

//        item = service.addItem(item);
//        List<Item> itemList = new ArrayList<>();
//        itemList.add(item);

        Item theThingIGotBack = service.addItem(item);
        item.setItemId(theThingIGotBack.getItemId());

        assertNotSame(0, theThingIGotBack.getItemId());
        assertEquals(item, theThingIGotBack);
    }

    @Test
    public void findItem(){
        Item itemWeExpectBack = new Item();
        itemWeExpectBack.setItemId(10);
        itemWeExpectBack.setName("Table");
        itemWeExpectBack.setDescription("A great table.");
        itemWeExpectBack.setDailyRate(33.20);

        Item itemWeGotBack = service.findItem(10);

        assertEquals(itemWeGotBack, itemWeExpectBack);
    }

    @Test
    public void findAllItems(){
        List<Item> theThingThatIGot = service.findAllItem();

        assertEquals(theThingThatIGot.size(), 1);
        assertEquals(theThingThatIGot.get(0).getItemId(), 10);
    }

    @Test
    public void removeItem(){
        int itemWeGotBack = service.removeItem(10);

        assertEquals(itemWeGotBack, 1);
    }

    private void setUpItemDaoMock() {
        itemDao = mock(ItemDaoJdbcTemplateImpl.class);

        Item item = new Item();
        item.setItemId(10);
        item.setName("Table");
        item.setDescription("A great table.");
        item.setDailyRate(33.20);

        Item item2 = new Item();
        item2.setName("Table");
        item2.setDescription("A great table.");
        item2.setDailyRate(33.20);

        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        doReturn(item).when(itemDao).addItem(item2);
        doReturn(item).when(itemDao).getItem(10);
        doReturn(itemList).when(itemDao).getAllItem();
        doReturn(1).when(itemDao).deleteItem(10);

    }
}
