package com.shahm.myapplication.view_activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ActivityDetailsBinding;
import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.viewmodel.VMMedicines;

public class ActivityDetails extends AppCompatActivity {
    private ActivityDetailsBinding binding;
    private VMMedicines viewModel;
    private Medicines model;
    private int isEdit = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);


        model = (Medicines) getIntent().getSerializableExtra("model");
        if (model!=null){
            binding.setModel(model);
        }
        binding.btnUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityEdit.class);
            intent.putExtra("model", model);
            startActivity(intent);
        });
        binding.btnSale.setOnClickListener(v -> {
            Toast.makeText(this, binding.edtPrice.getText().toString(), Toast.LENGTH_SHORT).show();

//            dialogSale();
        });





        binding.btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });


    }

//    private void dialogSale() {
//        AlertDialog dialog = new AlertDialog.Builder(this)
//                .setTitle(binding.getName())
//                .setPositiveButton("OK", (dialog1, which) -> {
//
//                    dialog1.dismiss();
//                }).create();
//        String message;
//        if (binding.edtPrice.getText() != null && !binding.edtPrice.getText().toString().isEmpty()) {
//            message = binding.getName() + "\n" + binding.getQuantity() + binding.edtPrice.getText().toString();
//            uploadSaleData();
//        } else {
//            message = "add price";
//        }
//        dialog.setMessage(message);
//        dialog.show();
//    }

    private void uploadUpdateData() {
//        String barcode = binding.getBarcode();
//        String name = binding.getName();
//        String concentration = binding.getConcentration();
//        String scientific = binding.getScientific();
//        String dosageform = binding.getDosageform();
//        String notes = binding.getNotes();
//        String location = binding.getLocation();
//        String store = binding.getStore();
//        String sachet = binding.getSachet();
////        String quntity = binding.edt
//        viewModel.postField(name, scientific, concentration, dosageform, notes, store, sachet, location, "5")
//                .observe(this, aVoid -> {
//                    Toast.makeText(this, "success upload data", Toast.LENGTH_SHORT).show();
//                });
    }

//    private void uploadSaleData() {
//        String itemId = binding.getId();
//        String price = Objects.requireNonNull(binding.edtPrice.getText()).toString().trim();
//        String isSachet = binding.edtSachet.getText().toString().trim();
////        view model in here
//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
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
