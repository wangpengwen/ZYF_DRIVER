package com.zyf.ui.bottomsheet;

import android.content.Context;

import com.biz.util.Lists;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zyf.driver.ui.R;

import java.util.List;

import rx.Observable;

public class BottomSheetMultipleItem implements MultiItemEntity {

    public static final int GALLERY = 12;
    public static final int CAMERA = 11;
    public static final int CANCEL = 13;

    private int itemType;

    public String item;

    public BottomSheetMultipleItem(String item){
        this.item = item;
    }


    public BottomSheetMultipleItem(int itemType, String item){
        this.itemType = itemType;
        this.item = item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public static List<BottomSheetMultipleItem> getList(Context context){
        String[] settings = context.getResources().getStringArray(R.array.array_photo);
        List<BottomSheetMultipleItem> list = Lists.newArrayList();
        Observable.range(0, settings.length)
                .map(i->{return  new BottomSheetMultipleItem(CAMERA+i, settings[i]);})
                .toList().subscribe(list::addAll);
        return list;
    }

}