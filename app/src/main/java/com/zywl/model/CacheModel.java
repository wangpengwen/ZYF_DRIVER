package com.zywl.model;


import com.zywl.glide.GlideCatchUtil;

import rx.Observable;

/**
 * Title: CacheUtil
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2018/1/10  12:03
 *
 * @author wangwei
 * @version 1.0
 */
public class CacheModel {
    public static Observable<String> getCacheSize(){
        return Observable.create(subscriber -> {
            long size=0l;
//            File dbFile = new File(BaseApplication.getAppContext().getDatabasePath(DBHelper.DB_NAME) + ".db");
//            size=size+dbFile.length();
            size=size+ GlideCatchUtil.getCacheSize();
//            size=size-500*1024;//忽略500K大小
            if(size<=0) size=0;
            subscriber.onNext(GlideCatchUtil.getFormatSize(size));
            subscriber.onCompleted();
        });
    }
    public static Observable<Boolean> clearCache(){
        return Observable.create(subscriber -> {
//            DBHelper.clear();
//            File dbFile = new File(BaseApplication.getAppContext().getDatabasePath(DBHelper.DB_NAME) + ".db");
//            dbFile.delete();
            GlideCatchUtil.clearCacheMemory();
            GlideCatchUtil.clearCacheDiskSelf();
            subscriber.onNext(true);
            subscriber.onCompleted();
        });
    }
}
