package com.zyf.model.entity;

/**
 * Title: GenderEnum
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/12/4  14:29
 *
 * @author wangwei
 * @version 1.0
 */
public interface GenderEnum {
    String FEMALE = "FEMALE";//女
    String MALE = "MALE";//男
    String SECRET = "SECRET";//秘密

    static String getGenderEnum(int index) {
        switch (index){
            case 0:return MALE;
            case 1:return FEMALE;
            case 2:return SECRET;
            default:return SECRET;
        }
    }
}
