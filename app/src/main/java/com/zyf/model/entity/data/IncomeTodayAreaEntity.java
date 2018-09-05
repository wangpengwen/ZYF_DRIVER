package com.zyf.model.entity.data;

/**
 * Created by ltxxx on 2018/4/15.
 */

public class IncomeTodayAreaEntity {

    String areaName;

    float sumCharge;

    float target;

    float bonus;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public float getSumCharge() {
        return sumCharge;
    }

    public void setSumCharge(float sumCharge) {
        this.sumCharge = sumCharge;
    }

    public float getTarget() {
        return target;
    }

    public void setTarget(float target) {
        this.target = target;
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }
}
