package com.example.qr_scanner.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.qr_scanner.database.dao.HistoryDao;
import com.example.qr_scanner.database.database.MyDatabase;
import com.example.qr_scanner.database.entity.HistoryModel;

import java.util.List;

public class HistoryRepository {
    private HistoryDao historyDao;
    public LiveData<List<HistoryModel>> getAllData;

    public HistoryRepository(Context context) {
        MyDatabase myDatabase = MyDatabase.getInstance(context);
        historyDao = myDatabase.historyDao();
        getAllData = historyDao.getAllData();
    }

    public void insert(HistoryModel historyModel) {
        new Thread(() -> historyDao.insert(historyModel)).start();
    }

    public void update(HistoryModel historyModel) {
        new Thread(() -> historyDao.update(historyModel)).start();
    }

    public void delete(HistoryModel historyModel) {
        new Thread(() -> historyDao.delete(historyModel)).start();
    }

    public void deleteAll() {
        new Thread(() -> historyDao.deleteAll()).start();
    }
}
