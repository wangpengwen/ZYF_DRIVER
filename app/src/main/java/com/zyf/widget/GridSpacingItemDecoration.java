package com.zyf.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liutong on 2017/3/22.
 */

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int hSpacing;
    private int vSpacing;

    public GridSpacingItemDecoration(int spanCount, int hSpacing, int vSpacing) {
        this.spanCount = spanCount;
        this.hSpacing = hSpacing;
        this.vSpacing = vSpacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        outRect.left = column * hSpacing / spanCount; // column * ((1f / spanCount) * spacing)
        outRect.right = hSpacing - (column + 1) * hSpacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
        if (position >= spanCount) {
            outRect.top = vSpacing; // item top
        }
    }
}
