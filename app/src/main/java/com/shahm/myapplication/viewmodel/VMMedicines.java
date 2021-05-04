package com.shahm.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.repositories.RepositoreMedicines;
import com.shahm.myapplication.responses.ResponseMedicines;

import java.util.List;

public class VMMedicines extends ViewModel {
    private RepositoreMedicines repositoreMedicines;
    private MutableLiveData<List<Medicines>> data;

    public VMMedicines() {
//        data = new MutableLiveData<>();
        repositoreMedicines = new RepositoreMedicines();
    }


    public LiveData<List<Medicines>> getMedicines(int page) {
        return repositoreMedicines.getListMedicines(page);
    }

   public LiveData<ResponseMedicines> postData (Medicines model){
        return repositoreMedicines.postData(model);
   }
}
