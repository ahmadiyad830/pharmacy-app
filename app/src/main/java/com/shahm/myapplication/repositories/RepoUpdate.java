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

public class RepoUpdate {
    private ApiService service;
    private static final String TAG = "RepoUpdate";
    public RepoUpdate() {
        service = ApiClint.getRetrofit().create(ApiService.class);
    }
    public LiveData<Void> postUpdate(String id, String name,
                                     String scientific
            , String concentration, String dosageform, String notes, String store, String sachet, String slocation, String squantity) {
        MutableLiveData<Void> data = new MutableLiveData<>();
        service.postUpdateDrug(id, name, scientific, concentration, dosageform, notes, store, sachet, slocation, squantity).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    try {
                        Log.d(TAG, "onResponse: " + response.code() + "\nerror \t" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return data;
    }
}
