package com.zywl.model.entity.order;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TCJK on 2018/4/14.
 */

public class PrePayEntity implements Parcelable {

    String orderNum;

    String url;

    String reciverName;

    String reciveraddr;

    String reciverPhone;

    String reciverCity;

    String senderPhone;

    String senderName;

    String senderProvince;

    String senderCity;

    String senderArea;

    String senderAddr;

    public String cargoAmount;

    public String cargoHeight;

    public String cargoWeight;

    public String cargoLong;

    public String cargoWide;

    public String cargoName;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReciverName() {
        return reciverName;
    }

    public void setReciverName(String reciverName) {
        this.reciverName = reciverName;
    }

    public String getReciveraddr() {
        return reciveraddr;
    }

    public void setReciveraddr(String reciveraddr) {
        this.reciveraddr = reciveraddr;
    }

    public String getReciverPhone() {
        return reciverPhone;
    }

    public void setReciverPhone(String reciverPhone) {
        this.reciverPhone = reciverPhone;
    }

    public String getReciverCity() {
        return reciverCity;
    }

    public void setReciverCity(String reciverCity) {
        this.reciverCity = reciverCity;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderProvince() {
        return senderProvince;
    }

    public void setSenderProvince(String senderProvince) {
        this.senderProvince = senderProvince;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getSenderArea() {
        return senderArea;
    }

    public void setSenderArea(String senderArea) {
        this.senderArea = senderArea;
    }

    public String getSenderAddr() {
        return senderAddr;
    }

    public void setSenderAddr(String senderAddr) {
        this.senderAddr = senderAddr;
    }

    public String getCargoAmount() {
        return cargoAmount;
    }

    public void setCargoAmount(String cargoAmount) {
        this.cargoAmount = cargoAmount;
    }

    public String getCargoHeight() {
        return cargoHeight;
    }

    public void setCargoHeight(String cargoHeight) {
        this.cargoHeight = cargoHeight;
    }

    public String getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(String cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public String getCargoLong() {
        return cargoLong;
    }

    public void setCargoLong(String cargoLong) {
        this.cargoLong = cargoLong;
    }

    public String getCargoWide() {
        return cargoWide;
    }

    public void setCargoWide(String cargoWide) {
        this.cargoWide = cargoWide;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderNum);
        dest.writeString(this.url);
        dest.writeString(this.reciverName);
        dest.writeString(this.reciveraddr);
        dest.writeString(this.reciverPhone);
        dest.writeString(this.reciverCity);
        dest.writeString(this.senderPhone);
        dest.writeString(this.senderName);
        dest.writeString(this.senderProvince);
        dest.writeString(this.senderCity);
        dest.writeString(this.senderArea);
        dest.writeString(this.senderAddr);
        dest.writeString(this.cargoAmount);
        dest.writeString(this.cargoHeight);
        dest.writeString(this.cargoWeight);
        dest.writeString(this.cargoLong);
        dest.writeString(this.cargoWide);
        dest.writeString(this.cargoName);
    }

    public PrePayEntity() {
    }

    protected PrePayEntity(Parcel in) {
        this.orderNum = in.readString();
        this.url = in.readString();
        this.reciverName = in.readString();
        this.reciveraddr = in.readString();
        this.reciverPhone = in.readString();
        this.reciverCity = in.readString();
        this.senderPhone = in.readString();
        this.senderName = in.readString();
        this.senderProvince = in.readString();
        this.senderCity = in.readString();
        this.senderArea = in.readString();
        this.senderAddr = in.readString();
        this.cargoAmount = in.readString();
        this.cargoHeight = in.readString();
        this.cargoWeight = in.readString();
        this.cargoLong = in.readString();
        this.cargoWide = in.readString();
        this.cargoName = in.readString();
    }

    public static final Parcelable.Creator<PrePayEntity> CREATOR = new Parcelable.Creator<PrePayEntity>() {
        @Override
        public PrePayEntity createFromParcel(Parcel source) {
            return new PrePayEntity(source);
        }

        @Override
        public PrePayEntity[] newArray(int size) {
            return new PrePayEntity[size];
        }
    };
}
