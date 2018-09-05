package com.zyf.model.entity.achievement;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TCJK on 2018/7/16.
 */

public class AchievementDataEntity implements Parcelable {

    int auth;

    int sum;

    int notAuth;

    float timeCarriage;

    int monthCount;

    int dayCount;

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getNotAuth() {
        return notAuth;
    }

    public void setNotAuth(int notAuth) {
        this.notAuth = notAuth;
    }

    public float getTimeCarriage() {
        return timeCarriage;
    }

    public void setTimeCarriage(float timeCarriage) {
        this.timeCarriage = timeCarriage;
    }

    public int getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.auth);
        dest.writeInt(this.sum);
        dest.writeInt(this.notAuth);
        dest.writeFloat(this.timeCarriage);
        dest.writeInt(this.monthCount);
        dest.writeInt(this.dayCount);
    }

    public AchievementDataEntity() {
    }

    protected AchievementDataEntity(Parcel in) {
        this.auth = in.readInt();
        this.sum = in.readInt();
        this.notAuth = in.readInt();
        this.timeCarriage = in.readFloat();
        this.monthCount = in.readInt();
        this.dayCount = in.readInt();
    }

    public static final Parcelable.Creator<AchievementDataEntity> CREATOR = new Parcelable.Creator<AchievementDataEntity>() {
        @Override
        public AchievementDataEntity createFromParcel(Parcel source) {
            return new AchievementDataEntity(source);
        }

        @Override
        public AchievementDataEntity[] newArray(int size) {
            return new AchievementDataEntity[size];
        }
    };
}
