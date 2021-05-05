package com.shahm.myapplication.view_frgament.home_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shahm.myapplication.R;
import com.shahm.myapplication.adapter.AdapterMedicines;
import com.shahm.myapplication.databinding.FragmentAllMedicinesBinding;
import com.shahm.myapplication.listeners.OnMedClick;
import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.view_activity.ActivityAdd;
import com.shahm.myapplication.view_activity.ActivityDetails;
import com.shahm.myapplication.viewmodel.VMMedicines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAllMedicines#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAllMedicines extends Fragment implements OnMedClick {
    private FragmentAllMedicinesBinding binding;
    private VMMedicines viewModel;
    private int currentDrug = 474, increment = 1;
    private AdapterMedicines adapter;


    private final List<Medicines> listMedicines = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_medicines, container, false);
        doInitialization();
        getMedicines(increment);
//        postMedicine();
        return binding.getRoot();
    }

    private void doInitialization() {
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()))
                .get(VMMedicines.class);
        adapter = new AdapterMedicines(listMedicines, this);
        binding.recyclerMedicines.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.recyclerMedicines.setHasFixedSize(true);
        binding.recyclerMedicines.setAdapter(adapter);
        binding.recyclerMedicines.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(1)) {
                    if (increment <= currentDrug) {
                        increment++;
                        getMedicines(increment);
                    }

                }
                if (recyclerView.canScrollVertically(-1)) {
                    binding.fabScrollRecycler.setVisibility(View.VISIBLE);
                    binding.fabScrollRecycler.setOnClickListener(v -> recyclerView.scrollToPosition(0));
                } else {
                    binding.fabScrollRecycler.setVisibility(View.GONE);
                }
            }
        });
    }

    private List<Medicines> getMedicines(int page) {
        toggleLoading();
        viewModel.getMedicines(page).observe(getViewLifecycleOwner(), medicines -> {
            toggleLoading();
            if (medicines != null) {
                binding.recyclerMedicines.setVisibility(View.VISIBLE);
                int oldCount = listMedicines.size();
                listMedicines.addAll(medicines);
                adapter.notifyItemRangeInserted(oldCount, listMedicines.size());
//                adapter.notifyDataSetChanged();
            }
        });
        return listMedicines;
    }

    private void toggleLoading() {
        if (currentDrug == 1) {
            binding.setIsLoading(binding.getIsLoading() == null || !binding.getIsLoading());
        } else {
            binding.setIsLoadingMore(binding.getIsLoadingMore() == null || !binding.getIsLoadingMore());
        }
    }



    @Override
    public void clickDetails(Medicines model) {
        Intent intent = new Intent(requireActivity(), ActivityDetails.class);
        intent.putExtra("listMed", model.listMed());
        startActivity(intent);
    }

    @Override
    public void clickSale(Medicines model) {
        Intent intent = new Intent(requireActivity(), ActivityAdd.class);
        intent.putExtra("model",model);
        intent.putExtra("type","sale");
        startActivity(intent);
    }
}