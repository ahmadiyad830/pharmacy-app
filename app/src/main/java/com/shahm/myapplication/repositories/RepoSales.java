package com.shahm.myapplication.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shahm.myapplication.model.SalesMed;
import com.shahm.myapplication.network.ApiClint;
import com.shahm.myapplication.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoSales {
    private ApiService service;

    public RepoSales() {
        service = ApiClint.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<SalesMed>> getSales(int page) {
        MutableLiveData<List<SalesMed>> data = new MutableLiveData<>();
        service.getSales(page).enqueue(new Callback<List<SalesMed>>() {
            @Override
            public void onResponse(Call<List<SalesMed>> call, Response<List<SalesMed>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<SalesMed>> call, Throwable t) {

            }
        });
        return data;
    }
}
