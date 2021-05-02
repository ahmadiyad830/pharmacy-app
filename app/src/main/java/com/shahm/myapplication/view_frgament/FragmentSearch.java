package com.shahm.myapplication.view_frgament;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.shahm.myapplication.R;
import com.shahm.myapplication.adapter.AdapterMedicines;
import com.shahm.myapplication.databinding.FragmentSearchBinding;
import com.shahm.myapplication.listeners.OnMedClick;
import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.view_activity.ActivityAdd;
import com.shahm.myapplication.view_activity.ActivityDetails;
import com.shahm.myapplication.viewmodel.VMMedicines;

import java.util.ArrayList;
import java.util.List;


public class FragmentSearch extends Fragment implements OnMedClick {

    private FragmentSearchBinding binding;
    private int increment = 1;
    private int currentDrug = 10000;
    private AdapterMedicines adapter;
    private List<Medicines> listMed = new ArrayList<>();
    private VMMedicines viewModel;
    private String query;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        doInitialization();



        return binding.getRoot();
    }

    private void doInitialization() {
        adapter = new AdapterMedicines(listMed, this);
        binding.recyclerMedicines.setLayoutManager(new LinearLayoutManager(requireActivity()));

        binding.recyclerMedicines.setLayoutManager(new WrapContentLinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerMedicines.setHasFixedSize(true);
        binding.recyclerMedicines.setAdapter(adapter);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(VMMedicines.class);
        binding.fabSearchRecycler.setOnClickListener(v -> {
             query = binding.inputSearch.getText().toString().trim();
            getAllMed();
        });

        binding.camSearch.setOnClickListener(v -> {
            camSearch();
        });
        binding.setIsEmpty(true);

    }

    private void getAllMed() {
        binding.recyclerMedicines.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(1)) {
                    if (increment <= currentDrug) {
                        increment++;
                        getMedicines();
                    }

                }
            }
        });
        getMedicines();
    }

    private List<Medicines> getMedicines() {
        viewModel.getMedicines(increment).observe(getViewLifecycleOwner(), medicines -> {
            for (Medicines model:medicines){
                if (model.getName().contains(query)){
                    int oldCount = listMed.size();
                    listMed.addAll(medicines);
                    adapter.notifyItemRangeInserted(oldCount, listMed.size());
                }
            }
        });
        return listMed;
    }


    private void camSearch() {
        IntentIntegrator intent = IntentIntegrator.forSupportFragment(this);
        intent.setPrompt("for flags");
        intent.setBeepEnabled(true);
        intent.setOrientationLocked(true);
        intent.setCaptureActivity(ActivityAdd.Capture.class);
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setCameraId(0);  // Use a specific camera of the device
        intent.setBarcodeImageEnabled(true);
        intent.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                binding.inputSearch.setText("");
                binding.inputSearch.setText(result.getContents().trim());
            }
        }
    }

    @Override
    public void onItemClick(Medicines model) {
        Intent intent = new Intent(requireActivity(), ActivityDetails.class);
        intent.putExtra("medicines", model);
        startActivity(intent);
    }
    public class WrapContentLinearLayoutManager extends LinearLayoutManager {

        public WrapContentLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        //... constructor
        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e) {
                Log.e("TAG", "meet a IOOBE in RecyclerView");
            }
        }
    }
}