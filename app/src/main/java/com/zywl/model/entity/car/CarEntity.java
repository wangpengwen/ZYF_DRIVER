package com.zywl.model.entity.car;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ltxxx on 2018/6/5.
 */

public class CarEntity implements Parcelable {

    String truckNum;

    String truckCarnum;

    String truckType;

    public String getTruckNum() {
        return truckNum;
    }

    public String getTruckCarnum() {
        return truckCarnum;
    }

    public String getTruckType() {
        return truckType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.truckNum);
        dest.writeString(this.truckCarnum);
        dest.writeString(this.truckType);
    }

    public CarEntity() {
    }

    protected CarEntity(Parcel in) {
        this.truckNum = in.readString();
        this.truckCarnum = in.readString();
        this.truckType = in.readString();
    }

    public static final Parcelable.Creator<CarEntity> CREATOR = new Parcelable.Creator<CarEntity>() {
        @Override
        public CarEntity createFromParcel(Parcel source) {
            return new CarEntity(source);
        }

        @Override
        public CarEntity[] newArray(int size) {
            return new CarEntity[size];
        }
    };
}
