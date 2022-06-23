package com.example.qr_scanner.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qr_scanner.databinding.ActivityResultBinding;
import com.example.qr_scanner.utils.Utility;

public class ResultActivity extends AppCompatActivity {
    private static String keyResult = "key";
    private ActivityResultBinding binding;
    private ClipboardManager clipboardManager;
    private ClipData clipData;

    public static void starter(Context context, String data) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(keyResult, data);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbarResult);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbarResult.getOverflowIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        binding.resultTextView.setText(getIntent().getStringExtra(keyResult));

        binding.copyButton.setOnClickListener(view -> {
            String text = binding.resultTextView.getText().toString().trim();
            clipData = ClipData.newPlainText("text", text);
            clipboardManager.setPrimaryClip(clipData);
            Utility.toast(this, "Copied");
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}