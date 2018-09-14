package com.zyf.ui.user.order;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyf.driver.ui.R;
import com.zyf.model.entity.order.WebOrderEntity;

/**
 * Created by TCJK on 2018/4/3.
 */

public class WebOrderAdapter extends BaseQuickAdapter<WebOrderEntity, WebOrderHolder> {

    String type;
    WebOrderViewModel mViewModel;

    public WebOrderAdapter(String type,WebOrderViewModel viewModel) {
        super(R.layout.item_user_order);
        this.type = type;
        this.mViewModel = viewModel;
    }

    @Override
    protected void convert(WebOrderHolder holder, WebOrderEntity item) {
        holder.bindData(item,mViewModel);
    }
}
