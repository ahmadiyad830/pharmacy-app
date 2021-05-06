package com.shahm.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.repositories.RepositoreMedicines;

import java.util.List;

public class VMMedicines extends ViewModel {
    private RepositoreMedicines repositoreMedicines;


    public VMMedicines() {

        repositoreMedicines = new RepositoreMedicines();
    }
    public LiveData<List<Medicines>> getMedicines(int page) {
        return repositoreMedicines.getListMedicines(page);
    }
}
