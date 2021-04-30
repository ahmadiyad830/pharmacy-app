package com.shahm.myapplication.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shahm.myapplication.model.MedicinesHave;
import com.shahm.myapplication.network.ApiClint;
import com.shahm.myapplication.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoMedicinesHave {
    private final ApiService service;

    public RepoMedicinesHave() {
        service = ApiClint.getRetrofit().create(ApiService.class);
    }
    public LiveData<List<MedicinesHave>> getMedicinesHave(int page){
        MutableLiveData<List<MedicinesHave>> data = new MutableLiveData<>();
        service.getMedicinesHave(page).enqueue(new Callback<List<MedicinesHave>>() {
            @Override
            public void onResponse(Call<List<MedicinesHave>> call, Response<List<MedicinesHave>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MedicinesHave>> call, Throwable t) {

            }
        });
        return data;
    }
}
