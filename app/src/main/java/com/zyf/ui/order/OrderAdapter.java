package com.zyf.ui.order;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyf.model.entity.order.OrderEntity;
import com.zyf.driver.ui.R;

/**
 * Created by TCJK on 2018/6/1.
 */

public class OrderAdapter extends BaseQuickAdapter<OrderEntity, OrderHolder> {

    public OrderAdapter() {
        super(R.layout.item_order_info);
    }

    @Override
    protected void convert(OrderHolder holder, OrderEntity item) {
        holder.tvId.setText(item.getOrderNum());
        holder.tvNum.setText(item.getCargoAmount());
        holder.tvStart.setText(item.getSenderAddr());
        holder.tvEnd.setText(item.getReciverAddr());
        holder.tvGoodsName.setText(item.getCargoName());
        holder.tvSender.setText(item.getSenderName());
        holder.tvReceipients.setText(item.getReciverName());
    }
}
