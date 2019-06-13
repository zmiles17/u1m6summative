package com.example.u1m6summative.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Invoice {

    private int invoiceId;
    private int customerId;
    private LocalDate orderDate;
    private LocalDate pickUpDate;
    private LocalDate returnDate;
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

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDate getReturndate() {
        return returnDate;
    }

    public void setReturndate(LocalDate returndate) {
        this.returnDate = returndate;
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
                returnDate.equals(invoice.returnDate) &&
                lateFee.equals(invoice.lateFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, customerId, orderDate, pickUpDate, returnDate, lateFee);
    }
}
