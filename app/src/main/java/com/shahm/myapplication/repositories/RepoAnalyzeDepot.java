package com.shahm.myapplication.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shahm.myapplication.model.AnalyzeDepot;
import com.shahm.myapplication.network.ApiClint;
import com.shahm.myapplication.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoAnalyzeDepot {
    private static final String TAG = "RepoAnalyzeDepot";
    private ApiService service;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public RepoAnalyzeDepot() {
        service = ApiClint.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<AnalyzeDepot>> listDepot(String depotName, String monthNum) {
        MutableLiveData<List<AnalyzeDepot>> data = new MutableLiveData<>();
        service.getDepot(depotName, monthNum).enqueue(new Callback<List<AnalyzeDepot>>() {
            @Override
            public void onResponse(Call<List<AnalyzeDepot>> call, Response<List<AnalyzeDepot>> response) {
                if (!response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+response.message()+"\n"+response.code());
                }
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AnalyzeDepot>> call, Throwable t) {

            }
        });
        return data;
    }

}
