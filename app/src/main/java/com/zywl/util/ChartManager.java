package com.zywl.util;

import android.graphics.Color;

import com.biz.util.Lists;
import com.biz.util.PriceUtil;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.zywl.model.entity.achievement.AchievementDataEntity;
import com.zywl.model.entity.data.AreaStatisticsEntity;
import com.zywl.model.entity.data.PersonStatisticsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TCJK on 2018/4/18.
 */

public class ChartManager {

    public static void initStatisticsChat(BarChart mChart, List<AchievementDataEntity> list){

        if(Lists.getLength(list) == 0) return;

        float groupSpace = 0.16f;
        float barSpace = 0.03f; // x2 DataSet
        float barWidth = 0.25f; // x2 DataSet
        // (0.4 + 0.03) * 2 + 0.14 = 1.00 -> interval per "group"

//        mChart.setOnChartValueSelectedListener(this);
        mChart.getDescription().setEnabled(false);

//        mChart.setDrawBorders(true);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setBackgroundColor(Color.WHITE);
//        barChart.setExtraOffsets(0f, 20f, 0f, 20f);
        mChart.setNoDataText("暂无数据");
        mChart.setNoDataTextColor(Color.parseColor("#c7c7c7"));
        mChart.setScaleEnabled(false);//不可以缩放
        mChart.setDrawValueAboveBar(true);//将Y数据显示在点的上方
        mChart.setDrawBarShadow(false); //设置阴影
        mChart.setDrawGridBackground(false);//设置背景是否网格显示
        mChart.setHighlightFullBarEnabled(false);  //设置高亮显示
        mChart.setTouchEnabled(false);  //设置支持触控

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
//        l.setTypeface(mTfLight);
        l.setYOffset(0f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        XAxis xAxis = mChart.getXAxis();
//        xAxis.setTypeface(mTfLight);
        xAxis.setGranularity(1f); //设置最小的区间，避免标签的迅速增多
        xAxis.setDrawAxisLine(true);   //是否绘制坐标轴的线，默认是true
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);   //坐标轴位置
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                if(value == 1){
                    return "星期一";
                } else if(value == 2){
                    return "星期二";
                } else if(value == 3){
                    return "星期三";
                } else if(value == 4){
                    return "星期四";
                } else if(value == 5){
                    return "星期五";
                } else if(value == 6){
                    return "星期六";
                } else if(value == 7){
                    return "星期日";
                }
                return String.valueOf((int) value);
            }
        });

        YAxis leftAxis = mChart.getAxisLeft();
//        leftAxis.setTypeface(mTfLight);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return PriceUtil.getPrecent(value);
            }
        });

        mChart.getAxisRight().setEnabled(false);

        //数据
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();

        for(int i=0;i<list.size();i++){

            float yChargeValue = 0f;
            if(list.get(i) != null){

                try {
                    yChargeValue = list.get(i).getSum();
                }catch (Exception e){
                    yChargeValue = 0f;
                }
            }

            yVals1.add(new BarEntry(i+1,yChargeValue));

            float yTargetValue = 0f;

            if(list.get(i) != null){

                try {
                    yTargetValue = list.get(i).getAuth();
                }catch (Exception e){
                    yTargetValue = 0f;
                }
            }

            yVals2.add(new BarEntry(i+1,yTargetValue));

            if(list.get(i) != null){

                try {
                    yTargetValue = list.get(i).getNotAuth();
                }catch (Exception e){
                    yTargetValue = 0f;
                }
            }

            yVals3.add(new BarEntry(i+1,yTargetValue));
        }

        BarDataSet set1, set2, set3;

        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {

            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) mChart.getData().getDataSetByIndex(1);
            set3 = (BarDataSet) mChart.getData().getDataSetByIndex(2);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            set3.setValues(yVals3);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();

        } else {
            // create 2 DataSets
            set1 = new BarDataSet(yVals1, "订单总数");
            set1.setColor(Color.rgb(104, 241, 175));
            set2 = new BarDataSet(yVals2, "实名订单");
            set2.setColor(Color.rgb(164, 228, 251));
            set3 = new BarDataSet(yVals3, "未实名订单");
            set3.setColor(Color.rgb(254, 246, 140));

            BarData data = new BarData(set1, set2, set3);
//            data.setValueFormatter(new LargeValueFormatter());
//            data.setValueTypeface(mTfLight);

            mChart.setData(data);
        }

