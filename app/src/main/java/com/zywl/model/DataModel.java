package com.zywl.model;

import com.biz.http.ParaConfig;
import com.biz.http.ResponseJson;
import com.biz.http.RestMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.zywl.model.entity.data.AreaRankEntity;
import com.zywl.model.entity.data.IncomeTodayEntity;
import com.zywl.model.entity.data.PersonRankEntity;
import com.zywl.model.entity.data.StatisticsChartEntity;
import com.zywl.model.entity.data.StatisticsEntity;
import com.zywl.net.RestRequest;
import com.zywl.util.TimeUtil;

import java.util.List;

import rx.Observable;

/**
 * Created by ltxxx on 2018/4/15.
 */

public class DataModel {



    public static Observable<ResponseJson<IncomeTodayEntity>> getIncomeToday(){

        return RestRequest.<ResponseJson<IncomeTodayEntity>>builder()
                .url("/perforbyday.do")
                .addBody("imei", ParaConfig.getInstance().imei)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<IncomeTodayEntity>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<List<AreaRankEntity>>> getAreaRank(){

        return RestRequest.<ResponseJson<List<AreaRankEntity>>>builder()
                .url("/arearanking.do")
                .addBody("imei", ParaConfig.getInstance().imei)
                .addBody("startTime", TimeUtil.getTodayZero()+"")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<List<AreaRankEntity>>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<List<PersonRankEntity>>> getPersonRank(){

        return RestRequest.<ResponseJson<List<PersonRankEntity>>>builder()
                .url("/adminrecovered.do")
                .addBody("imei", ParaConfig.getInstance().imei)
                .addBody("startTime", TimeUtil.getTodayZero()+"")
//                .addBody("startTime", "1501152700000")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<List<PersonRankEntity>>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<StatisticsEntity>> getStatisticsByDay(){

        return RestRequest.<ResponseJson<StatisticsEntity>>builder()
                .url("/perforbyday.do")
                .addBody("imei", ParaConfig.getInstance().imei)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<StatisticsEntity>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<StatisticsEntity>> getStatisticsByWeek(){

        return RestRequest.<ResponseJson<StatisticsEntity>>builder()
                .url("/perforbyweek.do")
                .addBody("imei", ParaConfig.getInstance().imei)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<StatisticsEntity>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<StatisticsChartEntity>> getStatisticsChartData(){

        return RestRequest.<ResponseJson<StatisticsChartEntity>>builder()
                .url("/perforbyweekdaily.do")
                .addBody("imei", ParaConfig.getInstance().imei)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<StatisticsChartEntity>>() {
                }.getType())
                .requestJson();
    }

}
