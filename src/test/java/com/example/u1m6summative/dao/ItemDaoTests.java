package com.example.u1m6summative.dao;

import com.example.u1m6summative.model.InvoiceItem;
import com.example.u1m6summative.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemDaoTests {
    @Autowired
    ItemDao itemDao;

    @Before
    public void setUp() throws Exception{
        List<Item> itemList = itemDao.getAllItem();
        for(Item item : itemList) {
            itemDao.deleteItem(item.getItemId());
        }
    }

    @Test
    public void addGetDeleteItem() {
        Item item = new Item();
        item.setName("unicorn");
        item.setDescription("A magical unicorn.");
        item.setDailyRate(33.33);
        itemDao.addItem(item);

        Item item2 = itemDao.getItem(item.getItemId());

        assertEquals(item2, item);
        itemDao.deleteItem(item.getItemId());
        item2 = itemDao.getItem(item.getItemId());
        assertNull(item2);
    }

    @Test
    public void getAllItem() {
        Item item = new Item();
        item.setName("unicorn");
        item.setDescription("A magical unicorn.");
        item.setDailyRate(33.33);
        itemDao.addItem(item);

        Item item2 = new Item();
        item2.setName("horses");
        item2.setDescription("Like a unicorn, but not magical.");
        item2.setDailyRate(33.33);
        itemDao.addItem(item2);

        List<Item> itemList = itemDao.getAllItem();

        assertEquals(itemList.size(), 2);

    }

    @Test
    public void updateItem() {
        Item myItem = new Item();
        myItem.setName("chair");
        myItem.setDescription("something to sit on");
        myItem.setDailyRate(12.33);
        itemDao.addItem(myItem);

        myItem.setName("sofa chair");
        myItem.setDescription("a soft sofa chair to sit on");
        myItem.setDailyRate(11.55);
        itemDao.updateItem(myItem);

        Item myItem2 = itemDao.getItem(myItem.getItemId());

        assertEquals(myItem2, myItem);
    }


}

