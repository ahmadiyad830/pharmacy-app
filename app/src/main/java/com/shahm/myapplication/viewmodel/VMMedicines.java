package com.shahm.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.repositories.RepositoreMedicines;

import java.util.HashMap;
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

   public LiveData<Void> postData (Medicines model){
        return repositoreMedicines.postData(model);
   }
    public LiveData<Void> postMap (HashMap<String, String> modelMap){
        return repositoreMedicines.postMap(modelMap);
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
        return repositoreMedicines.postField(name,
                scientific,
                concentration,
                dosageform,
                notes,
                store,
                sachet,
                slocation,
                squantity);
    }

    public LiveData<Void> postUpdate (String id,String name,
                                      String scientific, String concentration, String dosageform, String notes, String store, String sachet, String slocation, String squantity){
        return repositoreMedicines.postUpdate(id,name,
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
