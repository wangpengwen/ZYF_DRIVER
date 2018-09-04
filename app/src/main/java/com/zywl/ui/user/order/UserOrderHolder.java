package com.zywl.ui.user.order;

import android.view.View;
import android.widget.TextView;

import com.biz.base.BaseViewHolder;
import com.zywl.ui.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TCJK on 2018/5/30.
 */

public class UserOrderHolder extends BaseViewHolder {

    @BindView(R.id.tv_from_address_detail)
    TextView tvFromAddressDetail;
    @BindView(R.id.tv_sender)
    TextView tvSender;
    @BindView(R.id.tv_to_address_detail)
    TextView tvToAddressDetail;
    @BindView(R.id.tv_recipients)
    TextView tvRecipients;

    @BindView(R.id.tv_status)
    TextView tvOrderStatus;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    public UserOrderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
