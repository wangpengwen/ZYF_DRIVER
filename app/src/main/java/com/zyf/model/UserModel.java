package com.zyf.model;

import android.text.TextUtils;

import com.biz.http.HttpErrorException;
import com.biz.http.LocationCache;
import com.biz.http.ResponseJson;
import com.biz.http.RestMethodEnum;
import com.biz.util.Lists;
import com.biz.util.RxUtil;
import com.google.gson.reflect.TypeToken;
import com.zyf.event.LoginEvent;
import com.zyf.model.dao.DBHelper;
import com.zyf.model.dao.LoginHisUserDao;
import com.zyf.model.entity.LoginHisEntity;
import com.zyf.model.entity.UserEntity;
import com.zyf.net.RestRequest;

import java.util.List;

import de.greenrobot.event.EventBus;
import rx.Observable;

/**
 * Title: UserModel
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/11/17  11:09
 *
 * @author wangwei
 * @version 1.0
 */
public class UserModel {
    private static UserModel userModel;
    private UserEntity mUserEntity;
    //    private DepotEntity mUserDepot;
    private boolean isShop = false;
    private boolean isUserSelectedDepot;
    private boolean isShowAdv;
    private String userAddress;
    private double lat = LocationCache.getInstance().lat(), lon = LocationCache.getInstance().lon();

    public static UserModel getInstance() {
        if (userModel == null) {
            synchronized (UserModel.class) {
                userModel = new UserModel();
            }
        }
        return userModel;
    }

    public boolean isUserSelectedDepot() {
        return isUserSelectedDepot;
    }

//    public void addCoupon() {
//        if (mUserEntity != null) {
//            mUserEntity.couponQuantity = mUserEntity.couponQuantity + 1;
//        }
//        setUserEntity(mUserEntity);
//        EventBus.getDefault().post(new UserInfoChangeEvent());
//    }

//    public void setShowAdv(boolean showAdv) {
//        isShowAdv = showAdv;
//    }
//
//    public boolean isShowAdv() {
//        return isShowAdv;
//    }

//    /**
//     * 是否是商城
//     */
//    public boolean isShop() {
//        return isShop;
//    }

    public void setUserAddress(String userAddress) {
        if (!TextUtils.isEmpty(userAddress)) {
            this.userAddress = userAddress;
        }
    }

    public String getUserAddress() {
        return userAddress;
    }

//    public void setShop(boolean shop, double lat, double lon) {
//        if (!CartDataCache.getInstance().isChangeShop(shop) || isShop != shop) {
//            CartDataCache.getInstance().clearCart();
//        }
//        isShop = shop;
//        isUserSelectedDepot = true;
//        this.lat = lat;
//        this.lon = lon;
//        RxUtil.newThreadSubscribe(saveUserDepot(isShop()), b -> {
//        });
//        if (isLogin()) {
//            requestCart();
//        }
//    }

//    public double getLat() {
//        return lat;
//    }
//
//    public double getLon() {
//        return lon;
//    }

    //    public void setUserDepot(DepotEntity userDepot) {
//        mUserDepot = userDepot;
//        RxUtil.newThreadSubscribe(saveUserDepot(userDepot), b -> {
//        });
//    }
//
//    public DepotEntity getUserDepot(boolean isDef) {
//        if (isShop() && mUserDepot == null && isDef) {
//            DepotEntity depotEntity = new DepotEntity();
//            depotEntity.depotCode = "7777";
//            depotEntity.depotName = "同城酒库旗舰店";
//            return depotEntity;
//        }
//        return mUserDepot;
//    }
//
//    public DepotEntity getUserDepot() {
//        return getUserDepot(true);
//    }
    public String getUserToken() {
        return mUserEntity == null ? "" : mUserEntity.getSalesmanToken();
    }

    public String getUserId() {
        return mUserEntity == null ? "" : mUserEntity.getSalesmanNum();
    }

    public String getUserName(){
        return mUserEntity == null ? "" : mUserEntity.getSalesmanName();
    }

