package com.shahm.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.model.Depot;
import com.shahm.myapplication.repositories.RepoDepot;

import java.util.List;

public class VMDepot extends ViewModel {
    private RepoDepot repoDepot ;

    public VMDepot() {
        repoDepot = new RepoDepot();
    }
    public LiveData<List<Depot>> getDepot(String depotName,String monthNum){
        return repoDepot.listDepot(depotName,monthNum);
    }
}
