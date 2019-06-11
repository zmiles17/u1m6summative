package com.example.u1m6summative.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Invoice {

    private int invoiceId;
    private int customerId;
    private Date orderDate;
    private Date pickUpDate;
    private Date returndate;
    private BigDecimal lateFee;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return invoiceId == invoice.invoiceId &&
                customerId == invoice.customerId &&
                orderDate.equals(invoice.orderDate) &&
                pickUpDate.equals(invoice.pickUpDate) &&
                returndate.equals(invoice.returndate) &&
                lateFee.equals(invoice.lateFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, customerId, orderDate, pickUpDate, returndate, lateFee);
    }
}
