package com.shahm.myapplication.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shahm.myapplication.network.ApiClint;
import com.shahm.myapplication.network.ApiService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoSaleDrug {
    private ApiService service;
    private String TAG = "RepoSaleDrug";

    public RepoSaleDrug() {
        service = ApiClint.getRetrofit().create(ApiService.class);
    }
    public LiveData<Void> postSale(String itemId,String isSachet,String price){
        MutableLiveData<Void> data = new MutableLiveData<>();
        service.postSaleDrug(itemId,price,isSachet).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    try {
                        Log.d(TAG, "onResponse: "+response.errorBody().string()+"\n"+response.code());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return data;
    }
}
