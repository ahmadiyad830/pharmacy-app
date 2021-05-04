package com.shahm.myapplication.view_frgament.analyze;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.shahm.myapplication.R;
import com.shahm.myapplication.adapter.ARAnalyzeDrug;
import com.shahm.myapplication.databinding.FragmentDrugAnalyzeBinding;
import com.shahm.myapplication.model.AnalyzeDrug;
import com.shahm.myapplication.network.ApiService;
import com.shahm.myapplication.viewmodel.VMAnalyzeDrug;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDrugAnalyze#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDrugAnalyze extends Fragment {
    private FragmentDrugAnalyzeBinding binding;
    private List<AnalyzeDrug> listDrug = new ArrayList<>();
    private ARAnalyzeDrug adapter;
    private VMAnalyzeDrug viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drug_analyze, container, false);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(VMAnalyzeDrug.class);
        adapter = new ARAnalyzeDrug(listDrug);
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.recycler.setAdapter(adapter);
//        loadDrug();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.blacktools.io/sapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        Call<List<AnalyzeDrug>> listDrug = service.getSalesPharmacy("panadol","3");
       listDrug.enqueue(new Callback<List<AnalyzeDrug>>() {
           @Override
           public void onResponse(Call<List<AnalyzeDrug>> call, Response<List<AnalyzeDrug>> response) {
               for (AnalyzeDrug model:response.body()){
                   Toast.makeText(requireActivity(), model.getName(), Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<List<AnalyzeDrug>> call, Throwable t) {

           }
       });


        return binding.getRoot();
    }

    private void loadDrug() {
        viewModel.getSalesPH("panadol","1").observe(getViewLifecycleOwner(),objectDrug -> {
            Toast.makeText(requireActivity(), objectDrug.get(0).getName(), Toast.LENGTH_SHORT).show();
            if (objectDrug!=null){
                int old = listDrug.size();
                listDrug.addAll(objectDrug);
               adapter.notifyItemRangeInserted(old,listDrug.size());
            }
        });
    }
}