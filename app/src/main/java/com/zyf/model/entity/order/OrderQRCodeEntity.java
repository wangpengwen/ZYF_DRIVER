package com.zyf.model.entity.order;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by TCJK on 2018/6/8.
 */

public class OrderQRCodeEntity implements Parcelable {

    String orderNum;

    List<String> urls;

    String reciverName;

    String reciveraddr;

    String reciverPhone;

    String reciverCity;

    String senderPhone;

    String senderName;

    String senderaddr;

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

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
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

    public String getSenderaddr() {
        return senderaddr;
    }

    public void setSenderaddr(String senderaddr) {
        this.senderaddr = senderaddr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderNum);
        dest.writeStringList(this.urls);
        dest.writeString(this.reciverName);
        dest.writeString(this.reciveraddr);
        dest.writeString(this.reciverPhone);
        dest.writeString(this.reciverCity);
        dest.writeString(this.senderPhone);
        dest.writeString(this.senderName);
        dest.writeString(this.senderaddr);
        dest.writeString(this.cargoAmount);
        dest.writeString(this.cargoHeight);
        dest.writeString(this.cargoWeight);
        dest.writeString(this.cargoLong);
        dest.writeString(this.cargoWide);
        dest.writeString(this.cargoName);
    }

    public OrderQRCodeEntity() {
    }

    protected OrderQRCodeEntity(Parcel in) {
        this.orderNum = in.readString();
        this.urls = in.createStringArrayList();
        this.reciverName = in.readString();
        this.reciveraddr = in.readString();
        this.reciverPhone = in.readString();
        this.reciverCity = in.readString();
        this.senderPhone = in.readString();
        this.senderName = in.readString();
        this.senderaddr = in.readString();
        this.cargoAmount = in.readString();
        this.cargoHeight = in.readString();
        this.cargoWeight = in.readString();
        this.cargoLong = in.readString();
        this.cargoWide = in.readString();
        this.cargoName = in.readString();
    }

    public static final Creator<OrderQRCodeEntity> CREATOR = new Creator<OrderQRCodeEntity>() {
        @Override
        public OrderQRCodeEntity createFromParcel(Parcel source) {
            return new OrderQRCodeEntity(source);
        }

        @Override
        public OrderQRCodeEntity[] newArray(int size) {
            return new OrderQRCodeEntity[size];
        }
    };
}
