package com.shahm.myapplication.view_frgament;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.shahm.myapplication.R;
import com.shahm.myapplication.adapter.AdapterMedicines;
import com.shahm.myapplication.databinding.FragmentSearchBinding;
import com.shahm.myapplication.listeners.OnMedClick;
import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.view_activity.ActivityDetails;
import com.shahm.myapplication.viewmodel.VMMedicines;

import java.util.ArrayList;
import java.util.List;


public class FragmentSearch extends Fragment implements OnMedClick {

    private FragmentSearchBinding binding;
    private int currentDrug = 1, totaleAvalabeDrug = 1;
    private AdapterMedicines adapter;
    private List<Medicines> listMed = new ArrayList<>();
    private VMMedicines viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        doInitialization();
        binding.inputSearch.addTextChangedListener(searchInList);
        return binding.getRoot();
    }

    private void doInitialization() {
        adapter = new AdapterMedicines(listMed, this);
        binding.recyclerMedicines.setLayoutManager(new LinearLayoutManager(requireActivity()));

        binding.recyclerMedicines.setHasFixedSize(true);
        binding.recyclerMedicines.setAdapter(adapter);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(VMMedicines.class);
        viewModel.getMedicines(1).observe(getViewLifecycleOwner(), medicines -> {
            if (listMed != null){
                int oldCount = listMed.size();
                listMed.addAll(medicines);
                adapter.notifyItemRangeInserted(oldCount, listMed.size());
            }
        });
        binding.camSearch.setOnClickListener(v -> {
            camSearch();
        });
    }

    private final TextWatcher searchInList = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String textInput = s.toString().trim();
            boolean isEmpty = textInput.isEmpty();
            if (before > 0 && before > s.length() && !isEmpty) {
                currentDrug = 1;
                totaleAvalabeDrug = 1;
                listMed.clear();
                searchDrug(textInput);
                adapter.notifyDataSetChanged();
            } else if (isEmpty) {
                listMed.clear();
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            String textInput = s.toString().trim();
            boolean isEmpty = s.toString().trim().isEmpty();
            binding.setIsEmpty(true);
            binding.fabSearchRecycler.setOnClickListener(v -> {
                if (!isEmpty) {
                    currentDrug = 1;
                    totaleAvalabeDrug = 1;
//                    listMed.clear();
                    searchDrug(textInput);
                    adapter.notifyDataSetChanged();
                }
            });
            binding.removeTextSearch.setOnClickListener(v -> {
                if (!isEmpty) {
                    s.clear();
                    listMed.clear();
                    adapter.notifyDataSetChanged();
                }
            });
        }

    };

    private void searchDrug(String textInput) {
        for (int i = 0; i < listMed.size(); i++) {
            if (listMed.get(i).getName().equals(textInput)) {
                Toast.makeText(requireActivity(), listMed.get(i).getName(), Toast.LENGTH_SHORT).show();
                listMed.clear();
                listMed.add(listMed.get(i));
                adapter.notifyDataSetChanged();
            }
        }
    }
    private void camSearch(){
        IntentIntegrator intent = IntentIntegrator.forSupportFragment(this);
        intent.setPrompt("for flags");
        intent.setBeepEnabled(true);
        intent.setOrientationLocked(true);
        intent.setCaptureActivity(FragmentAdd.Capture.class);
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setCameraId(0);  // Use a specific camera of the device
        intent.setBarcodeImageEnabled(true);
        intent.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null){
            if (result.getContents()!=null){
                binding.inputSearch.setText("");
                binding.inputSearch.setText(result.getContents());
                searchDrug(binding.inputSearch.getText().toString().trim());
            }
        }
    }

    @Override
    public void onItemClick(Medicines model) {
        Intent intent = new Intent(requireActivity(), ActivityDetails.class);
        intent.putExtra("medicines", model);
        startActivity(intent);
    }
}