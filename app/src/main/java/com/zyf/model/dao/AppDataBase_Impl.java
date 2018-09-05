package com.zyf.model.dao;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.util.TableInfo;

import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class AppDataBase_Impl extends AppDataBase {
  private volatile UserDao _userDao;

  private volatile LoginHisUserDao _loginHisUserDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(15) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `User` (`memberId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `salesmanName` TEXT, `salesmanNum` TEXT, `salesmanPic` TEXT, `salesmanComnum` TEXT, `salesmanGoal` TEXT, `salesmanBonus` TEXT, `salesmanType` TEXT, `salesmanJoinTime` TEXT, `salesmanRealName` TEXT, `salesmanToken` TEXT, `storageName` TEXT, `salesmanStorage` TEXT)");
        _db.execSQL("CREATE  INDEX `index_User_memberId` ON `User` (`memberId`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `LoginHis` (`id` INTEGER NOT NULL, `mobile` TEXT, `ts` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE  INDEX `index_LoginHis_id` ON `LoginHis` (`id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"e5f82393a0213cc52eab64c5b08de3c9\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `User`");
        _db.execSQL("DROP TABLE IF EXISTS `LoginHis`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(13);
        _columnsUser.put("memberId", new TableInfo.Column("memberId", "INTEGER", true, 1));
        _columnsUser.put("salesmanName", new TableInfo.Column("salesmanName", "TEXT", false, 0));
        _columnsUser.put("salesmanNum", new TableInfo.Column("salesmanNum", "TEXT", false, 0));
        _columnsUser.put("salesmanPic", new TableInfo.Column("salesmanPic", "TEXT", false, 0));
        _columnsUser.put("salesmanComnum", new TableInfo.Column("salesmanComnum", "TEXT", false, 0));
        _columnsUser.put("salesmanGoal", new TableInfo.Column("salesmanGoal", "TEXT", false, 0));
        _columnsUser.put("salesmanBonus", new TableInfo.Column("salesmanBonus", "TEXT", false, 0));
        _columnsUser.put("salesmanType", new TableInfo.Column("salesmanType", "TEXT", false, 0));
        _columnsUser.put("salesmanJoinTime", new TableInfo.Column("salesmanJoinTime", "TEXT", false, 0));
        _columnsUser.put("salesmanRealName", new TableInfo.Column("salesmanRealName", "TEXT", false, 0));
        _columnsUser.put("salesmanToken", new TableInfo.Column("salesmanToken", "TEXT", false, 0));
        _columnsUser.put("storageName", new TableInfo.Column("storageName", "TEXT", false, 0));
        _columnsUser.put("salesmanStorage", new TableInfo.Column("salesmanStorage", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(1);
        _indicesUser.add(new TableInfo.Index("index_User_memberId", false, Arrays.asList("memberId")));
        final TableInfo _infoUser = new TableInfo("User", _columnsUser, _foreignKeysUser, _indicesUser);
        final TableInfo _existingUser = TableInfo.read(_db, "User");
        if (! _infoUser.equals(_existingUser)) {
          throw new IllegalStateException("Migration didn't properly handle User(com.zywl.model.entity.UserEntity).\n"
                  + " Expected:\n" + _infoUser + "\n"
                  + " Found:\n" + _existingUser);
        }
        final HashMap<String, TableInfo.Column> _columnsLoginHis = new HashMap<String, TableInfo.Column>(3);
        _columnsLoginHis.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsLoginHis.put("mobile", new TableInfo.Column("mobile", "TEXT", false, 0));
        _columnsLoginHis.put("ts", new TableInfo.Column("ts", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLoginHis = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLoginHis = new HashSet<TableInfo.Index>(1);
        _indicesLoginHis.add(new TableInfo.Index("index_LoginHis_id", false, Arrays.asList("id")));
        final TableInfo _infoLoginHis = new TableInfo("LoginHis", _columnsLoginHis, _foreignKeysLoginHis, _indicesLoginHis);
        final TableInfo _existingLoginHis = TableInfo.read(_db, "LoginHis");
        if (! _infoLoginHis.equals(_existingLoginHis)) {
          throw new IllegalStateException("Migration didn't properly handle LoginHis(com.zywl.model.entity.LoginHisEntity).\n"
                  + " Expected:\n" + _infoLoginHis + "\n"
                  + " Found:\n" + _existingLoginHis);
        }
      }
    }, "e5f82393a0213cc52eab64c5b08de3c9", "5ec019ae8261292877c247d98aec952f");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "User","LoginHis");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `User`");
      _db.execSQL("DELETE FROM `LoginHis`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public LoginHisUserDao loginHisUserDao() {
    if (_loginHisUserDao != null) {
      return _loginHisUserDao;
    } else {
      synchronized(this) {
        if(_loginHisUserDao == null) {
          _loginHisUserDao = new LoginHisUserDao_Impl(this);
        }
        return _loginHisUserDao;
      }
    }
  }
}
