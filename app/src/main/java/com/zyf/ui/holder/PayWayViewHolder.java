//package com.lcparking.ui.holder;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.support.design.widget.BottomSheetDialog;
//import android.support.v7.widget.AppCompatImageView;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.biz.base.BaseViewHolder;
//import com.biz.util.Utils;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.lcparking.ui.R;
//
//import java.util.List;
//
//import cn.iwgang.simplifyspan.SimplifySpanBuild;
//import cn.iwgang.simplifyspan.other.SpecialGravity;
//import cn.iwgang.simplifyspan.unit.BaseSpecialUnit;
//import cn.iwgang.simplifyspan.unit.SpecialLabelUnit;
//import cn.iwgang.simplifyspan.unit.SpecialTextUnit;
//import rx.Observable;
//import rx.functions.Action1;
//
///**
// * Title: PayWayViewHolder
// * Description:
// * Copyright:Copyright(c)2016
// * Company:博智维讯信息技术有限公司
// * CreateTime:06/12/2017  15:44
// *
// * @author johnzheng
// * @version 1.0
// */
//
//public class PayWayViewHolder extends BaseViewHolder {
//
//    public ImageView icon;
//    public TextView title;
//    public TextView titleHint;
//    public AppCompatImageView rightIcon;
//
//    public PayWayViewHolder(View itemView) {
//        super(itemView);
//        icon = findViewById(R.id.icon);
//        title = findViewById(R.id.title);
//        titleHint = findViewById(R.id.title_hint);
//        rightIcon = findViewById(R.id.icon_right);
//    }
//
//    public void setChecked(boolean isChecked) {
//        if (rightIcon == null) return;
//        if (isChecked) {
//            rightIcon.setImageResource(R.drawable.vector_pay_checked);
//        } else {
//            rightIcon.setImageBitmap(null);
//        }
//    }
//
//
//    public void bindData(PaymentTypeEntity s) {
//        title.setTextColor(Color.parseColor("#2c2c2c"));
//        titleHint.setTextColor(getColors(R.color.color_money));
//        titleHint.setVisibility(View.GONE);
//        if (s.isAlipay()) {
//            icon.setImageResource(R.drawable.vector_alipay);
//        } else if (s.isWechaty()) {
//            icon.setImageResource(R.drawable.vector_wechat_pay);
//        } else if (s.isBCard()) {
//            icon.setImageResource(R.drawable.vector_bank_card);
//        } else if (s.isECard()) {
//            //getECardStringNoMoney(s);
//            if (!s.isStatus) {
//                title.setTextColor(getColors(R.color.gray));
//            }
//            titleHint.setVisibility(View.VISIBLE);
//            titleHint.setText("优惠价");
//            titleHint.setTextColor(s.isStatus ? getColors(R.color.color_money) : getColors(R.color.gray));
//            titleHint.setBackgroundResource(s.isStatus ? R.drawable.shape_pay_hint_red_bg : R.drawable.shape_pay_hint_gray_bg);
//            icon.setImageResource(s.isStatus ? R.drawable.vector_e_wallet : R.drawable.vector_e_wallet_gray);
//        } else if (s.isDeliver()) {
//            icon.setImageResource(R.drawable.vector_cod);
//        }
//        title.setText(s.getPayName());
//    }
//
//    private CharSequence getECardStringNoMoney(PaymentTypeEntity s) {
//        SimplifySpanBuild spanBuild = new SimplifySpanBuild();
//        int textColor = getColors(R.color.color_b9b9b9);
//        spanBuild.append(new SpecialTextUnit(s.getPayName(), textColor, 16))
//                .append(" ")
//                .append(createLabel(getString(R.string.text_coupon_price), textColor, Color.TRANSPARENT, textColor))
//                .append("   ")
//                .append(new SpecialTextUnit(getString(R.string.text_no_money_tip), textColor, 14))
//                .append("");
//
//        return spanBuild.build();
//    }
//
//    private CharSequence getECardString(TextView view, PaymentTypeEntity s) {
//        view.setTextColor(Color.TRANSPARENT);
//        SimplifySpanBuild spanBuild = new SimplifySpanBuild();
//        int textColor = getColors(R.color.color_2c2c2c);
//        int redColor = getColors(R.color.color_money);
//        if (!s.isStatus) {
//            textColor = getColors(R.color.gray);
//            redColor = getColors(R.color.gray);
//        }
//
//        spanBuild.append(new SpecialTextUnit(s.getPayName(), textColor, 16))
//                .append(" ")
//                .append(createLabel(getString(R.string.text_coupon_price), redColor, Color.WHITE, redColor))
//                .append("|");
//
//        return spanBuild.build();
//    }
//
//
//    public BaseSpecialUnit createLabel(String text, int color, int bgColor, int borderColor) {
//        return new SpecialLabelUnit(text, color, Utils.dip2px(14), bgColor)
//                .showBorder(borderColor, 1)
//                .setLabelBgRadius(4)
//                .setPadding(Utils.dip2px(4))
//                .setGravity(SpecialGravity.CENTER);
////       return new SpecialTextUnit(text, color, 12);
////        return new SpecialLabelUnit(text, color, Utils.dip2px(12), bgColor)
////                .setLabelBgRadius(1)
////                .showBorder(borderColor, 1)
////                .setPadding(Utils.dip2px(5))
////                .setGravity(SpecialGravity.CENTER);
//    }
//
//    public BaseSpecialUnit createRedLabel(String text) {
//        return createLabel(text, Color.RED, Color.TRANSPARENT, Color.RED);
//
//    }
//
//    public static PayWayViewHolder createPayView(ViewGroup parent, PaymentTypeEntity s) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pay_way_new_layout, parent, false);
//        PayWayViewHolder holder = new PayWayViewHolder(view);
//        holder.bindData(s);
//        view.setTag(s);
//        parent.addView(view);
//        return holder;
//    }
//
//    public static void createSheet(Context context, List<PaymentTypeEntity> list, String checked, Action1<String> paymentId) {
//        createSheet(context, list, checked, paymentId, false);
//    }
//
//    public static void createSheet(Context context, List<PaymentTypeEntity> list, String checked, Action1<String> paymentId, boolean isBack) {
//        PayWayAdapter mPayWayAdapter = new PayWayAdapter();
//        View mTitleView = View.inflate(context, R.layout.item_single_text_layout, null);
//        TextView textView = mTitleView.findViewById(R.id.title);
//        textView.setText(R.string.text_choose_pay_type);
//        mPayWayAdapter.addHeaderView(mTitleView);
//        mPayWayAdapter.setNewData(list);
//        BottomSheetDialog mDialog = BottomSheetBuilder.createBottomSheet(context, mPayWayAdapter);
//        if (isBack) {
//            View backView = mTitleView.findViewById(R.id.ic_back);
//            backView.setVisibility(View.VISIBLE);
//            backView.setOnClickListener(v -> {
//                mDialog.dismiss();
//            });
//        }
//        if (!TextUtils.isEmpty(checked)) {
//            mPayWayAdapter.setChecked(checked);
//        }
//        mPayWayAdapter.setOnItemClickListener((BaseQuickAdapter baseQuickAdapter, View v, int i) -> {
//            PaymentTypeEntity s = mPayWayAdapter.getItem(i);
//            if (!s.isStatus) return;
//            //textPayWay.setText(s.getPayName());
//            Observable.just(s.paymentId).subscribe(paymentId);
//            if (mDialog != null) {
//                mDialog.dismiss();
//            }
//        });
//    }
//
//}
