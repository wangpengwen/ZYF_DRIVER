package com.zyf.ui.info;

import android.arch.lifecycle.MutableLiveData;

import com.zyf.model.AuthenticationModel;
import com.zyf.model.UserModel;
import com.zyf.model.ValidateModel;
import com.zyf.model.entity.UserEntity;
import com.zyf.ui.BaseUploadImageViewModel;

/**
 * Created by ltxxx on 2018/9/7.
 */

public class ValidateViewModel extends BaseUploadImageViewModel {

    protected MutableLiveData<UserEntity> vehicleLiveData = new MutableLiveData<>();
    protected MutableLiveData<ImgResult> imageLiveData = new MutableLiveData<>();
    protected MutableLiveData<UserEntity> driverInfoLiveData = new MutableLiveData<>();
    protected MutableLiveData<Object> licenseLiveData = new MutableLiveData<>();

    public void uploadVehicle(String vehicleType,String num){

        submitRequest(AuthenticationModel.bindVehicleInfo(vehicleType,num), r -> {

            if(r.isOk()){
                UserModel.getInstance().getUserEntity().driverTruckNum = r.data.driverTruckNum;
                UserModel.getInstance().getUserEntity().driverNum = num;
                UserModel.getInstance().getUserEntity().driverFlag = r.data.driverFlag;
                UserModel.getInstance().setUserEntity(UserModel.getInstance().getUserEntity());

                vehicleLiveData.postValue(r.data);

            }else {
                sendError(r);
            }
        });
    }

    public void uploadImage(int type,String image) {
        uploadImage(image, r -> {
            ImgResult result = new ImgResult(type,r);
            imageLiveData.postValue(result);
        });
    }

    public void getDriverInfo(){

        submitRequest(ValidateModel.driverInfo(), r -> {
            if(r.isOk()){
                driverInfoLiveData.postValue(r.data);
            }else {
                sendError(r);
            }
        });
    }

    public void uploadDriverLicense(String idFront,String idOppsite,String driverLicencePic,String driverVehiclePic){

        submitRequest(ValidateModel.uploadDriverLicense(idFront, idOppsite, driverLicencePic, driverVehiclePic), r -> {
            if(r.isOk()){
                licenseLiveData.postValue(r.data);
            }else {
                sendError(r);
            }
        });
    }

    public MutableLiveData<UserEntity> getVehicleLiveData() {
        return vehicleLiveData;
    }

    public MutableLiveData<ImgResult> getImageLiveData() {
        return imageLiveData;
    }

    public MutableLiveData<UserEntity> getDriverInfoLiveData() {
        return driverInfoLiveData;
    }

    public MutableLiveData<Object> getLicenseLiveData() {
        return licenseLiveData;
    }

    class ImgResult {

        int type;

        String r;

        public ImgResult(int type, String r) {
            this.type = type;
            this.r = r;
        }
    }
}
