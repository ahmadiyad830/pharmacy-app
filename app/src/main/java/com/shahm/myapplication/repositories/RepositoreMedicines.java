package com.shahm.myapplication.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.network.ApiClint;
import com.shahm.myapplication.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoreMedicines {
    private static final String TAG = "RepositoreMedicines";
    private ApiService service;

    public RepositoreMedicines() {
        service = ApiClint.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<Medicines>> getListMedicines(int page) {
        MutableLiveData<List<Medicines>> data = new MutableLiveData<>();
        service.getListMedicines(page).enqueue(new Callback<List<Medicines>>() {
            @Override
            public void onResponse(@NonNull Call<List<Medicines>> call, @NonNull Response<List<Medicines>> response) {
                try {
                    data.setValue(response.body());
                } catch (Exception e) {
                    Log.d(TAG, "onResponse: "+response.code());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Medicines>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
        return data;
    }




}
