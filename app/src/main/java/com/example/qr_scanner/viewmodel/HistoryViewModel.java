package com.example.qr_scanner.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.qr_scanner.database.entity.HistoryModel;
import com.example.qr_scanner.repository.HistoryRepository;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    HistoryRepository historyRepository;
    public LiveData<List<HistoryModel>> getAllData;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        historyRepository = new HistoryRepository(application);
        getAllData = historyRepository.getAllData;
    }

    public void insert(HistoryModel historyModel) {
        historyRepository.insert(historyModel);
    }

    public void update(HistoryModel historyModel) {
        historyRepository.update(historyModel);
    }

    public void delete(HistoryModel historyModel) {
        historyRepository.delete(historyModel);
    }

    public void deleteAll() {
        historyRepository.deleteAll();
    }
}