    public String getUserRealName() {
        return mUserEntity == null ? "" : mUserEntity.getSalesmanRealName();
    }

    public String getStoreName(){
        return mUserEntity == null ? "" : mUserEntity.getStorageName();
    }

    public String getStoreNum(){
        return mUserEntity == null ? "" : mUserEntity.getSalesmanStorage();
    }

    public UserModel() {
        RxUtil.newThreadSubscribe(getLoginHisUser(), userEntity -> {
//            RxUtil.newThreadSubscribe(getSaveUserDepot(), depotEntity -> {
//                mUserDepot = depotEntity.getDepotEntity();
//                isShop = depotEntity.isShop;
//            });
            this.mUserEntity = userEntity;
        });
    }

//    public void autoLogin() {
//        RxUtil.newThreadSubscribe(getSaveUserDepot(), depotEntity -> {
//            mUserDepot = depotEntity.getDepotEntity();
//            isShop = depotEntity.isShop;
//        });
//        RxUtil.newThreadSubscribe(getLoginHisUser(), userEntity -> {
//            RxUtil.newThreadSubscribe(getSaveUserDepot(), depotEntity -> {
//                mUserDepot = depotEntity.getDepotEntity();
//                isShop = depotEntity.isShop;
//            });
//            this.mUserEntity = userEntity;
//            if (isLogin()) {
//                RxUtil.newThreadSubscribe(autoLoginObservable(), userEntityResponseJson -> {
//                    if (userEntityResponseJson.isOk()) {
//                        requestCart();
//                        EventBus.getDefault().post(new LoginEvent());
//                    } else {
//                        this.mUserEntity = null;
//                    }
//                });
//            }
//        });
//    }

//    public static void requestCart() {
//        RxUtil.newThreadSubscribe(CartModel.getCart(), cartEntityResponseJson -> {
//            if (cartEntityResponseJson.isOk()) {
//                CartDataCache.getInstance().setCartEntity(cartEntityResponseJson.data);
//                EventBus.getDefault().post(new LoginEvent());
//            }
//        });
//    }

    public void setUserEntity(UserEntity userEntity) {
        this.mUserEntity = userEntity;
        RxUtil.newThreadSubscribe(saveUserEntity(userEntity), b -> {
        });
    }

    public UserEntity getUserEntity() {
        return mUserEntity;
    }

    public void loginOut() {
        if (!TextUtils.isEmpty(getInstance().getUserId())) {
            //TODO 登出接口
//            RxUtil.newThreadSubscribe(loginOutOb(), b -> {
//            });
        }
        clearLoginStatus();
    }

    public void clearLoginStatus() {
        this.mUserEntity = null;
        RxUtil.newThreadSubscribe(saveLoginOut(), b -> {
        });
    }

//    public boolean isTodaySign() {
//        if (mUserEntity == null || TextUtils.isEmpty(mUserEntity.lastSignTime)) {
//            return false;
//        }
//        return TimeUtil.format(SysTimeUtil.getSysTime(BaseApplication.getAppContext()), TimeUtil.FORMAT_YYYYMMDD)
//                .equals(mUserEntity.lastSignTime);
//    }


    public boolean isLogin() {
        return this.mUserEntity != null && !TextUtils.isEmpty(mUserEntity.getSalesmanToken());
    }

//    public boolean createLoginDialog(Context context) {
//        return createLoginDialog(context, null);
//    }
//
//    public boolean createLoginDialog(Context context, Action0 loginOnNext) {
//        if (context == null) return false;
//        if (!isLogin()) {
//            DialogUtil.createDialogView(context,
//                    R.string.dialog_title_notice,
//                    R.string.dialog_login_tip,
//                    (DialogInterface dialog, int which) -> {
//                        dialog.dismiss();
//                    }
//                    , R.string.btn_cancel,
//                    (DialogInterface dialog, int which) -> {
//                        LoginActivity.goLogin(context);
//                    }
//                    , R.string.btn_im_login);
//            return true;
//        } else {
//            if (loginOnNext != null)
//                loginOnNext.call();
//        }
//        return false;
//    }


