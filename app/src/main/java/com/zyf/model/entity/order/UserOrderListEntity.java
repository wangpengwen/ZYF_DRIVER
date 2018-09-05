package com.zyf.model.entity.order;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by TCJK on 2018/6/6.
 */

public class UserOrderListEntity implements Parcelable {

    List<UserOrderItemEntity> alreadyPay;

    List<UserOrderItemEntity> noPay;

    List<UserOrderItemEntity> cod;

    public List<UserOrderItemEntity> getAlreadyPay() {
        return alreadyPay;
    }

    public void setAlreadyPay(List<UserOrderItemEntity> alreadyPay) {
        this.alreadyPay = alreadyPay;
    }

    public List<UserOrderItemEntity> getNoPay() {
        return noPay;
    }

    public void setNoPay(List<UserOrderItemEntity> noPay) {
        this.noPay = noPay;
    }

    public List<UserOrderItemEntity> getCod() {
        return cod;
    }

    public void setCod(List<UserOrderItemEntity> cod) {
        this.cod = cod;
    }

    public UserOrderListEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.alreadyPay);
        dest.writeTypedList(this.noPay);
        dest.writeTypedList(this.cod);
    }

    protected UserOrderListEntity(Parcel in) {
        this.alreadyPay = in.createTypedArrayList(UserOrderItemEntity.CREATOR);
        this.noPay = in.createTypedArrayList(UserOrderItemEntity.CREATOR);
        this.cod = in.createTypedArrayList(UserOrderItemEntity.CREATOR);
    }

    public static final Creator<UserOrderListEntity> CREATOR = new Creator<UserOrderListEntity>() {
        @Override
        public UserOrderListEntity createFromParcel(Parcel source) {
            return new UserOrderListEntity(source);
        }

        @Override
        public UserOrderListEntity[] newArray(int size) {
            return new UserOrderListEntity[size];
        }
    };
}
