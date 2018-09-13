package com.zyf.model.entity.order;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Title: WebOrderEntity
 * Description:
 * Copyright:Copyright(c)2018
 * Company:同城酒库电子商务有限公司
 * CreateTime:2018/9/13  11:52
 *
 * @author liutong
 */
public class WebOrderEntity implements Parcelable {

    /**
     * webId : 12
     * webInsDate : 2018-09-12 16:52:24.0
     * orderNum : OD854718091200038
     * webCarriage : 0.1
     * webDistance : 0
     * startAddr : 另一r
     * startX : 36.67748425301759
     * startY : 117.13481677620857
     * startName : 郝晓龙
     * startPhone : 18612444860
     * endAddr : 鑫盛大厦
     * endX : 36.677490
     * endY : 117.134830
     * endName : 山东中云物流
     * endPhone : 18553106667
     * cargoAmount : 1
     * cargoHeight : 1
     * cargoWeight : 1
     * cargoLong : 1
     * cargoWide : 1
     * cargoName : 机械设备、电器
     * webDrvId : 10
     * webIsLast : 0
     */

    public String webId;
    public String webInsDate;
    public String orderNum;
    public float webCarriage;
    public float webDistance;
    public String startAddr;
    public String startX;
    public String startY;
    public String startName;
    public String startPhone;
    public String endAddr;
    public String endX;
    public String endY;
    public String endName;
    public String endPhone;
    public int cargoAmount;
    public float cargoHeight;
    public float cargoWeight;
    public float cargoLong;
    public float cargoWide;
    public String cargoName;
    public String webDrvId;
    public int webIsLast;

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId;
    }

    public String getWebInsDate() {
        return webInsDate;
    }

    public void setWebInsDate(String webInsDate) {
        this.webInsDate = webInsDate;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public float getWebCarriage() {
        return webCarriage;
    }

    public void setWebCarriage(float webCarriage) {
        this.webCarriage = webCarriage;
    }

    public float getWebDistance() {
        return webDistance;
    }

    public void setWebDistance(float webDistance) {
        this.webDistance = webDistance;
    }

    public String getStartAddr() {
        return startAddr;
    }

    public void setStartAddr(String startAddr) {
        this.startAddr = startAddr;
    }

    public String getStartX() {
        return startX;
    }

    public void setStartX(String startX) {
        this.startX = startX;
    }

    public String getStartY() {
        return startY;
    }

    public void setStartY(String startY) {
        this.startY = startY;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getStartPhone() {
        return startPhone;
    }

    public void setStartPhone(String startPhone) {
        this.startPhone = startPhone;
    }

    public String getEndAddr() {
        return endAddr;
    }

    public void setEndAddr(String endAddr) {
        this.endAddr = endAddr;
    }

    public String getEndX() {
        return endX;
    }

    public void setEndX(String endX) {
        this.endX = endX;
    }

    public String getEndY() {
        return endY;
    }

    public void setEndY(String endY) {
        this.endY = endY;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public String getEndPhone() {
        return endPhone;
    }

    public void setEndPhone(String endPhone) {
        this.endPhone = endPhone;
    }

    public int getCargoAmount() {
        return cargoAmount;
    }

    public void setCargoAmount(int cargoAmount) {
        this.cargoAmount = cargoAmount;
    }

    public float getCargoHeight() {
        return cargoHeight;
    }

    public void setCargoHeight(float cargoHeight) {
        this.cargoHeight = cargoHeight;
    }

    public float getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(float cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public float getCargoLong() {
        return cargoLong;
    }

    public void setCargoLong(float cargoLong) {
        this.cargoLong = cargoLong;
    }

    public float getCargoWide() {
        return cargoWide;
    }

    public void setCargoWide(float cargoWide) {
        this.cargoWide = cargoWide;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getWebDrvId() {
        return webDrvId;
    }

    public void setWebDrvId(String webDrvId) {
        this.webDrvId = webDrvId;
    }

    public int getWebIsLast() {
        return webIsLast;
    }

    public void setWebIsLast(int webIsLast) {
        this.webIsLast = webIsLast;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.webId);
        dest.writeString(this.webInsDate);
        dest.writeString(this.orderNum);
        dest.writeFloat(this.webCarriage);
        dest.writeFloat(this.webDistance);
        dest.writeString(this.startAddr);
        dest.writeString(this.startX);
        dest.writeString(this.startY);
        dest.writeString(this.startName);
        dest.writeString(this.startPhone);
        dest.writeString(this.endAddr);
        dest.writeString(this.endX);
        dest.writeString(this.endY);
        dest.writeString(this.endName);
        dest.writeString(this.endPhone);
        dest.writeInt(this.cargoAmount);
        dest.writeFloat(this.cargoHeight);
        dest.writeFloat(this.cargoWeight);
        dest.writeFloat(this.cargoLong);
        dest.writeFloat(this.cargoWide);
        dest.writeString(this.cargoName);
        dest.writeString(this.webDrvId);
        dest.writeInt(this.webIsLast);
    }

    public WebOrderEntity() {
    }

    protected WebOrderEntity(Parcel in) {
        this.webId = in.readString();
        this.webInsDate = in.readString();
        this.orderNum = in.readString();
        this.webCarriage = in.readFloat();
        this.webDistance = in.readFloat();
        this.startAddr = in.readString();
        this.startX = in.readString();
        this.startY = in.readString();
        this.startName = in.readString();
        this.startPhone = in.readString();
        this.endAddr = in.readString();
        this.endX = in.readString();
        this.endY = in.readString();
        this.endName = in.readString();
        this.endPhone = in.readString();
        this.cargoAmount = in.readInt();
        this.cargoHeight = in.readFloat();
        this.cargoWeight = in.readFloat();
        this.cargoLong = in.readFloat();
        this.cargoWide = in.readFloat();
        this.cargoName = in.readString();
        this.webDrvId = in.readString();
        this.webIsLast = in.readInt();
    }

    public static final Parcelable.Creator<WebOrderEntity> CREATOR = new Parcelable.Creator<WebOrderEntity>() {
        @Override
        public WebOrderEntity createFromParcel(Parcel source) {
            return new WebOrderEntity(source);
        }

        @Override
        public WebOrderEntity[] newArray(int size) {
            return new WebOrderEntity[size];
        }
    };
}
