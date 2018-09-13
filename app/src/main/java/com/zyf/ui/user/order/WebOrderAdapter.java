package com.zyf.ui.user.order;

import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyf.driver.ui.R;
import com.zyf.model.entity.order.WebOrderEntity;

import butterknife.BindView;

/**
 * Created by TCJK on 2018/4/3.
 */

public class WebOrderAdapter extends BaseQuickAdapter<WebOrderEntity, WebOrderHolder> {

    String type;

    public WebOrderAdapter(String type) {
        super(R.layout.item_user_order);
        this.type = type;
    }

    @Override
    protected void convert(WebOrderHolder holder, WebOrderEntity item) {
        holder.bindData(item);
    }
}
