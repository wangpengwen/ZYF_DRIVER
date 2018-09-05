package com.zyf.model.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.zyf.model.entity.UserEntity;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfUserEntity;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfUserEntity;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserEntity = new EntityInsertionAdapter<UserEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `User`(`memberId`,`salesmanName`,`salesmanNum`,`salesmanPic`,`salesmanComnum`,`salesmanGoal`,`salesmanBonus`,`salesmanType`,`salesmanJoinTime`,`salesmanRealName`,`salesmanToken`,`storageName`,`salesmanStorage`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserEntity value) {
        stmt.bindLong(1, value.memberId);
        if (value.salesmanName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.salesmanName);
        }
        if (value.salesmanNum == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.salesmanNum);
        }
        if (value.salesmanPic == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.salesmanPic);
        }
        if (value.salesmanComnum == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.salesmanComnum);
        }
        if (value.salesmanGoal == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.salesmanGoal);
        }
        if (value.salesmanBonus == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.salesmanBonus);
        }
        if (value.salesmanType == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.salesmanType);
        }
        if (value.salesmanJoinTime == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.salesmanJoinTime);
        }
        if (value.salesmanRealName == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.salesmanRealName);
        }
        if (value.salesmanToken == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.salesmanToken);
        }
        if (value.storageName == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.storageName);
        }
        if (value.salesmanStorage == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.salesmanStorage);
        }
      }
    };
    this.__deletionAdapterOfUserEntity = new EntityDeletionOrUpdateAdapter<UserEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `User` WHERE `memberId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserEntity value) {
        stmt.bindLong(1, value.memberId);
      }
    };
  }

  @Override
  public void insert(UserEntity... userEntities) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserEntity.insert(userEntities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(List<UserEntity> userEntities) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserEntity.insert(userEntities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll(List<UserEntity> userEntities) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfUserEntity.handleMultiple(userEntities);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public UserEntity[] query() {
    final String _sql = "select * from user";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMemberId = _cursor.getColumnIndexOrThrow("memberId");
      final int _cursorIndexOfSalesmanName = _cursor.getColumnIndexOrThrow("salesmanName");
      final int _cursorIndexOfSalesmanNum = _cursor.getColumnIndexOrThrow("salesmanNum");
      final int _cursorIndexOfSalesmanPic = _cursor.getColumnIndexOrThrow("salesmanPic");
      final int _cursorIndexOfSalesmanComnum = _cursor.getColumnIndexOrThrow("salesmanComnum");
      final int _cursorIndexOfSalesmanGoal = _cursor.getColumnIndexOrThrow("salesmanGoal");
      final int _cursorIndexOfSalesmanBonus = _cursor.getColumnIndexOrThrow("salesmanBonus");
      final int _cursorIndexOfSalesmanType = _cursor.getColumnIndexOrThrow("salesmanType");
      final int _cursorIndexOfSalesmanJoinTime = _cursor.getColumnIndexOrThrow("salesmanJoinTime");
      final int _cursorIndexOfSalesmanRealName = _cursor.getColumnIndexOrThrow("salesmanRealName");
      final int _cursorIndexOfSalesmanToken = _cursor.getColumnIndexOrThrow("salesmanToken");
      final int _cursorIndexOfStorageName = _cursor.getColumnIndexOrThrow("storageName");
      final int _cursorIndexOfSalesmanStorage = _cursor.getColumnIndexOrThrow("salesmanStorage");
      final UserEntity[] _result = new UserEntity[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final UserEntity _item;
        _item = new UserEntity();
        _item.memberId = _cursor.getLong(_cursorIndexOfMemberId);
        _item.salesmanName = _cursor.getString(_cursorIndexOfSalesmanName);
        _item.salesmanNum = _cursor.getString(_cursorIndexOfSalesmanNum);
        _item.salesmanPic = _cursor.getString(_cursorIndexOfSalesmanPic);
        _item.salesmanComnum = _cursor.getString(_cursorIndexOfSalesmanComnum);
        _item.salesmanGoal = _cursor.getString(_cursorIndexOfSalesmanGoal);
        _item.salesmanBonus = _cursor.getString(_cursorIndexOfSalesmanBonus);
        _item.salesmanType = _cursor.getString(_cursorIndexOfSalesmanType);
        _item.salesmanJoinTime = _cursor.getString(_cursorIndexOfSalesmanJoinTime);
        _item.salesmanRealName = _cursor.getString(_cursorIndexOfSalesmanRealName);
        _item.salesmanToken = _cursor.getString(_cursorIndexOfSalesmanToken);
        _item.storageName = _cursor.getString(_cursorIndexOfStorageName);
        _item.salesmanStorage = _cursor.getString(_cursorIndexOfSalesmanStorage);
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<UserEntity> queryList() {
    final String _sql = "select * from user";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMemberId = _cursor.getColumnIndexOrThrow("memberId");
      final int _cursorIndexOfSalesmanName = _cursor.getColumnIndexOrThrow("salesmanName");
      final int _cursorIndexOfSalesmanNum = _cursor.getColumnIndexOrThrow("salesmanNum");
      final int _cursorIndexOfSalesmanPic = _cursor.getColumnIndexOrThrow("salesmanPic");
      final int _cursorIndexOfSalesmanComnum = _cursor.getColumnIndexOrThrow("salesmanComnum");
      final int _cursorIndexOfSalesmanGoal = _cursor.getColumnIndexOrThrow("salesmanGoal");
      final int _cursorIndexOfSalesmanBonus = _cursor.getColumnIndexOrThrow("salesmanBonus");
      final int _cursorIndexOfSalesmanType = _cursor.getColumnIndexOrThrow("salesmanType");
      final int _cursorIndexOfSalesmanJoinTime = _cursor.getColumnIndexOrThrow("salesmanJoinTime");
      final int _cursorIndexOfSalesmanRealName = _cursor.getColumnIndexOrThrow("salesmanRealName");
      final int _cursorIndexOfSalesmanToken = _cursor.getColumnIndexOrThrow("salesmanToken");
      final int _cursorIndexOfStorageName = _cursor.getColumnIndexOrThrow("storageName");
      final int _cursorIndexOfSalesmanStorage = _cursor.getColumnIndexOrThrow("salesmanStorage");
      final List<UserEntity> _result = new ArrayList<UserEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final UserEntity _item;
        _item = new UserEntity();
        _item.memberId = _cursor.getLong(_cursorIndexOfMemberId);
        _item.salesmanName = _cursor.getString(_cursorIndexOfSalesmanName);
        _item.salesmanNum = _cursor.getString(_cursorIndexOfSalesmanNum);
        _item.salesmanPic = _cursor.getString(_cursorIndexOfSalesmanPic);
        _item.salesmanComnum = _cursor.getString(_cursorIndexOfSalesmanComnum);
        _item.salesmanGoal = _cursor.getString(_cursorIndexOfSalesmanGoal);
        _item.salesmanBonus = _cursor.getString(_cursorIndexOfSalesmanBonus);
        _item.salesmanType = _cursor.getString(_cursorIndexOfSalesmanType);
        _item.salesmanJoinTime = _cursor.getString(_cursorIndexOfSalesmanJoinTime);
        _item.salesmanRealName = _cursor.getString(_cursorIndexOfSalesmanRealName);
        _item.salesmanToken = _cursor.getString(_cursorIndexOfSalesmanToken);
        _item.storageName = _cursor.getString(_cursorIndexOfStorageName);
        _item.salesmanStorage = _cursor.getString(_cursorIndexOfSalesmanStorage);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public UserEntity[] query(long userId) {
    final String _sql = "select * from user where memberId=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMemberId = _cursor.getColumnIndexOrThrow("memberId");
      final int _cursorIndexOfSalesmanName = _cursor.getColumnIndexOrThrow("salesmanName");
      final int _cursorIndexOfSalesmanNum = _cursor.getColumnIndexOrThrow("salesmanNum");
      final int _cursorIndexOfSalesmanPic = _cursor.getColumnIndexOrThrow("salesmanPic");
      final int _cursorIndexOfSalesmanComnum = _cursor.getColumnIndexOrThrow("salesmanComnum");
      final int _cursorIndexOfSalesmanGoal = _cursor.getColumnIndexOrThrow("salesmanGoal");
      final int _cursorIndexOfSalesmanBonus = _cursor.getColumnIndexOrThrow("salesmanBonus");
      final int _cursorIndexOfSalesmanType = _cursor.getColumnIndexOrThrow("salesmanType");
      final int _cursorIndexOfSalesmanJoinTime = _cursor.getColumnIndexOrThrow("salesmanJoinTime");
      final int _cursorIndexOfSalesmanRealName = _cursor.getColumnIndexOrThrow("salesmanRealName");
      final int _cursorIndexOfSalesmanToken = _cursor.getColumnIndexOrThrow("salesmanToken");
      final int _cursorIndexOfStorageName = _cursor.getColumnIndexOrThrow("storageName");
      final int _cursorIndexOfSalesmanStorage = _cursor.getColumnIndexOrThrow("salesmanStorage");
      final UserEntity[] _result = new UserEntity[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final UserEntity _item;
        _item = new UserEntity();
        _item.memberId = _cursor.getLong(_cursorIndexOfMemberId);
        _item.salesmanName = _cursor.getString(_cursorIndexOfSalesmanName);
        _item.salesmanNum = _cursor.getString(_cursorIndexOfSalesmanNum);
        _item.salesmanPic = _cursor.getString(_cursorIndexOfSalesmanPic);
        _item.salesmanComnum = _cursor.getString(_cursorIndexOfSalesmanComnum);
        _item.salesmanGoal = _cursor.getString(_cursorIndexOfSalesmanGoal);
        _item.salesmanBonus = _cursor.getString(_cursorIndexOfSalesmanBonus);
        _item.salesmanType = _cursor.getString(_cursorIndexOfSalesmanType);
        _item.salesmanJoinTime = _cursor.getString(_cursorIndexOfSalesmanJoinTime);
        _item.salesmanRealName = _cursor.getString(_cursorIndexOfSalesmanRealName);
        _item.salesmanToken = _cursor.getString(_cursorIndexOfSalesmanToken);
        _item.storageName = _cursor.getString(_cursorIndexOfStorageName);
        _item.salesmanStorage = _cursor.getString(_cursorIndexOfSalesmanStorage);
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
