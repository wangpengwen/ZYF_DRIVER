package com.zyf.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.biz.util.Lists;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yqritc.recyclerviewflexibledivider.FlexibleDividerDecoration;
import com.zyf.model.entity.store.StoreEntity;
import com.zyf.driver.ui.R;

/**
 * Created by TCJK on 2018/3/29.
 */

public class StoreAdapter extends BaseQuickAdapter<StoreEntity, BaseViewHolder>
        implements FlexibleDividerDecoration.ColorProvider{

    public StoreAdapter(Context context){
        super(R.layout.item_single_text_layout, Lists.newArrayList());
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreEntity item) {
        helper.setText(R.id.title, item.getStorageName());
    }

    @Override
    public int dividerColor(int position, RecyclerView parent) {
        return parent.getResources().getColor(R.color.color_divider);
    }
}
