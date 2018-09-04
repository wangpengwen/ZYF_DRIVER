package com.zywl.ui.car;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zywl.model.entity.car.CarEntity;
import com.zywl.model.entity.goods.StationEntity;
import com.zywl.ui.R;
import com.zywl.ui.goods.station.StationViewHolder;

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
