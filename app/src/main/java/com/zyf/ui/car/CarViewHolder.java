package com.zyf.ui.car;

import android.view.View;
import android.widget.TextView;

import com.biz.base.BaseViewHolder;
import com.zyf.driver.ui.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ltxxx on 2018/6/5.
 */

public class CarViewHolder extends BaseViewHolder {

    @BindView(R.id.tv_car_num)
    TextView carNum;
    @BindView(R.id.tv_car_id)
    TextView carID;

    public CarViewHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }
}
