package com.zywl.model.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.zywl.model.entity.LoginHisEntity;
import com.zywl.model.entity.UserEntity;

/**
 * Title: AppDataBase
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/11/17  14:14
 *
 * @author wangwei
 * @version 1.0
 */
@Database(entities = {UserEntity.class,
        LoginHisEntity.class}, version = DBHelper.DB_VERSION)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract LoginHisUserDao loginHisUserDao();

}
