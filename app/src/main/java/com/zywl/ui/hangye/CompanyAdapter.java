package com.zywl.ui.hangye;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zywl.model.entity.hangye.CompanyEntity;
import com.zywl.ui.R;

/**
 * Created by ltxxx on 2018/6/28.
 */

public class CompanyAdapter extends BaseQuickAdapter<CompanyEntity, CompanyViewHolder> {

    public CompanyAdapter() {
        super(R.layout.item_company);
    }

    @Override
    protected void convert(CompanyViewHolder holder, CompanyEntity item) {
        holder.nameTV.setText(item.getLogisticName());
        holder.addressTV.setText(item.getLogisticAddr());
        holder.phoneTV.setText(item.getLogisticPhone());
        holder.lineTV.setText(item.getLogisticLine());
        holder.roadTV.setText(item.getLogisticRoad());
    }
}
