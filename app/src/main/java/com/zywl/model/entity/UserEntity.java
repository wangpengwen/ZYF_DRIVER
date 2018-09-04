package com.zywl.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.text.TextUtils;

import com.biz.util.TimeUtil;

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
@Entity(tableName = "User", indices = {@Index("memberId")})
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    public long memberId;
//    @ColumnInfo(name = "ts")
//    public long ts;
    @ColumnInfo(name = "salesmanName")
    public String salesmanName;
    @ColumnInfo(name = "salesmanNum")
    public String salesmanNum;
    @ColumnInfo(name = "salesmanPic")
    public String salesmanPic;
    @ColumnInfo(name = "salesmanComnum")
    public String salesmanComnum;
    @ColumnInfo(name = "salesmanGoal")
    public String salesmanGoal;
    @ColumnInfo(name = "salesmanBonus")
    public String salesmanBonus;
    @ColumnInfo(name = "salesmanType")
    public String salesmanType;
    @ColumnInfo(name = "salesmanJoinTime")
    public String salesmanJoinTime;
    @ColumnInfo(name = "salesmanRealName")
    public String salesmanRealName;
    @ColumnInfo(name = "salesmanToken")
    public String salesmanToken;
    @ColumnInfo(name = "storageName")
    public String storageName;
    @ColumnInfo(name = "salesmanStorage")
    public String salesmanStorage;

//    @ColumnInfo(name = "token")
//    public String token;
//    @ColumnInfo(name = "areaName")
//    public String areaName;
//    @ColumnInfo(name = "areaNo")
//    public String areaNo;
//    @ColumnInfo(name = "deviceNo")
//    public String deviceNo;
//    @ColumnInfo(name = "idcard")
//    public String idcard;
//    @ColumnInfo(name = "validTimeStart")
//    public Long validTimeStart;
//    @ColumnInfo(name = "loginTime")
//    public String loginTime;
//    @ColumnInfo(name = "storeNo")
//    public String storeNo;
//    @ColumnInfo(name = "storeName")
//    public String storeName;

//    @ColumnInfo(name = "accountStateType")
//    public String accountStateType;
//    @ColumnInfo(name = "autoToken")
//    public String autoToken;
//    @ColumnInfo(name = "loginCount")
//    public int loginCount;
//    @ColumnInfo(name = "regTime")
//    public String regTime;
//    @ColumnInfo(name = "birthday")
//    public String birthday;
//    @ColumnInfo(name = "couponQuantity")
//    public int couponQuantity;
//    @ColumnInfo(name = "email")
//    public String email;
//    @ColumnInfo(name = "memberLevel")
//    public int memberLevel;
//    @ColumnInfo(name = "mobile")
//    public String mobile;
//    @ColumnInfo(name = "nickName")
//    public String nickName;
//    @ColumnInfo(name = "portraitUrl")
//    public String portraitUrl;
//    @ColumnInfo(name = "sexType")
//    public String sexType;
//    @ColumnInfo(name = "walletBalance")
//    public long walletBalance;
//    @ColumnInfo(name = "point")
//    public int point;
//    @ColumnInfo(name = "userToken")
//    public String userToken;
//    @ColumnInfo(name = "walletAccount")
//    public String walletAccount;
//    @ColumnInfo(name = "hasTurnedOnWallet")
//    public boolean hasTurnedOnWallet ;
//    @ColumnInfo(name = "lastSignTime")
//    public String lastSignTime;


    public long getMemberId() {
        return memberId;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public String getSalesmanNum() {
        return salesmanNum;
    }

    public String getSalesmanPic() {
        return salesmanPic;
    }

    public String getSalesmanComnum() {
        return salesmanComnum;
    }

    public String getSalesmanGoal() {
        return salesmanGoal;
    }

    public String getSalesmanBonus() {
        return salesmanBonus;
    }

    public String getSalesmanType() {
        return salesmanType;
    }

    public String getSalesmanJoinTime() {
        return salesmanJoinTime;
    }

    public String getSalesmanRealName() {
        return salesmanRealName;
    }

    public String getSalesmanToken() {
        return salesmanToken;
    }

    public String getStorageName() {
        return storageName;
    }

    public String getSalesmanStorage() {
        return salesmanStorage;
    }

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
