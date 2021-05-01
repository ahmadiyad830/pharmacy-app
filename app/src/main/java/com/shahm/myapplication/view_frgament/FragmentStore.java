package com.shahm.myapplication.view_frgament;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shahm.myapplication.R;
import com.shahm.myapplication.adapter.ARFilterSales;
import com.shahm.myapplication.databinding.FragmentStoreBinding;
import com.shahm.myapplication.listeners.OnSalesClick;
import com.shahm.myapplication.model.SalesMed;
import com.shahm.myapplication.viewmodel.VMSales;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentStore#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentStore extends Fragment implements AdapterView.OnItemSelectedListener, OnSalesClick {
    private FragmentStoreBinding binding;
    private VMSales viewModel;
    private int increment = 1;
    private int currentDrug = 10000;
    private ARFilterSales adapter;
    private List<SalesMed> listSales = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        doInitialization(inflater, container);


        return binding.getRoot();
    }

    private void doInitialization(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store, container, false);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(VMSales.class);

        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapter = new ARFilterSales(listSales, this);
        binding.recycler.setAdapter(adapter);
        listenerRecycler();
        getListSales(increment);
//        filterList();
    }

    private void listenerRecycler() {
        binding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(1)) {
                    if (increment <= currentDrug) {
                        increment++;
                        getListSales(increment);
                    }

                }
            }
        });
    }


    private void getListSales(int increment) {
        viewModel.getSales(increment).observe(getViewLifecycleOwner(), salesMed -> {
            if (listSales != null) {
                listSales.addAll(salesMed);
                adapter.setAllListMed(listSales);
                int oldSize = listSales.size();
                adapter.notifyItemRangeChanged(oldSize, listSales.size());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        spinnerSort();
    }

    private void spinnerSort() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireActivity(), R.array.spinner_sort_by, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerSortBy.setAdapter(adapter);
        binding.spinnerSortBy.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sort = parent.getItemAtPosition(position).toString();
        switch (sort) {
            case "month":
//                sortByMonth();
                break;
            case "three month":
//                sortByThreeMonth();
                break;
            case "year":
//                sortByYear();
                break;
            default:
//                sortByDay();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(SalesMed model) {

    }

}
