package com.zyf.ui.login;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.biz.util.RxUtil;
import com.biz.util.ValidUtil;
import com.zyf.model.UserModel;
import com.zyf.driver.ui.R;

/**
 * Title: LoginViewModel
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/11/17  11:09
 *
 * @author wangwei
 * @version 1.0
 */
public class LoginViewModel extends BaseViewModel {
    private MutableLiveData<Boolean> loginStatus=new MutableLiveData<>();
    protected MutableLiveData<Object> smsLiveData = new MutableLiveData<>();
    protected MutableLiveData<String> mHisLoginUserLiveData = new MutableLiveData<>();
    private String mobile;
    private String smsCode;

    public MutableLiveData<Boolean> getLoginStatus() {
        return loginStatus;
    }

    public MutableLiveData<Object> getSmsLiveData() {
        return smsLiveData;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

//    public void sendSms(){
//        if (!ValidUtil.accountValid(mobile)) {
//            sendError(R.string.error_invalid_account);
//            return;
//        }
//        submitRequest(SmsModel.sendSms(mobile, SmsModel.SMS_TYPE_QUICK_LOGIN), objectResponseJson -> {
//            if (objectResponseJson.isOk()) {
//                smsLiveData.postValue(true);
//            } else sendError(objectResponseJson);
//        });
//    }
//    public void smsLogin(){
//        if (!ValidUtil.phoneNumberValid(mobile)) {
//            sendError(R.string.text_error_mobile_valid);
//            return;
//        }
//        if (TextUtils.isEmpty(smsCode) || smsCode.length() < 3) {
//            sendError(R.string.text_error_sms_code_valid);
//            return;
//        }
//        submitRequest(UserModel.smsLogin(mobile,smsCode),userEntityResponseJson -> {
//            if(userEntityResponseJson.isOk()){
//                RxUtil.newThreadSubscribe(UserModel.saveLoginUser(mobile), s->{}, throwable -> {});
//                loginStatus.postValue(true);
//            }else {
//                sendError(userEntityResponseJson);
//            }
//        });
//    }
    public void login(String account, String pwd){
        if (!ValidUtil.accountValid(account)) {
            sendError(R.string.error_invalid_account);
            return;
        }
        if (!ValidUtil.pwdValid(pwd)) {
            sendError(R.string.error_invalid_password);
            return;
        }
        submitRequest(UserModel.login(account,pwd), userEntityResponseJson -> {
            if(userEntityResponseJson.isOk()){
                RxUtil.newThreadSubscribe(UserModel.saveLoginUser(account), s->{}, throwable -> {});
                loginStatus.postValue(true);
            }else {
                sendError(userEntityResponseJson);
            }
        });
    }
    public void loadHisUser(){
        submitRequest(UserModel.getLoginUser(),s -> {
            mHisLoginUserLiveData.postValue(s);
        },throwable -> {});
    }

    public MutableLiveData<String> getHisLoginUserLiveData() {
        return mHisLoginUserLiveData;
    }
}
