package com.zywl.ui.user.achievement;

import android.text.format.DateFormat;

import com.biz.http.ResponseJson;
import com.biz.http.RestMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.zywl.model.UserModel;
import com.zywl.model.entity.UserEntity;
import com.zywl.model.entity.achievement.AchievementEntity;
import com.zywl.net.RestRequest;

import java.util.Calendar;
import java.util.Date;

import rx.Observable;

/**
 * Created by TCJK on 2018/7/16.
 */

public class AchievementModel {

    public static Observable<ResponseJson<AchievementEntity>> data(){

        return RestRequest.<ResponseJson<AchievementEntity>>builder()
                .url("/business/certification.do")
                .addBody("date", DateFormat.format("yyyy-MM-dd", Calendar.getInstance()).toString())
//                .addBody("date", "2018-06-18")
                .addBody("isTime","2")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<AchievementEntity>>() {
                }.getType())
                .requestJson();
    }

}
