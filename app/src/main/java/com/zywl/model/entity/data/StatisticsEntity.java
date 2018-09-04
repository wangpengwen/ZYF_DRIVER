package com.zywl.model.entity.data;

/**
 * Created by TCJK on 2018/4/18.
 */

public class StatisticsEntity {

    AreaStatisticsEntity areaIncome;

    PersonStatisticsEntity adminIncome;

    public AreaStatisticsEntity getAreaIncome() {
        return areaIncome;
    }

    public void setAreaIncome(AreaStatisticsEntity areaIncome) {
        this.areaIncome = areaIncome;
    }

    public PersonStatisticsEntity getAdminIncome() {
        return adminIncome;
    }

    public void setAdminIncome(PersonStatisticsEntity adminIncome) {
        this.adminIncome = adminIncome;
    }
}
