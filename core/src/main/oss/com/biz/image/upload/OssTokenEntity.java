package com.biz.image.upload;

import android.content.Context;

import com.biz.util.SysTimeUtil;

public class OssTokenEntity {
    public String accessKeyId;
    public String accessKeySecret;
    public String securityToken;
    public long expiration;

    public boolean isEffective(Context context){
        if(expiration-1000*60*10< SysTimeUtil.getSysTime(context)){
            return false;
        }
        return true;
    }
}
