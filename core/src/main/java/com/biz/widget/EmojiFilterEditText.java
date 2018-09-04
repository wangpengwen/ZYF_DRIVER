package com.biz.widget;


import com.biz.util.EmojiInputFilter;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.util.AttributeSet;

/**
 * 过滤emoji表情
 *
 * CreateTime: 17/11/2 17:03
 * @author zhengjiong
 */
public class EmojiFilterEditText extends AppCompatEditText {

    public EmojiFilterEditText(Context context) {
        super(context);
    }

    public EmojiFilterEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmojiFilterEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setFilters(new InputFilter[]{new EmojiInputFilter()});
    }
}
