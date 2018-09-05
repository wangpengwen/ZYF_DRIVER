package com.zyf.ui.order;

import android.support.annotation.Nullable;
import android.view.View;

import com.biz.util.RxUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyf.model.entity.order.OrderDetailEntity;
import com.zyf.driver.ui.R;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by TCJK on 2018/6/9.
 */

public class GoodsAddOrderAdapter extends BaseQuickAdapter<OrderDetailEntity, OrderHolder> {

    public Set<String> mEditCheckedSet= new HashSet<>();

    public GoodsAddOrderAdapter() {
        super(R.layout.item_order_info);
    }

    @Override
    protected void convert(OrderHolder holder, OrderDetailEntity item) {
        if(item==null) return;

        if(mEditCheckedSet.contains(item.getOrderNum())){

            holder.checkBox.setChecked(true);
        }else {

            holder.checkBox.setChecked(false);
        }

        holder.checkBox.setVisibility(View.VISIBLE);
        holder.tvId.setText(item.getOrderNum());
        holder.tvNum.setText(item.getCargo()!=null ? item.getCargo().getCargoAmount():"");
        holder.tvStart.setText(item.getSender()!=null ? item.getSender().getSenderAddr():"");
        holder.tvEnd.setText(item.getReciver()!=null ? item.getReciver().getReciverAddr():"");
        holder.tvGoodsName.setText(item.getCargo()!=null ? item.getCargo().getCargoName():"");
        holder.tvSender.setText(item.getSender()!=null ? item.getSender().getSenderName():"");
        holder.tvReceipients.setText(item.getReciver()!=null ? item.getReciver().getReciverName():"");

        RxUtil.click(holder.checkBox).subscribe(o -> {

            if(holder.checkBox.isChecked()){

                mEditCheckedSet.add(item.getOrderNum());
            }else {
                if(mEditCheckedSet.contains(item.getOrderNum())){
                    mEditCheckedSet.remove(item.getOrderNum());
                }
            }
            notifyItemChanged(this.getParentPosition(item));
        });
    }

    public void checkAll(){

        for (OrderDetailEntity orderDetailEntity:getData()) {
            mEditCheckedSet.add(orderDetailEntity.getOrderNum());
        }
        notifyDataSetChanged();
    }

    public void clearCheck(){
        mEditCheckedSet.clear();
        notifyDataSetChanged();
    }

    public void clearCurrentCheck(){

        for (OrderDetailEntity entity:getData()) {
            if(mEditCheckedSet.contains(entity.getOrderNum()))
                mEditCheckedSet.remove(entity.getOrderNum());
        }
        notifyDataSetChanged();
    }

    @Override
    public void setNewData(@Nullable List<OrderDetailEntity> data) {
        super.setNewData(data);
//        clearCheck();
    }

    public String getCheckedIds(){

        StringBuffer sb = new StringBuffer();
        for (String id:mEditCheckedSet) {
            sb.append(id).append("_");
        }
        if(sb.length()>0){
            sb = sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }
}