    public static Observable<ResponseJson<UserEntity>> login(String account, String pwd) {

        return RestRequest.<ResponseJson<UserEntity>>builder()
                .addBody("name", account)
                .addBody("pwd", pwd)
                .addBody("marker","0")
//                .addBody("registId", "")
                .url("/sys/login.do")
                .restMethod(RestMethodEnum.POST)
                .setToJsonType(new TypeToken<ResponseJson<UserEntity>>() {
                }.getType())
                .requestJson().map(r -> {
                    if (r.isOk()) {
                        UserModel.getInstance().setUserEntity(r.data);
//                        requestCart();
                        EventBus.getDefault().post(new LoginEvent());
                    }
                    return r;
                });
    }


    public static Observable<ResponseJson<UserEntity>> info(){

        return RestRequest.<ResponseJson<UserEntity>>builder()
                .url("/user/queryuser.do")
                .addBody("marker", "0")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<UserEntity>>() {
                }.getType())
                .requestJson();
    }


//    public static Observable<ResponseJson<UserEntity>> smsLogin(String mobile, String smsCode) {
//        return RestRequest.<ResponseJson<UserEntity>>builder()
//                .addBody("mobile", mobile)
//                .addBody("channelCode", "APP")
//                .addBody("smsCode", smsCode)
//                .addBody("deviceToken", PushManager.getInstance().getPushTag())
//                .url("/users/smsCodeLogin")
//                .restMethod(RestMethodEnum.POST)
//                .setToJsonType(new TypeToken<ResponseJson<UserEntity>>() {
//                }.getType())
//                .requestJson().map(r -> {
//                    if (r.isOk()) {
//                        UserModel.getInstance().setUserEntity(r.data);
//                        requestCart();
//                        EventBus.getDefault().post(new LoginEvent());
//                    }
//                    return r;
//                });
//    }

