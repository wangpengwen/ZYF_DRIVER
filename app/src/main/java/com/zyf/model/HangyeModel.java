package com.zyf.model;


import com.biz.http.ResponseJson;
import com.biz.http.RestMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.zyf.model.entity.hangye.CompanyEntity;
import com.zyf.net.RestRequest;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by ltxxx on 2018/6/28.
 */

public class HangyeModel {

    public static Observable<ResponseJson<List<String>>> findStart(String s){

        return RestRequest.<ResponseJson<List<String>>>builder()
                .url("/business/logicity.do")
                .addBody("city", s)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<List<String>>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<List<String>>> findEnd(String s){

        return RestRequest.<ResponseJson<List<String>>>builder()
                .url("/business/logiroad.do")
                .addBody("road", s)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<List<String>>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<ArrayList<CompanyEntity>>> getListBySE(String start, String end){

        return RestRequest.<ResponseJson<ArrayList<CompanyEntity>>>builder()
                .url("/business/logiInfo.do")
                .addBody("city",start)
                .addBody("road", end)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<ArrayList<CompanyEntity>>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<ArrayList<String>>> findCompany(String company){

        return RestRequest.<ResponseJson<ArrayList<String>>>builder()
                .url("/business/logiName.do")
                .addBody("name",company)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<ArrayList<String>>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<ArrayList<CompanyEntity>>> getListByCompany(String company){

        return RestRequest.<ResponseJson<ArrayList<CompanyEntity>>>builder()
                .url("/business/logiInfoByName.do")
                .addBody("name",company)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<ArrayList<CompanyEntity>>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<ArrayList<String>>> findLine(String line){

        return RestRequest.<ResponseJson<ArrayList<String>>>builder()
                .url("/business/logiline.do")
                .addBody("line",line)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<ArrayList<String>>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<ArrayList<CompanyEntity>>> getListByLine(String line){

        return RestRequest.<ResponseJson<ArrayList<CompanyEntity>>>builder()
                .url("/business/logiInfoByLine.do")
                .addBody("line",line)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<ArrayList<CompanyEntity>>>() {
                }.getType())
                .requestJson();
    }
}
