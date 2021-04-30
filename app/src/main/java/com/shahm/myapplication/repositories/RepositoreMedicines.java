package com.shahm.myapplication.repositories;

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
    private ApiService apiService;

    public RepositoreMedicines() {
        apiService = ApiClint.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<Medicines>> getListMedicines(int page) {
        MutableLiveData<List<Medicines>> data = new MutableLiveData<>();
       apiService.getListMedicines(page).enqueue(new Callback<List<Medicines>>() {
           @Override
           public void onResponse(@NonNull Call<List<Medicines>> call,@NonNull  Response<List<Medicines>> response) {
               data.setValue(response.body());
           }

           @Override
           public void onFailure(@NonNull Call<List<Medicines>> call,@NonNull  Throwable t) {

           }
       });
       return data;
    }
}
