package com.zyf.ui.user.register;

import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.biz.base.BaseViewModel;
import com.biz.util.ValidUtil;
import com.zyf.driver.ui.R;
import com.zyf.model.SmsModel;
import com.zyf.model.UserModel;
import com.zyf.model.entity.UserEntity;

/**
 * Title: RegisterPhoneVerifyViewModel
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/11/24  14:16
 *
 * @author wangwei
 * @version 1.0
 */
public class RegisterPhoneVerifyViewModel extends BaseViewModel {
    protected MutableLiveData<Object> smsLiveData = new MutableLiveData<>();
    protected MutableLiveData<UserEntity> registerLiveData = new MutableLiveData<>();
    protected String mobile;
    protected String smsCode;

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public MutableLiveData<Object> getSmsLiveData() {
        return smsLiveData;
    }

    public MutableLiveData<UserEntity> getRegisterLiveData() {
        return registerLiveData;
    }

    public void sendSms() {
        if (!ValidUtil.phoneNumberValid(mobile)) {
            sendError(R.string.text_error_mobile_valid);
            return;
        }
        submitRequest(SmsModel.sendSms(mobile), objectResponseJson -> {
            if (objectResponseJson.isOk()) {
                smsLiveData.postValue(true);
            } else sendError(objectResponseJson);
        });
    }

    public void register() {
        if (!ValidUtil.phoneNumberValid(mobile)) {
            sendError(R.string.text_error_mobile_valid);
            return;
        }
        if (TextUtils.isEmpty(smsCode) || smsCode.length() < 4) {
            sendError(R.string.text_error_sms_code_valid);
            return;
        }
        submitRequest(UserModel.registerUser(mobile, smsCode)
                , objectResponseJson -> {
                    if (objectResponseJson.isOk()) {
                        submitRequest(UserModel.saveLoginUser(mobile),r->{
                            registerLiveData.postValue(objectResponseJson.data);
                        },throwable -> {
                            registerLiveData.postValue(objectResponseJson.data);
                        });
                    } else {
                        sendError(objectResponseJson);
                    }
                });
    }
}
