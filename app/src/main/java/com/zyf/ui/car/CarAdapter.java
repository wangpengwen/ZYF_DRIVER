package com.zyf.ui.car;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyf.model.entity.car.CarEntity;
import com.zyf.driver.ui.R;

/**
 * Created by ltxxx on 2018/6/5.
 */

public class CarAdapter extends BaseQuickAdapter<CarEntity, CarViewHolder> {

    public CarAdapter() {
        super(R.layout.item_car);
    }

    @Override
    protected void convert(CarViewHolder holder, CarEntity item) {

        holder.carNum.setText(item.getTruckCarnum());
        holder.carID.setText(item.getTruckNum());
    }
}
