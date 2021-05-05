package com.shahm.myapplication.view_activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ActivityDetailsBinding;
import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.viewmodel.VMMedicines;

import java.util.Objects;

public class ActivityDetails extends AppCompatActivity {
    private ActivityDetailsBinding binding;
    private VMMedicines viewModel;
    private Medicines model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(VMMedicines.class);

        String[] intentModel = getIntent().getStringArrayExtra("listMed");
        if (intentModel != null) {
            binding.setId(intentModel[0]);
            binding.setBarcode(intentModel[1]);
            binding.setName(intentModel[2]);
            binding.setScientific(intentModel[3]);
            binding.setConcentration(intentModel[4]);
            binding.setDosageform(intentModel[5]);
            binding.setNotes(intentModel[6]);
            binding.setStore(intentModel[7]);
            binding.setSachet(intentModel[8]);
            binding.setLocation(intentModel[9]);
            binding.setQuantity(intentModel[10]);
        }

        binding.btnUpdate.setOnClickListener(v -> {

            viewModel.postUpdate("7250", "test update", "test update", "test update", "test update", "test update", "test update", "test update", "test update"
                    , "test update").observe(this, aVoid -> {
                Toast.makeText(this, "success update", Toast.LENGTH_SHORT).show();
            });
        });
        binding.btnSale.setOnClickListener(v -> {

            dialogSale();
        });

//        if (getIntent().getSerializableExtra("medicines") instanceof Medicines){
//            Medicines intentModel = (Medicines) getIntent().getSerializableExtra("medicines");
//            if (intentModel != null)
//                setValue();
//            else finish();

//        }else {
//            MedicinesHave intentModel = (MedicinesHave) getIntent().getSerializableExtra("medicines");
//            if (intentModel != null){

//            } else finish();
//        }


        binding.btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });


    }

    private void dialogSale() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(binding.getName())
                .setPositiveButton("OK", (dialog1, which) -> {

                    dialog1.dismiss();
                }).create();
        String message;
        if (binding.edtPrice.getText() != null && !binding.edtPrice.getText().toString().isEmpty()) {
            message = binding.getName() + "\n" + binding.getQuantity() + binding.edtPrice.getText().toString();
            uploadSaleData();
        } else {
            message = "add price";
        }
        dialog.setMessage(message);
        dialog.show();
    }

    private void uploadUpdateData() {
        String barcode = binding.getBarcode();
        String name = binding.getName();
        String concentration = binding.getConcentration();
        String scientific = binding.getScientific();
        String dosageform = binding.getDosageform();
        String notes = binding.getNotes();
        String location = binding.getLocation();
        String store = binding.getStore();
        String sachet = binding.getSachet();
//        String quntity = binding.edt
        viewModel.postField(name, scientific, concentration, dosageform, notes, store, sachet, location, "5")
                .observe(this, aVoid -> {
                    Toast.makeText(this, "success upload data", Toast.LENGTH_SHORT).show();
                });
    }

    private void uploadSaleData() {
        String itemId = binding.getId();
        String price = Objects.requireNonNull(binding.edtPrice.getText()).toString().trim();
        String isSachet = binding.edtSachet.getText().toString().trim();
//        view model in here
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.collapseActionView.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        binding.collapseActionView.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
        convertNumToBarcode();
    }

    private void convertNumToBarcode() {
        String barCode = "6253500622058";
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            int w = (int) getResources().getDimension(R.dimen._350sdp);
            int h = (int) getResources().getDimension(R.dimen._200sdp);
            BitMatrix matrix = writer.encode(barCode, BarcodeFormat.CODABAR, w, h);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
//            binding.imageTVShow.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
