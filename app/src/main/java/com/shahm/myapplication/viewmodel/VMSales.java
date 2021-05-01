package com.shahm.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.model.SalesMed;
import com.shahm.myapplication.repositories.RepoSales;

import java.util.List;

public class VMSales extends ViewModel {
    private RepoSales sales;

    public VMSales() {
        sales = new RepoSales();
    }
    public LiveData<List<SalesMed>> getSales(int page){
        return sales.getSales(page);
    }
}
