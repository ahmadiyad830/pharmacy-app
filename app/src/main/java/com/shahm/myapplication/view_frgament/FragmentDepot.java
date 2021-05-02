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

import com.shahm.myapplication.R;
import com.shahm.myapplication.adapter.ARDepot;
import com.shahm.myapplication.databinding.FragmentDepotBinding;
import com.shahm.myapplication.listeners.OnSalesClick;
import com.shahm.myapplication.model.Depot;
import com.shahm.myapplication.model.SalesMed;
import com.shahm.myapplication.viewmodel.VMDepot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDepot#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDepot extends Fragment implements AdapterView.OnItemSelectedListener, OnSalesClick {
    private FragmentDepotBinding binding;
    private VMDepot viewModel;
    private int increment = 1;
    private int currentDrug = 10000;
    private List<Depot> listDepot = new ArrayList<>();
    private SearchView searchView;
    private ARDepot adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_depot, container, false);
        doInitialization();

        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.storeToolbar);
        return binding.getRoot();
    }

    private void doInitialization() {
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(VMDepot.class);

        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(requireActivity()));
//        insert adapter
        adapter = new ARDepot(listDepot);
        binding.recycler.setAdapter(adapter);
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


    private void getListDepot(String name, String monthNum) {
        viewModel.getDepot(name, monthNum).observe(getViewLifecycleOwner(), depots -> {
            if (depots != null && depots.size() > 1) {
                int oldSize = depots.size();
                listDepot.addAll(depots);
                adapter.notifyItemRangeInserted(oldSize, listDepot.size());
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
        if (position==0){
            getListDepot("حكمة", "1");
        }else getListDepot("طنوس", "1");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(SalesMed model) {

    }
}