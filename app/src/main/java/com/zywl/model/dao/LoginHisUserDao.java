package com.zywl.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.zywl.model.entity.LoginHisEntity;

import java.util.List;


/**
 * Title: LoginHisUserDao
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2018/1/16  15:48
 *
 * @author wangwei
 * @version 1.0
 */
@Dao
public interface LoginHisUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<LoginHisEntity> loginHisEntities);

    @Delete
    void delete(List<LoginHisEntity> loginHisEntities);

    @Query("select * from loginhis")
    List<LoginHisEntity> queryList();
}
