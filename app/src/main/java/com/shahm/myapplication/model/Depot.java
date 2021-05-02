package com.shahm.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Depot {
    @SerializedName("Name")
    private String name;
    @SerializedName("Store")
    private String store;
    @SerializedName("Time Statics")
    private String timeStatics;
    @SerializedName("Available")
    private String available;
    @SerializedName("Purchases")
    private int purchases;
    @SerializedName("Sales")
    private int sales;
    @SerializedName("Average Sale")
    private double averageSale;
    private boolean expanded;

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getTimeStatics() {
        return timeStatics;
    }

    public void setTimeStatics(String timeStatics) {
        this.timeStatics = timeStatics;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public int getPurchases() {
        return purchases;
    }

    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public double getAverageSale() {
        return averageSale;
    }

    public void setAverageSale(double averageSale) {
        this.averageSale = averageSale;
    }
}
