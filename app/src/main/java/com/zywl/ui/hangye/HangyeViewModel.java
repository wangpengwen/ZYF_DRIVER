package com.zywl.ui.hangye;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.zywl.model.HangyeModel;
import com.zywl.model.entity.hangye.CompanyEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TCJK on 2018/6/25.
 */

public class HangyeViewModel extends BaseViewModel {

    protected MutableLiveData<List<String>> startLiveData = new MutableLiveData<>();
    protected MutableLiveData<List<String>> endLiveData = new MutableLiveData<>();
    protected MutableLiveData<ArrayList<CompanyEntity>> seListLiveData = new MutableLiveData<>();
    protected MutableLiveData<ArrayList<String>> companyLiveData = new MutableLiveData<>();
    protected MutableLiveData<ArrayList<CompanyEntity>> companyListLiveData = new MutableLiveData<>();
    protected MutableLiveData<ArrayList<String>> lineLiveData = new MutableLiveData<>();
    protected MutableLiveData<ArrayList<CompanyEntity>> lineListLiveData = new MutableLiveData<>();

    public void findStart(String s){

        submitRequest(HangyeModel.findStart(s), r -> {

            if(r.isOk()){
                startLiveData.postValue(r.data);
            }else {
                sendError(r);
            }
        });
    }

    public void findEnd(String s){

        submitRequest(HangyeModel.findEnd(s), r -> {

            if(r.isOk()){
                endLiveData.postValue(r.data);
            }else {
                sendError(r);
            }
        });
    }

    public void getListByStartEnd(String start,String end){

        submitRequest(HangyeModel.getListBySE(start, end), r -> {

            if(r.isOk()){
                seListLiveData.postValue(r.data);
            }else {
                sendError(r);
            }
        });
    }

    public void findCompany(String company){

        submitRequest(HangyeModel.findCompany(company), r -> {

            if(r.isOk()){
                companyLiveData.postValue(r.data);
            }else {
                sendError(r);
            }
        });
    }

    public void getListByCompany(String company){

        submitRequest(HangyeModel.getListByCompany(company), r -> {

            if(r.isOk()){
                companyListLiveData.postValue(r.data);
            }else {
                sendError(r);
            }
        });
    }

    public void findLine(String line){

        submitRequest(HangyeModel.findLine(line), r -> {

            if(r.isOk()){
                lineLiveData.postValue(r.data);
            }
        });
    }

    public void getListByLine(String line){

        submitRequest(HangyeModel.getListByLine(line), r -> {

            if(r.isOk()){
                lineListLiveData.postValue(r.data);
            }else {
                sendError(r);
            }
        });
    }

    public MutableLiveData<List<String>> getStartLiveData() {
        return startLiveData;
    }

    public MutableLiveData<List<String>> getEndLiveData() {
        return endLiveData;
    }

    public MutableLiveData<ArrayList<CompanyEntity>> getSeListLiveData() {
        return seListLiveData;
    }

    public MutableLiveData<ArrayList<String>> getCompanyLiveData() {
        return companyLiveData;
    }

    public MutableLiveData<ArrayList<CompanyEntity>> getCompanyListLiveData() {
        return companyListLiveData;
    }

    public MutableLiveData<ArrayList<String>> getLineLiveData() {
        return lineLiveData;
    }

    public MutableLiveData<ArrayList<CompanyEntity>> getLineListLiveData() {
        return lineListLiveData;
    }
}
