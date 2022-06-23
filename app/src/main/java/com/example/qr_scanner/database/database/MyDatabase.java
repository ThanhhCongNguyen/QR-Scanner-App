package com.example.qr_scanner.database.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.qr_scanner.database.dao.HistoryDao;
import com.example.qr_scanner.database.entity.HistoryModel;

@Database(entities = HistoryModel.class, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract HistoryDao historyDao();

    public static MyDatabase INSTANCE;

    public static MyDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MyDatabase.class,
                    "Histories"
            ).build();
        }
        return INSTANCE;
    }
}
