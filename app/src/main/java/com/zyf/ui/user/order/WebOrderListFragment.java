package com.zyf.ui.user.order;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biz.base.BaseLazyFragment;
import com.biz.util.IntentBuilder;
import com.biz.util.Lists;
import com.biz.widget.recyclerview.SuperRefreshManager;
import com.zyf.driver.ui.R;
import com.zyf.event.UserOrderEvent;
import com.zyf.event.WebOrderEvent;
import com.zyf.model.entity.order.WebOrderEntity;
import com.zyf.ui.map.MapRouteActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * Created by TCJK on 2018/5/30.
 */

public class WebOrderListFragment extends BaseLazyFragment<WebOrderViewModel> {

    public static final String TYPE_FIRST = "TYPE_FIRST";
    public static final String TYPE_LAST = "TYPE_LAST";

    private SuperRefreshManager mSuperRefreshManager;
    Unbinder unbinder;

    WebOrderAdapter mAdapter;
    String type = "";

    List<WebOrderEntity> firstList = Lists.newArrayList();
    List<WebOrderEntity> lastList = Lists.newArrayList();

    public static WebOrderListFragment newInstance(String type) {
        Bundle args = new Bundle();
        WebOrderListFragment fragment = new WebOrderListFragment();
        args.putString(IntentBuilder.KEY_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(WebOrderViewModel.class,this.toString() + "" + WebOrderViewModel.class.getCanonicalName(),true);

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

        mAdapter = new WebOrderAdapter(type,mViewModel);
        mSuperRefreshManager.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener((adapter, view1, position) -> {
//
//            UserOrderItemEntity item = (UserOrderItemEntity) adapter.getItem(position);
//            IntentBuilder.Builder()
//                    .putExtra(IntentBuilder.KEY_ORDER_ID,item.getOrderNum())
//                    .putExtra(IntentBuilder.KEY_TYPE,OrderDetailFragment.TYPE_REVIEW)
//                    .startParentActivity(getBaseActivity(), OrderDetailFragment.class,true);
//        });

        mAdapter.setEmptyView(View.inflate(getBaseActivity(), R.layout.item_empty_layout, null));

        mViewModel.getWebOrderLiveData().observe(this, obj -> {

            mSuperRefreshManager.finishRefresh();

            if(obj!=null){

                firstList.clear();
                lastList.clear();

//                for (WebOrderEntity entity:list) {
                    switch (obj.webIsLast){

                        case 0:
                            firstList.add(obj);
                            break;
                        case 1:
                            lastList.add(obj);
                            break;
                    }
//                }

                if(TYPE_FIRST.equals(type)){
                    mAdapter.setNewData(firstList);
                }else if(TYPE_LAST.equals(type)){
                    mAdapter.setNewData(lastList);
                }
            }else {
                mAdapter.setNewData(Lists.newArrayList());
            }
        });

        mViewModel.getTakingWebOrderLiveData().observe(this, entity -> {
            IntentBuilder.Builder(getActivity(), MapRouteActivity.class)
                    .putExtra(IntentBuilder.KEY_DATA,entity)
                    .putExtra(IntentBuilder.KEY_VALUE,1)
                    .overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
                    .startActivity();
        });

        mSuperRefreshManager.setOnRefreshListener(refreshlayout -> {
            mSuperRefreshManager.finishRefresh();
            mViewModel.getWebOrder();
        });
    }

    @Override
    public void lazyLoad() {
        mViewModel.getWebOrder();
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

    public void onEventMainThread(WebOrderEvent event){
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
