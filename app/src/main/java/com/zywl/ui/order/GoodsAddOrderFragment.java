package com.zywl.ui.order;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import com.biz.base.BaseLiveDataFragment;
import com.biz.util.IntentBuilder;
import com.biz.util.Lists;
import com.biz.util.LogUtil;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.biz.widget.recyclerview.SuperRefreshManager;
import com.zywl.event.GoodsListEvent;
import com.zywl.model.entity.order.OrderDetailEntity;
import com.zywl.model.entity.order.OrderEntity;
import com.zywl.ui.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * Created by TCJK on 2018/6/9.
 */

public class GoodsAddOrderFragment extends BaseLiveDataFragment<OrderListViewModel> {

    @BindView(R.id.spinner)
    AppCompatSpinner spinner;

    @BindView(R.id.btn_add_order)
    Button btnAdd;

    @BindView(R.id.checkbox_all)
    CheckBox checkAll;

    Unbinder unbinder;

    SuperRefreshManager mSuperRefreshManager;

    GoodsAddOrderAdapter mAdapter;

    ArrayAdapter areaAdapter;

    String goodsNum;
    String city;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(OrderListViewModel.class);

        goodsNum = getActivity().getIntent().getStringExtra(IntentBuilder.KEY_ID);
        city = getActivity().getIntent().getStringExtra(IntentBuilder.KEY_VALUE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_add_order, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("添加订单");

        mSuperRefreshManager = new SuperRefreshManager(view);
        mSuperRefreshManager.setLoadDefault(false);
        mSuperRefreshManager.init(view);
        mSuperRefreshManager.addDefaultItemDecoration(false);
        mSuperRefreshManager.setEnableRefresh(true);
        mSuperRefreshManager.setEnableLoadMore(false);
        mSuperRefreshManager.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter = new GoodsAddOrderAdapter();
        mSuperRefreshManager.setAdapter(mAdapter);

        mSuperRefreshManager.setOnRefreshListener(refreshlayout -> {
            mViewModel.getArea(city);
        });

        mAdapter.setOnItemClickListener((adapter, view1, position) -> {

            OrderDetailEntity item = mAdapter.getItem(position);
            IntentBuilder.Builder()
                    .putExtra(IntentBuilder.KEY_ORDER_ID,item.getOrderNum())
                    .putExtra(IntentBuilder.KEY_TYPE,OrderDetailFragment.TYPE_REVIEW)
                    .startParentActivity(getBaseActivity(), OrderDetailFragment.class,true);
        });

        areaAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, Lists.newArrayList());
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(areaAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String area = (String) areaAdapter.getItem(position);
                mViewModel.orderListByArea(area);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                LogUtil.print("");
            }
        });

        mViewModel.orderListByAreaLiveData.observe(this, list -> {
            mAdapter.setNewData(list);
        });

        mViewModel.getArea(city);
        mViewModel.getAreaLiveData().observe(this, strings -> {
            areaAdapter.clear();
            areaAdapter.addAll(strings);
            areaAdapter.notifyDataSetChanged();
        });

        mViewModel.getGoodsAddOrderLiveData().observe(this, aBoolean -> {

            if(aBoolean){
                ToastUtils.showLong(getActivity(),"添加成功");
                EventBus.getDefault().post(new GoodsListEvent());
                finish();
            }
        });

        RxUtil.click(checkAll).subscribe(o -> {

            if(checkAll.isChecked()){
                mAdapter.checkAll();
            }else {
                mAdapter.clearCurrentCheck();
            }
        });

        RxUtil.click(btnAdd).subscribe(o -> {

            String checkedIds = mAdapter.getCheckedIds();
            LogUtil.print(checkedIds);
            mViewModel.goodsAddOrder(goodsNum,checkedIds);

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
