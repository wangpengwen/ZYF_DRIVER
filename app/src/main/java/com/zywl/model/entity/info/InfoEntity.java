package com.zywl.model.entity.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TCJK on 2018/3/31.
 */

public class InfoEntity implements Parcelable {

    String id;

    String orderId;

    String adminNo;

    String carNo;

    int carType;

    long inTime;

    long outTime;

    String areaNo;

    float actualCharge;

    int billType;

    int discountType;

    String inDeviceNo;

    String outDeviceNo;

    float payableCharge;

    float discountCharge;


    String inPic;

    String outPic;

    //-------------入场记录独有------------
    String pic;

    String deviceNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(String adminNo) {
        this.adminNo = adminNo;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public int getCarType() {
        return carType;
    }

    public void setCarType(int carType) {
        this.carType = carType;
    }

    public long getInTime() {
        return inTime;
    }

    public void setInTime(long inTime) {
        this.inTime = inTime;
    }

    public long getOutTime() {
        return outTime;
    }

    public void setOutTime(long outTime) {
        this.outTime = outTime;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    public float getActualCharge() {
        return actualCharge;
    }

    public void setActualCharge(float actualCharge) {
        this.actualCharge = actualCharge;
    }

    public int getBillType() {
        return billType;
    }

    public void setBillType(int billType) {
        this.billType = billType;
    }

    public int getDiscountType() {
        return discountType;
    }

    public void setDiscountType(int discountType) {
        this.discountType = discountType;
    }

    public String getInDeviceNo() {
        return inDeviceNo;
    }

    public void setInDeviceNo(String inDeviceNo) {
        this.inDeviceNo = inDeviceNo;
    }

    public String getOutDeviceNo() {
        return outDeviceNo;
    }

    public void setOutDeviceNo(String outDeviceNo) {
        this.outDeviceNo = outDeviceNo;
    }

    public float getPayableCharge() {
        return payableCharge;
    }

    public void setPayableCharge(float payableCharge) {
        this.payableCharge = payableCharge;
    }

    public float getDiscountCharge() {
        return discountCharge;
    }

    public void setDiscountCharge(float discountCharge) {
        this.discountCharge = discountCharge;
    }

    public String getInPic() {
        return inPic;
    }

    public void setInPic(String inPic) {
        this.inPic = inPic;
    }

    public String getOutPic() {
        return outPic;
    }

    public void setOutPic(String outPic) {
        this.outPic = outPic;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.orderId);
        dest.writeString(this.adminNo);
        dest.writeString(this.carNo);
        dest.writeInt(this.carType);
        dest.writeLong(this.inTime);
        dest.writeLong(this.outTime);
        dest.writeString(this.areaNo);
        dest.writeFloat(this.actualCharge);
        dest.writeInt(this.billType);
        dest.writeInt(this.discountType);
        dest.writeString(this.inDeviceNo);
        dest.writeString(this.outDeviceNo);
        dest.writeFloat(this.payableCharge);
        dest.writeFloat(this.discountCharge);
        dest.writeString(this.inPic);
        dest.writeString(this.outPic);
        dest.writeString(this.pic);
        dest.writeString(this.deviceNo);
    }

    public InfoEntity() {
    }

    protected InfoEntity(Parcel in) {
        this.id = in.readString();
        this.orderId = in.readString();
        this.adminNo = in.readString();
        this.carNo = in.readString();
        this.carType = in.readInt();
        this.inTime = in.readLong();
        this.outTime = in.readLong();
        this.areaNo = in.readString();
        this.actualCharge = in.readFloat();
        this.billType = in.readInt();
        this.discountType = in.readInt();
        this.inDeviceNo = in.readString();
        this.outDeviceNo = in.readString();
        this.payableCharge = in.readFloat();
        this.discountCharge = in.readFloat();
        this.inPic = in.readString();
        this.outPic = in.readString();
        this.pic = in.readString();
        this.deviceNo = in.readString();
    }

    public static final Parcelable.Creator<InfoEntity> CREATOR = new Parcelable.Creator<InfoEntity>() {
        @Override
        public InfoEntity createFromParcel(Parcel source) {
            return new InfoEntity(source);
        }

        @Override
        public InfoEntity[] newArray(int size) {
            return new InfoEntity[size];
        }
    };
}
