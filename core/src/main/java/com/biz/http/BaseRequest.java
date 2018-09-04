package com.biz.http;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.biz.application.BaseApplication;
import com.biz.http.sign.Signer;
import com.biz.util.FileUtil;
import com.biz.util.GsonUtil;
import com.biz.util.Maps;
import com.biz.util.SysTimeUtil;
import com.biz.util.UrlUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by wangwei on 2016/3/15.
 */
public class BaseRequest<T> {
    public static final String DEF_CONFIG_URL = BuildConfig.APPLICATION_ID + ".text";
    private Map<String, Object> mapBody = Maps.newHashMap();
    protected Type toJsonType;
    private Object bodyObj;
    protected String headUrl;
    private String url;
    private boolean isAddUrlPara = true;
    private RestMethodEnum restMethodEnum = RestMethodEnum.POST;
    private long beginTime = 0, endTime = 0;
    protected Object userId;
    protected Object token;
    private long connectTime = 15 * 1000;
    private long readTime = 20 * 1000;
    private boolean isHttps = false;
    protected Map<String, Object> paraPublic = Maps.newHashMap();
    private boolean isHtml = false;
    FormBody.Builder requestBuilder = new FormBody.Builder();

    private static boolean isDebug = false;

    public BaseRequest() {
        headUrl = BaseApplication.getAppContext().getString(R.string.defualt_http_url);
        if (!UrlUtils.isEndEquals(headUrl, "/")) {
            headUrl = UrlUtils.addEndSeparator(headUrl);
        }
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setDebug(boolean isDebug) {
        BaseRequest.isDebug = isDebug;
    }

    public static void init(Context context) {
        ParaConfig.initialize();
    }


    public static <T> BaseRequest<T> builder() {
        BaseRequest<T> request = new BaseRequest<T>();
        request.https(false);
        request.setDefaultConnectTime();
        return request;
    }

    public BaseRequest<T> setDefaultConnectTime() {
        return connectTime(BaseApplication.getAppContext().getResources().getInteger(R.integer.time_connect_out))
                .readTime(BaseApplication.getAppContext().getResources().getInteger(R.integer.time_read_out));
    }

    /**
     * 是否走HTTPS通道
     */
    public BaseRequest<T> https(boolean isHttps) {
        this.isHttps = isHttps;
        return this;
    }

    /**
     * 设置访问网络的连接时间
     */
    public BaseRequest<T> connectTime(long connectTime) {
        this.connectTime = connectTime;
        return this;
    }

    /**
     * 设置访问网络的读取时间
     */
    public BaseRequest<T> readTime(long readTime) {
        this.readTime = readTime;
        return this;
    }

    /**
     * 设置访问head
     * 默认为缓存库里面的
     */
    public BaseRequest<T> headUrl(String headUrl) {
        this.headUrl = headUrl;
        if (this.headUrl != null && !UrlUtils.isEndEquals(this.headUrl, "/")) {
            this.headUrl = UrlUtils.addEndSeparator(this.headUrl);
        }
        return this;
    }

    /**
     * 转换JSON的对象 参考goson
     */
    public BaseRequest<T> setToJsonType(Type toJsonType) {
        this.toJsonType = toJsonType;
        return this;
    }

    /**
     * 网络访问url
     */
    public BaseRequest<T> url(String url) {
        this.url = url;
        if (this.url != null && UrlUtils.isBeginEquals(this.url, "/")) {
            this.url = UrlUtils.deleteBeginSeparator(this.url);
        }
        return this;
    }

    /**
     * 网络访问url
     *
     * @param url res api
     */
    public BaseRequest<T> url(int url) {
        this.url = BaseApplication.getAppContext().getString(url);
        if (this.url != null && !UrlUtils.isBeginEquals(this.url, "/")) {
            this.url = UrlUtils.deleteBeginSeparator(this.url);
        }
        return this;
    }

    /**
     * 添加网络访问参数
     * 不能与addBody(Object obj) 共用
     * 如果设置了addBody(Object obj)后 本方法失效
     */
    public BaseRequest<T> addBody(String key, String value) {
        requestBuilder.add(key, value);
//        if (mapBody.containsKey(key)) mapBody.remove(key);
//        mapBody.put(key, obj);
        return this;
    }

    public BaseRequest<T> addPublicPara(String key, String value) {
        requestBuilder.add(key, value);
//        if (paraPublic.containsKey(key)) paraPublic.remove(key);
//        paraPublic.put(key, obj);
        return this;
    }

//    /**
//     * 添加网络访问参数
//     */
//    public BaseRequest<T> addBody(Object obj) {
//        bodyObj = obj;
//        return this;
//    }

    /**
     * userId
     */
    public BaseRequest<T> userId(Object userId) {
        this.userId = userId;
        return this;
    }

    /**
     * token
     */
    public BaseRequest<T> token(Object token) {
        this.token = token;
        return this;
    }

    /**
     * 当HTML 为true时 不返回rest错误
     */
    public BaseRequest<T> html(boolean html) {
        isHtml = html;
        return this;
    }

    public RestMethodEnum getRestMethodEnum() {
        return restMethodEnum;
    }

//    public String getBodyObj() {
//        try {
//            if (bodyObj != null) return bodyObj.toString();
//        } catch (Exception e) {
//        }
//        if (mapBody == null || mapBody.size() == 0) return null;
//        try {
//            return GsonUtil.toJson(mapBody);
//        } catch (Exception e) {
//            return null;
//        }
//
//    }

    public RequestBody getRequestBody(){
        buildParaPublic();
        return requestBuilder.build();
    }

    /**
     * 访问方式
     */
    public BaseRequest<T> restMethod(RestMethodEnum restMethodEnum) {
        this.restMethodEnum = restMethodEnum;
        return this;
    }

    public String getHeadUrl() {
        return headUrl;
    }


    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public boolean isHtml() {
        return isHtml;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public boolean isHttps() {
        return isHttps;
    }

    public void replaceHeadUrl() {
//        if (isDebug()) {
//            try {
//                File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + getDefConfigUrl());
//                if (!file.exists()) {
//                    FileUtil.setString(Environment.getExternalStorageDirectory().getPath() + File.separator + getDefConfigUrl(), getHeadUrl());
//                }
//                String headUrl = FileUtil.getString(Environment.getExternalStorageDirectory().getPath() + File.separator + getDefConfigUrl());
//                if (!TextUtils.isEmpty(headUrl)) {
//                    headUrl(headUrl);
//                }
//            } catch (Exception e) {
//            }
//        }
    }

    protected String getDefConfigUrl() {
        String name = null;
        try {
            name = BaseApplication.getAppContext().getString(R.string.def_url_config_name);
        } catch (Exception e) {
        }
        if (TextUtils.isEmpty(name)) {
            return DEF_CONFIG_URL;
        }
        return name;
    }

    /**
     * 返回网络访问对象 没开线程
     */
    public Observable<T> requestJson() {
        Observable<T> observable = RxNet.newRequest(this)
                .map(s -> GsonUtil.fromJson(s, toJsonType));
        observable = observable.map(e -> {
            if (e instanceof ResponseJson) {
                ResponseJson responseJson = (ResponseJson) e;
                if (responseJson.isOk()) {
                    SysTimeUtil.initTime(BaseApplication.getAppContext(), responseJson.ts);
                } else {
                    if (TextUtils.isEmpty(responseJson.errormsg)) {
                        responseJson.errormsg =BaseApplication.getAppContext().getString(R.string.text_network_error);
                    }
                }
                responseJson.execTime = endTime - beginTime;
            }
            return e;
        });
        return observable;
    }

    /**
     * @return
     */
    public Observable<String> requestHtml() {
        return RxNet.newRequest(this);
    }

    public String getUrl() {
        return getUrl(true);
    }
    public String getUrl(boolean isSign) {
        if(!isSign){
            if(url!=null&&(url.contains("http://")||url.contains("https://"))){
                return url;
            }else {
                return getHeadUrl() + url;
            }
        }
//        if (isAddUrlPara) {
            String s =null;
            if(url!=null&&(url.contains("http://")||url.contains("https://"))){
                s=url;
            }else {
                s=getHeadUrl() + url;
            }
            isAddUrlPara = false;
            isHtml = false;

//            if (s.indexOf("?") > -1) {
//                return url = s + "&" + Signer.toSign(getParaPublic(), restMethodEnum, getBodyObj());
//            } else {
//                return url = s + "?" + Signer.toSign(getParaPublic(), restMethodEnum, getBodyObj());
//            }
            return s;
//        } else return url;
    }

    protected Map<String, Object> getParaPublic() {
//        if (userId != null && !TextUtils.isEmpty(userId.toString())) {
//            if (paraPublic.containsKey("userId")) paraPublic.remove("userId");
//            paraPublic.put("userId", userId);
//        }
        return paraPublic;
    }

    protected void buildParaPublic() {
        if (token != null && !TextUtils.isEmpty(token.toString())) {
            requestBuilder.add("token",token.toString());
        }
    }

    public OkHttpClient.Builder getOKHttpBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(connectTime, TimeUnit.MILLISECONDS)
                .writeTimeout(connectTime, TimeUnit.MILLISECONDS)
                .readTimeout(readTime, TimeUnit.MILLISECONDS);
        if (isHttps()) {
            try {
                builder.sslSocketFactory(getCertificates(BaseApplication.getAppContext().getAssets().open("cert.cer")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return builder;
    }

    public okhttp3.Request getOKHttpRequest() {
        okhttp3.Request request = null;
        if (restMethodEnum == RestMethodEnum.POST) {
//            RequestBody requestBody = new FormBody.Builder().add("data", getBodyObj()).build();
            RequestBody requestBody = getRequestBody();
            request = new okhttp3.Request.Builder().url(getUrl())
                    .post(requestBody).build();
        } else if (restMethodEnum == RestMethodEnum.GET) {
            request = new okhttp3.Request.Builder().url(getUrl()).get().build();
        }
        return request;
    }


    private static SSLSocketFactory getCertificates(InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
                    );
            return sslContext.getSocketFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("not exists ssl");
    }
}
