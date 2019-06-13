package com.example.u1m6summative.dao;

import com.example.u1m6summative.model.Customer;
import com.example.u1m6summative.model.Invoice;
import com.example.u1m6summative.model.InvoiceItem;
import com.example.u1m6summative.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemDaoTests {

    @Autowired
    InvoiceItemDao invoiceItemDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    ItemDao itemDao;
    @Autowired
    CustomerDao customerDao;


    @Before
    public void setUp() throws Exception {
        List<InvoiceItem> invoiceItemsList = invoiceItemDao.getAllInvoiceItem();
        for (InvoiceItem invoiceItem : invoiceItemsList) {
            invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId());
        }
        List<Invoice> invoiceList = invoiceDao.getAllInvoice();
        for (Invoice invoice : invoiceList) {
            invoiceDao.deleteInvoice(invoice.getInvoiceId());
        }
        List<Item> ItemList = itemDao.getAllItem();
        for (Item Item : ItemList) {
            itemDao.deleteItem(Item.getItemId());
        }
    }


    @Test
    public void getAllInvoiceItem() {

        Customer customer = new Customer();
        customer.setFirstName("Basvaraj");
        customer.setLastName("Ramya");
        customer.setCompany("Cognizant");
        customer.setEmail("ramya@email.com");
        customer.setPhone("4513763247");
        Customer actualCustomer = customerDao.addCustomer(customer);

        Item item = new Item();
        item.setDailyRate(new BigDecimal(999.78));
        item.setName("Laptop");
        item.setDescription("Electronics item");
        Item actualItem = itemDao.addItem(item);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(actualCustomer.getCustomerId());
        invoice.setLateFee(new BigDecimal(65));
        invoice.setOrderDate(new Date());
        invoice.setPickUpDate(new Date());
        invoice.setReturndate(new Date());
        Invoice actualInvoice = invoiceDao.addInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setItemId(actualItem.getItemId());
        invoiceItem.setInvoiceId(actualInvoice.getInvoiceId());
        invoiceItem.setDiscount(new BigDecimal(50));
        invoiceItem.setQuantity(6);
        invoiceItem.setUnitRate(new BigDecimal(30));
        InvoiceItem actualInvoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItem();
        Assert.assertEquals(invoiceItemList.size(), 1);

    }


    @Test
    public void updateInvoiceItem() {
        Customer customer = new Customer();
        customer.setFirstName("Basvaraj");
        customer.setLastName("Ramya");
        customer.setCompany("Cognizant");
        customer.setEmail("ramya@email.com");
        customer.setPhone("4513763247");
        Customer actualCustomer = customerDao.addCustomer(customer);

        Item item = new Item();
        item.setDailyRate(new BigDecimal(999.78));
        item.setName("Laptop");
        item.setDescription("Electronics item");
        Item actualItem = itemDao.addItem(item);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(actualCustomer.getCustomerId());
        invoice.setLateFee(new BigDecimal(65));
        invoice.setOrderDate(new Date());
        invoice.setPickUpDate(new Date());
        invoice.setReturndate(new Date());
        Invoice actualInvoice = invoiceDao.addInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setItemId(actualItem.getItemId());
        invoiceItem.setInvoiceId(actualInvoice.getInvoiceId());
        invoiceItem.setDiscount(new BigDecimal(50));
        invoiceItem.setQuantity(6);
        invoiceItem.setUnitRate(new BigDecimal(30));

        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        invoiceItem.setDiscount(new BigDecimal(70));
        invoiceItem.setQuantity(1);
        invoiceItem.setUnitRate(new BigDecimal(10));
        InvoiceItem actualInvoiceItem = invoiceItemDao.updateInvoiceItem(invoiceItem);

        InvoiceItem expectedInvoiceItem = invoiceItemDao.getInvoiceItem(actualInvoiceItem.getInvoiceItemId());
        Assert.assertEquals(expectedInvoiceItem, actualInvoiceItem);

    }


    @Test
    public void addGetDeleteInvoiceItem() {
        // need to create customer, items, invoice First
        Customer customer = new Customer();
        customer.setFirstName("Basvaraj");
        customer.setLastName("Ramya");
        customer.setCompany("Cognizant");
        customer.setEmail("ramya@email.com");
        customer.setPhone("4513763247");
        Customer actualCustomer = customerDao.addCustomer(customer);

        Item item = new Item();
        item.setDailyRate(new BigDecimal(999.78));
        item.setName("Laptop");
        item.setDescription("Electronics item");
        Item actualItem = itemDao.addItem(item);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(actualCustomer.getCustomerId());
        invoice.setLateFee(new BigDecimal(65));
        invoice.setOrderDate(new Date());
        invoice.setPickUpDate(new Date());
        invoice.setReturndate(new Date());
        Invoice actualInvoice = invoiceDao.addInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setItemId(actualItem.getItemId());
        invoiceItem.setInvoiceId(actualInvoice.getInvoiceId());
        invoiceItem.setDiscount(new BigDecimal(50));
        invoiceItem.setQuantity(6);
        invoiceItem.setUnitRate(new BigDecimal(30));
        InvoiceItem actualInvoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        InvoiceItem expectedInvoiceItem = invoiceItemDao.getInvoiceItem(actualInvoiceItem.getInvoiceItemId());
        Assert.assertEquals(actualInvoiceItem, expectedInvoiceItem);

        invoiceItemDao.deleteInvoiceItem(actualInvoiceItem.getInvoiceItemId());

        expectedInvoiceItem = invoiceItemDao.getInvoiceItem(actualInvoiceItem.getInvoiceItemId());
        Assert.assertNull(expectedInvoiceItem);

    }

}
