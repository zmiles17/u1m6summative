package com.example.u1m6summative.service;


import com.example.u1m6summative.dao.CustomerDao;
import com.example.u1m6summative.dao.InvoiceDao;
import com.example.u1m6summative.dao.InvoiceItemDao;
import com.example.u1m6summative.dao.ItemDao;
import org.junit.Before;

public class ServiceLayerTest {

    RentalStoreService service;
    CustomerDao customerDao;
    InvoiceDao invoiceDao;
    ItemDao itemDao;
    InvoiceItemDao invoiceItemDao;

    @Before
    public void setUp() throws Exception {
        service = new RentalStoreService(customerDao, invoiceDao, itemDao, invoiceItemDao);

    }
}
