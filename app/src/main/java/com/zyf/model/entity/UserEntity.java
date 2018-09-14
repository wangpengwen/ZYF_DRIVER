package com.zyf.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Title: UserEntity
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/11/17  11:11
 *
 * @author wangwei
 * @version 1.0
 * @Entity(tableName = 'animal') ，    必须
 *
 * 在Animal类前添加该注解,app运行时变会生成一张table,表名默认为类名，也可以通过属性tableName手动设置表名.
 * @PrimaryKey(autoGenerate=true)，必须 在成员变量前添加该注解, 可以设置animal表的主键, 属性autoGenerate =
 * true即主键会自动增长，即使实体类只有一个成员属性，也必须声明主键.
 * @ColumnInfo(name = "sname") 
 *
 * 在成员变量前添加该注解,可以手动设置字段名,默认字段名即为变量名.
 * @Ignore         在成员属性前添加该注解, 可以设置不想存入animal表中的成员变量.
 * @Embedded SQLITE数据库是没有Country类型的, 所以该注解可以将嵌套在Animal类中的Country类存入到animal表中, 下图中,
 * animal表会同时拥有Country类的字段
 * (cid 和 cname).
 */
@Entity(tableName = "User", indices = {@Index("driverId")})
public class UserEntity {
    @PrimaryKey(autoGenerate = false)
    public long driverId;
    @ColumnInfo(name = "driverNum")
    public String driverNum;
    @ColumnInfo(name = "driverRealName")
    public String driverRealName;
    @ColumnInfo(name = "driverName")
    public String driverName;
    @ColumnInfo(name = "driverLicence")
    public String driverLicence;
    @ColumnInfo(name = "driverIdcard")
    public String driverIdcard;
    @ColumnInfo(name = "driverPhone")
    public String driverPhone;
    @ColumnInfo(name = "driverToken")
    public String driverToken;
    @ColumnInfo(name = "driverIsShort")
    public int driverIsShort;
    @ColumnInfo(name = "driverTruckNum")
    public String driverTruckNum;
    @ColumnInfo(name = "driverLicencePic")
    public String driverLicencePic;
    @ColumnInfo(name = "driverToExamine")
    public int driverToExamine;
    @ColumnInfo(name = "driverFlag")
    public int driverFlag;
    @ColumnInfo(name = "driverVehiclePic")
    public String driverVehiclePic;

    //    public String getHideMobile() {
//        if(!TextUtils.isEmpty(nickName)) return nickName;
//        try {
//            return mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
//        } catch (Exception e) {
//            return mobile;
//        }
//
//    }
//
//
}
