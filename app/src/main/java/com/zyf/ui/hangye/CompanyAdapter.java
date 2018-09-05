package com.zyf.ui.hangye;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyf.model.entity.hangye.CompanyEntity;
import com.zyf.driver.ui.R;

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
