package com.zywl.model.entity.goods;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TCJK on 2018/6/5.
 */

public class StationEntity implements Parcelable {

    String stationNum;

    String stationProvince;

    String stationPhone;

    String stationCity;

    String stationArea;

    String stationComnum;

    String stationAddr;

    float stationX;

    float stationY;

    String stationName;

    public String getStationNum() {
        return stationNum;
    }

    public void setStationNum(String stationNum) {
        this.stationNum = stationNum;
    }

    public String getStationProvince() {
        return stationProvince;
    }

    public void setStationProvince(String stationProvince) {
        this.stationProvince = stationProvince;
    }

    public String getStationPhone() {
        return stationPhone;
    }

    public void setStationPhone(String stationPhone) {
        this.stationPhone = stationPhone;
    }

    public String getStationCity() {
        return stationCity;
    }

    public void setStationCity(String stationCity) {
        this.stationCity = stationCity;
    }

    public String getStationArea() {
        return stationArea;
    }

    public void setStationArea(String stationArea) {
        this.stationArea = stationArea;
    }

    public String getStationComnum() {
        return stationComnum;
    }

    public void setStationComnum(String stationComnum) {
        this.stationComnum = stationComnum;
    }

    public String getStationAddr() {
        return stationAddr;
    }

    public void setStationAddr(String stationAddr) {
        this.stationAddr = stationAddr;
    }

    public float getStationX() {
        return stationX;
    }

    public void setStationX(float stationX) {
        this.stationX = stationX;
    }

    public float getStationY() {
        return stationY;
    }

    public void setStationY(float stationY) {
        this.stationY = stationY;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.stationNum);
        dest.writeString(this.stationProvince);
        dest.writeString(this.stationPhone);
        dest.writeString(this.stationCity);
        dest.writeString(this.stationArea);
        dest.writeString(this.stationComnum);
        dest.writeString(this.stationAddr);
        dest.writeFloat(this.stationX);
        dest.writeFloat(this.stationY);
        dest.writeString(this.stationName);
    }

    public StationEntity() {
    }

    protected StationEntity(Parcel in) {
        this.stationNum = in.readString();
        this.stationProvince = in.readString();
        this.stationPhone = in.readString();
        this.stationCity = in.readString();
        this.stationArea = in.readString();
        this.stationComnum = in.readString();
        this.stationAddr = in.readString();
        this.stationX = in.readFloat();
        this.stationY = in.readFloat();
        this.stationName = in.readString();
    }

    public static final Parcelable.Creator<StationEntity> CREATOR = new Parcelable.Creator<StationEntity>() {
        @Override
        public StationEntity createFromParcel(Parcel source) {
            return new StationEntity(source);
        }

        @Override
        public StationEntity[] newArray(int size) {
            return new StationEntity[size];
        }
    };
}
