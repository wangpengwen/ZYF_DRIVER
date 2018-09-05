package com.zyf.ui.goods;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.biz.base.BaseLiveDataFragment;
import com.biz.util.IntentBuilder;
import com.biz.widget.recyclerview.SuperRefreshManager;
import com.zyf.event.GoodsListEvent;
import com.zyf.model.entity.goods.GoodsEntity;
import com.zyf.driver.ui.R;
import com.zyf.ui.order.OrderDetailFragment;
import com.zyf.ui.order.OrderListFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * Created by TCJK on 2018/5/21.
 */

public class GoodsListFragment extends BaseLiveDataFragment<GoodsListViewModel> {

    Unbinder unbinder;

    SuperRefreshManager mSuperRefreshManager;
    GoodsAdapter mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(GoodsListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        EventBus.getDefault().register(this);

        mSuperRefreshManager = new SuperRefreshManager(view);
        mSuperRefreshManager.setLoadDefault(false);
        mSuperRefreshManager.init(view);
        mSuperRefreshManager.addDefaultItemDecoration(false);
        mSuperRefreshManager.setEnableRefresh(true);
        mSuperRefreshManager.setEnableLoadMore(false);
        mSuperRefreshManager.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter = new GoodsAdapter();
        mSuperRefreshManager.setAdapter(mAdapter);

        mSuperRefreshManager.setOnRefreshListener(refreshlayout -> {
            mViewModel.goodsList();
        });

        mAdapter.setOnItemClickListener((adapter, view1, position) -> {

            GoodsEntity item = mAdapter.getItem(position);
            IntentBuilder.Builder()
                    .putExtra(IntentBuilder.KEY_ID,item.getManifestNum())
                    .startParentActivity(getBaseActivity(), OrderListFragment.class,true);
        });

        mViewModel.goodsList();
        mViewModel.getGoodsLiveData().observe(this, list -> {
            mSuperRefreshManager.finishRefresh();
            mAdapter.setNewData(list);
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("货单列表");

        mToolbar.getMenu().add(0, 0, 0, "新建货单").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == 0){
                    //新建货单
                    IntentBuilder.Builder().startParentActivity(getBaseActivity(), GoodsAddFragment.class,true);
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(GoodsListEvent event){
        if (mSuperRefreshManager != null){
            mSuperRefreshManager.autoRefresh();
        }
    }

}
