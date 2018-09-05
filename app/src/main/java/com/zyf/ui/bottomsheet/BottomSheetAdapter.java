package com.zyf.ui.bottomsheet;


import android.support.v7.widget.RecyclerView;

import com.biz.base.BaseViewHolder;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.zyf.driver.ui.R;
import com.yqritc.recyclerviewflexibledivider.FlexibleDividerDecoration;

import java.util.List;

public class BottomSheetAdapter extends BaseMultiItemQuickAdapter<BottomSheetMultipleItem, BaseViewHolder>
        implements FlexibleDividerDecoration.ColorProvider {


    public BottomSheetAdapter(List<BottomSheetMultipleItem> data) {
        super(data);
        addItemType(BottomSheetMultipleItem.CAMERA, R.layout.item_single_text_layout);
        addItemType(BottomSheetMultipleItem.GALLERY, R.layout.item_single_text_layout);
        addItemType(BottomSheetMultipleItem.CANCEL, R.layout.item_single_text_layout);
    }


    @Override
    protected void convert(BaseViewHolder holder, BottomSheetMultipleItem bottomSheetMultipleItem) {
        holder.setText(R.id.title, bottomSheetMultipleItem.item);
    }


    @Override
    public int dividerColor(int position, RecyclerView parent) {
        return parent.getResources().getColor(R.color.color_divider);
    }
}
