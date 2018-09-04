package com.zywl.ui.goods;

import android.view.View;
import android.widget.TextView;

import com.biz.base.BaseViewHolder;
import com.zywl.ui.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TCJK on 2018/5/24.
 */

public class GoodsHolder extends BaseViewHolder {

    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;
    @BindView(R.id.tv_car)
    TextView tvCar;
    @BindView(R.id.tv_goods_status)
    TextView tvGoodsStatus;
    @BindView(R.id.tv_receipt_status)
    TextView tvReceiptStatus;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;

    public GoodsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

}
