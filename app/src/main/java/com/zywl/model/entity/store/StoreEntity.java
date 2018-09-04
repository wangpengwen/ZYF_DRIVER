package com.zywl.model.entity.store;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TCJK on 2018/6/5.
 */

public class StoreEntity implements Parcelable {

    String storageNum;

    String storageSize;

    String storageType;

    String storageAddr;

    String storageState;

    String storageName;

    public String getStorageNum() {
        return storageNum;
    }

    public void setStorageNum(String storageNum) {
        this.storageNum = storageNum;
    }

    public String getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(String storageSize) {
        this.storageSize = storageSize;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getStorageAddr() {
        return storageAddr;
    }

    public void setStorageAddr(String storageAddr) {
        this.storageAddr = storageAddr;
    }

    public String getStorageState() {
        return storageState;
    }

    public void setStorageState(String storageState) {
        this.storageState = storageState;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.storageSize);
        dest.writeString(this.storageNum);
        dest.writeString(this.storageType);
        dest.writeString(this.storageAddr);
        dest.writeString(this.storageState);
        dest.writeString(this.storageName);
    }

    public StoreEntity() {
    }

    protected StoreEntity(Parcel in) {
        this.storageSize = in.readString();
        this.storageNum = in.readString();
        this.storageType = in.readString();
        this.storageAddr = in.readString();
        this.storageState = in.readString();
        this.storageName = in.readString();
    }

    public static final Parcelable.Creator<StoreEntity> CREATOR = new Parcelable.Creator<StoreEntity>() {
        @Override
        public StoreEntity createFromParcel(Parcel source) {
            return new StoreEntity(source);
        }

        @Override
        public StoreEntity[] newArray(int size) {
            return new StoreEntity[size];
        }
    };
}
