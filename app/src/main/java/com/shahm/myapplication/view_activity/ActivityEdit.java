package com.shahm.myapplication.view_activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ActivityEditBinding;
import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.viewmodel.VMMedicines;

public class ActivityEdit extends AppCompatActivity {
    private ActivityEditBinding binding;
    private Medicines model;
    private VMMedicines viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(VMMedicines.class);
        if (getIntent() != null) {
            model = (Medicines) getIntent().getSerializableExtra("model");
            if (model!=null)
                binding.setModel(model);
        }
        binding.btnUpdate.setOnClickListener(v -> {
            String name = binding.edtName.getText().toString();
            String id = binding.txtId.getText().toString();
            String scientific = binding.edtScientific.getText().toString();
            String concentration = binding.edtConcentration.getText().toString();
            String dosageform = binding.edtDosageform.getText().toString();
            String notes = binding.edtNote.getText().toString();
            String store = binding.edtStore.getText().toString();
            String sachet = binding.edtSachet.getText().toString();
            String location = binding.edtLocation.getText().toString();
            String quantity = binding.edtQuantity.getText().toString();
            viewModel.postUpdate("7250", name, scientific, concentration, dosageform, notes, store, sachet, location,quantity).observe(this, aVoid -> {
                Toast.makeText(this, "success update", Toast.LENGTH_SHORT).show();
            });

        });
        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }

}