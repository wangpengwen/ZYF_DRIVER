package com.biz.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.AnimRes;

import com.biz.base.BaseFragment;
import com.biz.base.FragmentParentActivity;
import com.biz.http.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Title: IntentBuilder
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/1/3  11:57
 *
 * @author wangwei
 * @version 1.0
 */
public class IntentBuilder {

    public static final String ACTION_VIEW = "com.biz.action.VIEW";
    public static final String ACTION_VIEW_WEB = "com.biz.web.action.VIEW";

    public static final String KEY_ID = "KEY_ID";
    public static final String KEY_TRACE_ID = "KEY_TRACE_ID";
    public static final String KEY_CENTER_ID = "KEY_CENTER_ID";
    public static final String KEY_ORDER_ID = "KEY_ORDER_ID";
    public static final String KEY_TYPE = "KEY_TYPE";
    public static final String KEY_PAGE_INDEX = "index";
    public static final String KEY_HIS_URL="KEY_HIS_URL";
    public static final String KEY_INFO = "KEY_INFO";
    public static final String KEY_LIST = "KEY_LIST";
    public static final String KEY_SEARCH_LIST = "KEY_SEARCH_LIST";
    public static final String KEY_TITLE = "KEY_TITLE";
    public static final String KEY_PAGE_TITLE = "KEY_PAGE_TITLE";
    public static final String KEY_CATEGORY_ID = "KEY_CATEGORY_ID";
    public static final String KEY_FIELD = "KEY_FIELD";
    public static final String KEY_VALUE = "KEY_VALUE";
    public static final String KEY_MOBILE="KEY_MOBILE";
    public static final String KEY_CODE="KEY_CODE";
    public static final String KEY_VALUE_NAME = "KEY_VALUE_NAME";
    public static final String KEY_PWD = "KEY_PWD";
    public static final String KEY_BOOLEAN = "KEY_BOOLEAN";
    public static final String KEY_BOOLEAN_LOGIN_OUT="KEY_BOOLEAN_LOGIN_OUT";
    public static final String KEY_BOOLEAN_OVERLAY = "KEY_BOOLEAN_OVERLAY";


    public static final String KEY_KUAIHE = "KEY_KUAIHE";

    public static final String KEY_KEY = "KEY_KEY";
    public static final String KEY_KEY1 = "KEY_KEY1";
    public static final String KEY_KEY2 = "KEY_KEY2";
    public static final String KEY_KEY3 = "KEY_KEY3";
    public static final String KEY_KEY4 = "KEY_KEY4";
    public static final String KEY_KEY5 = "KEY_KEY5";

    public static final String KEY_TAG = "KEY_TAG";
    public static final String KEY_IMAGE = "KEY_IMAGE";
    public static final String KEY_ADDRESS = "KEY_ADDRESS";
    public static final String KEY_RONG_MSG = "KEY_RONG_MSG";
    public static final String KEY_KILL = "KEY_KILL";
    public static final String KEY_BOOLEAN_KUAIHE = "KEY_BOOLEAN_KUAIHE";
    public static final String KEY_KUAIHE_ID = "KEY_KUAIHE_ID";
    public static final String KEY_FAMILY = "KEY_FAMILY";
    public static final String KEY_COMPANY = "KEY_COMPANY";
    public static final String KEY_SUGGESTION = "KEY_SUGGESTION";
    public static final String KEY_DATA = "KEY_DATA";
    public static final String KEY_LAT = "KEY_LAT";
    public static final String KEY_LON = "KEY_LON";

    public static final String KEY_LABEL_INFO="KEY_LABEL_INFO";
    public static final String KEY_TYPE_LABEL="KEY_TYPE_LABEL";

    private Intent intent;

    private Context mContext;
    private @AnimRes int animOpen=R.anim.right_in;
    private @AnimRes int animExit=R.anim.left_out;

    public static IntentBuilder Builder() {
        return new IntentBuilder();
    }

    public static IntentBuilder Builder(Intent intent) {
        return new IntentBuilder(intent);
    }

    public static IntentBuilder Builder(String action) {
        return new IntentBuilder(action);
    }

    public static IntentBuilder Builder(String action, Uri uri) {
        return new IntentBuilder(action, uri);
    }

    public static IntentBuilder Builder(Context packageContext, Class<?> cls) {
        return new IntentBuilder(packageContext, cls);
    }

    public static IntentBuilder Builder(String action, Uri uri, Context packageContext, Class<?> cls) {
        return new IntentBuilder(action, uri, packageContext, cls);
    }

    public IntentBuilder() {
        intent = new Intent();
    }

    public IntentBuilder(Intent intent) {
        intent = new Intent(intent);
    }

    public IntentBuilder(String action) {
        intent = new Intent(action);
    }

    public IntentBuilder(String action, Uri uri) {
        intent = new Intent(action, uri);
    }

    public IntentBuilder(Context packageContext, Class<?> cls) {
        mContext = packageContext;
        intent = new Intent(packageContext, cls);
    }

