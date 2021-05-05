package com.shahm.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Medicines  implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("barcode")
    @Expose
    private String barcode;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("scientific")
    @Expose
    private String scientific;

    @SerializedName("concentration")
    @Expose
    private String concentration;

    @SerializedName("dosageform")
    @Expose
    private String dosageform;

    @SerializedName("notes")
    @Expose
    private String notes;

    @SerializedName("store")
    @Expose
    private String store;

    @SerializedName("sachet")
    @Expose
    private String sachet;

    @SerializedName("slocation")
    @Expose
    private String location;

    @SerializedName("squantity")
    @Expose
    private String quantity;

    private boolean expanded = false;

    public Medicines(String name, String scientific, String concentration, String dosageform, String notes, String store, String sachet, String location, String quantity) {
        this.name = name;
        this.scientific = scientific;
        this.concentration = concentration;
        this.dosageform = dosageform;
        this.notes = notes;
        this.store = store;
        this.sachet = sachet;
        this.location = location;
        this.quantity = quantity;
    }


    public String[] listMed(){
        return new String[]{id,barcode,name,scientific,concentration,dosageform,notes,store,sachet,location,quantity};
    }

    @Override
    public String toString() {
        return "Medicines{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", scientific='" + scientific + '\'' +
                ", concentration='" + concentration + '\'' +
                ", dosageform='" + dosageform + '\'' +
                ", notes='" + notes + '\'' +
                ", store='" + store + '\'' +
                ", sachet='" + sachet + '\'' +
                ", location='" + location + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
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

    public String getDosageform() {
        return dosageform;
    }

    public void setDosageform(String dosageform) {
        this.dosageform = dosageform;
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

    public String getSachet() {
        return sachet;
    }

    public void setSachet(String sachet) {
        this.sachet = sachet;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


}
