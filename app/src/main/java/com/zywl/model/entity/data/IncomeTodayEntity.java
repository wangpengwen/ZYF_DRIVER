package com.zywl.model.entity.data;

/**
 * Created by ltxxx on 2018/4/15.
 */

public class IncomeTodayEntity {

    private IncomeTodayAreaEntity areaIncome;

    private IncomeTodayPersonEntity adminIncome;

    public IncomeTodayAreaEntity getAreaIncome() {
        return areaIncome;
    }

    public void setAreaIncome(IncomeTodayAreaEntity areaIncome) {
        this.areaIncome = areaIncome;
    }

    public IncomeTodayPersonEntity getAdminIncome() {
        return adminIncome;
    }

    public void setAdminIncome(IncomeTodayPersonEntity adminIncome) {
        this.adminIncome = adminIncome;
    }

}
