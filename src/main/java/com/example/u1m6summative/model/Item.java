package com.example.u1m6summative.model;

import java.util.Objects;

public class Item {
    private int itemId;
    private String name;
    private String Description;
    private Double dailyRate;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(Double dailyRate) {
        this.dailyRate = dailyRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemId == item.itemId &&
                name.equals(item.name) &&
                Objects.equals(Description, item.Description) &&
                dailyRate.equals(item.dailyRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, name, Description, dailyRate);
    }
}
