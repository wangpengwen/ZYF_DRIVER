package com.zywl.model.entity;

import com.biz.util.GsonUtil;
import com.biz.util.Maps;

import java.util.Map;

/**
 * Title: UserChangeInfoEntity
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/11/24  11:14
 *
 * @author wangwei
 * @version 1.0
 */
public class UserChangeInfoEntity {
    public String birthday;
    public String email;
    public String idCardName;
    public String mobile;
    public String nickName;
    public String portraitUrl;
    public String sex;

    public String toJson() {
        Map<String, Object> map = Maps.newHashMap();
        if (birthday!=null)
            map.put("birthday", birthday);
        if (email!=null)
            map.put("email", email);
        if (idCardName!= null)
            map.put("idCardName", idCardName);
        if (mobile!=null)
            map.put("mobile", mobile);
        if (nickName!=null)
            map.put("nickName", nickName);
        if (portraitUrl!=null)
            map.put("portraitUrl", portraitUrl);
        if (sex!=null)
            map.put("sex", sex);
        return GsonUtil.toJson(map);
    }

    public void changeUserEntity(UserEntity entity) {
//        if (birthday!=null) {
//            entity.birthday = birthday;
//        }
//        if (email!=null) {
//            entity.email = email;
//        }
//        if (mobile!=null) {
//            entity.mobile = mobile;
//        }
//        if (nickName!=null) {
//            entity.nickName = nickName;
//        }
//        if (portraitUrl!=null) {
//            entity.portraitUrl = portraitUrl;
//        }
//        if (sex!=null) {
//            entity.sexType = sex;
//        }
    }
}
