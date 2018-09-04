package com.biz.http;

import android.os.Looper;
import android.text.TextUtils;

import com.biz.application.BaseApplication;
import com.biz.util.GsonUtil;
import com.biz.util.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;


/**
 * Title: RxNet
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/11/7  10:37
 *
 * @author wangwei
 * @version 1.0
 */
public class RxNet {
    public static Observable<String> newRequest(BaseRequest request) {
        return Observable.create(subscriber -> {
            request.replaceHeadUrl();
            request.setBeginTime(System.currentTimeMillis());
            String url = request.getUrl();
            if (TextUtils.isEmpty(url)) {
                throw new RuntimeException("http url is empty !");
            }
            LogUtil.print(request.getBeginTime() + " url:" + url);
            String s = submitRequest(subscriber, request);
            LogUtil.print(request.getBeginTime() + " s:" + s);
            if (!request.isHtml() && TextUtils.isEmpty(s)) {
                ResponseJson responseJson = new ResponseJson();
                responseJson.status = -1;
                responseJson.errormsg = BaseApplication.getAppContext().getString(R.string.text_network_error);
                s = GsonUtil.toJson(responseJson);
            } else if (request.isHtml() && TextUtils.isEmpty(s)) {
                s = "";
            }
            request.setEndTime(System.currentTimeMillis());
            subscriber.onNext(s);
            subscriber.onCompleted();
        });
    }

    private synchronized static String submitRequest(Subscriber subscriber, BaseRequest request) {
        OkHttpClient client = request.getOKHttpBuilder().build();
        String result = null;
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        Call call = null;
        try {
            okhttp3.Request r = request.getOKHttpRequest();
            if (r == null) {
                return null;
            }
            final Call call1 = client.newCall(r);
            call = call1;
            subscriber.add(unSubscribeInUiThread(() -> {
                if (call1 != null)
                    call1.cancel();
            }));
            Response response = call.execute();
            if (response.code() == 200) {
                is = response.body().byteStream();
                if (is != null) {
                    bos = new ByteArrayOutputStream();
                    byte b[] = new byte[1024];
                    int i = 0;
                    while ((i = is.read(b, 0, b.length)) != -1) {
                        bos.write(b, 0, i);
                    }
                    result = new String(bos.toByteArray(), "UTF-8");
                } else {
                    result = null;
                }
            } else {
                LogUtil.print("error code:" + response.code() + " url:" + request.getUrl());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            LogUtil.print("MalformedURLException"+request.getUrl());
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            LogUtil.print("SocketTimeoutException"+request.getUrl());
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.print("IOException"+request.getUrl());
        } finally {
            try {
                if (call != null) {
                    call.cancel();
                }
                if (is != null) {
                    is.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
            }
        }
        return result;
    }

    public static Subscription unSubscribeInUiThread(final Action0 unSubscribe) {
        return Subscriptions.create(new Action0() {
            @Override
            public void call() {
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    unSubscribe.call();
                } else {
                    final Scheduler.Worker inner = AndroidSchedulers.mainThread().createWorker();
                    inner.schedule(new Action0() {
                        @Override
                        public void call() {
                            unSubscribe.call();
                            inner.unsubscribe();
                        }
                    });
                }
            }
        });
    }
}
