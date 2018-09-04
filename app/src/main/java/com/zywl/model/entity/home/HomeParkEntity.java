package com.zywl.model.entity.home;

import android.text.TextUtils;

/**
 * Created by TCJK on 2018/3/28.
 */

public class HomeParkEntity {

    private String areaNo;

    private int parkingSpaceNo;

    private int status;

    private boolean isEmpty;

    private String carNo;

    public int getParkNum() {
        return parkingSpaceNo;
    }

    public void setParkNum(int parkNum) {
        this.parkingSpaceNo = parkNum;
    }

    public boolean isEmpty() {
        return status == 0 ? true : false;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
        status = empty ? 0 : 1;
    }

    public String getCarNum() {
        return TextUtils.isEmpty(carNo) ? "" : carNo;
    }

    public void setCarNum(String carNum) {
        this.carNo = carNum;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    public int getParkingSpaceNo() {
        return parkingSpaceNo;
    }

    public void setParkingSpaceNo(int parkingSpaceNo) {
        this.parkingSpaceNo = parkingSpaceNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void carIn(String carNum){
//        this.parkingSpaceNo = parkingSpaceNo;
        this.carNo = carNum;
        this.setEmpty(false);
    }

    public void carOut(){
        this.carNo = "";
        this.setEmpty(true);
    }
}
