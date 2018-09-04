package com.zywl.ui.user.achievement;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.zywl.model.entity.achievement.AchievementEntity;

/**
 * Created by TCJK on 2018/5/29.
 */

public class AchievementViewModel extends BaseViewModel {

    protected MutableLiveData<AchievementEntity> dataLiveData = new MutableLiveData<>();

    public void getData(){

        submitRequest(AchievementModel.data(), r -> {

            if(r.isOk()){
                dataLiveData.postValue(r.data);
            }else {
                sendError(r);
            }
        });
    }

    public MutableLiveData<AchievementEntity> getDataLiveData() {
        return dataLiveData;
    }
}
