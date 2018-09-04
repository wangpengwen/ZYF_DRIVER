package com.zywl.ui.order;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biz.base.BaseLiveDataFragment;
import com.biz.util.IntentBuilder;
import com.biz.util.ToastUtils;
import com.biz.widget.recyclerview.SuperRefreshManager;
import com.zywl.model.entity.order.OrderEntity;
import com.zywl.ui.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by TCJK on 2018/5/21.
 */

public class OrderListFragment extends BaseLiveDataFragment<OrderListViewModel> {

    Unbinder unbinder;

    SuperRefreshManager mSuperRefreshManager;
    OrderAdapter mAdapter;

    String goodsNum;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(OrderListViewModel.class);

        goodsNum = getActivity().getIntent().getStringExtra(IntentBuilder.KEY_ID);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        mSuperRefreshManager = new SuperRefreshManager(view);
        mSuperRefreshManager.setLoadDefault(false);
        mSuperRefreshManager.init(view);
        mSuperRefreshManager.addDefaultItemDecoration(false);
        mSuperRefreshManager.setEnableRefresh(false);
        mSuperRefreshManager.setEnableLoadMore(false);
        mSuperRefreshManager.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter = new OrderAdapter();
        mSuperRefreshManager.setAdapter(mAdapter);

        mSuperRefreshManager.setOnRefreshListener(refreshlayout -> {

        });

        mAdapter.setOnItemClickListener((adapter, view1, position) -> {

            OrderEntity item = mAdapter.getItem(position);
            IntentBuilder.Builder()
                    .putExtra(IntentBuilder.KEY_ORDER_ID,item.getOrderNum())
                    .putExtra(IntentBuilder.KEY_TYPE,OrderDetailFragment.TYPE_REVIEW)
                    .startParentActivity(getBaseActivity(), OrderDetailFragment.class,true);
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("订单列表");

        mViewModel.orderListByGoodsNum(goodsNum);
        mViewModel.getOrderListLiveData().observe(this, list -> {
            mAdapter.setNewData(list);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
