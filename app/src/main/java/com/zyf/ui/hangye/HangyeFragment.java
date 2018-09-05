package com.zyf.ui.hangye;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biz.base.BaseLiveDataFragment;
import com.biz.base.FragmentAdapter;
import com.biz.util.Lists;
import com.biz.util.Utils;
import com.zyf.driver.ui.R;
import com.zyf.ui.user.order.UserOrderViewModel;
import com.zyf.util.TabUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by TCJK on 2018/5/29.
 */

public class HangyeFragment extends BaseLiveDataFragment<UserOrderViewModel> {

    Unbinder unbinder;

    protected TabLayout mTabLayout;
    protected ViewPager mViewPager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(UserOrderViewModel.class,UserOrderViewModel.class.getName(),true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_order, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setTitle("");

        mTabLayout = findViewById(R.id.tabs);
        TabUtils.setIndicator(mTabLayout, Utils.dip2px(12));
        mViewPager = findViewById(R.id.viewpager);

        List<String> titles = Lists.newArrayList(getString(R.string.text_hangye_route), getString(R.string.text_hangye_company), getString(R.string.text_hangye_special));
        List<Fragment> fragments = Lists.newArrayList(new HangyeRouteFragment(),new HangyeCompanyFragment(),new HangyeSpecialFragment());
        mViewPager.setAdapter(new FragmentAdapter(getChildFragmentManager(), fragments, titles));
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setAnimationCacheEnabled(false);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0, false);
    }

}
