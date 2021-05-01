package com.shahm.myapplication.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.network.ApiClint;
import com.shahm.myapplication.network.ApiService;
import com.shahm.myapplication.repositories.RepositoreMedicines;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VMMedicines extends ViewModel {
    private RepositoreMedicines repositoreMedicines;
    private MutableLiveData<List<Medicines>> data;

    public VMMedicines() {
//        data = new MutableLiveData<>();
        repositoreMedicines = new RepositoreMedicines();
    }


    public LiveData<List<Medicines>> getMedicines(int page){
        return repositoreMedicines.getListMedicines(page);
    }
    public MutableLiveData<List<Medicines>> callApi(int id) {
        ApiService service = ApiClint.getRetrofit().create(ApiService.class);
        Call<List<Medicines>> call = service.getMed(id);
        call.enqueue(new Callback<List<Medicines>>() {
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
}
