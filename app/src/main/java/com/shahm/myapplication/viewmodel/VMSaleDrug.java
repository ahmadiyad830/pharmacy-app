package com.shahm.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.repositories.RepoSaleDrug;

public class VMSaleDrug extends ViewModel {
    private RepoSaleDrug saleDrug;

    public VMSaleDrug() {
        saleDrug = new RepoSaleDrug();
    }
    public LiveData<Void> postSaleDrug(String itemId,String isSachet,String price ){
        return saleDrug.postSale(itemId,isSachet,price);
    }
}
