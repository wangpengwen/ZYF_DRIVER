package com.zyf.ui.goods.station;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.biz.http.ResponseJson;
import com.zyf.model.StationModel;
import com.zyf.model.entity.goods.StationEntity;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by TCJK on 2018/6/5.
 */

public class StationViewModel extends BaseViewModel {

    protected MutableLiveData<List<StationEntity>> stationLiveData = new MutableLiveData<>();

    public void findStation(String stationName){

        submitRequest(StationModel.findStation(stationName), objectResponseJson -> {

            if(objectResponseJson.isOk()){

                stationLiveData.postValue(objectResponseJson.data);
            }else {

                sendError(objectResponseJson);
            }

        });

    }

    public MutableLiveData<List<StationEntity>> getStationLiveData() {
        return stationLiveData;
    }
}
