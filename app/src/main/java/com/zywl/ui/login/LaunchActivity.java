package com.zywl.ui.login;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.biz.base.BaseLiveDataActivity;
import com.biz.util.IntentBuilder;
import com.biz.util.ToastUtils;
import com.zywl.model.UserModel;
import com.zywl.ui.R;
import com.zywl.ui.main.MainActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Title: LaunchActivity
 * Description:
 * Copyright:Copyright(c)2016
 * Company:博智维讯信息技术有限公司
 * CreateTime:12/12/2017  18:21
 *
 * @author johnzheng
 * @version 1.0
 */

public class LaunchActivity extends BaseLiveDataActivity {

    private static String[] PERMISSIONS = {
            Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA
    };

    CompositeSubscription mSubscription;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideNavigation();
//        AppCompatImageView imageView = new AppCompatImageView(getActivity());
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));
//        setContentView(imageView);
        //Glide.with(this).load(R.mipmap.loadding).into(imageView);
        mSubscription = new CompositeSubscription();
//        mSubscription.add(Observable.just(1).delay(2000, TimeUnit.MILLISECONDS).subscribe(s->{
//            startMain();
//            //IntentBuilder.Builder().startParentActivity(getActivity(), TestFragment.class);
//            //startActivity(new Intent(getActivity(), MainActivity.class));
//            finish();
//        }));

        if (getRxPermission().isGranted(Manifest.permission.READ_PHONE_STATE)) {
            mSubscription.add(Observable.just(1).delay(2000, TimeUnit.MILLISECONDS).subscribe(s->{
                startMain();
                //IntentBuilder.Builder().startParentActivity(getActivity(), TestFragment.class);
                //startActivity(new Intent(getActivity(), MainActivity.class));
                finish();
            }));
        } else {
            mSubscription.add(getRxPermission().request(PERMISSIONS)
                    .subscribe(b -> {
                        if (!b) {
                            ToastUtils.showLong(getActivity(),
                                    getString(com.biz.http.R.string.text_error_permission));
                        }
                        mSubscription.add(Observable.just(1).delay(2000, TimeUnit.MILLISECONDS).subscribe(s->{
                            startMain();
                            //IntentBuilder.Builder().startParentActivity(getActivity(), TestFragment.class);
                            //startActivity(new Intent(getActivity(), MainActivity.class));
                            finish();
                        }));
                    }));
        }
    }

    private void requestPermission() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getRxPermission().requestEach(PERMISSIONS)
                    .subscribe(permission -> {

                    }, throwable -> {
                    }, () -> {

                    });

        } else {

        }
    }

    private void startMain() {
        if (getIntent().getData()!=null)
            startUrl(getIntent().getData().toString());
        else startMain("");
    }

    private void startUrl(String url) {
        if (!TextUtils.isEmpty(url)) {

            if(UserModel.getInstance().isLogin()){
                IntentBuilder.Builder(this, MainActivity.class)
                        .overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
                        .finish(this)
                        .startActivity();
            }else {
                IntentBuilder.Builder(this, LoginActivity.class)
                        .setData(Uri.parse(url))
                        .addFlag(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                        .overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
                        .finish(this)
                        .startActivity();
            }
        }
    }

    private void startMain(Object o) {
        if(UserModel.getInstance().isLogin()){
            IntentBuilder.Builder(this, MainActivity.class)
                    .overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
                    .finish(this)
                    .startActivity();
        }else {
            IntentBuilder.Builder(this, LoginActivity.class)
                    .overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
                    .finish(this)
                    .startActivity();
        }
    }

    private void hideNavigation() {
        int flags;
        int curApiVersion = Build.VERSION.SDK_INT;
        if (curApiVersion >= Build.VERSION_CODES.KITKAT) {
            flags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;

        } else {
            flags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }
        getWindow().getDecorView().setSystemUiVisibility(flags);
    }
}
