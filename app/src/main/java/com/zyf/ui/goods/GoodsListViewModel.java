package com.zyf.ui.goods;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.zyf.model.GoodsModel;
import com.zyf.model.entity.goods.GoodsEntity;

import java.util.List;

/**
 * Created by TCJK on 2018/5/21.
 */

public class GoodsListViewModel extends BaseViewModel {

    protected MutableLiveData<List<GoodsEntity>> goodsLiveData = new MutableLiveData<>();
    protected MutableLiveData<String> createLiveData = new MutableLiveData<>();

    public void goodsList(){

        submitRequest(GoodsModel.goodsList(), objectResponseJson -> {

            if(objectResponseJson.isOk()){

                goodsLiveData.postValue(objectResponseJson.data);
            }else {
                sendError(objectResponseJson);
            }

        });
    }

    public void createGoods(String goodsName,String startCode,String endCode,String carCode){

        submitRequest(GoodsModel.createGoods(goodsName,startCode,endCode,carCode), objectResponseJson -> {

            if(objectResponseJson.isOk()){

                createLiveData.postValue(objectResponseJson.data);
            }else {
                sendError(objectResponseJson);
            }

        });
    }

    public MutableLiveData<List<GoodsEntity>> getGoodsLiveData() {
        return goodsLiveData;
    }

    public MutableLiveData<String> getCreateLiveData() {
        return createLiveData;
    }
}
