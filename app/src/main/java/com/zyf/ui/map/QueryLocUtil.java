package com.zyf.ui.map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.baidu.location.BDLocationListener;
import com.biz.util.MathUtil;

import rx.Observable;
import rx.functions.Action1;

/**
 * Title: QueryLocUtil
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/1/13  17:11
 *
 * @author wangwei
 * @version 1.0
 */
public class QueryLocUtil {
    private LocationHelper locationHelper;
    private Context context;
    private Action1<Boolean> queryLocListener;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public QueryLocUtil(Context context) {
        this.context = context;
    }

    public LocationHelper getLocationHelper() {
        if (locationHelper == null)
            locationHelper = new LocationHelper(context, getDefaultBDLocationListener());
        return locationHelper;
    }

    public void queryLoc(Action1<Boolean> queryLocListener) {
        this.queryLocListener = queryLocListener;
        queryLoc();
        mHandler.postDelayed(mRunnable, LocationHelper.LOCATION_TIME_OUT);
    }

    public void onDestroyView() {
        try {
            mHandler.removeCallbacks(mRunnable);
        } catch (Exception e) {
        }
        queryLocListener = null;
        getLocationHelper().stop();
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            execQueryLocListener(false);
        }
    };


    private void queryLoc() {
        queryLoc(false);
    }

    private void queryLoc(boolean isError) {
        getLocationHelper().start();
    }

    private void execQueryLocListener(Boolean latLng) {
        if (queryLocListener != null) {
            Observable.just(latLng).subscribe(queryLocListener);
        }
        try {
            mHandler.removeCallbacks(mRunnable);
        } catch (Exception e) {
        }
        stop();
    }

    public void stop() {
        try {
            mHandler.removeCallbacks(mRunnable);
        } catch (Exception e) {
        }
        getLocationHelper().stop();
        locationHelper = null;
    }

    private BDLocationListener getDefaultBDLocationListener() {
        return bdLocation -> {
            execQueryLocListener(!(MathUtil.compareEquals(bdLocation.getLatitude(), 0d)
                    && MathUtil.compareEquals(bdLocation.getLongitude(), 0d)));
        };
    }
}
