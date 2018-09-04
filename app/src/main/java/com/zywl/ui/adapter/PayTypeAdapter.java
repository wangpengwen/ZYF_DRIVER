package com.zywl.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.biz.util.Lists;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yqritc.recyclerviewflexibledivider.FlexibleDividerDecoration;
import com.zywl.ui.R;

/**
 * Created by TCJK on 2018/3/29.
 */

public class PayTypeAdapter extends BaseQuickAdapter<String, BaseViewHolder>
        implements FlexibleDividerDecoration.ColorProvider{

    public PayTypeAdapter(Context context){
        super(R.layout.item_single_text_layout, Lists.newArrayList(
                context.getString(R.string.text_weixin_pay),
                context.getString(R.string.text_alipay_pay),
                context.getString(R.string.text_pingan_pay),
                context.getString(R.string.text_pay_on_delivery)));
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.title, item);
    }

    @Override
    public int dividerColor(int position, RecyclerView parent) {
        return parent.getResources().getColor(R.color.color_divider);
    }
}
