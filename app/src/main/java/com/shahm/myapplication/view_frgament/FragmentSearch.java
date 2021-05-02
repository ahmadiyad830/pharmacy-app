package com.shahm.myapplication.view_frgament;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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
    private int increment = 1;
    private int currentDrug = 10000;
    private AdapterMedicines adapter;
    private List<Medicines> listMed = new ArrayList<>();
    private VMMedicines viewModel;
    private SearchView searchView;
    private CharSequence title = "";
    private MenuItem setTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        doInitialization();
        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.storeToolbar);
        return binding.getRoot();
    }

    private void doInitialization() {
        adapter = new AdapterMedicines(listMed, this);
        binding.recyclerMedicines.setLayoutManager(new LinearLayoutManager(requireActivity()));

        binding.recyclerMedicines.setHasFixedSize(true);
        binding.recyclerMedicines.setAdapter(adapter);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(VMMedicines.class);
        getMedicines();
        getAllMed();
        binding.camSearch.setOnClickListener(v -> {
            camSearch();
        });
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
    }

    private void getMedicines() {
        viewModel.getMedicines(increment).observe(getViewLifecycleOwner(), medicines -> {
            if (listMed != null ) {
                int oldCount = listMed.size();
                listMed.addAll(medicines);
                adapter.notifyItemRangeInserted(oldCount, listMed.size());
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.store_menu, menu);
        searchView = (SearchView) menu.findItem(R.id.search_menu_store).getActionView();
        title = menu.findItem(R.id.search_menu_store).getTitle();
        setTitle = menu.findItem(R.id.search_menu_store);

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


    private void camSearch() {
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
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                setTitle.setTitle("");
                setTitle.setTitle(result.getContents());
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