package com.zywl.ui.hangye;

import android.view.View;
import android.widget.TextView;

import com.biz.base.BaseViewHolder;
import com.zywl.ui.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ltxxx on 2018/6/28.
 */

public class CompanyViewHolder extends BaseViewHolder {

    @BindView(R.id.tv_name)
    TextView nameTV;
    @BindView(R.id.tv_address)
    TextView addressTV;
    @BindView(R.id.tv_phone)
    TextView phoneTV;
    @BindView(R.id.tv_road)
    TextView roadTV;
    @BindView(R.id.tv_line)
    TextView lineTV;

    public CompanyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
