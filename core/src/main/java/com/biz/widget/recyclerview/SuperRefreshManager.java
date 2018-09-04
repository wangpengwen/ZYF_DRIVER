package com.biz.widget.recyclerview;

import com.biz.http.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Title: SuperRefreshManager
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/11/7  16:31
 *
 * @author wangwei
 * @version 1.0
 */
public class SuperRefreshManager {
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private View mEmptyView;
    private Button mBtnEmpty;

    public SuperRefreshManager(){

    }
    public SuperRefreshManager(View view){
        init(view);
    }


    public void init(Activity view) {
        this.mRefreshLayout = view.findViewById(R.id.refreshLayout);
        initView();
    }

    public void init(View view) {
        this.mRefreshLayout = view.findViewById(R.id.refreshLayout);
        initView();
    }

    private void initView() {
        if (this.mRefreshLayout != null) {
            mRefreshLayout.setEnableLoadmoreWhenContentNotFull(true);
            mRecyclerView = this.mRefreshLayout.findViewById(android.R.id.list);
            mEmptyView = this.mRefreshLayout.findViewById(R.id.view_empty);
            setEmptyVisibility(View.GONE);

            setEnableRefresh(false);
            setEnableLoadMore(false);
            //重新加载
            if(mEmptyView!=null){
                mBtnEmpty=mEmptyView.findViewById(R.id.btn_empty);
                mBtnEmpty.setVisibility(View.VISIBLE);
                mBtnEmpty.setText("重新加载");
                mBtnEmpty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setEmptyVisibility(View.GONE);
                        mRefreshLayout.autoRefresh();
                    }
                });
            }
        }
    }

    public void setLoadDefault(boolean loadDefault) {
    }

    public void setEnableEmptyButton(boolean enableEmptyButton) {
        if(mBtnEmpty==null)return;
        if(enableEmptyButton){
            mBtnEmpty.setVisibility(View.VISIBLE);
        }else {
            mBtnEmpty.setVisibility(View.GONE);
        }
    }
    public void autoRefresh(){
        setEmptyVisibility(View.GONE);
        mRefreshLayout.autoRefresh(0);
    }

    public void setDefaultHead() {
        if (mRefreshLayout != null)
            mRefreshLayout.setRefreshHeader(new ClassicsHeader(mRefreshLayout.getContext()));
    }

    public void setDefaultFooter() {
        if (mRefreshLayout != null)
            mRefreshLayout.setRefreshFooter(new ClassicsFooter(mRefreshLayout.getContext()));
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        if (mRefreshLayout != null && onRefreshListener != null)
            mRefreshLayout.setOnRefreshListener(onRefreshListener);
    }

    public void setOnLoadMoreListener(OnLoadmoreListener onLoadMoreListener) {
        if (mRefreshLayout != null && onLoadMoreListener != null) {
            mRefreshLayout.setOnLoadmoreListener(onLoadMoreListener);
        }
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        if (mRecyclerView != null && itemDecoration != null) {
            mRecyclerView.addItemDecoration(itemDecoration);
        }
    }

    public void addDefaultItemDecoration() {
        if (getRecyclerView().getContext() != null)
            addItemDecoration(new HorizontalDividerItemDecoration.Builder(getRecyclerView().getContext())
                    .colorResId(R.color.color_divider).size(1)
                    .showLastDivider().build());
    }
    public void addDefaultItemDecoration(boolean isShowLast) {
        if (getRecyclerView().getContext() != null)
            if(!isShowLast)
            addItemDecoration(new HorizontalDividerItemDecoration.Builder(getRecyclerView().getContext())
                    .colorResId(R.color.color_divider).size(1)
                    .build());
        else addDefaultItemDecoration();
    }

    public void setLayoutManager(RecyclerView.LayoutManager layout) {
        if (mRecyclerView != null) {
            mRecyclerView.setLayoutManager(layout);
        }
    }


    public void setAdapter(RecyclerView.Adapter adapter) {
        if (mRecyclerView != null && adapter != null) {
            mRecyclerView.setAdapter(adapter);
        }
    }


    public void setEnableLoadMore(boolean isEnable) {
        if (mRefreshLayout != null) {
            mRefreshLayout.setEnableLoadmore(isEnable);
        }
    }

    public void setEnableRefresh(boolean isEnable) {
        if (mRefreshLayout != null) {
            mRefreshLayout.setEnableRefresh(isEnable);
        }
    }

    public void finishRefresh() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishRefresh(0);
        }
    }
    public void finishLoadmore() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishLoadmore(0);
        }
    }
    public void setEmptyVisibility(int visibility) {
        if (mEmptyView != null) {
            mEmptyView.setVisibility(visibility);
            if (mRecyclerView != null && visibility == View.GONE) {
                mRecyclerView.setVisibility(View.VISIBLE);
            } else if (mRecyclerView != null && visibility == View.VISIBLE) {
                mRecyclerView.setVisibility(View.GONE);
            }
        }
    }

    public void setEmptyVisibility(boolean visibility) {
        if (mEmptyView != null) {
            mEmptyView.setVisibility(visibility ? View.VISIBLE : View.GONE);
            if (mRecyclerView != null && !visibility) {
                mRecyclerView.setVisibility(View.VISIBLE);
            } else if (mRecyclerView != null && visibility) {
                mRecyclerView.setVisibility(View.GONE);
            }
        }
    }

    public void setEmptyString(String emptyString) {
        if (mEmptyView != null) {
            TextView textView = mEmptyView.findViewById(R.id.title);
            if (textView != null) {
                textView.setText(emptyString);
            }
        }
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public View getEmptyView() {
        return mEmptyView;
    }

    public SmartRefreshLayout getRefreshLayout() {
        return mRefreshLayout;
    }
}
