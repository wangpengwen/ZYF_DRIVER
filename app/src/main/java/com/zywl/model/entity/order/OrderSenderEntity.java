package com.zywl.model.entity.order;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TCJK on 2018/6/6.
 */

public class OrderSenderEntity implements Parcelable {

    String senderNum;

    String senderName;

    String senderProvince;

    String senderPhone;

    String senderCity;

    String senderArea;

    String senderAddr;

    String senderIdcard;

    String senderSexual;

    String senderBirthday;

    String senderOrderNum;

    public String getSenderNum() {
        return senderNum;
    }

    public void setSenderNum(String senderNum) {
        this.senderNum = senderNum;
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

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
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

    public String getSenderIdcard() {
        return senderIdcard == null ? "" : senderIdcard;
    }

    public void setSenderIdcard(String senderIdcard) {
        this.senderIdcard = senderIdcard;
    }

    public String getSenderSexual() {
        return senderSexual == null ? "" : senderSexual;
    }

    public void setSenderSexual(String senderSexual) {
        this.senderSexual = senderSexual;
    }

    public String getSenderBirthday() {
        return senderBirthday;
    }

    public void setSenderBirthday(String senderBirthday) {
        this.senderBirthday = senderBirthday;
    }

    public String getSenderOrderNum() {
        return senderOrderNum;
    }

    public void setSenderOrderNum(String senderOrderNum) {
        this.senderOrderNum = senderOrderNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.senderNum);
        dest.writeString(this.senderName);
        dest.writeString(this.senderProvince);
        dest.writeString(this.senderPhone);
        dest.writeString(this.senderCity);
        dest.writeString(this.senderArea);
        dest.writeString(this.senderAddr);
        dest.writeString(this.senderIdcard);
        dest.writeString(this.senderSexual);
        dest.writeString(this.senderBirthday);
        dest.writeString(this.senderOrderNum);
    }

    public OrderSenderEntity() {
    }

    protected OrderSenderEntity(Parcel in) {
        this.senderNum = in.readString();
        this.senderName = in.readString();
        this.senderProvince = in.readString();
        this.senderPhone = in.readString();
        this.senderCity = in.readString();
        this.senderArea = in.readString();
        this.senderAddr = in.readString();
        this.senderIdcard = in.readString();
        this.senderSexual = in.readString();
        this.senderBirthday = in.readString();
        this.senderOrderNum = in.readString();
    }

    public static final Parcelable.Creator<OrderSenderEntity> CREATOR = new Parcelable.Creator<OrderSenderEntity>() {
        @Override
        public OrderSenderEntity createFromParcel(Parcel source) {
            return new OrderSenderEntity(source);
        }

        @Override
        public OrderSenderEntity[] newArray(int size) {
            return new OrderSenderEntity[size];
        }
    };
}
