package com.example.u1m6summative.dao;

import com.example.u1m6summative.model.Customer;
import com.example.u1m6summative.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTests {

    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        List<Invoice> iList = invoiceDao.getAllInvoice();
        for (Invoice i : iList){
            invoiceDao.deleteInvoice(i.getInvoiceId());
        }

        List<Customer> cList = customerDao.getAllCustomer();
        for (Customer c : cList){
            customerDao.deleteCustomer(c.getCustomerId());
        }
    }

    @Test
    public void addGetDeleteInvoice(){
        Customer customer = new Customer();
       // customer.setCustomerId(8);
        customer.setFirstName("Ramya");
        customer.setLastName("B");
        customer.setEmail("rb@yahoo.com");
        customer.setCompany("Ramya's Tool & Die");
        customer.setPhone("213-746-9957");
        customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
       // invoice.setInvoiceId(8);
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 6, 13));
        invoice.setPickUpDate(LocalDate.of(2019, 6, 13));
        invoice.setReturndate(LocalDate.of(2019, 6, 14));
        invoice.setLateFee(Double.valueOf(19.00));
        invoice=invoiceDao.addInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice1, invoice);

        invoiceDao.deleteInvoice(invoice.getInvoiceId());

        invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertNull(invoice1);

    }

    @Test
    public void getAllInvoice() {
        Customer customer = new Customer();
        //customer.setCustomerId(8);
        customer.setFirstName("Ramya");
        customer.setLastName("B");
        customer.setEmail("rb@yahoo.com");
        customer.setCompany("Ramya's Tool & Die");
        customer.setPhone("213-746-9957");
        customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        //invoice.setInvoiceId(8);
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 6, 13));
        invoice.setPickUpDate(LocalDate.of(2019, 6, 13));
        invoice.setReturndate(LocalDate.of(2019, 6, 14));
        invoice.setLateFee(Double.valueOf(19.99));
        invoiceDao.addInvoice(invoice);

        invoice = new Invoice();
        //invoice.setInvoiceId(10);
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 6, 13));
        invoice.setPickUpDate(LocalDate.of(2019, 6, 13));
        invoice.setReturndate(LocalDate.of(2019, 6, 14));
        invoice.setLateFee(Double.valueOf(14.99));
        invoiceDao.addInvoice(invoice);

        List<Invoice> iList = invoiceDao.getAllInvoice();

        assertEquals(iList.size(), 2);
    }

    @Test
    public void updateInvoice() {
        Customer customer = new Customer();
        //customer.setCustomerId(8);
        customer.setFirstName("Ramya");
        customer.setLastName("B");
        customer.setEmail("rb@yahoo.com");
        customer.setCompany("Ramya's Tool & Die");
        customer.setPhone("213-746-9957");
        customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        //invoice.setInvoiceId(8);
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 6, 13));
        invoice.setPickUpDate(LocalDate.of(2019, 6, 13));
        invoice.setReturndate(LocalDate.of(2019, 6, 14));
        invoice.setLateFee(Double.valueOf(19.99));
        invoice = invoiceDao.addInvoice(invoice);

        //invoice.setInvoiceId(8);
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 6, 13));
        invoice.setPickUpDate(LocalDate.of(2019, 6, 13));
        invoice.setReturndate(LocalDate.of(2019, 6, 14));
        invoice.setLateFee(Double.valueOf(29.99));
        invoice = invoiceDao.updateInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice1, invoice);
    }

    @Test
    public void getByCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Ramya");
        customer.setLastName("B");
        customer.setEmail("rb@yahoo.com");
        customer.setCompany("Ramya's Tool & Die");
        customer.setPhone("213-746-9957");
        customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 6, 13));
        invoice.setPickUpDate(LocalDate.of(2019, 6, 13));
        invoice.setReturndate(LocalDate.of(2019, 6, 14));
        invoice.setLateFee(Double.valueOf(19.99));
        invoice = invoiceDao.addInvoice(invoice);

        invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 6, 18));
        invoice.setPickUpDate(LocalDate.of(2019, 6, 18));
        invoice.setReturndate(LocalDate.of(2019, 6, 25));
        invoice.setLateFee(Double.valueOf(29.99));
        invoice = invoiceDao.addInvoice(invoice);

        invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 6, 21));
        invoice.setPickUpDate(LocalDate.of(2019, 6, 28));
        invoice.setReturndate(LocalDate.of(2019, 7, 4));
        invoice.setLateFee(Double.valueOf(9.99));
        invoice = invoiceDao.addInvoice(invoice);

        List<Invoice> iList = invoiceDao.getByCustomer("Ramya", "B");

        assertEquals(iList.size(), 3);

    }
}
