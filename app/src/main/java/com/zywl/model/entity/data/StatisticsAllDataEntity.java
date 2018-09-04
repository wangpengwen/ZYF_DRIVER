package com.zywl.model.entity.data;

/**
 * Created by TCJK on 2018/4/18.
 * 统计数据集合，通过3个接口的返回值拼装
 * 分别是 当天个人/区域统计数据
 *       本周个人/区域统计数据
 *       本周每天个人/区域实际与目标 统计图数据
 */
public class StatisticsAllDataEntity {

    StatisticsEntity dayStatisticsEntity;

    StatisticsEntity weekStatisticsEntity;

    StatisticsChartEntity chartStatisticsEntity;

    public StatisticsEntity getDayStatisticsEntity() {
        return dayStatisticsEntity;
    }

    public void setDayStatisticsEntity(StatisticsEntity dayStatisticsEntity) {
        this.dayStatisticsEntity = dayStatisticsEntity;
    }

    public StatisticsEntity getWeekStatisticsEntity() {
        return weekStatisticsEntity;
    }

    public void setWeekStatisticsEntity(StatisticsEntity weekStatisticsEntity) {
        this.weekStatisticsEntity = weekStatisticsEntity;
    }

    public StatisticsChartEntity getChartStatisticsEntity() {
        return chartStatisticsEntity;
    }

    public void setChartStatisticsEntity(StatisticsChartEntity chartStatisticsEntity) {
        this.chartStatisticsEntity = chartStatisticsEntity;
    }
}
