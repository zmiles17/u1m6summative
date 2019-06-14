package com.example.u1m6summative.service;

import com.example.u1m6summative.dao.CustomerDao;
import com.example.u1m6summative.dao.InvoiceDao;
import com.example.u1m6summative.dao.InvoiceItemDao;
import com.example.u1m6summative.dao.ItemDao;
import com.example.u1m6summative.model.Address;
import com.example.u1m6summative.model.Customer;
import com.example.u1m6summative.model.Invoice;
import com.example.u1m6summative.model.InvoiceItem;
import com.example.u1m6summative.viewmodel.PurchaseViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class PurchaseServiceLayer {

    private CustomerDao customerDao;
    private InvoiceDao invoiceDao;
    private ItemDao itemDao;
    private InvoiceItemDao invoiceItemDao;

    @Autowired
    public PurchaseServiceLayer(CustomerDao customerDao, InvoiceDao invoiceDao, ItemDao itemDao, InvoiceItemDao invoiceItemDao) {
        this.customerDao = customerDao;
        this.invoiceDao = invoiceDao;
        this.itemDao = itemDao;
        this.invoiceItemDao = invoiceItemDao;
    }

    @Transactional
    public PurchaseViewModel saveInvoiceItem(PurchaseViewModel viewModel) {

        //Persist InvoiceItem
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(viewModel.getInvoiceId());


        // Add invoice id to invoiceitems on the order and persist to database
        List<InvoiceItem> invoiceItems = viewModel.getInvoiceItemList();

        invoiceItems.stream()
                .forEach(item ->
                {
                    item.setInvoiceId(viewModel.getInvoiceId());
                    item = invoiceItemDao.addInvoiceItem(item);
                });

                invoiceItems = invoiceItemDao.getInvoiceItemsByCustomer(viewModel.getCustomerId());
                viewModel.setInvoiceItemList(invoiceItems);

        return viewModel;

    }

    private PurchaseViewModel buildPurchaseViewModel(Customer customer) {
        List<Invoice> invoiceList = invoiceDao.getInvoiceByCustomer(customer.getCustomerId());
        List<InvoiceItem> invoiceItemList;
        invoiceList.stream()
                .forEach(item -> invoiceItemDao.addInvoiceItem(item.getInvoiceId()));

        return null;
    }

}
