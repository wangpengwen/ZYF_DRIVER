package com.zywl.app;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.biz.application.BaseApplication;
import com.biz.push.PushManager;
import com.zywl.model.UserModel;
import com.zywl.net.RestRequest;
import com.zywl.ui.BuildConfig;
import com.zywl.ui.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;

/**
 * Copyright © 2017 http://www.biz-united.com.cn All rights reserved.
 * Created by zanezhao on 2017/11/17.
 *
 * @Project: TCJK
 * @Package: com.biz
 * @author: zanezhao
 * @version: V1.0
 */

public class App extends BaseApplication {


    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
            layout.setPrimaryColorsId(android.R.color.white, R.color.colorBlueBtn);//全局设置主题颜色
            return new BezierRadarHeader(context);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context);
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RestRequest.setDebug(BuildConfig.DEBUG);
//        UpgradeManager.register(this);
        PushManager.getInstance().register(this);
        UserModel.getInstance();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static int getScreenWidth() {
        return getAppContext().getResources().getDisplayMetrics().widthPixels;
    }


}