// specify the width each bar should have
        mChart.getBarData().setBarWidth(barWidth);

        // restrict the x-axis range
        mChart.getXAxis().setAxisMinimum(1);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        mChart.getXAxis().setAxisMaximum(1+mChart.getBarData().getGroupWidth(groupSpace, barSpace) * 7);
        mChart.groupBars(1, groupSpace, barSpace);
        mChart.invalidate();
        mChart.animateY(2000);
    }


    public static void initCarriageChat(BarChart barChart, List<AchievementDataEntity> list){

        if(Lists.getLength(list) == 0) return;

        barChart.setBackgroundColor(Color.WHITE);
        barChart.setExtraOffsets(0f, 20f, 0f, 20f);
        barChart.getDescription().setEnabled(false);
        barChart.setNoDataText("暂无数据");
        barChart.setNoDataTextColor(Color.parseColor("#c7c7c7"));
        barChart.setScaleEnabled(false);//不可以缩放
        barChart.setDrawValueAboveBar(true);//将Y数据显示在点的上方
        barChart.setDrawBarShadow(false); //设置阴影
        barChart.setDrawGridBackground(false);//设置背景是否网格显示
        barChart.setHighlightFullBarEnabled(false);  //设置高亮显示
        barChart.setTouchEnabled(false);  //设置支持触控

        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
//        l.setTypeface(mTfLight);
        l.setYOffset(0f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        XAxis xAxis = barChart.getXAxis();
//        xAxis.setTypeface(mTfLight);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);   //坐标轴位置
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);   //是否绘制坐标轴的线，默认是true
        xAxis.setLabelCount(7); //设置进入时的标签数量
        xAxis.setGranularity(1f); //设置最小的区间，避免标签的迅速增多
//        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                if(value == 1){
                    return "星期一";
                } else if(value == 2){
                    return "星期二";
                } else if(value == 3){
                    return "星期三";
                } else if(value == 4){
                    return "星期四";
                } else if(value == 5){
                    return "星期五";
                } else if(value == 6){
                    return "星期六";
                } else if(value == 7){
                    return "星期日";
                }
                return String.valueOf((int) value);
            }
        });

        YAxis leftAxis = barChart.getAxisLeft();
//        leftAxis.setTypeface(mTfLight);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return PriceUtil.getPrecent(value);
            }
        });

        barChart.getAxisRight().setEnabled(false);

        //数据
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for(int i=0;i<list.size();i++){

            float yChargeValue = 0f;
            if(list.get(i) != null){

                try {
                    yChargeValue = list.get(i).getTimeCarriage();
                }catch (Exception e){
                    yChargeValue = 0f;
                }
            }

            yVals1.add(new BarEntry(i + 1, yChargeValue));
        }

        BarDataSet barDataSet = new BarDataSet(yVals1, "总费用");
        barDataSet.setColor(Color.rgb(104, 241, 175));

        BarData barData = new BarData(barDataSet);
        barData.setValueTextSize(11f);
        barData.setBarWidth(0.6f);

        barChart.setData(barData);
        barChart.animateY(2000);
        barChart.invalidate();


