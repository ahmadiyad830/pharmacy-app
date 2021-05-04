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
import com.shahm.myapplication.adapter.AdapterMedHave;
import com.shahm.myapplication.databinding.FragmentMedicinesHaveBinding;
import com.shahm.myapplication.listeners.OnMedHaveClick;
import com.shahm.myapplication.model.MedicinesHave;
import com.shahm.myapplication.view_activity.ActivityDetails;
import com.shahm.myapplication.viewmodel.VMMedicinesHave;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMedicinesHave#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMedicinesHave extends Fragment implements OnMedHaveClick {
    private FragmentMedicinesHaveBinding binding;
    private VMMedicinesHave viewModel;
    private final List<MedicinesHave> listMed = new ArrayList<>();
    private AdapterMedHave adapter;
    private int increment=1,currentDrug=10000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_medicines_have, container, false);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(VMMedicinesHave.class);
        binding.recyclerMedicines.setHasFixedSize(true);
        binding.recyclerMedicines.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapter = new AdapterMedHave(listMed, this);
        doInitialization();
        return binding.getRoot();
    }

    private void doInitialization() {
        binding.recyclerMedicines.setAdapter(adapter);
        binding.recyclerMedicines.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(1)){
                    if (increment <= currentDrug) {
                        increment++;
                        loadListMedicinesHave();
                    }
                }
            }
        });
        loadListMedicinesHave();
    }

    private void loadListMedicinesHave() {
        int oldCount = listMed.size();
        viewModel.getMedicinesHave(increment).observe(getViewLifecycleOwner(), medicinesHaves -> {
            if (listMed != null) {
                listMed.addAll(medicinesHaves);
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onMedHavClick(MedicinesHave model) {
        Intent intent = new Intent(requireActivity(), ActivityDetails.class);
//        intent.putExtra("medicines", model);
        intent.putExtra("listMed",model.listMedHave());
        startActivity(intent);
    }
}