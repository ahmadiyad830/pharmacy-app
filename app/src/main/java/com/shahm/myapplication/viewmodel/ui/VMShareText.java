package com.shahm.myapplication.viewmodel.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.model.SalesPharmacy;

public class VMShareText extends ViewModel {
    private final MutableLiveData<SalesPharmacy> model ;

    public VMShareText() {
        model = new MutableLiveData<>();
    }

    public LiveData<SalesPharmacy> getText() {
        return model;
    }

    public void setText(SalesPharmacy model) {
        this.model.setValue(model);
    }
}
