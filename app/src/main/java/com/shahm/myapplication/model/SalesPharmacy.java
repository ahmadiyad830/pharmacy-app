package com.shahm.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//
public class SalesPharmacy implements Serializable {
    @SerializedName("Drug ID")
    private String id;
    @SerializedName("Name")
    private String name;
    @SerializedName("isSachet")
    private String isSachet;
    @SerializedName("Concentraion")
    private String concentraion;
    @SerializedName("DosageForm")
    private String DosageForm;
    @SerializedName("Store")
    private String Store;
    @SerializedName("price")
    private String price;
    @SerializedName("date")
    private String date;

    private boolean expanded;

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public String toString() {
        return "SalesPharmacy{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isSachet='" + isSachet + '\'' +
                ", concentraion='" + concentraion + '\'' +
                ", DosageForm='" + DosageForm + '\'' +
                ", Store='" + Store + '\'' +
                ", price='" + price + '\'' +
                ", date='" + date + '\'' +
                ", expanded=" + expanded +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsSachet() {
        return isSachet;
    }

    public void setIsSachet(String isSachet) {
        this.isSachet = isSachet;
    }

    public String getConcentraion() {
        return concentraion;
    }

    public void setConcentraion(String concentraion) {
        this.concentraion = concentraion;
    }

    public String getDosageForm() {
        return DosageForm;
    }

    public void setDosageForm(String dosageForm) {
        DosageForm = dosageForm;
    }

    public String getStore() {
        return Store;
    }

    public void setStore(String store) {
        Store = store;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
