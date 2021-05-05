package com.shahm.myapplication.view_activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ActivityDetailsBinding;
import com.shahm.myapplication.viewmodel.VMMedicines;

public class ActivityDetails extends AppCompatActivity {
    private ActivityDetailsBinding binding;
    private VMMedicines viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        viewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(VMMedicines.class);
        String[] model = getIntent().getStringArrayExtra("listMed");
        if (model != null) {
            binding.setId(model[0]);
            binding.setBarcode(model[1]);
            binding.setName(model[2]);
            binding.setScientific(model[3]);
            binding.setConcentration(model[4]);
            binding.setDosageform(model[5]);
            binding.setNotes(model[6]);
            binding.setStore(model[7]);
            binding.setSachet(model[8]);
            binding.setLocation(model[9]);
            binding.setQuantity(model[10]);
        }

        binding.btnUpdate.setOnClickListener(v -> {
            viewModel.postUpdate("7250","test update","test update","test update","test update","test update","test update","test update","test update"
            ,"test update").observe(this,aVoid -> {
                Toast.makeText(this, "success update", Toast.LENGTH_SHORT).show();
            });
        });

//        if (getIntent().getSerializableExtra("medicines") instanceof Medicines){
//            Medicines model = (Medicines) getIntent().getSerializableExtra("medicines");
//            if (model != null)
//                setValue();
//            else finish();

//        }else {
//            MedicinesHave model = (MedicinesHave) getIntent().getSerializableExtra("medicines");
//            if (model != null){

//            } else finish();
//        }


        binding.btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });


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
