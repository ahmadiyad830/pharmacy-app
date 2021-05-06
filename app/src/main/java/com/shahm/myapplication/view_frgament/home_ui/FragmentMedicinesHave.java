package com.shahm.myapplication.view_frgament.home_ui;

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
import com.shahm.myapplication.databinding.FragmentMedicinesHaveBinding;
import com.shahm.myapplication.listeners.OnMedClick;
import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.viewmodel.VMMedicinesHave;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMedicinesHave#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMedicinesHave extends Fragment implements OnMedClick {
    private FragmentMedicinesHaveBinding binding;
    private VMMedicinesHave viewModel;
    private final List<Medicines> listMed = new ArrayList<>();
    private AdapterMedicines adapterV2;
    private int increment=1,currentDrug=10000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_medicines_have, container, false);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(VMMedicinesHave.class);
        binding.recyclerMedicines.setHasFixedSize(true);
        binding.recyclerMedicines.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapterV2 = new AdapterMedicines(listMed, this);
        doInitialization();
        return binding.getRoot();
    }

    private void doInitialization() {
        binding.recyclerMedicines.setAdapter(adapterV2);
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
        viewModel.getMedicinesHave(increment).observe(getViewLifecycleOwner(), medicinesHaves -> {
            if (medicinesHaves != null) {
                int oldCount = listMed.size();
                listMed.addAll(medicinesHaves);
                adapterV2.notifyItemRangeInserted(oldCount, listMed.size());

            }
        });

    }

    @Override
    public void clickDetails(Medicines model) {

    }

    @Override
    public void clickSale(Medicines model) {

    }
}