//
//
//
//
//
//
//
//
//
//        float groupSpace = 0.75f;
//        float barSpace = 0f; // x2 DataSet
//        float barWidth = 0.25f; // x2 DataSet
//        // (0.4 + 0.03) * 2 + 0.14 = 1.00 -> interval per "group"
//
////        mChart.setOnChartValueSelectedListener(this);
//        mChart.getDescription().setEnabled(false);
//
////        mChart.setDrawBorders(true);
//
//        // scaling can now only be done on x- and y-axis separately
//        mChart.setPinchZoom(false);
//
//        mChart.setBackgroundColor(Color.WHITE);
////        barChart.setExtraOffsets(0f, 20f, 0f, 20f);
//        mChart.setNoDataText("暂无数据");
//        mChart.setNoDataTextColor(Color.parseColor("#c7c7c7"));
//        mChart.setScaleEnabled(false);//不可以缩放
//        mChart.setDrawValueAboveBar(true);//将Y数据显示在点的上方
//        mChart.setDrawBarShadow(false); //设置阴影
//        mChart.setDrawGridBackground(false);//设置背景是否网格显示
//        mChart.setHighlightFullBarEnabled(false);  //设置高亮显示
//        mChart.setTouchEnabled(false);  //设置支持触控
//
//        Legend l = mChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(true);
////        l.setTypeface(mTfLight);
//        l.setYOffset(0f);
//        l.setXOffset(10f);
//        l.setYEntrySpace(0f);
//        l.setTextSize(8f);
//
//        XAxis xAxis = mChart.getXAxis();
////        xAxis.setTypeface(mTfLight);
//        xAxis.setGranularity(1f); //设置最小的区间，避免标签的迅速增多
//        xAxis.setDrawAxisLine(true);   //是否绘制坐标轴的线，默认是true
//        xAxis.setDrawGridLines(false);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);   //坐标轴位置
//        xAxis.setCenterAxisLabels(true);
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//
//                if(value == 1){
//                    return "星期一";
//                } else if(value == 2){
//                    return "星期二";
//                } else if(value == 3){
//                    return "星期三";
//                } else if(value == 4){
//                    return "星期四";
//                } else if(value == 5){
//                    return "星期五";
//                } else if(value == 6){
//                    return "星期六";
//                } else if(value == 7){
//                    return "星期日";
//                }
//                return String.valueOf((int) value);
//            }
//        });
//
//        YAxis leftAxis = mChart.getAxisLeft();
////        leftAxis.setTypeface(mTfLight);
//        leftAxis.setValueFormatter(new LargeValueFormatter());
//        leftAxis.setDrawGridLines(true);
//        leftAxis.setSpaceTop(35f);
//        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        leftAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return PriceUtil.getPrecent(value);
//            }
//        });
//
//        mChart.getAxisRight().setEnabled(false);
//
//        //数据
//        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
//
//        for(int i=0;i<list.size();i++){
//
//            float yChargeValue = 0f;
//            if(list.get(i) != null){
//
//                try {
//                    yChargeValue = list.get(i).getTimeCarriage();
//                }catch (Exception e){
//                    yChargeValue = 0f;
//                }
//            }
//
//            yVals1.add(new BarEntry(i + 1, yChargeValue));
//        }
//
//        BarDataSet set1;
//
//        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
//
//            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
//            set1.setValues(yVals1);
//            mChart.getData().notifyDataChanged();
//            mChart.notifyDataSetChanged();
//
//        } else {
//            // create 1 DataSets
//            set1 = new BarDataSet(yVals1, "总费用");
//            set1.setColor(Color.rgb(104, 241, 175));
//
//            BarData data = new BarData(set1);
////            data.setValueFormatter(new LargeValueFormatter());
////            data.setValueTypeface(mTfLight);
//
//            mChart.setData(data);
//        }
//
//// specify the width each bar should have
//        mChart.getBarData().setBarWidth(barWidth);
//
//        // restrict the x-axis range
//        mChart.getXAxis().setAxisMinimum(1);
//
//        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
//        mChart.getXAxis().setAxisMaximum(1+mChart.getBarData().getGroupWidth(groupSpace, barSpace) * 7);
////        mChart.groupBars(1, groupSpace, barSpace);
//        mChart.invalidate();
//        mChart.animateY(2000);
    }
}
