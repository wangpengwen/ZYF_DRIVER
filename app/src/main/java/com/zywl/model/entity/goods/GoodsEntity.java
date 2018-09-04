package com.zywl.model.entity.goods;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TCJK on 2018/5/24.
 */

public class GoodsEntity implements Parcelable {

    String manifestNum;

    String manifestName;

    String manifestStart;

    String manifestEnd;

    String cargoCount;

    String car;

    String goodsStatus;

    String receiptStatus;

    String manifestInsTime;


    public String getManifestNum() {
        return manifestNum;
    }

    public void setManifestNum(String manifestNum) {
        this.manifestNum = manifestNum;
    }

    public String getManifestName() {
        return manifestName;
    }

    public void setManifestName(String manifestName) {
        this.manifestName = manifestName;
    }

    public String getManifestStart() {
        return manifestStart;
    }

    public void setManifestStart(String manifestStart) {
        this.manifestStart = manifestStart;
    }

    public String getManifestEnd() {
        return manifestEnd;
    }

    public void setManifestEnd(String manifestEnd) {
        this.manifestEnd = manifestEnd;
    }

    public String getCargoCount() {
        return cargoCount;
    }

    public void setCargoCount(String cargoCount) {
        this.cargoCount = cargoCount;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(String receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    public String getManifestInsTime() {
        return manifestInsTime;
    }

    public void setManifestInsTime(String manifestInsTime) {
        this.manifestInsTime = manifestInsTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.manifestNum);
        dest.writeString(this.manifestName);
        dest.writeString(this.manifestStart);
        dest.writeString(this.manifestEnd);
        dest.writeString(this.cargoCount);
        dest.writeString(this.car);
        dest.writeString(this.goodsStatus);
        dest.writeString(this.receiptStatus);
        dest.writeString(this.manifestInsTime);
    }

    public GoodsEntity() {
    }

    protected GoodsEntity(Parcel in) {
        this.manifestNum = in.readString();
        this.manifestName = in.readString();
        this.manifestStart = in.readString();
        this.manifestEnd = in.readString();
        this.cargoCount = in.readString();
        this.car = in.readString();
        this.goodsStatus = in.readString();
        this.receiptStatus = in.readString();
        this.manifestInsTime = in.readString();
    }

    public static final Creator<GoodsEntity> CREATOR = new Creator<GoodsEntity>() {
        @Override
        public GoodsEntity createFromParcel(Parcel source) {
            return new GoodsEntity(source);
        }

        @Override
        public GoodsEntity[] newArray(int size) {
            return new GoodsEntity[size];
        }
    };
}
