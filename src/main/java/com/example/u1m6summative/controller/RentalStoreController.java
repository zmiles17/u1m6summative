package com.example.u1m6summative.controller;

import com.example.u1m6summative.dao.CustomerDao;
import com.example.u1m6summative.dao.InvoiceDao;
import com.example.u1m6summative.model.Customer;
import com.example.u1m6summative.model.Invoice;
import com.example.u1m6summative.service.CustomerServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RentalStoreController {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    InvoiceDao invoiceDao;

    @Autowired
    CustomerServiceLayer customerServiceLayer;

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
    public Invoice addInvoice(@RequestBody Invoice invoice) throws IllegalStateException {
        return customerServiceLayer.addInvoice(invoice);
    }

    @RequestMapping(value = "/invoice/{invoiceId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int invoiceId){
        try {
            customerServiceLayer.deleteInvoice(invoiceId);
        }
        catch (Exception ex){
            throw new DataIntegrityViolationException(ex.getMessage());
        }

    }

    @RequestMapping(value = "/invoice/{firstName}/{lastName}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoicesByCustomer(@PathVariable String firstName, String lastName){
        List<Invoice> iList = customerServiceLayer.getInvoicesByCustomer(firstName, lastName);

        return iList;
    }


}
