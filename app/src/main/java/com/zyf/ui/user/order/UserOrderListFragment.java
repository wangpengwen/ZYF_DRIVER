package com.zyf.ui.user.order;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biz.base.BaseLazyFragment;
import com.biz.util.DialogUtil;
import com.biz.util.IntentBuilder;
import com.biz.util.Lists;
import com.biz.widget.recyclerview.SuperRefreshManager;
import com.zyf.event.UserOrderEvent;
import com.zyf.model.entity.order.UserOrderItemEntity;
import com.zyf.driver.ui.R;
import com.zyf.ui.order.OrderDetailFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * Created by TCJK on 2018/5/30.
 */

public class UserOrderListFragment extends BaseLazyFragment<UserOrderViewModel> {

    public static final String TYPE_FINISHED = "TYPE_FINISHED";
    public static final String TYPE_UNFINISHED = "TYPE_UNFINISHED";
    public static final String TYPE_COD = "TYPE_COD";

    private SuperRefreshManager mSuperRefreshManager;
    Unbinder unbinder;

    UserOrderAdapter mAdapter;
    String type = "";

    public static UserOrderListFragment newInstance(String type) {
        Bundle args = new Bundle();
        UserOrderListFragment fragment = new UserOrderListFragment();
        args.putString(IntentBuilder.KEY_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(UserOrderViewModel.class,this.toString() + "" + UserOrderViewModel.class.getCanonicalName(),false);

        EventBus.getDefault().register(this);

        if (getArguments() != null) {
            type = getArguments().getString(IntentBuilder.KEY_TYPE);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        mSuperRefreshManager = new SuperRefreshManager(view);
        mSuperRefreshManager.setLoadDefault(false);
        mSuperRefreshManager.init(view);
//        mSuperRefreshManager.addDefaultItemDecoration(false);
        mSuperRefreshManager.setEnableRefresh(true);
        mSuperRefreshManager.setEnableLoadMore(false);
        mSuperRefreshManager.getRecyclerView().setBackgroundResource(R.color.color_background);

        mAdapter = new UserOrderAdapter(type);
        mSuperRefreshManager.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view1, position) -> {

            UserOrderItemEntity item = (UserOrderItemEntity) adapter.getItem(position);
            IntentBuilder.Builder()
                    .putExtra(IntentBuilder.KEY_ORDER_ID,item.getOrderNum())
                    .putExtra(IntentBuilder.KEY_TYPE,OrderDetailFragment.TYPE_REVIEW)
                    .startParentActivity(getBaseActivity(), OrderDetailFragment.class,true);
        });

        mAdapter.setEmptyView(View.inflate(getBaseActivity(), R.layout.item_empty_layout, null));

        mViewModel.getUserOrderListLiveData().observe(this, o -> {

            mSuperRefreshManager.finishRefresh();

            if(o!=null){

                if(TYPE_FINISHED.equals(type)
                        && o.getAlreadyPay() != null){
                    mAdapter.setNewData(o.getAlreadyPay());
                }else if(TYPE_UNFINISHED.equals(type)
                        && o.getNoPay() != null){
                    mAdapter.setNewData(o.getNoPay());
                    mAdapter.setOnItemLongClickListener((adapter, view12, position) -> {
                        DialogUtil.createDialogView(getActivity(), "是否删除该订单？", (dialog, which) -> {
                            dialog.dismiss();
                        }, R.string.text_cancel, (dialog, which) -> {
                            // 删除
                            mViewModel.delOrder(mAdapter.getItem(position).getOrderNum());
                        }, R.string.text_confirm_del);
                        return false;
                    });
                }else if(TYPE_COD.equals(type)){
                    mAdapter.setNewData(o.getCod());
                }
            }else {
                mAdapter.setNewData(Lists.newArrayList());
            }
        });

        mSuperRefreshManager.setOnRefreshListener(refreshlayout -> {
            mSuperRefreshManager.finishRefresh();
            mViewModel.getUserOrder();
        });

        mViewModel.getDelOrderLiveData().observe(this, aBoolean -> {

            if(aBoolean)
                mViewModel.getUserOrder();
        });

    }

    @Override
    public void lazyLoad() {
//        mViewModel.getUserOrder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

//    public void onEventMainThread(HistoryListEvent event){
//        carNo = event.getCarNo();
//        if(isVisible)
//            mViewModel.getHistory(carNo);
//    }

    public void onEventMainThread(UserOrderEvent event){
        if (mSuperRefreshManager != null){
            mSuperRefreshManager.autoRefresh();
        }
    }

    @Override
    public void error(String error) {
        super.error(error);
        if (mSuperRefreshManager != null) {
            mSuperRefreshManager.finishRefresh();
        }
    }
}
