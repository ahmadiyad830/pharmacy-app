package com.shahm.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.model.SalesPharmacy;
import com.shahm.myapplication.repositories.RepoPHSales;

import java.util.List;

public class VMPHSales extends ViewModel {
    private RepoPHSales sales;

    public VMPHSales() {
        sales = new RepoPHSales();
    }

    public LiveData<List<SalesPharmacy>> getSales(int page) {
        return sales.getSales(page);
    }

    public LiveData<Void> postDelete(String salesId) {
        return sales.postSales(salesId);
    }

}
