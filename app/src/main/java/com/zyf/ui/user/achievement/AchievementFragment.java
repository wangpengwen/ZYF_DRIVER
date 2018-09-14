package com.zyf.ui.user.achievement;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biz.base.BaseLiveDataFragment;
import com.github.mikephil.charting.charts.BarChart;
import com.zyf.model.entity.achievement.AchievementDayEntity;
import com.zyf.driver.ui.R;
import com.zyf.util.ChartManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by TCJK on 2018/5/29.
 */

public class AchievementFragment extends BaseLiveDataFragment<AchievementViewModel> {

    @BindView(R.id.chart)
    BarChart mChart;
    @BindView(R.id.carriage_chart)
    BarChart mCarriageChart;
    @BindView(R.id.tv_dayCarriage)
    TextView tvDayCarriage;
    @BindView(R.id.tv_dayaddfee)
    TextView tvDayaddfee;
    @BindView(R.id.tv_daycod)
    TextView tvDaycod;
    @BindView(R.id.tv_daycollect)
    TextView tvDaycollect;
    @BindView(R.id.tv_dayorder)
    TextView tvDayorder;
    @BindView(R.id.tv_dayCollectOrder)
    TextView tvDayCollectOrder;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(AchievementViewModel.class, AchievementViewModel.class.getName(), false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievement, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setTitle("我的业绩");

//        mViewModel.getData();
//        mViewModel.dataLiveData.observe(this, o -> {
//            if(o!=null && o.getDayDto()!=null){
//
//                AchievementDayEntity dayDto = o.getDayDto();
//
//                tvDayCarriage.setText(dayDto.getDayCarriage()+"元");
//                tvDayaddfee.setText(dayDto.getDayAddedFee()+"元");
//                tvDaycod.setText(dayDto.getDayCod()+"元");
//                tvDaycollect.setText(dayDto.getDayCollect()+"元");
//                tvDayorder.setText(dayDto.getDayOrder()+"单");
//                tvDayCollectOrder.setText(dayDto.getDayCollectOrder()+"单");
//            }
//
//            if(o!=null && o.getList()!=null){
//                ChartManager.initStatisticsChat(mChart,o.getList());
//                ChartManager.initCarriageChat(mCarriageChart,o.getList());
//            }
//        });
        mChart.setNoDataText("暂无数据");
        mChart.setNoDataTextColor(Color.parseColor("#c7c7c7"));
        mCarriageChart.setNoDataText("暂无数据");
        mCarriageChart.setNoDataTextColor(Color.parseColor("#c7c7c7"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
