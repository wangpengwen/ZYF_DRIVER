package com.zyf.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import com.zyf.model.entity.UserEntity;

import java.util.List;

/**
 * Title: UserDao
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/11/17  14:09
 *
 * @author wangwei
 * @version 1.0
 */
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity... userEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<UserEntity> userEntities);

    @Query("select * from user")
    UserEntity[] query();

    @Query("select * from user")
    List<UserEntity> queryList();

    @Query("select * from user where memberId=:userId")
    UserEntity[] query(long userId);

    @Delete
    void deleteAll(List<UserEntity> userEntities);
}
