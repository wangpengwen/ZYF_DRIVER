package com.zywl.ui.goods.station;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zywl.model.entity.goods.StationEntity;
import com.zywl.ui.R;

/**
 * Created by TCJK on 2018/6/5.
 */

public class StationAdapter extends BaseQuickAdapter<StationEntity, StationViewHolder> {

    public StationAdapter() {
        super(R.layout.item_station);
    }

    @Override
    protected void convert(StationViewHolder holder, StationEntity item) {

        holder.stationName.setText(item.getStationName());
        holder.stationAddress.setText(item.getStationAddr());
    }
}
