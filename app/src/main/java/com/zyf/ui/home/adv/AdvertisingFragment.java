package com.zyf.ui.home.adv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.biz.base.BaseFragment;
import com.biz.base.FragmentBackHelper;
import com.biz.util.LogUtil;
import com.biz.util.Utils;
import com.bumptech.glide.Glide;
import com.zyf.driver.ui.R;

import java.math.BigDecimal;

/**
 * Title: AdvertisingFragment
 * Description:
 * Copyright:Copyright(c)2016
 * Company:博智维讯信息技术有限公司
 * CreateTime:22/05/2017  13:43
 *
 * @author johnzheng
 * @version 1.0
 */

public class AdvertisingFragment extends BaseFragment implements FragmentBackHelper {
    public static final int DEF_H=1136;
    public static final int DEF_W=640;
    private ImageView icon;
    private AppCompatImageView btnClose;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_advertising_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int w=getContext().getResources().getDisplayMetrics().widthPixels- Utils.dip2px(40);
        int h=(new BigDecimal(w)
                .multiply(new BigDecimal(DEF_H))).divide(new BigDecimal(DEF_W),1, BigDecimal.ROUND_HALF_UP).intValue();
        LogUtil.print("adv h:"+h);
        icon =  findViewById(R.id.icon);
        icon.setScaleType(ImageView.ScaleType.FIT_XY);
        ConstraintLayout.LayoutParams layoutParams=new ConstraintLayout.LayoutParams(w,h);
        icon.setLayoutParams(layoutParams);
        btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(v -> {
            removeFragment();
        });
        view.setOnClickListener(v -> {});
        icon.setOnClickListener(v -> {

            removeFragment();
        });

    }

    @Override
    public void onResume() {
        super.onResume();
       icon.postDelayed(()->{
           Glide.with(icon).load(R.mipmap.img_adv).into(icon);
       },500);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    private void removeFragment(){
        getActivity().getSupportFragmentManager().beginTransaction()
                .remove(AdvertisingFragment.this)
                .commitAllowingStateLoss();
    }

    @Override
    public boolean onBackPressed() {
        removeFragment();
        return true;
    }
}
