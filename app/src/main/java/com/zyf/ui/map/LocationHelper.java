package com.zyf.ui.map;

import android.content.Context;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * Title: LocationHeper
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2016/9/13  11:22
 *
 * @author wangwei
 * @version 1.0
 */
public class LocationHelper {
    private LocationClient mLocationClient;
    public static final int LOCATION_TIME_OUT = 5 * 1000;
    private Context context;
    private BDLocationListener locationListener;
    private double lat, lon;
    private String city;

    public String getCity() {
        return city==null?"":city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getUserLat() {
        return lat;
    }

    public double getUserLon() {
        return lon;
    }

    public void setUserLatLon(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }


    public LocationHelper(Context context, BDLocationListener locationListener) {
        this.context = context;
        this.locationListener = getDefaultBDLocationListener(this, locationListener);
        init();
    }

    public LocationHelper(Context context) {
        this.context = context;
        this.locationListener = getDefaultBDLocationListener(this, null);
        init();
    }

    private void init() {
        mLocationClient = new LocationClient(context);
        mLocationClient.registerLocationListener(locationListener);
        locationClientOption(LOCATION_TIME_OUT, true);
    }

    /**
     * 定位配置
     *
     * @param timeOut       超时时间
     * @param isNeedAddress 是否需要地址
     */
    public void locationClientOption(int timeOut, boolean isNeedAddress) {
        if (mLocationClient == null) return;
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setIsNeedAddress(isNeedAddress);//设置是否需要地址信息，默认为无地址
        option.setTimeOut(timeOut);//设置网络超时时间
        //option.setScanSpan(1000);// 设置发起定位请求的间隔时间为1000ms
        option.setIsNeedLocationDescribe(true);//设置是否需要返回位置语义化信息
        option.setIsNeedLocationPoiList(true);//设置是否需要返回位置POI信息
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy); //设置定位模式   低功耗模式
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    /**
     * 开始定位
     */
    public void start() {
        if (mLocationClient == null) {
            init();
        } else {
            locationClientOption(LOCATION_TIME_OUT, true);
        }
    }

    public void stop() {
        if (mLocationClient != null) {
            mLocationClient.stop();
        }
    }

    public static BDLocationListener getDefaultBDLocationListener(LocationHelper locationHelper, BDLocationListener bdLocationListener) {
        return bdLocation -> {
            if (locationHelper != null)
                locationHelper.setUserLatLon(bdLocation.getLatitude(), bdLocation.getLongitude());
            if (bdLocationListener != null) {
                bdLocationListener.onReceiveLocation(bdLocation);
            }
        };
    }
}
