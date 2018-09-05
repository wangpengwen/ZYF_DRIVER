package com.zyf.model.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.zyf.model.entity.LoginHisEntity;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class LoginHisUserDao_Impl implements LoginHisUserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfLoginHisEntity;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfLoginHisEntity;

  public LoginHisUserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLoginHisEntity = new EntityInsertionAdapter<LoginHisEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `LoginHis`(`id`,`mobile`,`ts`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LoginHisEntity value) {
        stmt.bindLong(1, value.id);
        if (value.mobile == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.mobile);
        }
        stmt.bindLong(3, value.ts);
      }
    };
    this.__deletionAdapterOfLoginHisEntity = new EntityDeletionOrUpdateAdapter<LoginHisEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `LoginHis` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LoginHisEntity value) {
        stmt.bindLong(1, value.id);
      }
    };
  }

  @Override
  public void insert(List<LoginHisEntity> loginHisEntities) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfLoginHisEntity.insert(loginHisEntities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(List<LoginHisEntity> loginHisEntities) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfLoginHisEntity.handleMultiple(loginHisEntities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<LoginHisEntity> queryList() {
    final String _sql = "select * from loginhis";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfMobile = _cursor.getColumnIndexOrThrow("mobile");
      final int _cursorIndexOfTs = _cursor.getColumnIndexOrThrow("ts");
      final List<LoginHisEntity> _result = new ArrayList<LoginHisEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final LoginHisEntity _item;
        _item = new LoginHisEntity();
        _item.id = _cursor.getLong(_cursorIndexOfId);
        _item.mobile = _cursor.getString(_cursorIndexOfMobile);
        _item.ts = _cursor.getLong(_cursorIndexOfTs);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
