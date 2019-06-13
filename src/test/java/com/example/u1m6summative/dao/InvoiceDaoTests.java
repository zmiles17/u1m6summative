package com.example.u1m6summative.dao;

import com.example.u1m6summative.model.Customer;
import com.example.u1m6summative.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
        customer.setCustomerId(8);
        customer.setFirstName("Ramya");
        customer.setLastName("B");
        customer.setEmail("rb@yahoo.com");
        customer.setCompany("Ramya's Tool & Die");
        customer.setPhone("213-746-9957");

//        private int customerId;
//        private String firstName;
//        private String lastName;
//        private String email;
//        private String company;
//        private String phone;

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(8);
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 6, 13));
        invoice.setPickUpDate(LocalDate.of(2019, 6, 13));
        invoice.setReturndate(LocalDate.of(2019, 6, 14));
        invoice.setLateFee(BigDecimal.valueOf(19.99f));
        invoiceDao.addInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice1, invoice);

        invoiceDao.deleteInvoice(invoice.getInvoiceId());

        invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertNull(invoice1);

    }

    @Test
    public void getAllInvoice() {
        Customer customer = new Customer();
        customer.setCustomerId(8);
        customer.setFirstName("Ramya");
        customer.setLastName("B");
        customer.setEmail("rb@yahoo.com");
        customer.setCompany("Ramya's Tool & Die");
        customer.setPhone("213-746-9957");

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(8);
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 6, 13));
        invoice.setPickUpDate(LocalDate.of(2019, 6, 13));
        invoice.setReturndate(LocalDate.of(2019, 6, 14));
        invoice.setLateFee(BigDecimal.valueOf(19.99f));
        invoiceDao.addInvoice(invoice);

        invoice = new Invoice();
        invoice.setInvoiceId(10);
        invoice.setCustomerId(9);
        invoice.setOrderDate(LocalDate.of(2019, 6, 13));
        invoice.setPickUpDate(LocalDate.of(2019, 6, 13));
        invoice.setReturndate(LocalDate.of(2019, 6, 14));
        invoice.setLateFee(BigDecimal.valueOf(14.99f));
        invoiceDao.addInvoice(invoice);

        List<Invoice> iList = invoiceDao.getAllInvoice();

        assertEquals(iList.size(), 2);
    }

    @Test
    public void updateInvoice() {
        Customer customer = new Customer();
        customer.setCustomerId(8);
        customer.setFirstName("Ramya");
        customer.setLastName("B");
        customer.setEmail("rb@yahoo.com");
        customer.setCompany("Ramya's Tool & Die");
        customer.setPhone("213-746-9957");

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(8);
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 6, 13));
        invoice.setPickUpDate(LocalDate.of(2019, 6, 13));
        invoice.setReturndate(LocalDate.of(2019, 6, 14));
        invoice.setLateFee(BigDecimal.valueOf(19.99f));
        invoiceDao.addInvoice(invoice);

        invoice.setInvoiceId(8);
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 6, 13));
        invoice.setPickUpDate(LocalDate.of(2019, 6, 13));
        invoice.setReturndate(LocalDate.of(2019, 6, 14));
        invoice.setLateFee(BigDecimal.valueOf(29.99f));
        invoiceDao.addInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice1, invoice);
    }
}
