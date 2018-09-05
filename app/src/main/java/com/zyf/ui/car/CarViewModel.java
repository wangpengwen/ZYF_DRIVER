package com.zyf.ui.car;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.zyf.model.CarModel;
import com.zyf.model.entity.car.CarEntity;

import java.util.List;

/**
 * Created by ltxxx on 2018/6/5.
 */

public class CarViewModel extends BaseViewModel {

    protected MutableLiveData<List<CarEntity>> carLiveData = new MutableLiveData<>();

    public void findCar(String carNum){

        submitRequest(CarModel.findCar(carNum), objectResponseJson -> {

            if(objectResponseJson.isOk()){

                carLiveData.postValue(objectResponseJson.data);
            }else {

                sendError(objectResponseJson);
            }
        });
    }

    public MutableLiveData<List<CarEntity>> getCarLiveData() {
        return carLiveData;
    }
}
