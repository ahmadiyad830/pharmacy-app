package com.shahm.myapplication.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.network.ApiClint;
import com.shahm.myapplication.network.ApiService;
import com.shahm.myapplication.responses.ResponseMedicines;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

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
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Medicines>> call, @NonNull Throwable t) {

            }
        });
        return data;
    }

    public LiveData<List<Medicines>> postMedicines(@Body Medicines model) {
        MutableLiveData<List<Medicines>> data = new MutableLiveData<>();
//        service.postMed(model).enqueue(new Callback<Medicines>() {
//            @Override
//            public void onResponse(Call<Medicines> call, Response<Medicines> response) {
//                data.setValue(response.body());
//            }

//            @Override
//            public void onFailure(Call<Medicines> call, Throwable t) {

//            }
//        });
        return data;
    }
    public LiveData<ResponseMedicines> postData(Medicines model){
        MutableLiveData<ResponseMedicines> data = new MutableLiveData<>();
        service.postData(model).enqueue(new Callback<ResponseMedicines>() {
            @Override
            public void onResponse(Call<ResponseMedicines> call, Response<ResponseMedicines> response) {
                if (!response.isSuccessful())
                    Log.d(TAG, "onResponse: "+response.code()+"\n"+response.errorBody());
                data.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseMedicines> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return data;
    }
}
