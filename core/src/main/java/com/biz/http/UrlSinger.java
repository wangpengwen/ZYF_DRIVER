package com.biz.http;


import com.biz.application.BaseApplication;
import com.biz.http.sign.Signer;
import com.biz.util.UrlUtils;

/**
 * Created by wangwei on 2016/3/31.
 */
final public class UrlSinger {
    private String url;
    private String head;
    private String userId;

    public UrlSinger() {
        head = BaseApplication.getAppContext().getString(R.string.defualt_http_url);
        if (UrlUtils.isEndEquals(head, "/")) {
            head = UrlUtils.addEndSeparator("/");
        }
    }

    public static UrlSinger builder() {
        UrlSinger urlSinger = new UrlSinger();
        return urlSinger;
    }

    public UrlSinger userId(String userId) {
        this.userId = userId;
        return this;
    }

    public UrlSinger url(String url) {
        this.url = url;
        return this;
    }

    public String toUrl() {
        if (this.url != null && this.url.indexOf("http") > -1) {
            if (this.url.indexOf("?") > -1) {
                return url + "&" + Signer.toSign(userId, RestMethodEnum.GET);
            } else {
                return url + "?" + Signer.toSign(userId, RestMethodEnum.GET);
            }
        } else {
            if (this.url.indexOf("?") > -1) {
                return head + url + "&" + Signer.toSign(userId, RestMethodEnum.GET);
            } else {
                return head + url + "?" + Signer.toSign(userId, RestMethodEnum.GET);
            }
        }
    }
}
