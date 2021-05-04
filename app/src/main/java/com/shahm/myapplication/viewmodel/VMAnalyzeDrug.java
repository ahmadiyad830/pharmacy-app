package com.shahm.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.model.AnalyzeDrug;
import com.shahm.myapplication.repositories.RepoAnalyzeDrug;

import java.util.List;

public class VMAnalyzeDrug extends ViewModel {
    private RepoAnalyzeDrug repoAnalyzeDrug;

    public VMAnalyzeDrug() {
        repoAnalyzeDrug = new RepoAnalyzeDrug();
    }
    public LiveData<List<AnalyzeDrug>> getSalesPH(String name, String monthNum){
        return repoAnalyzeDrug.getSalesPH(name,monthNum);
    }
}
