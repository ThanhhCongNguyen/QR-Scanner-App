package com.example.qr_scanner.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.qr_scanner.database.entity.HistoryModel;

import java.util.List;

@Dao
public interface HistoryDao {
    @Query("SELECT * FROM Histories ORDER BY id DESC")
    LiveData<List<HistoryModel>> getAllData();

    @Insert
    void insert(HistoryModel historyModel);

    @Update
    void update(HistoryModel historyModel);

    @Delete
    void delete(HistoryModel historyModel);

    @Query("DELETE FROM histories")
    void deleteAll();
}
