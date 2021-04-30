package com.shahm.myapplication.responses;

import com.google.gson.annotations.SerializedName;
import com.shahm.myapplication.model.Medicines;

import java.util.List;

public class ResponseMedicines {
    @SerializedName("id")
    private int id;
    @SerializedName("?drug=3")
    private List<Medicines> getMedicines;

    public List<Medicines> getGetMedicines() {
        return getMedicines;
    }

    public int getId() {
        return id;
    }
}
//https://www.blacktools.io/api/?drug=3