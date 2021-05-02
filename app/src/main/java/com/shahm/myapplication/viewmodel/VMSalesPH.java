package com.shahm.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.model.SalesPharmacy;
import com.shahm.myapplication.repositories.RepoSalesPH;

import java.util.List;

public class VMSalesPH extends ViewModel {
    private RepoSalesPH repoSalesPH;

    public VMSalesPH() {
        repoSalesPH = new RepoSalesPH();
    }
    public LiveData<List<SalesPharmacy>> getSalesPH(String name,int monthNum){
        return repoSalesPH.getSalesPH(name,monthNum);
    }
}
