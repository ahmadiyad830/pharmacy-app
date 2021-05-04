package com.shahm.myapplication.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shahm.myapplication.model.AnalyzeDrug;
import com.shahm.myapplication.network.ApiClint;
import com.shahm.myapplication.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoAnalyzeDrug {
    private ApiService service;

    public RepoAnalyzeDrug() {
        service = ApiClint.getRetrofit().create(ApiService.class);
    }
    public LiveData<List<AnalyzeDrug>> getSalesPH(String name, String monthNum ){
        MutableLiveData<List<AnalyzeDrug>> data = new MutableLiveData<>();
        service.getSalesPharmacy(name,monthNum).enqueue(new Callback<List<AnalyzeDrug>>() {
            @Override
            public void onResponse(Call<List<AnalyzeDrug>> call, Response<List<AnalyzeDrug>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AnalyzeDrug>> call, Throwable t) {

            }
        });
        return data;
    }
}
