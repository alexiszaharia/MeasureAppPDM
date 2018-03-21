package com.example.stud.measureapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stud on 3/21/2018.
 */

@Dao
public interface MasuratoareDAO {

    @Insert
    public long insert(Masuratoare masuratoare);

    @Insert
    public long [] insertAll(List<Masuratoare> masuratori);


    @Query("SELECT * FROM masuratori")
    public List<Masuratoare> selectMasuratori();
    //etc...

}
