package com.example.u1m6summative.controller;

import com.example.u1m6summative.dao.CustomerDao;
import com.example.u1m6summative.dao.InvoiceDao;
import com.example.u1m6summative.model.Customer;
import com.example.u1m6summative.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class RentalStoreController {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    InvoiceDao invoiceDao;

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer){
        return customerDao.addCustomer(customer);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@RequestBody int id){
        
        return customerDao.getCustomer(id);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody Invoice invoice){

        try {
        return invoiceDao.addInvoice(invoice);

        } catch (IllegalStateException e){
            return null;
        }
    }


}
