package com.shahm.myapplication.view_frgament;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.FragmentStoreBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentStore#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentStore extends Fragment implements AdapterView.OnItemSelectedListener {
    private FragmentStoreBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store, container, false);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.storeToolbar);
        return binding.getRoot();
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.store_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_best_selling) {
//            sortByBest();
        } else if (id == R.id.menu_Less_selling) {
//            sortByLess();
        } else if (id == R.id.toolbar_store_setting) {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_fragmentStore_to_fragmentSetting);
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sort = parent.getItemAtPosition(position).toString();
        switch (sort) {
            case "month":
                sortByMonth();
                break;
            case "three month":
                sortByThreeMonth();
                break;
            case "year":
                sortByYear();
                break;
            default:
                sortByDay();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void sortByDay() {

    }

    private void sortByYear() {

    }

    private void sortByThreeMonth() {

    }

    private void sortByMonth() {

    }
}
