package com.zyf.ui.goods.station;

import android.view.View;
import android.widget.TextView;

import com.biz.base.BaseViewHolder;
import com.zyf.driver.ui.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TCJK on 2018/6/5.
 */

public class StationViewHolder extends BaseViewHolder {

    @BindView(R.id.tv_station_name)
    TextView stationName;
    @BindView(R.id.tv_station_address)
    TextView stationAddress;

    public StationViewHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }
}
