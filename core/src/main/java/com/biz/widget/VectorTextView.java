package com.biz.widget;

import com.biz.http.R;
import com.biz.util.DrawableHelper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Title: VectorTextView
 * Description:
 * Copyright:Copyright(c)2016
 * Company:博智维讯信息技术有限公司
 * CreateTime:22/12/2017  16:26
 *
 * @author johnzheng
 * @version 1.0
 */

public class VectorTextView extends AppCompatTextView {
    public VectorTextView(Context context) {
        this(context, null);
    }

    public VectorTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VectorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.VectorTextView);
        int left = typedArray.getResourceId(R.styleable.VectorTextView_drawableLeft, 0);
        int right = typedArray.getResourceId(R.styleable.VectorTextView_drawableRight, 0);
        int top = typedArray.getResourceId(R.styleable.VectorTextView_drawableTop, 0);
        int bottom = typedArray.getResourceId(R.styleable.VectorTextView_drawableBottom, 0);
        setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        }
    }

    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundDrawable(DrawableHelper.getDrawable(getContext(), resId));
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(int left, int top, int right, int bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left != 0 ? DrawableHelper.getDrawableWithBounds(getContext(), left) : null,
                top != 0 ? DrawableHelper.getDrawable(getContext(), top) : null,
                right != 0 ? DrawableHelper.getDrawable(getContext(), right) : null,
                bottom != 0 ? DrawableHelper.getDrawable(getContext(), bottom) : null);
    }

    @Override
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int start, int top, int end, int bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(start != 0 ? DrawableHelper.getDrawableWithBounds(getContext(), start) : null,
                top != 0 ? DrawableHelper.getDrawable(getContext(), top) : null,
                end != 0 ? DrawableHelper.getDrawable(getContext(), end) : null,
                bottom != 0 ? DrawableHelper.getDrawable(getContext(), bottom) : null);
    }

}
