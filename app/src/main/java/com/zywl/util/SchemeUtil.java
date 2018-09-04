package com.zywl.util;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.biz.util.IntentBuilder;
import com.biz.util.LogUtil;
import com.zywl.ui.R;
import com.zywl.ui.web.WebViewActivity;

/**
 * Title: SUtils
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2018/1/24  14:21
 *
 * @author wangwei
 * @version 1.0
 */
public class SchemeUtil {
    public final static String KEY_LOGIN = "needLogin";
    public final static String KEY_GLOBAL = "_TCJK_globalParams";

    public static String reassembleUrl(String urlHis) {
        if (TextUtils.isEmpty(urlHis)) return urlHis;
        String url = urlHis;
        try {
            if (urlHis.contains("#") && urlHis.contains("?") && urlHis.indexOf("?") < urlHis.indexOf("#")) {
                //解析UTL
                String path = urlHis.substring(urlHis.indexOf("#") + 1);
                LogUtil.print(path);
                String para = urlHis.substring(urlHis.indexOf("?"), urlHis.indexOf("#"));
                LogUtil.print(para);
                String host = urlHis.substring(0, urlHis.indexOf("?"));
                LogUtil.print(host);
                String urlNew = host + path + para;
                LogUtil.print(urlNew);
                url = urlNew;
            }
        } catch (Exception e1) {
            url = urlHis;
        }
        return url;
    }

//    public static boolean isStartPaySuccess(Uri uri, Context activity) {
//        if (BaseApplication.getAppContext().getString(R.string.pay_success_scheme_host).equals(uri.getHost())
//                && BaseApplication.getAppContext().getString(R.string.pay_success_scheme_path).equals(uri.getPath())) {
//            String status = uri.getQueryParameter("orderSummaryCode");
//            String tag = ((Activity) activity).getIntent().getStringExtra(IntentBuilder.KEY_ID);
//            if (!TextUtils.isEmpty(tag) && tag.contains(status)) {
//                EventBus.getDefault().post(new H5PaySuccessEvent(tag));
//            } else {
//                EventBus.getDefault().post(new H5PaySuccessEvent(status));
//            }
//            return true;
//        }
//        return false;
//    }

//    public static boolean isStartHome(Uri uri, Context activity) {
//        if (BaseApplication.getAppContext().getString(R.string.scheme_host).equals(uri.getHost())
//                && BaseApplication.getAppContext().getString(R.string.home_scheme_path).equals(uri.getPath())) {
//            Activity activity1= ActivityStackManager.finishToMain();
//            if(activity1!=null&&activity1 instanceof MainActivity){
//                MainActivity mainActivity= (MainActivity) activity1;
//                mainActivity.openMain();
//            }
//            return true;
//        }
//        return false;
//    }


    /**
     * 用于webView 跳转
     * @param context
     * @param urlHis
     * @return
     */
    public static boolean startUri(Activity context, String urlHis) {
        try {
            if (!TextUtils.isEmpty(urlHis)) {
                String url = SchemeUtil.reassembleUrl(urlHis);
                Uri uri = Uri.parse(url);
//                boolean isLogin = uri.getBooleanQueryParameter(KEY_LOGIN, false);
//                boolean isGlobal = uri.getBooleanQueryParameter(KEY_GLOBAL, false);
//                if (SchemeUtil.isStartPaySuccess(uri,context)) {
//                    context.finish();
//                    return true;
//                }
//                if(SchemeUtil.isStartHome(uri,context)){
//                    return true;
//                }
//                if (isLogin && !UserModel.getInstance().isLogin()) {
//                    UserModel.getInstance().createLoginDialog(context, () -> {
//                    });
//                    return true;
//                }
//                if (isGlobal) {
//                    url = UrlSinger.builder()
//                            .url(url)
//                            .userId(UserModel.getInstance().getUserId() + "")
//                            .toUrl();
//                    uri = Uri.parse(url);
//                }
                IntentBuilder.Builder(IntentBuilder.ACTION_VIEW)
                        .setData(uri)
                        .putExtra(IntentBuilder.KEY_HIS_URL, urlHis)
                        .startActivity(context);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * 用于首页跳转
     * @param context
     * @param urlHis
     */
    public static void startMainUri(Context context, String urlHis) {
        try {
            if (!TextUtils.isEmpty(urlHis)) {
                String url = SchemeUtil.reassembleUrl(urlHis);
                Uri uri = Uri.parse(url);
//                boolean isLogin = uri.getBooleanQueryParameter(KEY_LOGIN, false);
//                boolean isGlobal = uri.getBooleanQueryParameter(KEY_GLOBAL, false);
//                if(SchemeUtil.isStartHome(uri,context)){
//                    return;
//                }
//                if (isLogin && !UserModel.getInstance().isLogin()) {
//                    UserModel.getInstance().createLoginDialog(context, () -> {
//                    });
//                    return;
//                }
//
//                if (isGlobal) {
//                    url = UrlSinger.builder().url(url)
//                            .userId(UserModel.getInstance().getUserId() + "")
//                            .toUrl();
//                    uri = Uri.parse(url);
//                }
//                if (url.contains(context.getString(R.string.scheme_host))&&url.contains(context.getString(R.string.sign_scheme_path))) {
//                    MainActivity.startMainWithAnim((Activity) context, 4, url);
//                    return;
//                }
                IntentBuilder.Builder(IntentBuilder.ACTION_VIEW)
                        .setData(uri)
                        .putExtra(IntentBuilder.KEY_HIS_URL, urlHis)
                        .startActivity((Activity) context);
            }
        } catch (Exception e) {
            IntentBuilder.Builder(IntentBuilder.ACTION_VIEW_WEB)
                    .setClass(context, WebViewActivity.class)
                    .setData(Uri.parse(urlHis))
                    .startActivity((Activity) context);
        }
    }

    public static void startWebView(Activity context, String url) {
        IntentBuilder.Builder().setData(Uri.parse(url))
                .setClass(context, WebViewActivity.class)
                .startActivity();
    }
}
