package com.zyf.model.entity.vehicle;

/**
 * Title: VehicleEntity
 * Description:
 * Copyright:Copyright(c)2018
 * Company:同城酒库电子商务有限公司
 * CreateTime:2018/9/7  16:07
 *
 * @author liutong
 */
public class VehicleEntity {

    /**中型  小型  微型*/
    public String type;

    public String name;

    public String desc;

    public boolean isChecked = false;

    public VehicleEntity(String type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }
}
