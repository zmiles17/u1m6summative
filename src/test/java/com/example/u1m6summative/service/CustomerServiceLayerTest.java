package com.example.u1m6summative.service;

import com.example.u1m6summative.dao.CustomerDao;
import com.example.u1m6summative.dao.InvoiceDao;
import com.example.u1m6summative.dao.InvoiceItemDao;
import com.example.u1m6summative.dao.ItemDao;
import com.example.u1m6summative.model.Customer;
import com.example.u1m6summative.model.Invoice;
import com.example.u1m6summative.model.InvoiceItem;
import com.example.u1m6summative.model.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class CustomerServiceLayerTest {
    CustomerServiceLayer customerServiceLayer;
    CustomerDao customerDao;
    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;
    ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        setUpInvoiceDaoMock();
        setUpInvoiceItemDaoMock();
        customerServiceLayer = new CustomerServiceLayer(customerDao, invoiceDao, itemDao, invoiceItemDao);
    }

    @Test
    public void addInvoice() {
        Invoice actualInvoice = new Invoice();
        actualInvoice.setCustomerId(1);
        actualInvoice.setLateFee(4.00);
        actualInvoice.setOrderDate(LocalDate.of(2019, 6, 13));
        actualInvoice.setPickUpDate(LocalDate.of(2019, 6, 19));
        actualInvoice.setReturndate(LocalDate.of(2019, 6, 25));
        Invoice expectedInvoice = customerServiceLayer.addInvoice(actualInvoice);
        actualInvoice.setInvoiceId(expectedInvoice.getInvoiceId());
        Assert.assertEquals(expectedInvoice,actualInvoice);
    }
    @Test
    public void deleteInvoice() {
        Invoice actualInvoice = new Invoice();
        actualInvoice.setCustomerId(1);
        actualInvoice.setLateFee(4.00);
        actualInvoice.setOrderDate(LocalDate.of(2019, 6, 13));
        actualInvoice.setPickUpDate(LocalDate.of(2019, 6, 19));
        actualInvoice.setReturndate(LocalDate.of(2019, 6, 25));
        actualInvoice = customerServiceLayer.addInvoice(actualInvoice);
     int deleted =   customerServiceLayer.deleteInvoice(actualInvoice.getInvoiceId());
        Assert.assertEquals(1,deleted);
    }

    @Test
    public void addInvoiceItem() {
        InvoiceItem actualInvoiceItem = new InvoiceItem();
        actualInvoiceItem.setItemId(1);
        actualInvoiceItem.setInvoiceId(1);
        actualInvoiceItem.setDiscount(50.00);
        actualInvoiceItem.setQuantity(6);
        actualInvoiceItem.setUnitRate(30.00);
        InvoiceItem expectedInvoiceItem = customerServiceLayer.addInvoiceItem(actualInvoiceItem);
        actualInvoiceItem.setInvoiceItemId(expectedInvoiceItem.getInvoiceItemId());
        Assert.assertEquals(expectedInvoiceItem,actualInvoiceItem);
    }

    public void setUpInvoiceDaoMock() {

        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("Ramya");
        customer.setLastName("B");
        customer.setEmail("ramya@gmail.com");
        customer.setCompany("Google");
        customer.setPhone("222-222-2222");


        Item item = new Item();
        item.setItemId(1);
        item.setDailyRate(33.33);
        item.setName("Laptop");
        item.setDescription("Electronics item");

        invoiceDao = mock(InvoiceDao.class);
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(1);
        invoice.setLateFee(4.00);
        invoice.setOrderDate(LocalDate.of(2019, 6, 13));
        invoice.setPickUpDate(LocalDate.of(2019, 6, 19));
        invoice.setReturndate(LocalDate.of(2019, 6, 25));

        Invoice invoice1 = new Invoice();
        invoice1.setCustomerId(1);
        invoice1.setLateFee(4.00);
        invoice1.setOrderDate(LocalDate.of(2019, 6, 13));
        invoice1.setPickUpDate(LocalDate.of(2019, 6, 19));
        invoice1.setReturndate(LocalDate.of(2019, 6, 25));

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setItemId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setDiscount(50.00);
        invoiceItem.setQuantity(6);
        invoiceItem.setUnitRate(30.00);

        doReturn(invoice).when(invoiceDao).addInvoice(invoice1);
       doReturn(1).when(invoiceDao).deleteInvoice(1);

    }

    public void setUpInvoiceItemDaoMock(){
        invoiceItemDao = mock(InvoiceItemDao.class);
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setItemId(1);
        invoiceItem.setDiscount(50.00);
        invoiceItem.setQuantity(6);
        invoiceItem.setUnitRate(30.00);

        InvoiceItem invoiceItem1 = new InvoiceItem();
        invoiceItem1.setItemId(1);
        invoiceItem1.setInvoiceId(1);
        invoiceItem1.setDiscount(50.00);
        invoiceItem1.setQuantity(6);
        invoiceItem1.setUnitRate(30.00);

        doReturn(invoiceItem).when(invoiceItemDao).addInvoiceItem(invoiceItem1);

    }


}
