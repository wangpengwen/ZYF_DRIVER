package com.zywl.model.entity.achievement;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TCJK on 2018/7/16.
 */

public class AchievementDayEntity implements Parcelable {

    /**当日总费用*/
    float dayCarriage;
    /**当日的总订单数*/
    int dayOrder;
    /**当日代收货款总额*/
    float dayCod;
    /**当日附加费的总额*/
    float dayAddedFee;
    /**当日货到付款的订单数*/
    int dayCollectOrder;
    /**当日货到付款的总数*/
    float dayCollect;

    public float getDayCarriage() {
        return dayCarriage;
    }

    public void setDayCarriage(float dayCarriage) {
        this.dayCarriage = dayCarriage;
    }

    public int getDayOrder() {
        return dayOrder;
    }

    public void setDayOrder(int dayOrder) {
        this.dayOrder = dayOrder;
    }

    public float getDayCod() {
        return dayCod;
    }

    public void setDayCod(float dayCod) {
        this.dayCod = dayCod;
    }

    public float getDayAddedFee() {
        return dayAddedFee;
    }

    public void setDayAddedFee(float dayAddedFee) {
        this.dayAddedFee = dayAddedFee;
    }

    public int getDayCollectOrder() {
        return dayCollectOrder;
    }

    public void setDayCollectOrder(int dayCollectOrder) {
        this.dayCollectOrder = dayCollectOrder;
    }

    public float getDayCollect() {
        return dayCollect;
    }

    public void setDayCollect(float dayCollect) {
        this.dayCollect = dayCollect;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.dayCarriage);
        dest.writeInt(this.dayOrder);
        dest.writeFloat(this.dayCod);
        dest.writeFloat(this.dayAddedFee);
        dest.writeInt(this.dayCollectOrder);
        dest.writeFloat(this.dayCollect);
    }

    public AchievementDayEntity() {
    }

    protected AchievementDayEntity(Parcel in) {
        this.dayCarriage = in.readFloat();
        this.dayOrder = in.readInt();
        this.dayCod = in.readFloat();
        this.dayAddedFee = in.readFloat();
        this.dayCollectOrder = in.readInt();
        this.dayCollect = in.readFloat();
    }

    public static final Parcelable.Creator<AchievementDayEntity> CREATOR = new Parcelable.Creator<AchievementDayEntity>() {
        @Override
        public AchievementDayEntity createFromParcel(Parcel source) {
            return new AchievementDayEntity(source);
        }

        @Override
        public AchievementDayEntity[] newArray(int size) {
            return new AchievementDayEntity[size];
        }
    };
}
