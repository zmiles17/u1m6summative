package com.example.u1m6summative.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Invoice {

    private int invoiceId;
    @NotNull(message = "Customer Id cannot be Empty or null")
    private int customerId;
    @NotNull(message = "Order Date cannot be Empty or null")
    private LocalDate orderDate;
    @NotNull(message = "Pick Up Date cannot be Empty or null")
    private LocalDate pickUpDate;
    @NotNull(message = "Return Date cannot be Empty or null")
    private LocalDate returnDate;
    @NotNull(message = "Late fee cannot be Empty or null")
    private Double lateFee;

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

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Double getLateFee() {
        return lateFee;
    }

    public void setLateFee(Double lateFee) {
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
