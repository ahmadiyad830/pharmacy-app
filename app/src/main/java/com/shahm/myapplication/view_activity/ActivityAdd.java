package com.shahm.myapplication.view_activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.ActivityAddBinding;
import com.shahm.myapplication.model.Medicines;
import com.shahm.myapplication.viewmodel.VMNewDrug;


public class ActivityAdd extends AppCompatActivity {

    private ActivityAddBinding binding;
    private VMNewDrug viewModel;
    private Medicines model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(VMNewDrug.class);
        if (getIntent() != null) {
            if (getIntent().getSerializableExtra("model") != null) {
                model = ((Medicines) getIntent().getSerializableExtra("model"));
                if (model != null) {
                    binding.setModel(model);
                }
            }
        }

        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });
        binding.btnAdd.setOnClickListener(v -> {
//            viewModel.postMap(map).observe(this, aVoid -> {
//                Toast.makeText(this, "success upload data", Toast.LENGTH_SHORT).show();
//            });

            uploadData();
        });
    }

    private void uploadData() {
        String name = binding.name.getText().toString().trim();
        String scientific = binding.scientific.getText().toString().trim();
        String concentration = binding.concentration.getText().toString().trim();
        String dosageform = binding.dosageform.getText().toString().trim();
        String note = binding.note.getText().toString().trim();
        String store = binding.store.getText().toString().trim();
        String sachet = binding.sachet.getText().toString().trim();
        String location = binding.location.getText().toString().trim();
        String quantity = binding.quantity.getText().toString().trim();
        if (!name.isEmpty() && !scientific.isEmpty() && !concentration.isEmpty() && !dosageform.isEmpty()) {
            viewModel.postField(name, scientific, concentration, dosageform, note, store, sachet, location, quantity)
                    .observe(this, aVoid -> {
                        Toast.makeText(this, "the new item is added", Toast.LENGTH_SHORT).show();
                        finish();
                    });

        } else Toast.makeText(this, "need more information", Toast.LENGTH_SHORT).show();

    }


//    private void saveInstance(Bundle outState) {
//        Medicines model = new Medicines(binding.edtBarCode.getText().toString(),
//                binding.name.getText().toString(),
//                binding.scientific.getText().toString(),
//                binding.concentration.getText().toString(),
//                binding.dosageform.getText().toString()       ,
//                binding.note.getText().toString(),
//                binding.store.getText().toString()        ,
//                binding.sachet.getText().toString()        ,
//                binding.location.getText().toString()          , "");
//        if (model != null) {
//            try {
//                outState.putSerializable("save_medicines", model);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private void onScan() {
        IntentIntegrator intent = new IntentIntegrator(this);
        intent.setPrompt("for flags");
        intent.setBeepEnabled(true);
        intent.setOrientationLocked(true);
        intent.setCaptureActivity(Capture.class);
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setCameraId(0);  // Use a specific camera of the device
        intent.setBarcodeImageEnabled(true);
        intent.initiateScan();
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (result != null)
//            if (result.getContents() != null) {
//                binding.edtBarCode.setText(result.getContents());
//                String barCode = binding.edtBarCode.getText().toString();
//                MultiFormatWriter writer = new MultiFormatWriter();
//                try {
////                    TODO warning maybe the not bar code maybe qr use dialog when user chiois if qr of barcode
//                    BitMatrix matrix = writer.encode(barCode, BarcodeFormat.CODABAR,
//                            (int) getResources().getDimension(R.dimen._350sdp),
//                            (int) getResources().getDimension(R.dimen._200sdp));
//                    BarcodeEncoder encoder = new BarcodeEncoder();
//                    Bitmap bitmap = encoder.createBitmap(matrix);
//
//                    binding.imgBarcode.setImageBitmap(bitmap);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Toast.makeText(this, "error\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//
//            } else Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//    }

    public static class Capture extends CaptureActivity {

    }
}