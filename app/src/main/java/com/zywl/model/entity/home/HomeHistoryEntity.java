//package com.lcparking.model.entity.home;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//import android.support.v4.util.TimeUtils;
//
//import com.biz.util.TimeUtil;
//
///**
// * Created by TCJK on 2018/4/3.
// */
//
//public class HomeHistoryEntity implements Parcelable {
//
//    String id;
//
//    String orderId;
//
//    String adminNo;
//
//    String carNo;
//
//    int carType;
//
//    long inTime;
//
//    long outTime;
//
//    String areaNo;
//
//    float actualCharge;
//
//    int billType;
//
//    int discountType;
//
//    String inDeviceNo;
//
//    String outDeviceNo;
//
//    float discountCharge;
//
//    String inPic;
//
//    String outPic;
//
////    int inOutType = -1;
//
//    String time;
//
//    String payStatus;
//
////    String street;
//
//    //-------------入场记录独有------------
//    String pic;
//
//    String deviceNo;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(String orderId) {
//        this.orderId = orderId;
//    }
//
//    public String getCarNo() {
//        return carNo;
//    }
//
//    public void setCarNo(String carNo) {
//        this.carNo = carNo;
//    }
//
//    public int getCarType() {
//        return carType;
//    }
//
//    public void setCarType(int carType) {
//        this.carType = carType;
//    }
//
//    public long getInTime() {
//        return inTime;
//    }
//
//    public void setInTime(long inTime) {
//        this.inTime = inTime;
//    }
//
//    public long getOutTime() {
//        return outTime;
//    }
//
//    public void setOutTime(long outTime) {
//        this.outTime = outTime;
//    }
//
//    public float getActualCharge() {
//        return actualCharge;
//    }
//
//    public void setActualCharge(float actualCharge) {
//        this.actualCharge = actualCharge;
//    }
//
//    public int getBillType() {
//        return billType;
//    }
//
//    public void setBillType(int billType) {
//        this.billType = billType;
//    }
//
//    public int getDiscountType() {
//        return discountType;
//    }
//
//    public void setDiscountType(int discountType) {
//        this.discountType = discountType;
//    }
//
//    public String getInDeviceNo() {
//        return inDeviceNo;
//    }
//
//    public void setInDeviceNo(String inDeviceNo) {
//        this.inDeviceNo = inDeviceNo;
//    }
//
//    public String getOutDeviceNo() {
//        return outDeviceNo;
//    }
//
//    public void setOutDeviceNo(String outDeviceNo) {
//        this.outDeviceNo = outDeviceNo;
//    }
//
//    public float getDiscountCharge() {
//        return discountCharge;
//    }
//
//    public void setDiscountCharge(float discountCharge) {
//        this.discountCharge = discountCharge;
//    }
//
//    public String getAreaNo() {
//        return areaNo;
//    }
//
//    public void setAreaNo(String areaNo) {
//        this.areaNo = areaNo;
//    }
//
//    public String getAdminNo() {
//        return adminNo;
//    }
//
//    public void setAdminNo(String adminNo) {
//        this.adminNo = adminNo;
//    }
//
//    public String getInPic() {
//        return inPic;
//    }
//
//    public void setInPic(String inPic) {
//        this.inPic = inPic;
//    }
//
//    public String getOutPic() {
//        return outPic;
//    }
//
//    public void setOutPic(String outPic) {
//        this.outPic = outPic;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
//
//    public String getPayStatus() {
//        return payStatus;
//    }
//
//    public void setPayStatus(String payStatus) {
//        this.payStatus = payStatus;
//    }
//
//    public String getPic() {
//        return pic;
//    }
//
//    public void setPic(String pic) {
//        this.pic = pic;
//    }
//
//    public String getDeviceNo() {
//        return deviceNo;
//    }
//
//    public void setDeviceNo(String deviceNo) {
//        this.deviceNo = deviceNo;
//    }
//
//    public static Creator<HomeHistoryEntity> getCREATOR() {
//        return CREATOR;
//    }
//
//    public HomeHistoryEntity() {
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(this.id);
//        dest.writeString(this.orderId);
//        dest.writeString(this.carNo);
//        dest.writeInt(this.carType);
//        dest.writeLong(this.inTime);
//        dest.writeLong(this.outTime);
//        dest.writeFloat(this.actualCharge);
//        dest.writeInt(this.billType);
//        dest.writeInt(this.discountType);
//        dest.writeString(this.inDeviceNo);
//        dest.writeString(this.outDeviceNo);
//        dest.writeFloat(this.discountCharge);
//        dest.writeString(this.areaNo);
//        dest.writeString(this.adminNo);
//        dest.writeString(this.inPic);
//        dest.writeString(this.outPic);
//        dest.writeString(this.time);
//        dest.writeString(this.payStatus);
//        dest.writeString(this.pic);
//        dest.writeString(this.deviceNo);
//    }
//
//    protected HomeHistoryEntity(Parcel in) {
//        this.id = in.readString();
//        this.orderId = in.readString();
//        this.carNo = in.readString();
//        this.carType = in.readInt();
//        this.inTime = in.readLong();
//        this.outTime = in.readLong();
//        this.actualCharge = in.readFloat();
//        this.billType = in.readInt();
//        this.discountType = in.readInt();
//        this.inDeviceNo = in.readString();
//        this.outDeviceNo = in.readString();
//        this.discountCharge = in.readFloat();
//        this.areaNo = in.readString();
//        this.adminNo = in.readString();
//        this.inPic = in.readString();
//        this.outPic = in.readString();
//        this.time = in.readString();
//        this.payStatus = in.readString();
//        this.pic = in.readString();
//        this.deviceNo = in.readString();
//    }
//
//    public static final Creator<HomeHistoryEntity> CREATOR = new Creator<HomeHistoryEntity>() {
//        @Override
//        public HomeHistoryEntity createFromParcel(Parcel source) {
//            return new HomeHistoryEntity(source);
//        }
//
//        @Override
//        public HomeHistoryEntity[] newArray(int size) {
//            return new HomeHistoryEntity[size];
//        }
//    };
//}
