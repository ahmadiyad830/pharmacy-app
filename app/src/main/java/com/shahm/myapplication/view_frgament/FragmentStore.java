package com.shahm.myapplication.view_frgament;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
    private SearchView searchView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        doInitialization(inflater, container);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.storeToolbar);

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
        getListSales();
//        filterList();
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.store_menu, menu);
        searchView = (SearchView) menu.findItem(R.id.search_menu_store).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        searchView.setOnCloseListener(() -> {
            Toast.makeText(requireActivity(), "true", Toast.LENGTH_SHORT).show();
            return false;
        });
        searchView.setSubmitButtonEnabled(true);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        super.onCreateOptionsMenu(menu, inflater);

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
