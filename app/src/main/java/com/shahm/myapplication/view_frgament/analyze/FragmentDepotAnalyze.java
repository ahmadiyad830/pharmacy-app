package com.shahm.myapplication.view_frgament.analyze;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.shahm.myapplication.R;
import com.shahm.myapplication.adapter.ARAnalyzeDepot;
import com.shahm.myapplication.databinding.DetailsDepotBinding;
import com.shahm.myapplication.databinding.FragmentDepotAnalyzeBinding;
import com.shahm.myapplication.listeners.OnDepotClick;
import com.shahm.myapplication.model.AnalyzeDepot;
import com.shahm.myapplication.viewmodel.VMAnalyzeDepot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDepotAnalyze#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDepotAnalyze extends Fragment implements OnDepotClick<AnalyzeDepot> {
    private FragmentDepotAnalyzeBinding binding;
    private VMAnalyzeDepot viewModel;
    private List<AnalyzeDepot> listAnalyzeDepot = new ArrayList<>();
    private ARAnalyzeDepot adapter;
    private BottomSheetDialog episodesBottomSheetDialog;
    private DetailsDepotBinding bindingDetails;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_depot_analyze, container, false);
        doInitialization();
        binding.search.setOnClickListener(v -> {
            String storeName = binding.storeName.getText().toString().trim();
            String monthNum = binding.monthNum.getText().toString().trim();
            if (storeName != null && !storeName.isEmpty()) {
                if (monthNum != null && !monthNum.isEmpty()) {
                    listAnalyzeDepot.clear();
                    getListDepot(storeName, monthNum);
                    adapter.notifyDataSetChanged();
                } else
                    binding.monthNum.setError("month");
            } else
                binding.storeName.setError("store name");
        });
        return binding.getRoot();
    }

    private void doInitialization() {
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(VMAnalyzeDepot.class);
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapter = new ARAnalyzeDepot(listAnalyzeDepot, this);
        binding.recycler.setAdapter(adapter);

    }

    private void getListDepot(String name, String monthNum) {
        viewModel.getDepot(name, monthNum).observe(getViewLifecycleOwner(), depots -> {
            if (depots != null) {

                int oldSize = depots.size();
                listAnalyzeDepot.addAll(depots);
                adapter.notifyItemRangeInserted(oldSize, listAnalyzeDepot.size());
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (episodesBottomSheetDialog == null) {
            episodesBottomSheetDialog = new BottomSheetDialog(requireActivity());
            bindingDetails = DataBindingUtil.inflate(LayoutInflater.from(requireActivity()),
                    R.layout.details_depot, view.findViewById(R.id.container_details_depot), false);
            episodesBottomSheetDialog.setContentView(bindingDetails.getRoot());
            FrameLayout frameLayout = episodesBottomSheetDialog.findViewById(
                    com.google.android.material.R.id.design_bottom_sheet);
            if (frameLayout != null) {
                BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(frameLayout);
                bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        }


    }

    @Override
    public void onItemClick(AnalyzeDepot model) {
        episodesBottomSheetDialog.show();
    }
}