package com.zyf.ui.user.order;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyf.model.entity.order.UserOrderItemEntity;
import com.zyf.driver.ui.R;

/**
 * Created by TCJK on 2018/4/3.
 */

public class UserOrderAdapter extends BaseQuickAdapter<UserOrderItemEntity, UserOrderHolder> {

    String type;

    public UserOrderAdapter(String type){
        super(R.layout.item_user_order);
        this.type = type;
    }


    @Override
    protected void convert(UserOrderHolder holder, UserOrderItemEntity item) {

        holder.tvSender.setText(item.getSenderName());
        holder.tvRecipients.setText(item.getReciverName());

        holder.tvFromAddressDetail.setText(item.getSenderAddr());
        holder.tvToAddressDetail.setText(item.getReciverAddr());

//        if("0".equals(item.getOrderManifestState())){
//            holder.tvOrderStatus.setText("未完成");
//        }else if("1".equals(item.getOrderManifestState())){
//            holder.tvOrderStatus.setText("已完成");
//        }else if("4".equals(item.getOrderManifestState())){
//            holder.tvOrderStatus.setText("货到付款");
//        }
        holder.tvPrice.setText(item.getCarriage()+"元");
    }
}
