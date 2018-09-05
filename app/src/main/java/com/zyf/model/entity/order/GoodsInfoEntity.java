package com.zyf.model.entity.order;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TCJK on 2018/6/4.
 */

public class GoodsInfoEntity implements Parcelable {

    public String cargoName;

    public String cargoWeight;

    public String cargoAmount;

    public String cargoLong;

    public String cargoWide;

    public String cargoHeight;

    public String cargoId;

    public String cargoNum;

    public String cargoType;

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(String cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public String getCargoAmount() {
        return cargoAmount;
    }

    public void setCargoAmount(String cargoAmount) {
        this.cargoAmount = cargoAmount;
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

    public String getCargoHeight() {
        return cargoHeight;
    }

    public void setCargoHeight(String cargoHeight) {
        this.cargoHeight = cargoHeight;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }

    public String getCargoNum() {
        return cargoNum;
    }

    public void setCargoNum(String cargoNum) {
        this.cargoNum = cargoNum;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cargoName);
        dest.writeString(this.cargoWeight);
        dest.writeString(this.cargoAmount);
        dest.writeString(this.cargoLong);
        dest.writeString(this.cargoWide);
        dest.writeString(this.cargoHeight);
        dest.writeString(this.cargoId);
        dest.writeString(this.cargoNum);
        dest.writeString(this.cargoType);
    }

    public GoodsInfoEntity() {
    }

    protected GoodsInfoEntity(Parcel in) {
        this.cargoName = in.readString();
        this.cargoWeight = in.readString();
        this.cargoAmount = in.readString();
        this.cargoLong = in.readString();
        this.cargoWide = in.readString();
        this.cargoHeight = in.readString();
        this.cargoId = in.readString();
        this.cargoNum = in.readString();
        this.cargoType = in.readString();
    }

    public static final Creator<GoodsInfoEntity> CREATOR = new Creator<GoodsInfoEntity>() {
        @Override
        public GoodsInfoEntity createFromParcel(Parcel source) {
            return new GoodsInfoEntity(source);
        }

        @Override
        public GoodsInfoEntity[] newArray(int size) {
            return new GoodsInfoEntity[size];
        }
    };
}
