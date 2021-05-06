package com.shahm.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.repositories.RepoNewDrug;

public class VMNewDrug extends ViewModel {
    private RepoNewDrug repoNewDrug;

    public VMNewDrug() {
        repoNewDrug = new RepoNewDrug();
    }
    public LiveData<Void> postField (String name,
                                     String scientific,
                                     String concentration,
                                     String dosageform,
                                     String notes,
                                     String store,
                                     String sachet,
                                     String slocation,
                                     String squantity      ){
        return repoNewDrug.postField(name,
                scientific,
                concentration,
                dosageform,
                notes,
                store,
                sachet,
                slocation,
                squantity);
    }
}
