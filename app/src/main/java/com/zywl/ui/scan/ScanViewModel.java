package com.zywl.ui.scan;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.zywl.model.OrderModel;

/**
 * Title: ScanViewModel
 * Description:
 * Copyright:Copyright(c)2016
 * CreateTime:2017/12/20  11:28
 *
 * @author liutong
 * @version 1.0
 */
public class ScanViewModel extends BaseViewModel {

    protected MutableLiveData<Boolean> intoStorageLiveData = new MutableLiveData<>();

    public void intoStorage(String code){
        submitRequest(OrderModel.intoStorage(code), stringResponseJson -> {
            if(stringResponseJson.isOk()){
                intoStorageLiveData.postValue(true);
            }else {
                sendError(stringResponseJson);
            }
        });
    }

    public MutableLiveData<Boolean> getIntoStorageLiveData() {
        return intoStorageLiveData;
    }
}
