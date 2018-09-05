package com.zyf.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biz.base.BaseArrayListAdapter;

/**
 * Created by ltxxx on 2018/6/28.
 */

public class FilterAdapter extends BaseArrayListAdapter<String> {

    public FilterAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater(android.R.layout.simple_spinner_dropdown_item, parent);
        TextView text = (TextView) convertView
                .findViewById(android.R.id.text1);
        text.setText(getItem(position));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = inflater(android.R.layout.simple_spinner_dropdown_item, parent);
        TextView text = (TextView) convertView
                .findViewById(android.R.id.text1);
        text.setText(getItem(position));
        return convertView;
    }
}
