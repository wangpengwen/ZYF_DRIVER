package com.zyf.model.entity.data;

/**
 * Created by ltxxx on 2018/4/16.
 */

public class AreaRankEntity {

    String areaName;
    /**会员费用*/
    String userCharge;

    String charge;

    String sumCharge;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getUserCharge() {
        return userCharge;
    }

    public void setUserCharge(String userCharge) {
        this.userCharge = userCharge;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getSumCharge() {
        return sumCharge;
    }

    public void setSumCharge(String sumCharge) {
        this.sumCharge = sumCharge;
    }
}
