package com.example.stud.measureapp;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class MasuratoareDAO_Impl implements MasuratoareDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfMasuratoare;

  public MasuratoareDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMasuratoare = new EntityInsertionAdapter<Masuratoare>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `masuratori`(`denumire`,`data`,`id`) VALUES (?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Masuratoare value) {
        if (value.getDenumire() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getDenumire());
        }
        if (value.getData() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getData());
        }
        stmt.bindLong(3, value.getId());
      }
    };
  }

  @Override
  public long insert(Masuratoare masuratoare) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfMasuratoare.insertAndReturnId(masuratoare);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long[] insertAll(List<Masuratoare> masuratori) {
    __db.beginTransaction();
    try {
      long[] _result = __insertionAdapterOfMasuratoare.insertAndReturnIdsArray(masuratori);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Masuratoare> selectMasuratori() {
    final String _sql = "SELECT * FROM masuratori";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfDenumire = _cursor.getColumnIndexOrThrow("denumire");
      final int _cursorIndexOfData = _cursor.getColumnIndexOrThrow("data");
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final List<Masuratoare> _result = new ArrayList<Masuratoare>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Masuratoare _item;
        _item = new Masuratoare();
        final String _tmpDenumire;
        _tmpDenumire = _cursor.getString(_cursorIndexOfDenumire);
        _item.setDenumire(_tmpDenumire);
        final String _tmpData;
        _tmpData = _cursor.getString(_cursorIndexOfData);
        _item.setData(_tmpData);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Cursor selectCursorMasuratori() {
    final String _sql = "SELECT * FROM masuratori";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _tmpResult = __db.query(_statement);
    return _tmpResult;
  }
}
