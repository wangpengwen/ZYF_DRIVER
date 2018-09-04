package com.zywl.ui.goods;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zywl.model.entity.goods.GoodsEntity;
import com.zywl.ui.R;

import butterknife.BindView;

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
