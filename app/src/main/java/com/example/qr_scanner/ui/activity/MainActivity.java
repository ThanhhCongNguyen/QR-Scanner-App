package com.example.qr_scanner.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.qr_scanner.R;
import com.example.qr_scanner.database.entity.HistoryModel;
import com.example.qr_scanner.databinding.ActivityMainBinding;
import com.example.qr_scanner.ui.adapter.HistoryAdapter;
import com.example.qr_scanner.utils.Utility;
import com.example.qr_scanner.viewmodel.HistoryViewModel;

public class MainActivity extends AppCompatActivity implements HistoryAdapter.CallBack {
    private ActivityMainBinding binding;
    private HistoryViewModel historyViewModel;
    private HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_QR_Scanner);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.toolbar.getOverflowIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        historyAdapter = new HistoryAdapter(this);
        binding.recyclerView.setAdapter(historyAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        historyViewModel.getAllData.observe(this, historyModels -> {
            if (!historyModels.isEmpty()) {
                binding.recyclerView.setVisibility(View.VISIBLE);
                binding.emptyLayout.getRoot().setVisibility(View.GONE);

                historyAdapter.setHistoryModels(historyModels);
            } else {
                binding.recyclerView.setVisibility(View.GONE);
                binding.emptyLayout.getRoot().setVisibility(View.VISIBLE);
            }
        });

        binding.scanButton.setOnClickListener(view -> {
            startActivity(new Intent(this, QRScanActivity.class));
        });
    }

    @Override
    public void deleteHistory(HistoryModel historyModel) {
        new AlertDialog.Builder(this)
                .setTitle("Delete ?")
                .setMessage("Do you want to delete this QR code ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        historyViewModel.delete(historyModel);
                        Utility.toast(MainActivity.this, "Deleted successfully");
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleteAll) {
            historyViewModel.deleteAll();
            Utility.toast(MainActivity.this, "Deleted successfully");
        }
        return true;
    }
}