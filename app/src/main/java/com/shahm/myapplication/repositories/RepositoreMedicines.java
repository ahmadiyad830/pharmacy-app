package com.shahm.myapplication.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.network.ApiClint;
import com.shahm.myapplication.network.ApiService;

import java.io.IOException;
import java.util.HashMap;
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

    public LiveData<Void> postMap(HashMap<String, String> modelMap) {
        MutableLiveData<Void> data = new MutableLiveData<>();
        service.postMap(modelMap).enqueue(new Callback<Void>() {
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

    public LiveData<Void> postData(Medicines model) {
        MutableLiveData<Void> data = new MutableLiveData<>();
        service.postData(model).enqueue(new Callback<Void>() {
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

    public LiveData<Void> postField(String name,
                                    String scientific, String concentration, String dosageform, String notes, String store, String sachet, String slocation, String squantity) {
        MutableLiveData<Void> data = new MutableLiveData<>();
        service.postNewDrug(name, scientific, concentration, dosageform, notes, store, sachet, slocation, squantity).enqueue(new Callback<Void>() {
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

    public LiveData<Void> postUpdate(String id,String name,
                                    String scientific, String concentration, String dosageform, String notes, String store, String sachet, String slocation, String squantity) {
        MutableLiveData<Void> data = new MutableLiveData<>();
        service.postUpdateDrug(id,name, scientific, concentration, dosageform, notes, store, sachet, slocation, squantity).enqueue(new Callback<Void>() {
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
