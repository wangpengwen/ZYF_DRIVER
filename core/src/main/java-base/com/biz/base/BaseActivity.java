package com.biz.base;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.biz.http.BaseRequest;
import com.biz.http.R;
import com.biz.util.ActivityStackManager;
import com.biz.util.Lists;
import com.biz.util.ToastUtils;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Title: BaseActivity
 * Description: activity基类
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:3/8/16  上午10:58
 *
 * @author johnzheng
 * @version 1.0
 */

@SuppressWarnings("deprecation")
public class BaseActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    ViewGroup rootView;
    protected View mProgressView;

    protected AppBarLayout mAppBarLayout;
    @Nullable
    protected Toolbar mToolbar;
    protected List<FragmentBackHelper> fragmentBackHelperList;
    private RxPermissions mRxPermission;
    private Dialog mDialogError;


    public void setFragmentBackHelper(FragmentBackHelper i) {
        if (i != null)
            fragmentBackHelperList.add(i);
    }

    public RxPermissions getRxPermission() {
        if (mRxPermission == null)
            mRxPermission = new RxPermissions(getActivity());
        return mRxPermission;
    }

    public void removeFragmentBackHelper(FragmentBackHelper i) {
        if (i != null && fragmentBackHelperList.contains(i))
            fragmentBackHelperList.remove(i);
    }

    public void dismissKeyboard() {
        try {
            this.getCurrentFocus().clearFocus();
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(this.getCurrentFocus()
                                    .getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
        }

    }

    public void setRootView(ViewGroup rootView) {
        this.rootView = rootView;
    }

    public void setViewDisableDelay(final View view) {
        view.setEnabled(false);
        view.postDelayed(() -> {
            view.setEnabled(true);
        }, 600);
    }

    protected void initProgressLayout() {
        if (mProgressView == null) {
            mProgressView = getLayoutInflater().inflate(R.layout.loading_layout, rootView
                    , false);
            mProgressView.setOnClickListener(v -> {
            });
            setProgressVisible(false);
            rootView.addView(mProgressView);
        }
    }

    public void setProgressVisible(boolean show) {
        if (mProgressView != null) {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    public int getColors(@ColorRes int resId) {
        return getResources().getColor(resId);
    }

    @Override
    public void onBackPressed() {
        for (int i = fragmentBackHelperList.size() - 1; i > -1; i--) {
            if (fragmentBackHelperList.get(i).onBackPressed()) return;
        }
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public void startActivity(Intent intent, boolean isBack) {
        super.startActivity(intent);
//        if (isBack)
//            overridePendingTransition(R.anim.left_in, R.anim.right_out);
//        else
//            overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar =  findViewById(R.id.toolbar);
        setToolbarBackDrawable(mToolbar);
        rootView = (ViewGroup) getWindow().getDecorView();
        initProgressLayout();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mToolbar = findViewById(R.id.toolbar);
        setToolbarBackDrawable(mToolbar);
        rootView = (ViewGroup) getWindow().getDecorView();
        initProgressLayout();
    }


    public void setToolbarBackDrawable(Toolbar mToolbar) {
        mAppBarLayout =  findViewById(R.id.appbar);

        if (null != mToolbar) {

            mToolbar.setNavigationOnClickListener(e -> onBackPressed());
        }
        if (null != mAppBarLayout && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            View.inflate(getActivity(), R.layout.line_dark, mAppBarLayout);
        }
    }

    public void showToast(@StringRes int stringRes) {
        ToastUtils.showLong(getActivity(),stringRes);
        //Snackbar.make(getWindow().getDecorView(), stringRes, Snackbar.LENGTH_LONG).show();
    }

    public void showToast(View view, @StringRes int stringRes) {
        ToastUtils.showLong(view.getContext(),stringRes);
        //Snackbar.make(view == null ? getWindow().getDecorView() : view, stringRes, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        fragmentBackHelperList = Lists.newArrayList();
        //StatusBarHelper.Builder(this).setStatusBarLightMode(true);
        super.onCreate(savedInstanceState);
        ActivityStackManager.add(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getName());
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getName());
        MobclickAgent.onResume(this);
    }

    public void error(String error) {
        setProgressVisible(false);
        if (!TextUtils.isEmpty(error)) {
            if(mDialogError!=null){
                mDialogError.dismiss();
                mDialogError=null;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(error);
            builder.setPositiveButton(R.string.btn_confirm,  (dialog, which) -> {
                dialog.dismiss();
            });
            mDialogError=builder.show();
        }
    }

    public void error(int code, String error) {
        if (code == 2400) {
            setProgressVisible(false);
            finish();
            return;
        }
        error(error);
    }

    /**
     * 检查权限
     * permission 获取方式： Manifest.permission.CAMERA
     */
    protected void checkAppPermission(String permission, Action1<? super Boolean> onNext) {
        getRxPermission().request(permission).observeOn(AndroidSchedulers.mainThread()).subscribe(onNext,
                throwable -> {
                    if (BaseRequest.isDebug())
                        ToastUtils.showLong(getActivity(), throwable.getMessage());
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackManager.remove(this);

    }

    public Activity getActivity() {
        return this;
    }

}
