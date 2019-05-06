package com.hfad.ntp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ShareActionProvider;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Qrcode_generation extends AppCompatActivity {

    public static final String TEXT = "text";

    private ShareActionProvider shareActionProvider;

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_generation);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mImageView = findViewById(R.id.image_qrcode);

        Intent intent = getIntent();
        String text = intent.getStringExtra(TEXT).trim();

        if (text != null) {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                mImageView.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }

        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_acitivity_qrcode_generation, menu);
//        MenuItem item = menu.findItem(R.id.action_share);
//        shareActionProvider = (ShareActionProvider) item.getActionProvider();
//        setShareIntent();
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    private void setShareIntent() {
//        if(shareActionProvider != null) {
//            Uri path = Uri.parse("android.resource://com.hfad.ntp/R.id.image_qrcode");
//            String imgPath = path.toString();
//            Intent intent = new Intent();
//            intent.setAction(Intent.ACTION_SEND);
//            intent.putExtra(Intent.EXTRA_STREAM, imgPath);
//            intent.setType("image/*");
//            startActivity(Intent.createChooser(intent, "share.."));
//        }
//
//    }
}
