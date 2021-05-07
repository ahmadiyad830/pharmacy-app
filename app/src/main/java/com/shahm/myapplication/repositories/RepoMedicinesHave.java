package com.shahm.myapplication.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.network.ApiClint;
import com.shahm.myapplication.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoMedicinesHave {
    private static final String TAG = "RepoMedicinesHave";
    private final ApiService service;

    public RepoMedicinesHave() {
        service = ApiClint.getRetrofit().create(ApiService.class);
    }
    public LiveData<List<Medicines>> getMedicinesHave(int page){
        MutableLiveData<List<Medicines>> data = new MutableLiveData<>();
        service.getMedicinesHave(page).enqueue(new Callback<List<Medicines>>() {
            @Override
            public void onResponse(Call<List<Medicines>> call, Response<List<Medicines>> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception e) {
                    Log.d(TAG, "onResponse: "+response.code());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Medicines>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return data;
    }
}
