package com.zyf.app;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.biz.util.LogUtil;
import com.biz.util.UrlUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(path)
                .apply(RequestOptions.centerInsideTransform())
                .into(imageView);
    }

    public static String getOssImageUri(String uri) {
        if (uri == null) return "";
        if (uri.contains("http")) {
            return uri;
        }
        String url = UrlUtils.addEndSeparator("https://zyf-hwpic.oss-cn-beijing.aliyuncs.com/") + UrlUtils.deleteBeginSeparator(uri);
        LogUtil.print(url);
        return url;
    }
    public static String trimOss(String uri){
        if(TextUtils.isEmpty(uri)) return uri;
        String headUri=UrlUtils.addEndSeparator("https://zyf-hwpic.oss-cn-beijing.aliyuncs.com/");
        if(!TextUtils.isEmpty(headUri)){
            return uri.replace(headUri,"");
        }
        return uri;
    }
}