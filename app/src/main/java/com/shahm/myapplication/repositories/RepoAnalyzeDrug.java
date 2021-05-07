package com.shahm.myapplication.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shahm.myapplication.model.AnalyzeDrug;
import com.shahm.myapplication.network.ApiClint;
import com.shahm.myapplication.network.ApiService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoAnalyzeDrug {
    private static final String TAG = "RepoAnalyzeDrug";
    private ApiService service;

    public RepoAnalyzeDrug() {
        service = ApiClint.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<AnalyzeDrug>> getSalesPH(String name, String monthNum) {
        MutableLiveData<List<AnalyzeDrug>> data = new MutableLiveData<>();
        service.getSalesPharmacy(name, monthNum).enqueue(new Callback<List<AnalyzeDrug>>() {
            @Override
            public void onResponse(Call<List<AnalyzeDrug>> call, Response<List<AnalyzeDrug>> response) {
                if (!response.isSuccessful()) {
                    try {
                        Log.d(TAG, "onResponse: " + response.errorBody().string() + "\n" + response.code());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<AnalyzeDrug>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                t.printStackTrace();
            }
        });
        return data;
    }
}
