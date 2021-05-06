package com.shahm.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.repositories.RepoUpdate;

public class VMUpdate extends ViewModel {
    private RepoUpdate repoUpdate;


    public VMUpdate() {

        repoUpdate = new RepoUpdate();
    }

    public LiveData<Void> postUpdate(String id, String name,
                                     String scientific, String concentration, String dosageform, String notes, String store, String sachet, String slocation, String squantity) {
        return repoUpdate.postUpdate(id, name,
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
