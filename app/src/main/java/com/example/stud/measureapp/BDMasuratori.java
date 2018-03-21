package com.example.stud.measureapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by stud on 3/21/2018.
 */

@Database(entities = Masuratoare.class, version = 1)
public abstract class BDMasuratori extends RoomDatabase{
    public abstract MasuratoareDAO getMasuratoareDao();

    private static BDMasuratori db;

    public static BDMasuratori getInstance(Context context) {

        if (db == null) {
            db = Room.databaseBuilder(context,
                    BDMasuratori.class, "masuratori.db")
                    .allowMainThreadQueries()//nerecomandat
                    .build();
        }

        return db;
    }
}
