package com.biz.widget;

import com.biz.http.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Title: CountDownView
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2018/1/15  15:06
 *
 * @author wangwei
 * @version 1.0
 */
public class CountDownView extends LinearLayout {
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    private TimeEntity mTimeEntity;
    private CountDownViewHolder mCountDownViewHolder;
    private boolean mIsShowDay;
    private int textColor = Color.WHITE;
    private int textBgColor = Color.RED;
    private int textEnabledBgColor = Color.RED;
    private int textEnabledColor = Color.WHITE;
    private int textUnitColor = Color.BLACK;
    private int textEnabledUnitColor = Color.BLACK;

    public CountDownView(Context context) {
        super(context);
        init(null);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void setShowDay(boolean showDay) {
        mIsShowDay = showDay;
        if (mCountDownViewHolder != null) {
            mCountDownViewHolder.mTvDay.setVisibility(showDay ? VISIBLE : GONE);
            mCountDownViewHolder.mTvDayText.setVisibility(showDay ? VISIBLE : GONE);
        }
    }

    public void init(AttributeSet attrs) {
        final View view = View.inflate(getContext(), R.layout.item_count_down_layout, this);
        mCountDownViewHolder = new CountDownViewHolder(view);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CountDownStyle);
            setShowDay(a.getBoolean(R.styleable.CountDownStyle_isShowDay, false));
            textBgColor = a.getColor(R.styleable.CountDownStyle_textBgColor, Color.TRANSPARENT);
            textColor = a.getColor(R.styleable.CountDownStyle_textColor, Color.TRANSPARENT);
            textEnabledBgColor = a.getColor(R.styleable.CountDownStyle_textEnabledBgColor, Color.TRANSPARENT);
            textEnabledColor = a.getColor(R.styleable.CountDownStyle_textEnabledColor, Color.TRANSPARENT);
            textEnabledUnitColor = a.getColor(R.styleable.CountDownStyle_textEnabledUnitColor, Color.BLACK);
            textUnitColor = a.getColor(R.styleable.CountDownStyle_textUnitColor, Color.BLACK);
            mCountDownViewHolder.setBackgroundDrawable(textBgColor);
            mCountDownViewHolder.setTextColor(textColor);
            mCountDownViewHolder.setUnitColor(textUnitColor);
            mCountDownViewHolder.setTextSize(a.getDimensionPixelSize(R.styleable.CountDownStyle_textSize, 12));
        }
    }

    public void start(long startTime, long endTime, RefreshViewListener refreshViewListener) {
        stop();
        mTimeEntity = TimeEntity.build(startTime, endTime, true, refreshViewListener);
        mSubscription.add(Observable.interval(1, 1, TimeUnit.SECONDS,Schedulers.trampoline()).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(i -> {
                    if (mTimeEntity != null) {
                        mTimeEntity.computeTime();
                    }
                    setText(mTimeEntity);
                }, throwable -> {
                }));
        setText(mTimeEntity);
    }

    public void stop() {
        if (mTimeEntity != null) {
            mTimeEntity.refreshViewListener = null;
            mTimeEntity = null;
        }
        mSubscription.clear();
    }

    public void setText(TimeEntity entity) {
        if (null == entity) {
            mCountDownViewHolder.mTvDay.setVisibility(GONE);
            mCountDownViewHolder.mTvDayText.setVisibility(GONE);
            mCountDownViewHolder.mTvHour.setText("00");
            mCountDownViewHolder.mTvMinute.setText("00");
            mCountDownViewHolder.mTvSecond.setText("00");
        } else {
            if (entity.isEnd()) {
                if (entity.refreshViewListener != null) {
                    entity.refreshViewListener.call();
                }
                stop();
            }
            if (TextUtils.isEmpty(entity.getDay()) || "00".equals(entity.getDay())) {
                mCountDownViewHolder.mTvDay.setVisibility(View.GONE);
                mCountDownViewHolder.mTvDayText.setVisibility(GONE);
            } else {
                if (mIsShowDay) {
                    mCountDownViewHolder.mTvDay.setVisibility(View.VISIBLE);
                    mCountDownViewHolder.mTvDayText.setVisibility(VISIBLE);
                    mCountDownViewHolder.mTvDay.setText(entity.getDay());
                } else {
                    mCountDownViewHolder.mTvDay.setVisibility(View.GONE);
                    mCountDownViewHolder.mTvDayText.setVisibility(GONE);
                }
            }
            mCountDownViewHolder.mTvHour.setText(entity.getHour());
            mCountDownViewHolder.mTvMinute.setText(entity.getMin());
            mCountDownViewHolder.mTvSecond.setText(entity.getSecond());
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (mCountDownViewHolder != null) {
            mCountDownViewHolder.setTextColor(enabled ? textColor : textEnabledColor);
            mCountDownViewHolder.setBackgroundDrawable(enabled ? textBgColor : textEnabledBgColor);
            mCountDownViewHolder.setUnitColor(enabled ? textUnitColor : textEnabledUnitColor);
        }
    }

    private static class CountDownViewHolder {
        public TextView mTvDay;
        public TextView mTvHour;
        public TextView mTvMinute;
        public TextView mTvSecond;
        public TextView mTvDayText;
        public TextView mTvUnit1;
        public TextView mTvUnit2;

        public CountDownViewHolder(View itemView) {
            mTvDay = itemView.findViewById(R.id.tv_day);
            mTvHour = itemView.findViewById(R.id.tv_hour);
            mTvMinute = itemView.findViewById(R.id.tv_minute);
            mTvSecond = itemView.findViewById(R.id.tv_second);
            mTvDayText = itemView.findViewById(R.id.tv_day_text);
            mTvUnit1 = itemView.findViewById(R.id.tv_unit1);
            mTvUnit2 = itemView.findViewById(R.id.tv_unit2);
        }

        public void setBackgroundDrawable(@ColorInt Integer drawable) {
            if (drawable == Color.TRANSPARENT) return;
            mTvDay.setBackgroundDrawable(createShapeWithStrokeDrawable(mTvDay.getContext(), drawable, drawable, 2));
            mTvHour.setBackgroundDrawable(createShapeWithStrokeDrawable(mTvDay.getContext(), drawable, drawable, 2));
            mTvMinute.setBackgroundDrawable(createShapeWithStrokeDrawable(mTvDay.getContext(), drawable, drawable, 2));
            mTvSecond.setBackgroundDrawable(createShapeWithStrokeDrawable(mTvDay.getContext(), drawable, drawable, 2));
        }

        public void setUnitColor(@ColorInt Integer color) {
            mTvDayText.setTextColor(color);
            mTvUnit1.setTextColor(color);
            mTvUnit2.setTextColor(color);
        }

        public static GradientDrawable createShapeWithStrokeDrawable(Context context, int color, int strokeColor, int corner) {
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(color);
            gd.setStroke(2, strokeColor);
            gd.setCornerRadius(dip2px(context, corner));
            return gd;
        }

        public static int dip2px(Context context, float dipValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dipValue * scale + 0.5f);
        }

        public void setTextColor(@ColorInt Integer color) {
            mTvDay.setTextColor(color);
            mTvHour.setTextColor(color);
            mTvMinute.setTextColor(color);
            mTvSecond.setTextColor(color);
        }

        public void setTextSize(int size) {
            mTvDay.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
            mTvHour.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
            mTvMinute.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
            mTvSecond.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
            mTvDayText.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        }
    }

    public static class TimeEntity {
        public RefreshViewListener refreshViewListener;
        public long day, hour, min, second;//天，小时，分钟，秒
        public boolean isBegin;

        public static TimeEntity build(long startTime, long endTime, boolean isBegin, RefreshViewListener refreshViewListener) {
            TimeEntity entity = new TimeEntity();
            entity.initTime(startTime, endTime);
            entity.isBegin = isBegin;
            entity.refreshViewListener = refreshViewListener;
            return entity;
        }

        public String getDay() {
            if (day < 10) {
                return "0" + day;
            } else {
                return day + "";
            }
//            return String.valueOf(day);
        }

        public String getHour() {
            if (hour < 10) {
                return "0" + hour;
            } else {
                return hour + "";
            }
        }

        public String getMin() {
            if (min < 10) {
                return "0" + min;
            } else {
                return min + "";
            }
        }

        public String getSecond() {
            if (second < 10) {
                return "0" + second;
            } else {
                return second + "";
            }
        }


        public boolean isEnd() {
            if (day < 0L) {
                return true;
            }
            return day <= 0L && hour <= 0L && min <= 0L && second <= 0L;
        }


        public void initTime(long startTime, long endTime) {
            //按照传入的格式生成一个simpledateformate对象
            long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
            long nh = 1000 * 60 * 60;//一小时的毫秒数
            long nm = 1000 * 60;//一分钟的毫秒数
            long ns = 1000;//一秒钟的毫秒数long diff;try {
            //获得两个时间的毫秒时间差异
            long diff = endTime - startTime;
            day = diff / nd;//计算差多少天
            if (day < 0) {
                day = 0;
                diff = diff + nd;
            }
            hour = diff % nd / nh;//计算差多少小时
            if (hour < 0) {
                hour = 0;
                diff = diff + nh;
            }
            min = diff % nd % nh / nm;//计算差多少分钟
            if (min < 0) {
                min = 0;
                diff = diff + nm;
            }
            second = diff % nd % nh % nm / ns;//计算差多少秒//输出结果
            if (second < 0) {
                second = 0;
            }
        }

        /**
         * 倒计时计算
         */
        private void computeTime() {
            second--;
            if (second < 0) {
                min--;
                second = 59;
                if (min < 0) {
                    min = 59;
                    hour--;
                    if (hour < 0) {
                        // 倒计时结束
                        hour = 59;
                        day--;
                    }
                }
            }
        }
    }

    public interface RefreshViewListener {
        void call();
    }
}
