package com.biz.util;

import android.text.InputFilter;
import android.text.Spanned;

import com.vdurmont.emoji.EmojiManager;

/**
 * CreateTime: 17/11/2 16:58
 *
 * @author zhengjiong
 */
public class EmojiInputFilter implements InputFilter {

    @Override
    public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
        if (EmojiManager.isEmoji(charSequence.toString())) {
            return "";
        }
        return charSequence;
    }
}
