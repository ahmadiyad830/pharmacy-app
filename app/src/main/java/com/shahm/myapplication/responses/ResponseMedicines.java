package com.shahm.myapplication.responses;

import com.google.gson.annotations.SerializedName;
import com.shahm.myapplication.model.Medicines;

import java.util.List;

public class ResponseMedicines {
    @SerializedName("id")
    private int id;
    @SerializedName("?drug=3")
    private List<Medicines> getMedicines;
    @SerializedName("?adddrug&")
    private List<Medicines> listData;

    public List<Medicines> getGetMedicines() {
        return getMedicines;
    }

    public int getId() {
        return id;
    }
    public List<Medicines> postData(){
        return listData;
    }
}
//https://www.blacktools.io/api/?drug=3

//https://www.blacktools.io/sapi/?adddrug&name=test&scientific=test&concentration=test&dosageform=test&notes=test&store=test&sachet=test&slocation=1&squantity=1