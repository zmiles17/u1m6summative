package com.example.u1m6summative.service;

import com.example.u1m6summative.dao.CustomerDao;
import com.example.u1m6summative.dao.InvoiceDao;
import com.example.u1m6summative.dao.InvoiceItemDao;
import com.example.u1m6summative.dao.ItemDao;
import com.example.u1m6summative.model.*;
import com.example.u1m6summative.viewmodel.CustomerViewModel;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
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
        setUpCustomerMock();
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
        actualInvoice.setReturnDate(LocalDate.of(2019, 6, 25));

        Invoice expectedInvoice = customerServiceLayer.addInvoice(actualInvoice);
        actualInvoice.setInvoiceId(expectedInvoice.getInvoiceId());
        assertEquals(expectedInvoice,actualInvoice);

        actualInvoice = customerServiceLayer.addInvoice(actualInvoice);
        assertEquals(1,actualInvoice.getInvoiceId());

    }
    @Test
    public void deleteInvoice() {
        Invoice actualInvoice = new Invoice();
        actualInvoice.setCustomerId(1);
        actualInvoice.setLateFee(4.00);
        actualInvoice.setOrderDate(LocalDate.of(2019, 6, 13));
        actualInvoice.setPickUpDate(LocalDate.of(2019, 6, 19));
        actualInvoice.setReturnDate(LocalDate.of(2019, 6, 25));
        actualInvoice = customerServiceLayer.addInvoice(actualInvoice);

     int deleted =   customerServiceLayer.deleteInvoice(actualInvoice.getInvoiceId());
        assertEquals(1,deleted);
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
        assertEquals(expectedInvoiceItem,actualInvoiceItem);

        int deleted = customerServiceLayer.deleteInvoice(actualInvoiceItem.getInvoiceId());
        assertEquals(1,deleted);
    }

    @Test
    public void getInvoicesByCustomer() {

        List<Invoice> theThingIGot = customerServiceLayer.getInvoicesByCustomer("Ramya", "B");

        assertEquals(theThingIGot.size(), 1);
        assertEquals(theThingIGot.get(0).getInvoiceId(), 1);
    }

    @Test
    public void addCustomer() {
        CustomerViewModel customerViewModel = new CustomerViewModel();

        //create and save customer to customer view model
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("Jack");
        customer.setLastName("Smiles");
        customer.setEmail("email@yahoo.com");
        customer.setCompany("Yahoo");
        customer.setPhone("423-763-8976");

        Customer customer1 = new Customer();
        customer1.setCustomerId(1);
        customer1.setFirstName("Jack");
        customer1.setLastName("Smiles");
        customer1.setEmail("email@yahoo.com");
        customer1.setCompany("Yahoo");
        customer1.setPhone("423-763-8976");

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer1);
//        customer = customerServiceLayer.addCustomer(customer);

        //create a list of invoices and save it to the view model
        /*Invoice invoice1 = new Invoice();
        invoice1.setInvoiceId(1);
        invoice1.setLateFee(15.00);
        invoice1.setOrderDate(LocalDate.of(2019, 6, 17));
        invoice1.setPickUpDate(LocalDate.of(2019, 7, 4));
        invoice1.setReturndate(LocalDate.of(2019, 8, 29));
        invoice1 = customerServiceLayer.addInvoice(invoice1);*/

        //create a list of items and save it to the view model
        /*Item item1 = new Item();
        item1.setItemId(1);
        item1.setName("RX Bar");
        item1.setDailyRate(6.00);
        item1.setDescription("Energy snack bar");
        item1 = customerServiceLayer.addItem(item1);*/

        //create a map of invoice id and a list of items associated with the invoice
        /*InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setDiscount(1.00);
        invoiceItem.setQuantity(1);
        invoiceItem.setUnitRate(12.05);
        invoiceItem.setInvoiceItemId(1);
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        Map<Integer, List<InvoiceItem>> invoiceItemMap = new HashMap<>();
        invoiceItemMap.put(invoice1.getInvoiceId(), invoiceItems);*/


        assertEquals(customer1, customerDao.addCustomer(customer1));
        assertEquals(customer1, customerDao.getCustomer(1));
        assertEquals(customers, customerDao.getAllCustomer());
        assertTrue(customerDao.deleteCustomer(customer.getCustomerId()));
    }

    private void setUpCustomerMock() {
        customerDao = mock(CustomerDao.class);
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("Jack");
        customer.setLastName("Smiles");
        customer.setEmail("email@yahoo.com");
        customer.setCompany("Yahoo");
        customer.setPhone("423-763-8976");

        Customer customer1 = new Customer();
        customer1.setCustomerId(1);
        customer1.setFirstName("Jack");
        customer1.setLastName("Smiles");
        customer1.setEmail("email@yahoo.com");
        customer1.setCompany("Yahoo");
        customer1.setPhone("423-763-8976");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        customerList.add(customer1);

        doReturn(customer).when(customerDao).addCustomer(customer1);
        doReturn(customer1).when(customerDao).getCustomer(1);
        doReturn(customerList).when(customerDao).getAllCustomer();
        doReturn(true).when(customerDao).deleteCustomer(1);

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
        invoice.setReturnDate(LocalDate.of(2019, 6, 25));

        Invoice invoice1 = new Invoice();
        invoice1.setCustomerId(1);
        invoice1.setLateFee(4.00);
        invoice1.setOrderDate(LocalDate.of(2019, 6, 13));
        invoice1.setPickUpDate(LocalDate.of(2019, 6, 19));
        invoice1.setReturnDate(LocalDate.of(2019, 6, 25));


        List<Invoice> iList = new ArrayList<>();
        iList.add(invoice);

        invoiceItemDao = mock(InvoiceItemDao.class);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setItemId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setDiscount(50.00);
        invoiceItem.setQuantity(6);
        invoiceItem.setUnitRate(30.00);

        doReturn(invoice).when(invoiceDao).addInvoice(invoice1);
        doReturn(1).when(invoiceDao).deleteInvoice(1);
        doReturn(iList).when(invoiceDao).getInvoicesByCustomer("Ramya", "B");

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
