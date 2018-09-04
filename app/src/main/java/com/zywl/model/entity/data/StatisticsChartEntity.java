package com.zywl.model.entity.data;

import java.util.List;

/**
 * Created by TCJK on 2018/4/18.
 */

public class StatisticsChartEntity {

    List<AreaStatisticsEntity> areaIncom;

    List<PersonStatisticsEntity> adminIncom;

    public List<AreaStatisticsEntity> getAreaIncom() {
        return areaIncom;
    }

    public void setAreaIncom(List<AreaStatisticsEntity> areaIncom) {
        this.areaIncom = areaIncom;
    }

    public List<PersonStatisticsEntity> getAdminIncom() {
        return adminIncom;
    }

    public void setAdminIncom(List<PersonStatisticsEntity> adminIncom) {
        this.adminIncom = adminIncom;
    }
}
