package com.example.stud.measureapp;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;

public class BDMasuratori_Impl extends BDMasuratori {
  private volatile MasuratoareDAO _masuratoareDAO;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `masuratori` (`denumire` TEXT, `data` TEXT, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"7760be999d6f808cf4a3780974a67f5c\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `masuratori`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsMasuratori = new HashMap<String, TableInfo.Column>(3);
        _columnsMasuratori.put("denumire", new TableInfo.Column("denumire", "TEXT", false, 0));
        _columnsMasuratori.put("data", new TableInfo.Column("data", "TEXT", false, 0));
        _columnsMasuratori.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMasuratori = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMasuratori = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMasuratori = new TableInfo("masuratori", _columnsMasuratori, _foreignKeysMasuratori, _indicesMasuratori);
        final TableInfo _existingMasuratori = TableInfo.read(_db, "masuratori");
        if (! _infoMasuratori.equals(_existingMasuratori)) {
          throw new IllegalStateException("Migration didn't properly handle masuratori(com.example.stud.measureapp.Masuratoare).\n"
                  + " Expected:\n" + _infoMasuratori + "\n"
                  + " Found:\n" + _existingMasuratori);
        }
      }
    }, "7760be999d6f808cf4a3780974a67f5c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "masuratori");
  }

  @Override
  public MasuratoareDAO getMasuratoareDao() {
    if (_masuratoareDAO != null) {
      return _masuratoareDAO;
    } else {
      synchronized(this) {
        if(_masuratoareDAO == null) {
          _masuratoareDAO = new MasuratoareDAO_Impl(this);
        }
        return _masuratoareDAO;
      }
    }
  }
}
