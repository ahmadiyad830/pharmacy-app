package com.shahm.myapplication.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shahm.myapplication.model.Depot;
import com.shahm.myapplication.network.ApiClint;
import com.shahm.myapplication.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoDepot {
    private ApiService service;

    public RepoDepot() {
        service = ApiClint.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<Depot>> listDepot(String depotName, String monthNum) {
        MutableLiveData<List<Depot>> data = new MutableLiveData<>();
        service.getDepot(depotName, monthNum).enqueue(new Callback<List<Depot>>() {
            @Override
            public void onResponse(Call<List<Depot>> call, Response<List<Depot>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Depot>> call, Throwable t) {

            }
        });
        return data;
    }

}
