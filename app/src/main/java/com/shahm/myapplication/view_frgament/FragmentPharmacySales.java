package com.shahm.myapplication.view_frgament;

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
import com.shahm.myapplication.adapter.ARPHSales;
import com.shahm.myapplication.databinding.FragmentPharmacySalesBinding;
import com.shahm.myapplication.listeners.OnSalesClick;
import com.shahm.myapplication.model.SalesPharmacy;
import com.shahm.myapplication.viewmodel.VMPHSales;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPharmacySales#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPharmacySales extends Fragment implements OnSalesClick {

    private FragmentPharmacySalesBinding binding;
    private VMPHSales viewModel;
    private int increment = 1;
    private int currentDrug = 10000;
    private ARPHSales adapter;
    private List<SalesPharmacy> listSales = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        doInitialization(inflater, container);
        
        return binding.getRoot();
    }

    private void doInitialization(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pharmacy_sales, container, false);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(VMPHSales.class);

        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapter = new ARPHSales(listSales, this);
        binding.recycler.setAdapter(adapter);

        listenerRecycler();
        getListSales();
    }


    private void listenerRecycler() {
        binding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(1)) {
                    if (increment <= currentDrug) {
                        increment++;
                        getListSales();
                    }

                }
            }
        });
    }


    private void getListSales() {
        viewModel.getSales(increment).observe(getViewLifecycleOwner(), salesMed -> {
            if (listSales != null) {
                int oldSize = listSales.size();
                listSales.addAll(salesMed);
                adapter.notifyItemRangeInserted(oldSize, listSales.size());

            }
        });
    }

    @Override
    public void onItemClick(SalesPharmacy model) {

    }
}
