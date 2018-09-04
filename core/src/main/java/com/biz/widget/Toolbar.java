package com.biz.widget;


import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v7.widget.TintTypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biz.http.R;
import com.biz.util.DrawableHelper;
import com.biz.util.Utils;

/**
 * Created by johnzheng on 7/27/16.
 */

@SuppressWarnings("deprecation")
public class Toolbar extends android.support.v7.widget.Toolbar {
    TextView mTitleText;

    public Toolbar(Context context) {
        super(context);
    }

    public Toolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                android.support.v7.appcompat.R.styleable.Toolbar);

        int mTitleTextAppearance = a.getResourceId(android.support.v7.appcompat.R.styleable.Toolbar_titleTextAppearance, 0);
        if (mTitleTextAppearance != 0)
            setTitleTextAppearance(context, mTitleTextAppearance);
        final CharSequence title = a.getText(android.support.v7.appcompat.R.styleable.Toolbar_title);
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
        if (a.hasValue(android.support.v7.appcompat.R.styleable.Toolbar_titleTextColor)) {
            setTitleTextColor(a.getColor(android.support.v7.appcompat.R.styleable.Toolbar_titleTextColor, 0xffffffff));
        }
    }

    public Toolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                android.support.v7.appcompat.R.styleable.Toolbar, defStyleAttr, 0);

        int mTitleTextAppearance = a.getResourceId(android.support.v7.appcompat.R.styleable.Toolbar_titleTextAppearance, 0);
        if (mTitleTextAppearance != 0)
            setTitleTextAppearance(context, mTitleTextAppearance);
        final CharSequence title = a.getText(android.support.v7.appcompat.R.styleable.Toolbar_title);
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
        if (a.hasValue(android.support.v7.appcompat.R.styleable.Toolbar_titleTextColor)) {
            setTitleTextColor(a.getColor(android.support.v7.appcompat.R.styleable.Toolbar_titleTextColor, 0xffffffff));
        }

    }


    public void setTextDrawableLeft(@DrawableRes int resId) {
        if (mTitleText != null && resId > 0) {
            mTitleText.setCompoundDrawablePadding(Utils.dip2px(10));
            mTitleText.setCompoundDrawables(
                    DrawableHelper.getDrawableWithBounds(getContext(), resId),
                    null, null, null
            );
        }
    }

    @Override
    public void setTitleTextAppearance(Context context, @StyleRes int resId) {
        if (mTitleText != null) {
            mTitleText.setTextAppearance(context, resId);
        } else
            super.setTitleTextAppearance(context, resId);
    }

    @Override
    public void setTitleTextColor(@ColorInt int color) {

        if (mTitleText != null) {
            mTitleText.setTextColor(color);
        } else super.setTitleTextColor(color);
    }

    @Override
    public void setTitle(@StringRes int resId) {
        if (mTitleText != null)
            mTitleText.setText(resId);
        else
            super.setTitle(resId);
    }

    @Override
    public void setTitle(CharSequence title) {
        if (mTitleText != null)
            mTitleText.setText(title);
        else
            super.setTitle(title);
    }


    private void init() {
        mTitleText = new TextView(getContext());
        LayoutParams lp = new LayoutParams(Utils.dip2px(getContext(), ViewGroup.LayoutParams.WRAP_CONTENT),
                ViewGroup.LayoutParams.WRAP_CONTENT);
//        lp.gravity = Gravity.CENTER;
//        mTitleText.setGravity(Gravity.CENTER);
        mTitleText.setLayoutParams(lp);
        mTitleText.setSingleLine(true);
        mTitleText.setMaxLines(1);
        mTitleText.setTextColor(getResources().getColor(R.color.color_333333));
        mTitleText.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        mTitleText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        mTitleText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        addView(mTitleText);
    }
}
