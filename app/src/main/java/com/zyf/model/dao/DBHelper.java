package com.zyf.model.dao;

import android.arch.persistence.room.Room;
import android.os.Environment;

import com.biz.application.BaseApplication;
import com.biz.util.FileUtil;
import com.biz.util.LogUtil;

import java.io.File;

/**
 * Title: DBHelper
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/11/17  14:16
 *
 * @author wangwei
 * @version 1.0
 */
public class DBHelper {
    public static final int DB_VERSION = 2;
    public static final String DB_NAME = DB_VERSION + "cache2";
    private static DBHelper mDBHelper;
    private AppDataBase mAppDataBase;

    public static DBHelper getInstance() {
        if (mDBHelper == null) {
            synchronized (DBHelper.class) {
                mDBHelper = new DBHelper();
            }
        }
        return mDBHelper;
    }

    public AppDataBase getAppDataBase() {
        return mAppDataBase;
    }

    public static void clear() {
        getInstance().getAppDataBase().close();
        mDBHelper = null;
    }

    public DBHelper() {
        mAppDataBase = Room.databaseBuilder(BaseApplication.getAppContext(), AppDataBase.class, DB_NAME + ".db").build();
//        if (BuildConfig.DEBUG) {
//            copyDataBaseToSD();
//        }
    }

    public UserDao getUserDao() {
        return mAppDataBase.userDao();
    }
//
//    public SearchDao getSearchKeyDao() {
//        return mAppDataBase.searchDao();
//    }
//
//
//    public CacheDao getCacheDao() {
//        return mAppDataBase.cacheDao();
//    }
//
//    public ScanCartDao getScanCartDao() {
//        return mAppDataBase.scanCartDao();
//    }
//
//    public MessageDao getMessageDao() {
//        return mAppDataBase.messageDao();
//    }
//
    public LoginHisUserDao getLoginHisUserDao() {
        return mAppDataBase.loginHisUserDao();
    }
//
//    public UserDepotDao getUserDepotDao() {
//        return mAppDataBase.userDepotDao();
//    }

    /**
     * 拷贝数据库到sd卡
     */
    public static void copyDataBaseToSD() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return;
        }
        File dbFile = new File(BaseApplication.getAppContext().getDatabasePath(DB_NAME) + ".db");
        File file = new File(Environment.getExternalStorageDirectory(), DB_NAME + ".db");
        LogUtil.print("db exists " + dbFile.exists());
        if (FileUtil.copyChannelFile(dbFile + "", file + "")) {
            LogUtil.print("copy db is ok:" + file);
        } else {
            com.biz.util.LogUtil.print("copy dataBase to SD error.");
        }
    }
}
