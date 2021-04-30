package com.shahm.myapplication.view_frgament;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.shahm.myapplication.R;
import com.shahm.myapplication.adapter.AdapterMedHave;
import com.shahm.myapplication.databinding.FragmentMedicinesHaveBinding;
import com.shahm.myapplication.listeners.OnMedHaveClick;
import com.shahm.myapplication.model.MedicinesHave;
import com.shahm.myapplication.view_activity.ActivityDetails;
import com.shahm.myapplication.viewmodel.ViewModelMedicinesHave;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMedicinesHave#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMedicinesHave extends Fragment implements OnMedHaveClick {
    private FragmentMedicinesHaveBinding binding;
    private ViewModelMedicinesHave viewModel;
    private final List<MedicinesHave> listMed = new ArrayList<>();
    private AdapterMedHave adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_medicines_have, container, false);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(ViewModelMedicinesHave.class);
        binding.recyclerMedicines.setHasFixedSize(true);
        adapter = new AdapterMedHave(listMed, this);
        doInitialization();
        loadListMedicinesHave();
        return binding.getRoot();
    }

    private void doInitialization() {
        binding.recyclerMedicines.setAdapter(adapter);
    }

    private void loadListMedicinesHave() {
        viewModel.getMedicinesHave(1).observe(getViewLifecycleOwner(), medicinesHaves -> {
            if (listMed != null) {
                binding.recyclerMedicines.setVisibility(View.VISIBLE);
                int oldCount = listMed.size();
                listMed.addAll(medicinesHaves);
                adapter.notifyItemRangeInserted(oldCount, listMed.size());
//                adapter.notifyDataSetChanged();
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