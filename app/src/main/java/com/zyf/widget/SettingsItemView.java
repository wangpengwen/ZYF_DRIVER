package com.zyf.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyf.driver.ui.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright © 2017 http://www.biz-united.com.cn All rights reserved.
 * Created by zanezhao on 2017/11/17.
 *
 * @Project: TCJK
 * @Package: com.biz.widget
 * @author: zanezhao
 * @version: V1.0
 */

public class SettingsItemView extends RelativeLayout {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.text)
    TextView text;

    public SettingsItemView(Context context) {
        super(context);
        init(null, context);
    }

    public SettingsItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, context);
    }

    //初始化界面
    private void init(AttributeSet attrs, Context context) {
        final View view = View.inflate(context, R.layout.item_settings_layout, this);
        TypedValue typedValue = new TypedValue();
        view.getContext().getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
        view.setBackgroundResource(typedValue.resourceId);
        ButterKnife.bind(this, view);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.settingsItemView);
            String titleString = a.getString(R.styleable.settingsItemView_titleText);
            String subTitleString = a.getString(R.styleable.settingsItemView_subTitleText);
            String subTitleHintString = a.getString(R.styleable.settingsItemView_subTitleHintText);
            boolean hasNextIcon = a.getBoolean(R.styleable.settingsItemView_hasNextIcon, true);

            title.setText(titleString);
            if (!TextUtils.isEmpty(subTitleString)) {
                text.setText(subTitleString);
            }
            if (!TextUtils.isEmpty(subTitleHintString)) {
                text.setHint(subTitleHintString);
            }
            if (!hasNextIcon) {
                text.setCompoundDrawables(null, null, null, null);
            }
        }
    }

    @Override
    public void setBackgroundColor(int color) {
//        super.setBackgroundColor(color);
        if(text!=null){
            View view= (View) text.getParent();
            view.setBackgroundColor(color);
        }
    }

    public void setSubTitleText(String subTitle) {
        text.setText(subTitle);
    }

    public void setTitleText(String titleTxt) {
        title.setText(titleTxt == null ? "" : titleTxt);
    }

    public void setSubTitleHintText(String subTitle) {
        if (!TextUtils.isEmpty(subTitle)) {
            text.setHint(subTitle);
        }
    }

    public void setSubTitleText(@StringRes int subTitleRes) {
        if (subTitleRes > 0) {
            text.setText(subTitleRes);
        }
    }

    public void setArrow(@Nullable Drawable drawable) {
        if (text != null) {
            text.setCompoundDrawables(null, null, drawable, null);
        }

    }
}
