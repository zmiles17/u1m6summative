package com.example.u1m6summative.controller;

import com.example.u1m6summative.dao.CustomerDao;
import com.example.u1m6summative.dao.InvoiceDao;
import com.example.u1m6summative.model.Customer;
import com.example.u1m6summative.model.Invoice;
import com.example.u1m6summative.model.InvoiceItem;
import com.example.u1m6summative.model.Item;
import com.example.u1m6summative.service.CustomerServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

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
    public Customer addCustomer(@RequestBody @Valid Customer customer) {
        return customerDao.addCustomer(customer);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable int id) {
        //TO DO
     // return customerServiceLayer.ge(id);
        return null;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer getAllCustomer()
    {
        //TO DO
      //  return customerDao.;
        return null;
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Customer deleteCustomer(@PathVariable int id)
    {
        //TO DO
        //  return customerDao.;
        return null;
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Customer updateCustomer(@RequestBody @Valid Customer customer, @PathVariable int id)
    {
        //TO DO
        //  return customerDao.;
        return null;
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Item addItem(@RequestBody @Valid Item item) {
        return customerServiceLayer.addItem(item);
    }

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getAllItem() {
        return customerServiceLayer.findAllItem();
    }

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Item getItem(@PathVariable int itemId) {
        return customerServiceLayer.findItem(itemId);
    }

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable int itemId) {
        try {
            customerServiceLayer.removeItem(itemId);
        }
        catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("You cannot delete ITEM as its related to INVOICE");
        }
    }

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void getItem(@RequestBody @Valid Item item, @PathVariable int itemId) {
        item.setItemId(itemId);
        customerServiceLayer.updateItem(item);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody @Valid Invoice invoice) throws IllegalStateException {
        return customerServiceLayer.addInvoice(invoice);

    }

    @RequestMapping(value = "/invoice/{invoiceId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int invoiceId) {
        try {
            customerServiceLayer.deleteInvoice(invoiceId);
        } catch (Exception ex) {
            throw new DataIntegrityViolationException(ex.getMessage());
        }

    }


    @RequestMapping(value = "/invoiceItem", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceItem addInvoiceItem(@RequestBody @Valid InvoiceItem invoiceItem) {
        try {
            return customerServiceLayer.addInvoiceItem(invoiceItem);
        } catch (IllegalStateException e) {
            return null;
        }
    }

    @RequestMapping(value = "/invoice/{firstName}/{lastName}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoicesByCustomer(@PathVariable String firstName, String lastName){
        List<Invoice> iList = customerServiceLayer.getInvoicesByCustomer(firstName, lastName);

        return iList;

    }


}
