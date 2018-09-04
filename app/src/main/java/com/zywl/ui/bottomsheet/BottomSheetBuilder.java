package com.zywl.ui.bottomsheet;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;


/**
 * Title: BottomSheetBuilder
 * Description:
 * Copyright:Copyright(c)2016
 * Company:博智维讯信息技术有限公司
 * CreateTime:16/05/2017  10:36
 *
 * @author johnzheng
 * @version 1.0
 */

public class BottomSheetBuilder {


    public static BottomSheetDialog createBottomSheet(Context context, BaseQuickAdapter adapter) {
        RecyclerView recyclerView = new RecyclerView(context);
        //recyclerView.setPadding(0, 0, 0, Utils.dip2px(20));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context)
                .colorResId(com.biz.http.R.color.color_divider).size(1).build());
        recyclerView.setAdapter(adapter);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(recyclerView);
        bottomSheetDialog.show();
        return bottomSheetDialog;
    }



    public static BottomSheetDialog createPhotoBottomSheet(Context context , BaseQuickAdapter.OnItemClickListener onItemClickListener) {
        RecyclerView recyclerView = new RecyclerView(context);
        //recyclerView.setPadding(0, 0, 0, Utils.dip2px(20));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context)
                .colorResId(com.biz.http.R.color.color_divider).size(1).build());
        BottomSheetAdapter adapter = new BottomSheetAdapter(BottomSheetMultipleItem.getList(context));
        adapter.setOnItemClickListener(onItemClickListener);
        recyclerView.setAdapter(adapter);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(recyclerView);
        bottomSheetDialog.show();
        return bottomSheetDialog;
    }


//    public static BottomSheetDialog showShareDialog(Context context , int rowCount, BaseQuickAdapter.OnItemClickListener onItemClickListener){
//        RecyclerView recyclerView = new RecyclerView(context);
//        recyclerView.setBackgroundResource(android.R.color.transparent);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setLayoutManager(new GridLayoutManager(context,rowCount));
//        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context)
//                .colorResId(R.color.color_background).size(Utils.dip2px(10)).build());
//        BottomSheetShareAdapter adapter = new BottomSheetShareAdapter(BottomSheetShareMultipleItem.getList(context),rowCount);
//        recyclerView.setAdapter(adapter);
//        View footer = LayoutInflater.from(context).inflate(R.layout.bottom_share_cancel_footer, null);
//        adapter.addFooterView(footer);
//        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
//        footer.setOnClickListener(view1 -> bottomSheetDialog.dismiss());
//        bottomSheetDialog.setContentView(recyclerView);
//        adapter.setOnItemClickListener((adapter1, view1, position) -> {
//            onItemClickListener.onItemClick(adapter,view1,position);
//            bottomSheetDialog.dismiss();
//        });
//        bottomSheetDialog.show();
//        return bottomSheetDialog;
//    }

}
