package com.zywl.ui.goods.station;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.biz.base.BaseLiveDataFragment;
import com.biz.util.IntentBuilder;
import com.biz.util.RxUtil;
import com.biz.widget.recyclerview.SuperRefreshManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zywl.model.entity.goods.GoodsEntity;
import com.zywl.model.entity.goods.StationEntity;
import com.zywl.ui.R;
import com.zywl.ui.goods.GoodsAdapter;
import com.zywl.ui.goods.GoodsAddFragment;
import com.zywl.ui.goods.GoodsListViewModel;
import com.zywl.ui.order.OrderListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * Created by TCJK on 2018/6/5.
 */

public class StationFindFragment extends BaseLiveDataFragment<StationViewModel> {

    Unbinder unbinder;

    @BindView(R.id.edit_search)
    EditText searchET;

    SuperRefreshManager mSuperRefreshManager;
    StationAdapter mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(StationViewModel.class, StationViewModel.class.getName(), true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_station, container, false);
        unbinder = ButterKnife.bind(this, view);

        mSuperRefreshManager = new SuperRefreshManager(view);
        mSuperRefreshManager.setLoadDefault(false);
        mSuperRefreshManager.init(view);
        mSuperRefreshManager.addDefaultItemDecoration(false);
        mSuperRefreshManager.setEnableRefresh(false);
        mSuperRefreshManager.setEnableLoadMore(false);
        mSuperRefreshManager.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter = new StationAdapter();
        mSuperRefreshManager.setAdapter(mAdapter);

        searchET.setHint("请输入站点名称");

        RxUtil.textChanges(searchET).subscribe(s -> {

            if(TextUtils.isEmpty(s)){
                mAdapter.getData().clear();
                mAdapter.notifyDataSetChanged();
                return;
            }
            mViewModel.findStation(s);
        });

        mViewModel.getStationLiveData().observe(this, list -> {
            mAdapter.setNewData(list);
        });

        mAdapter.setOnItemClickListener((adapter, view1, position) -> {

            Intent intent = new Intent();
            intent.putExtra(IntentBuilder.KEY_DATA,mAdapter.getItem(position));
            getActivity().setResult(Activity.RESULT_OK,intent);
            finish();
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("站点查询");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
