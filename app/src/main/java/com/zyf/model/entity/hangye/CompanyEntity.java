package com.zyf.model.entity.hangye;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ltxxx on 2018/6/28.
 */

public class CompanyEntity implements Parcelable {

    String logisticName;

    String logisticRoad;

    String logisticPhone;

    String logisticAddr;

    String logisticLine;

    public String getLogisticName() {
        return logisticName;
    }

    public void setLogisticName(String logisticName) {
        this.logisticName = logisticName;
    }

    public String getLogisticRoad() {
        return logisticRoad;
    }

    public void setLogisticRoad(String logisticRoad) {
        this.logisticRoad = logisticRoad;
    }

    public String getLogisticPhone() {
        return logisticPhone;
    }

    public void setLogisticPhone(String logisticPhone) {
        this.logisticPhone = logisticPhone;
    }

    public String getLogisticAddr() {
        return logisticAddr;
    }

    public void setLogisticAddr(String logisticAddr) {
        this.logisticAddr = logisticAddr;
    }

    public String getLogisticLine() {
        return logisticLine;
    }

    public void setLogisticLine(String logisticLine) {
        this.logisticLine = logisticLine;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.logisticName);
        dest.writeString(this.logisticRoad);
        dest.writeString(this.logisticPhone);
        dest.writeString(this.logisticAddr);
        dest.writeString(this.logisticLine);
    }

    public CompanyEntity() {
    }

    protected CompanyEntity(Parcel in) {
        this.logisticName = in.readString();
        this.logisticRoad = in.readString();
        this.logisticPhone = in.readString();
        this.logisticAddr = in.readString();
        this.logisticLine = in.readString();
    }

    public static final Parcelable.Creator<CompanyEntity> CREATOR = new Parcelable.Creator<CompanyEntity>() {
        @Override
        public CompanyEntity createFromParcel(Parcel source) {
            return new CompanyEntity(source);
        }

        @Override
        public CompanyEntity[] newArray(int size) {
            return new CompanyEntity[size];
        }
    };
}
