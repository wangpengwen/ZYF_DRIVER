package com.zyf.ui.user.store;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.zyf.model.StoreModel;
import com.zyf.model.entity.store.StoreEntity;

import java.util.List;

/**
 * Created by TCJK on 2018/6/5.
 */
public class StoreViewModel extends BaseViewModel {

    protected MutableLiveData<List<StoreEntity>> storeLiveData = new MutableLiveData<>();
    protected MutableLiveData<StoreEntity> bindStoreLiveData = new MutableLiveData<>();

    public void getStoreList(){

        submitRequest(StoreModel.getStoreList(), objectResponseJson -> {
                if(objectResponseJson.isOk()){

                    storeLiveData.postValue(objectResponseJson.data);
                }else {
                    sendError(objectResponseJson);
                }
        });
    }

    public void bindStore(StoreEntity storeEntity){

        submitRequest(StoreModel.bindStore(storeEntity.getStorageNum()), objectResponseJson -> {

            if(objectResponseJson.isOk()){
                bindStoreLiveData.postValue(storeEntity);
            }else {
                sendError(objectResponseJson);
            }
        });
    }

    public MutableLiveData<List<StoreEntity>> getStoreLiveData() {
        return storeLiveData;
    }

    public MutableLiveData<StoreEntity> getBindStoreLiveData() {
        return bindStoreLiveData;
    }
}