//    public static Observable<ResponseJson<UserEntity>> autoLoginObservable() {
//        return RestRequest.<ResponseJson<UserEntity>>builder()
//                .addBody("autoToken", getInstance().getUserEntity() != null ? getInstance().getUserEntity().autoToken : "")
//                .addBody("deviceToken", PushManager.getInstance().getPushTag())
//                .addBody("channelCode", "APP")
//                .url("/users/autologin")
//                .userId(getInstance().getUserId())
//                .restMethod(RestMethodEnum.POST)
//                .setToJsonType(new TypeToken<ResponseJson<UserEntity>>() {
//                }.getType())
//                .requestJson().map(r -> {
//                    if (r.isOk()) {
//                        UserModel.getInstance().setUserEntity(r.data);
//                    } else {
//                        getInstance().clearLoginStatus();
//                    }
//                    return r;
//                });
//    }

    public static Observable<ResponseJson<Object>> changePassword(String oldPassword, String newPassword) {
        return RestRequest.<ResponseJson<Object>>builder()
                .addBody("oldPassword", oldPassword)
                .addBody("newPassword", newPassword)
                .url("/users/changepassword")
                .restMethod(RestMethodEnum.POST)
                .userId(getInstance().getUserId())
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<Object>> clearFootPrint() {
        return RestRequest.<ResponseJson<Object>>builder()
                .url("/users/footprint/clear")
                .userId(getInstance().getUserId())
                .restMethod(RestMethodEnum.POST)
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }


    public static Observable<ResponseJson<Object>> loginOutOb() {
        return RestRequest.<ResponseJson<Object>>builder()
                .url("/users/logout")
                .userId(getInstance().getUserId())
                .restMethod(RestMethodEnum.POST)
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<UserEntity>> registerUser(String account, String password, String smsCode) {
        return RestRequest.<ResponseJson<UserEntity>>builder()
                .addBody("account", account)
                .addBody("password", password)
                .addBody("smsCode", smsCode)
                .url("/users/register")
                .userId(getInstance().getUserId())
                .restMethod(RestMethodEnum.POST)
                .setToJsonType(new TypeToken<ResponseJson<UserEntity>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<UserEntity>> registerUser(String account, String smsCode) {
        return RestRequest.<ResponseJson<UserEntity>>builder()
                .addBody("mobile", account)
                .addBody("smsCode", smsCode)
                .addBody("channelCode", "APP")
                .url("/users/register")
                .userId(getInstance().getUserId())
                .restMethod(RestMethodEnum.POST)
                .setToJsonType(new TypeToken<ResponseJson<UserEntity>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<Object>> resetPassword(String mobile, String newPassword, String smsCode) {
        return RestRequest.<ResponseJson<Object>>builder()
                .addBody("mobile", mobile)
                .addBody("newPassword", newPassword)
                .addBody("smsCode", smsCode)
                .url("/users/resetpassword")
                .restMethod(RestMethodEnum.POST)
                .userId(getInstance().getUserId())
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<Object>> changeAvatar(String avatar) {
        return RestRequest.<ResponseJson<Object>>builder()
                .addBody("image", avatar)
                .url("/users/updateimage")
                .restMethod(RestMethodEnum.POST)
                .userId(getInstance().getUserId())
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }

//    public static Observable<ResponseJson<Object>> changeInfo(UserChangeInfoEntity userChangeInfoEntity) {
//        return RestRequest.<ResponseJson<Object>>builder()
//                .addBody(userChangeInfoEntity.toJson())
//                .url("/users/updateinfo")
//                .restMethod(RestMethodEnum.POST)
//                .userId(getInstance().getUserId())
//                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
//                }.getType())
//                .requestJson();
//    }


//    public static Observable<ResponseJson<List<SignEntity>>> getSignDate(String signDay) {
//        return RestRequest.<ResponseJson<List<SignEntity>>>builder()
//                .addBody("signDay", signDay)
//                .url("/users/sign/getMemberSignInRecordSByMothAndMemberId")
//                .restMethod(RestMethodEnum.POST)
//                .userId(getInstance().getUserId())
//                .setToJsonType(new TypeToken<ResponseJson<List<SignEntity>>>() {
//                }.getType())
//                .requestJson();
//    }
//
//    public static Observable<ResponseJson<SignMainEntity>> getSign() {
//        return RestRequest.<ResponseJson<SignMainEntity>>builder()
//                .addBody("signDay", TimeUtil.format(SysTimeUtil.getSysTime(BaseApplication.getAppContext()), TimeUtil.FORMAT_YYYYMM))
//                .url("/users/sign/getMemberSignInRecordAndCountAndNumByMothAndMemberId")
//                .restMethod(RestMethodEnum.POST)
//                .userId(getInstance().getUserId())
//                .setToJsonType(new TypeToken<ResponseJson<SignMainEntity>>() {
//                }.getType())
//                .requestJson();
//    }

//    public static Observable<ResponseJson<Object>> sign() {
//        return RestRequest.<ResponseJson<Object>>builder()
//                .addBody("signDay", TimeUtil.format(SysTimeUtil.getSysTime(BaseApplication.getAppContext()), TimeUtil.FORMAT_YYYYMMDD))
//                .url("/users/sign/sign")
//                .restMethod(RestMethodEnum.POST)
//                .userId(getInstance().getUserId())
//                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
//                }.getType())
//                .requestJson().map(r -> {
//                    if (r.isOk() || r.status == 55802) {
//                        UserEntity entity = UserModel.getInstance().getUserEntity();
//                        if (entity != null) {
//                            entity.lastSignTime = TimeUtil.format(SysTimeUtil.getSysTime(BaseApplication.getAppContext()), TimeUtil.FORMAT_YYYYMMDD);
//                            UserModel.getInstance().setUserEntity(entity);
//                        }
//                    }
//                    return r;
//                });
//    }
//
//    public static Observable<ResponseJson<List<SignPromotionEntity>>> signPromotion() {
//        return RestRequest.<ResponseJson<List<SignPromotionEntity>>>builder()
//                .url("/users/sign/getSignaturePromotion")
//                .restMethod(RestMethodEnum.POST)
//                .userId(getInstance().getUserId())
//                .setToJsonType(new TypeToken<ResponseJson<List<SignPromotionEntity>>>() {
//                }.getType())
//                .requestJson();
//    }

//    public static Observable<ResponseJson<Object>> signReceive(long promotionId) {
//        return RestRequest.<ResponseJson<Object>>builder()
//                .url("/users/sign/colPromotion")
//                .addBody("promotionId", promotionId)
//                .restMethod(RestMethodEnum.POST)
//                .userId(getInstance().getUserId())
//                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
//                }.getType())
//                .requestJson();
//    }

//    public static Observable<ResponseJson<List<IntegralDetailEntity>>> integral(int page, int size) {
//        return RestRequest.<ResponseJson<List<IntegralDetailEntity>>>builder()
//                .url("/users/integral/getChangeInfo")
//                .addBody("page", page)
//                .addBody("size", size)
//                .restMethod(RestMethodEnum.POST)
//                .userId(getInstance().getUserId())
//                .setToJsonType(new TypeToken<ResponseJson<List<IntegralDetailEntity>>>() {
//                }.getType())
//                .requestJson();
//    }

//    public static Observable<ResponseJson<Integer>> getIntegral() {
//        return RestRequest.<ResponseJson<Integer>>builder()
//                .url("/users/getIntegral")
//                .restMethod(RestMethodEnum.POST)
//                .userId(getInstance().getUserId())
//                .setToJsonType(new TypeToken<ResponseJson<Integer>>() {
//                }.getType())
//                .requestJson().map(r -> {
//                    if (r != null && r.data != null) {
//                        UserEntity userEntity = UserModel.getInstance().getUserEntity();
//                        if (userEntity != null) {
//                            userEntity.point = r.data;
//                            UserModel.getInstance().setUserEntity(userEntity);
//                        }
//                        EventBus.getDefault().post(new UserInfoChangeEvent());
//                    }
//                    return r;
//                });
//    }


    private static Observable<Boolean> saveLoginOut() {
        return Observable.create(subscriber -> {
            List<UserEntity> list = DBHelper.getInstance().getUserDao().queryList();
            if (list != null && list.size() > 0) {
//                for (int i = list.size() - 1; i >= 0; i--) {
//                    UserEntity entity = list.get(i);
//                    entity.ts = -1L;
//                }
                DBHelper.getInstance().getUserDao().deleteAll(list);
            }
            subscriber.onNext(true);
            subscriber.onCompleted();
        });
    }


    private static Observable<Boolean> saveUserEntity(UserEntity userInfo) {
        return Observable.create(subscriber -> {
            List<UserEntity> list = DBHelper.getInstance().getUserDao().queryList();
            boolean isExists = false;
//            userInfo.ts = System.currentTimeMillis();
            if (list != null && list.size() > 0) {
                for (int i = list.size() - 1; i >= 0; i--) {
                    UserEntity entity = list.get(i);
                    if (userInfo != null && userInfo.getSalesmanNum() == entity.getSalesmanNum()) {
                        list.remove(i);
                    } else {
//                        entity.ts = -1L;
                    }
                }
            } else if (list == null) {
                list = Lists.newArrayList();
            }
            list.add(userInfo);
            DBHelper.getInstance().getUserDao().insert(list);
            subscriber.onNext(true);
            subscriber.onCompleted();
        });
    }

    private static Observable<UserEntity> getLoginHisUser() {
        return Observable.create(subscriber -> {
            UserEntity mUserEntity = null;
            List<UserEntity> list = DBHelper.getInstance().getUserDao().queryList();
            if (list != null && list.size() > 0) {
                for (UserEntity entity : list) {
                    if (entity != null) {
                        mUserEntity = entity;
                        break;
                    }
                }
            }
            if (mUserEntity == null) {
                throw new HttpErrorException("user is null");
            }
            subscriber.onNext(mUserEntity);
            subscriber.onCompleted();
        });
    }

    public static Observable<Boolean> saveLoginUser(String userName) {
        return Observable.create(subscriber -> {
            LoginHisUserDao loginHisUserDao = DBHelper.getInstance().getLoginHisUserDao();
            List<LoginHisEntity> list = loginHisUserDao.queryList();
            LoginHisEntity loginHisEntity = null;
            if (list != null && list.size() > 0) {
                loginHisEntity = list.get(0);
            }
            if (loginHisEntity == null) {
                loginHisEntity = new LoginHisEntity();
                list = Lists.newArrayList();
                list.add(loginHisEntity);
            }
            loginHisEntity.mobile = userName;
            loginHisEntity.ts = System.currentTimeMillis();
            loginHisUserDao.insert(list);
            subscriber.onNext(true);
            subscriber.onCompleted();
        });
    }

    public static Observable<String> getLoginUser() {
        return Observable.create(subscriber -> {
            LoginHisUserDao loginHisUserDao = DBHelper.getInstance().getLoginHisUserDao();
            List<LoginHisEntity> list = loginHisUserDao.queryList();
            if (list != null && list.size() > 0) {
                LoginHisEntity loginHisEntity = list.get(0);
                if (loginHisEntity != null) {
                    String json = loginHisEntity.mobile;
                    if (json == null) json = "";
                    subscriber.onNext(json);
                    subscriber.onCompleted();
                    return;
                }
            }
            throw new RuntimeException("null");
        });
    }

//    public static Observable<Boolean> saveUserDepot(DepotEntity depotEntity) {
//        return Observable.create(subscriber -> {
//            UserDepotDao userDepotDao = DBHelper.getInstance().getUserDepotDao();
//            List<UserDepotEntity> list = userDepotDao.queryList();
//            UserDepotEntity userDepotEntity = null;
//            if (list != null && list.size() > 0) {
//                userDepotEntity = list.get(0);
//            }
//            if (userDepotEntity == null) {
//                userDepotEntity = new UserDepotEntity();
//                list = Lists.newArrayList();
//                list.add(userDepotEntity);
//            }
//            userDepotEntity.json = GsonUtil.toJson(depotEntity);
//            userDepotEntity.ts = System.currentTimeMillis();
//            userDepotDao.insert(list);
//            subscriber.onNext(true);
//            subscriber.onCompleted();
//        });
//    }

//    public static Observable<Boolean> saveUserDepot(boolean isShop) {
//        return Observable.create(subscriber -> {
//            UserDepotDao userDepotDao = DBHelper.getInstance().getUserDepotDao();
//            List<UserDepotEntity> list = userDepotDao.queryList();
//            UserDepotEntity userDepotEntity = null;
//            if (list != null && list.size() > 0) {
//                userDepotEntity = list.get(0);
//            }
//            if (userDepotEntity == null) {
//                userDepotEntity = new UserDepotEntity();
//                list = Lists.newArrayList();
//                list.add(userDepotEntity);
//            }
//            userDepotEntity.ts = System.currentTimeMillis();
//            userDepotEntity.isShop = isShop;
//            userDepotDao.insert(list);
//            subscriber.onNext(true);
//            subscriber.onCompleted();
//        });
//    }

//    public static Observable<UserDepotEntity> getSaveUserDepot() {
//        return Observable.create(subscriber -> {
//            UserDepotDao userDepotDao = DBHelper.getInstance().getUserDepotDao();
//            List<UserDepotEntity> list = userDepotDao.queryList();
//            if (list != null && list.size() > 0) {
//                UserDepotEntity userDepotEntity = list.get(0);
//                if (userDepotEntity != null) {
//                    subscriber.onNext(userDepotEntity);
//                    subscriber.onCompleted();
//                    return;
//                }
//            }
//            throw new RuntimeException("null");
//        });
//    }

}
