package com.zywl.ui.payment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.biz.base.BaseLiveDataFragment;
import com.biz.util.IntentBuilder;
import com.biz.util.RxUtil;
import com.biz.widget.recyclerview.SuperRefreshManager;
import com.zywl.model.entity.order.OrderEntity;
import com.zywl.ui.R;
import com.zywl.ui.goods.GoodsAdapter;
import com.zywl.ui.goods.GoodsListViewModel;
import com.zywl.ui.order.OrderAdapter;
import com.zywl.ui.order.OrderDetailFragment;
import com.zywl.ui.order.OrderListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * Created by TCJK on 2018/5/21.
 */

public class EPaymentFragment extends BaseLiveDataFragment<GoodsListViewModel> {

    @BindView(R.id.edit_search)
    EditText searchET;
    @BindView(R.id.button)
    Button button;

    SuperRefreshManager mSuperRefreshManager;

    OrderAdapter mAdapter;

    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(GoodsListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_e_pay, container, false);
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

        mAdapter.setOnItemClickListener((adapter, view1, position) -> IntentBuilder.Builder().startParentActivity(getBaseActivity(), OrderDetailFragment.class,true));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(R.string.text_e_payment);

        RxUtil.click(button).subscribe(o -> {
            mAdapter.setHeaderView(getLayoutInflater().inflate(R.layout.order_list_header,null));

//            List<OrderEntity> list = new ArrayList<>();
//            OrderEntity entity = new OrderEntity();
//            entity.setId("313146");
//            entity.setGoodsNum("313146-2");
//            entity.setStart("济南");
//            entity.setEnd("泰安");
//            entity.setGoodsName("钢材");
//            entity.setNum("2*50kg");
//            entity.setSender("王伟");
//            entity.setSenderContact("13583654155");
//            entity.setReceipients("龙泰");
//            entity.setReceipientsContact("13583654155");
//
//            list.add(entity);
//            mAdapter.setNewData(list);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
