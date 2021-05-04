package com.shahm.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.model.AnalyzeDepot;
import com.shahm.myapplication.repositories.RepoAnalyzeDepot;

import java.util.List;

public class VMAnalyzeDepot extends ViewModel {
    private RepoAnalyzeDepot repoAnalyzeDepot;

    public VMAnalyzeDepot() {
        repoAnalyzeDepot = new RepoAnalyzeDepot();
    }
    public LiveData<List<AnalyzeDepot>> getDepot(String depotName, String monthNum){
        return repoAnalyzeDepot.listDepot(depotName,monthNum);
    }


}
