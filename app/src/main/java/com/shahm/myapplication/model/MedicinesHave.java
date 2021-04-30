package com.shahm.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class MedicinesHave {
    @SerializedName("id")
    private String id;
    @SerializedName("barcode")
    private String barcode;
    @SerializedName("name")
    private String name;
    @SerializedName("scientific")
    private String scientific;
    @SerializedName("concentration")
    private String concentration;
    @SerializedName("dosageform")
    private String dosageForm;
    @SerializedName("notes")
    private String notes;
    @SerializedName("store")
    private String store;
    @SerializedName("sachet")
    private String sachet;
    @SerializedName("slocation")
    private String location;
    @SerializedName("squantity")
    private String quantity;
    private boolean expanded;

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public MedicinesHave(String barcode, String name, String scientific, String concentration, String dosageForm, String notes, String store, String sachet, String sLocation, String quantity) {
        this.barcode = barcode;
        this.name = name;
        this.scientific = scientific;
        this.concentration = concentration;
        this.dosageForm = dosageForm;
        this.notes = notes;
        this.store = store;
        this.sachet = sachet;
        this.location = sLocation;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientific() {
        return scientific;
    }

    public void setScientific(String scientific) {
        this.scientific = scientific;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "MedicinesHave{" +
                "id='" + id + '\'' +
                ", barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", scientific='" + scientific + '\'' +
                ", concentration='" + concentration + '\'' +
                ", dosageForm='" + dosageForm + '\'' +
                ", notes='" + notes + '\'' +
                ", store='" + store + '\'' +
                ", sachet='" + sachet + '\'' +
                ", sLocation='" + location + '\'' +
                ", sQuantity='" + quantity + '\'' +
                ", expanded=" + expanded +
                '}';
    }

    public String getSachet() {
        return sachet;
    }

    public void setSachet(String sachet) {
        this.sachet = sachet;
    }

    public String getsLocation() {
        return location;
    }

    public void setsLocation(String sLocation) {
        this.location = sLocation;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


}

/*
* "id": "12",
    "barcode": "",
    "name": "ACE press tablet",
    "scientific": "Enalapril Maleate 20 mg",
    "concentration": "20 mg",
    "dosageform": "30",
    "notes": "",
    "store": "الشركة الأردنية السويدية",
    "sachet": "1",
    "slocation": "R4",
    "squantity": "99"
*
* */
