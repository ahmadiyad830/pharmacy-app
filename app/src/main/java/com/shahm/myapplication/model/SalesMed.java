package com.shahm.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class SalesMed {
    @SerializedName("Drug ID")
    private String id;
    @SerializedName("name")
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
