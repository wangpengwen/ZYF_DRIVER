package com.zywl.model.entity.home;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TCJK on 2018/3/29.
 */
public class CarNoSelectTypeEntity implements Parcelable {

    String typeName;

    int type;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.typeName);
        dest.writeInt(this.type);
    }

    public CarNoSelectTypeEntity() {
    }

    protected CarNoSelectTypeEntity(Parcel in) {
        this.typeName = in.readString();
        this.type = in.readInt();
    }

    public static final Parcelable.Creator<CarNoSelectTypeEntity> CREATOR = new Parcelable.Creator<CarNoSelectTypeEntity>() {
        @Override
        public CarNoSelectTypeEntity createFromParcel(Parcel source) {
            return new CarNoSelectTypeEntity(source);
        }

        @Override
        public CarNoSelectTypeEntity[] newArray(int size) {
            return new CarNoSelectTypeEntity[size];
        }
    };
}
