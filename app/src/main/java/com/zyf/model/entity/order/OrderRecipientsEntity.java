package com.zyf.model.entity.order;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TCJK on 2018/6/6.
 */

public class OrderRecipientsEntity implements Parcelable {

    String reciverId;

    String reciverNum;

    String reciverName;

    String reciverPhone;

    String reciverProvince;

    String reciverCity;

    String reciverArea;

    String reciverAddr;

    public String getReciverId() {
        return reciverId;
    }

    public void setReciverId(String reciverId) {
        this.reciverId = reciverId;
    }

    public String getReciverNum() {
        return reciverNum;
    }

    public void setReciverNum(String reciverNum) {
        this.reciverNum = reciverNum;
    }

    public String getReciverName() {
        return reciverName;
    }

    public void setReciverName(String reciverName) {
        this.reciverName = reciverName;
    }

    public String getReciverPhone() {
        return reciverPhone;
    }

    public void setReciverPhone(String reciverPhone) {
        this.reciverPhone = reciverPhone;
    }

    public String getReciverProvince() {
        return reciverProvince;
    }

    public void setReciverProvince(String reciverProvince) {
        this.reciverProvince = reciverProvince;
    }

    public String getReciverCity() {
        return reciverCity;
    }

    public void setReciverCity(String reciverCity) {
        this.reciverCity = reciverCity;
    }

    public String getReciverArea() {
        return reciverArea;
    }

    public void setReciverArea(String reciverArea) {
        this.reciverArea = reciverArea;
    }

    public String getReciverAddr() {
        return reciverAddr;
    }

    public void setReciverAddr(String reciverAddr) {
        this.reciverAddr = reciverAddr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.reciverId);
        dest.writeString(this.reciverNum);
        dest.writeString(this.reciverName);
        dest.writeString(this.reciverPhone);
        dest.writeString(this.reciverProvince);
        dest.writeString(this.reciverCity);
        dest.writeString(this.reciverArea);
        dest.writeString(this.reciverAddr);
    }

    public OrderRecipientsEntity() {
    }

    protected OrderRecipientsEntity(Parcel in) {
        this.reciverId = in.readString();
        this.reciverNum = in.readString();
        this.reciverName = in.readString();
        this.reciverPhone = in.readString();
        this.reciverProvince = in.readString();
        this.reciverCity = in.readString();
        this.reciverArea = in.readString();
        this.reciverAddr = in.readString();
    }

    public static final Parcelable.Creator<OrderRecipientsEntity> CREATOR = new Parcelable.Creator<OrderRecipientsEntity>() {
        @Override
        public OrderRecipientsEntity createFromParcel(Parcel source) {
            return new OrderRecipientsEntity(source);
        }

        @Override
        public OrderRecipientsEntity[] newArray(int size) {
            return new OrderRecipientsEntity[size];
        }
    };
}
