package com.zywl.ui.order;

import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.TextView;

import com.biz.base.BaseViewHolder;
import com.zywl.ui.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TCJK on 2018/5/24.
 */

public class OrderHolder extends BaseViewHolder {

    @BindView(R.id.checkBox)
    AppCompatCheckBox checkBox;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_sender)
    TextView tvSender;
    @BindView(R.id.tv_sender_contact)
    TextView tvSenderContact;
    @BindView(R.id.tv_receipients)
    TextView tvReceipients;
    @BindView(R.id.tv_receipients_contact)
    TextView tvReceipientsContact;

    public OrderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

}
