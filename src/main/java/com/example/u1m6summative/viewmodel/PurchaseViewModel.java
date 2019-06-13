package com.example.u1m6summative.viewmodel;

import com.example.u1m6summative.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PurchaseViewModel {
    private int invoiceId;
    private int customerId;
    private Date orderDate;
    private Date pickUpDate;
    private Date returnDate;
    private double lateFee;
    private List<InvoiceItem> invoiceItemList = new ArrayList<>();

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

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getLateFee() {
        return lateFee;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }

    public List<InvoiceItem> getInvoiceItemList() {
        return invoiceItemList;
    }

    public void setInvoiceItemList(List<InvoiceItem> invoiceItemList) {
        this.invoiceItemList = invoiceItemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseViewModel that = (PurchaseViewModel) o;
        return invoiceId == that.invoiceId &&
                customerId == that.customerId &&
                Double.compare(that.lateFee, lateFee) == 0 &&
                orderDate.equals(that.orderDate) &&
                pickUpDate.equals(that.pickUpDate) &&
                returnDate.equals(that.returnDate) &&
                invoiceItemList.equals(that.invoiceItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, customerId, orderDate, pickUpDate, returnDate, lateFee, invoiceItemList);
    }
}
