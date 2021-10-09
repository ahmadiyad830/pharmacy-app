package com.shahm.myapplication.view_activity;

import android.app.AlertDialog;
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
import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.model.SalesPharmacy;
import com.shahm.myapplication.viewmodel.VMSaleDrug;

import java.util.Objects;

public class ActivityDetails extends AppCompatActivity {
    private ActivityDetailsBinding binding;
    private VMSaleDrug viewModel;
    private Medicines model;
    private SalesPharmacy modelSales;
    private final int isEdit = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(VMSaleDrug.class);


        if (getIntent()!=null){
            if (getIntent().getSerializableExtra("model")!=null){
                model = (Medicines) getIntent().getSerializableExtra("model");
            }else {
                modelSales = ((SalesPharmacy) getIntent().getSerializableExtra("model sales"));
            }
        }
        if (model != null) {
            binding.setModel(model);
        }
        binding.btnUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityEdit.class);
            intent.putExtra("model", model);
            startActivity(intent);
        });
        binding.btnSale.setOnClickListener(v -> {
            dialogSale();
        });

        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });


    }

    private boolean isEmpty() {
        return !binding.txtQuantity.getText().toString().isEmpty() &&
                !binding.txtSachet.getText().toString().isEmpty() &&
                !binding.edtPrice.getText().toString().isEmpty();
    }

    private void dialogSale() {
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(this);
        String message;
        if (binding.edtPrice.getText() != null && !binding.edtPrice.getText().toString().isEmpty()) {
            message = "name: " + binding.txtName.getText().toString().trim()
                    + "\nsachet: " + binding.txtSachet.getText().toString() +
                    "\nprice: " + binding.edtPrice.getText().toString();
            alertDialog.setNegativeButton("sale", (dialog1, which) -> {
                uploadSaleData(alertDialog.create());
            }).setPositiveButton("No", (dialog1, which) -> {
                dialog1.dismiss();
            });

        } else {
            alertDialog.setPositiveButton("Ok", (dialog1, which) -> {
                dialog1.dismiss();
            });
            message = "add price";
        }
        alertDialog.setMessage(message);
        alertDialog.create().show();
    }


    private void uploadSaleData(AlertDialog dialog) {
        String itemId = model.getId();
        String price = Objects.requireNonNull(binding.edtPrice.getText()).toString().trim();
        String isSachet = binding.txtSachet.getText().toString().trim();
        if (isEmpty()) {
            viewModel.postSaleDrug(itemId, isSachet, price).observe(this, aVoid -> {
                dialog.dismiss();
                Toast.makeText(this, "is success", Toast.LENGTH_SHORT).show();
            });
        }

    }


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
