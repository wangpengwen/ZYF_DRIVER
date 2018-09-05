package com.zyf.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Title: LoginHisEntity
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2018/1/16  15:48
 *
 * @author wangwei
 * @version 1.0
 */
@Entity(tableName = "LoginHis", indices = {@Index("id")})
public class LoginHisEntity {
    @PrimaryKey(autoGenerate = false)
    public long id;
    @ColumnInfo(name = "mobile")
    public String mobile;
    @ColumnInfo(name = "ts")
    public long ts;
}
