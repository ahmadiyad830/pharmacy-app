package com.shahm.myapplication.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shahm.myapplication.model.SalesPharmacy;
import com.shahm.myapplication.network.ApiClint;
import com.shahm.myapplication.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoPHSales {
    private ApiService service;

    public RepoPHSales() {
        service = ApiClint.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<SalesPharmacy>> getSales(int page) {
        MutableLiveData<List<SalesPharmacy>> data = new MutableLiveData<>();
        service.getSales(page).enqueue(new Callback<List<SalesPharmacy>>() {
            @Override
            public void onResponse(Call<List<SalesPharmacy>> call, Response<List<SalesPharmacy>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<SalesPharmacy>> call, Throwable t) {

            }
        });
        return data;
    }
}
