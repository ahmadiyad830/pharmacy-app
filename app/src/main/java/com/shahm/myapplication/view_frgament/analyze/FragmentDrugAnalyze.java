package com.shahm.myapplication.view_frgament.analyze;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.shahm.myapplication.R;
import com.shahm.myapplication.adapter.ARAnalyzeDrug;
import com.shahm.myapplication.databinding.FragmentDrugAnalyzeBinding;
import com.shahm.myapplication.model.AnalyzeDrug;
import com.shahm.myapplication.viewmodel.VMAnalyzeDrug;

import java.util.ArrayList;
import java.util.List;

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

        loadDrug();
        return binding.getRoot();
    }

    private void loadDrug() {
        binding.recycler.setAdapter(adapter);
        viewModel.getSalesPH("panadol","1").observe(getViewLifecycleOwner(),objectDrug -> {
            if (objectDrug!=null){
                int old = listDrug.size();
                listDrug.addAll(objectDrug);
               adapter.notifyItemRangeInserted(old,listDrug.size());
            }
        });
    }
}