package com.shahm.myapplication.view_frgament;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.shahm.myapplication.R;
import com.shahm.myapplication.databinding.FragmentAddBinding;
import com.shahm.myapplication.model.Medicines;


public class FragmentAdd extends Fragment {

    private FragmentAddBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false);
        binding.btnCamBarCode.setOnClickListener(v -> {
            onScan();
        });
        return binding.getRoot();
    }





    private void saveInstance(Bundle outState){
        Medicines model = new Medicines(binding.edtBarCode.getText().toString(),
                binding.name.getText().toString(),
                binding.scientific.getText().toString(),
                binding.concentration.getText().toString(),
                binding.dosageform.getText().toString(),
                binding.note.getText().toString(),
                binding.store.getText().toString(),
                binding.sachet.getText().toString(),
                binding.location.getText().toString(), "");
        if (model != null) {
            try {
                outState.putSerializable("save_medicines", model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void onScan() {
        IntentIntegrator intent = IntentIntegrator.forSupportFragment(FragmentAdd.this);
        intent.setPrompt("for flags");
        intent.setBeepEnabled(true);
        intent.setOrientationLocked(true);
        intent.setCaptureActivity(Capture.class);
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setCameraId(0);  // Use a specific camera of the device
        intent.setBarcodeImageEnabled(true);
        intent.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null)
            if (result.getContents() != null) {
                binding.edtBarCode.setText(result.getContents());
                String barCode = binding.edtBarCode.getText().toString();
                MultiFormatWriter writer = new MultiFormatWriter();
                try {
//                    TODO warning maybe the not bar code maybe qr use dialog when user chiois if qr of barcode
                    BitMatrix matrix = writer.encode(barCode, BarcodeFormat.CODABAR,
                            (int) requireActivity().getResources().getDimension(R.dimen._350sdp),
                            (int) requireActivity().getResources().getDimension(R.dimen._200sdp));
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(matrix);

                    binding.imgBarcode.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(requireActivity(), "error\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            } else Toast.makeText(requireActivity(), "", Toast.LENGTH_SHORT).show();
    }


    public static class Capture extends CaptureActivity {

    }
}