    public IntentBuilder(String action, Uri uri, Context packageContext, Class<?> cls) {
        mContext = packageContext;
        intent = new Intent(action, uri, packageContext, cls);
    }

    public IntentBuilder setData(Uri uri){
        intent.setData(uri);
        return this;
    }

    public Intent getIntent() {
        return intent;
    }

    public String getAction() {
        return intent.getAction();
    }

    public Uri getData() {
        return intent.getData();
    }

    public String getDataString() {
        return intent.getDataString();
    }

    public String getScheme() {
        return intent.getScheme();
    }

    public String getType() {
        return intent.getType();
    }


    public IntentBuilder setClass(Context packageContext, Class<?> cls) {
        mContext = packageContext;
        intent.setClass(mContext, cls);
        return this;
    }

    public IntentBuilder putExtra(String name, boolean value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, byte value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, char value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, short value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, int value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, long value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, float value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, double value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, String value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, CharSequence value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, Parcelable value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, Parcelable[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putParcelableArrayListExtra(String name, ArrayList<? extends Parcelable> value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putIntegerArrayListExtra(String name, ArrayList<Integer> value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putStringArrayListExtra(String name, ArrayList<String> value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putCharSequenceArrayListExtra(String name, ArrayList<CharSequence> value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, Serializable value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, boolean[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, byte[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, short[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, char[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, int[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, long[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, float[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, double[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, String[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, CharSequence[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtra(String name, Bundle value) {
        intent.putExtra(name, value);
        return this;
    }

    public IntentBuilder putExtras(Intent src) {
        intent.putExtras(src);
        return this;
    }

    public IntentBuilder putExtras(Bundle extras) {
        intent.putExtras(extras);
        return this;
    }

    public IntentBuilder setAction(String  action) {
        intent.setAction(action);
        return this;
    }

    public IntentBuilder setFlag(int  flag) {
        intent.setFlags(flag);
        return this;
    }

    public IntentBuilder addFlag(int  flag) {
        intent.addFlags(flag);
        return this;
    }

    public IntentBuilder finish(Activity activity){
        activity.finish();
        return this;
    }

    public IntentBuilder overridePendingTransition(@AnimRes int animOpen, @AnimRes int animExit){
        this.animExit = animExit;
        this.animOpen = animOpen;
        return this;
    }

    public IntentBuilder startActivity() {
        if (mContext != null){
            mContext.startActivity(intent);
            ((Activity)mContext).overridePendingTransition(animOpen, animExit);
        }
        return this;
    }
    public IntentBuilder startActivity(BaseFragment baseFragment,int requestCode) {
        if (baseFragment != null){
            baseFragment.startActivityForResult(intent,requestCode);
            ((Activity)mContext).overridePendingTransition(animOpen, animExit);
        }
        return this;
    }

    public void startActivity(Activity activity) {
        activity.startActivity(intent);
        activity.overridePendingTransition(animOpen, animExit);
    }

    public void startActivity(Activity activity, boolean isBack) {
        activity.startActivity(intent);
        if (isBack)
            activity.overridePendingTransition(animOpen, animExit);
        else
            activity.overridePendingTransition(animOpen, animExit);
    }


    public void startParentActivity(Activity context, Class clz, boolean isToolbar) {
        intent.setClass(context, FragmentParentActivity.class);
        intent.putExtra(FragmentParentActivity.KEY_FRAGMENT, clz);
        intent.putExtra(FragmentParentActivity.KEY_TOOLBAR, isToolbar);
        context.startActivity(intent);
        context.overridePendingTransition(animOpen, animExit);

    }


    public void startParentActivity(Activity context, Class clz) {
        intent.setClass(context, FragmentParentActivity.class);
        intent.putExtra(FragmentParentActivity.KEY_FRAGMENT, clz);
        context.startActivity(intent);
        context.overridePendingTransition(animOpen, animExit);

    }
    public void startParentActivity(Activity context, Class clz,int requestCode) {
        intent.setClass(context, FragmentParentActivity.class);
        intent.putExtra(FragmentParentActivity.KEY_FRAGMENT, clz);
        context.startActivityForResult(intent,requestCode);
        context.overridePendingTransition(animOpen, animExit);

    }
    public void startParentActivity(Activity context, Class clz,int requestCode, boolean isToolbar) {
        intent.setClass(context, FragmentParentActivity.class);
        intent.putExtra(FragmentParentActivity.KEY_FRAGMENT, clz);
        intent.putExtra(FragmentParentActivity.KEY_TOOLBAR, isToolbar);
        context.startActivityForResult(intent,requestCode);
        context.overridePendingTransition(animOpen, animExit);

    }
    public void startParentActivity(BaseFragment fragment, Class clz, int requestCode) {
        intent.setClass(fragment.getContext(), FragmentParentActivity.class);
        intent.putExtra(FragmentParentActivity.KEY_FRAGMENT, clz);
        fragment.startActivityForResult(intent,requestCode);
        fragment.getActivity().overridePendingTransition(animOpen, animExit);

    }
}
