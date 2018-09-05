package com.zyf.ui.goods;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyf.model.entity.goods.GoodsEntity;
import com.zyf.driver.ui.R;

/**
 * Created by TCJK on 2018/5/24.
 */

public class GoodsAdapter extends BaseQuickAdapter<GoodsEntity, GoodsHolder> {

    public GoodsAdapter() {
        super(R.layout.item_goods_info);
    }

    @Override
    protected void convert(GoodsHolder holder, GoodsEntity item) {
        holder.tvId.setText(item.getManifestNum());
        holder.tvName.setText(item.getManifestName());
        holder.tvStart.setText(item.getManifestStart());
        holder.tvEnd.setText(item.getManifestEnd());
        holder.tvGoodsNum.setText(item.getCargoCount());
        holder.tvCar.setText("");
//        holder.tvGoodsStatus.setText("");
//        holder.tvReceiptStatus.setText("");
        holder.tvCreateTime.setText(item.getManifestInsTime());
    }
}
