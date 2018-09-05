package com.zyf.ui.hangye;

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
import com.biz.widget.recyclerview.SuperRefreshManager;
import com.zyf.model.entity.hangye.CompanyEntity;
import com.zyf.driver.ui.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ltxxx on 2018/6/28.
 */

public class HangyeResultFragment extends BaseLiveDataFragment<HangyeViewModel> {

    Unbinder unbinder;

    SuperRefreshManager mSuperRefreshManager;
    CompanyAdapter mAdapter;

    List<CompanyEntity> list;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(HangyeViewModel.class);

        list = getActivity().getIntent().getParcelableArrayListExtra(IntentBuilder.KEY_LIST);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);

        mSuperRefreshManager = new SuperRefreshManager(view);
        mSuperRefreshManager.setLoadDefault(false);
        mSuperRefreshManager.init(view);
        mSuperRefreshManager.addDefaultItemDecoration(false);
        mSuperRefreshManager.setEnableRefresh(true);
        mSuperRefreshManager.setEnableLoadMore(false);
        mSuperRefreshManager.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter = new CompanyAdapter();
        mSuperRefreshManager.setAdapter(mAdapter);

        mAdapter.setEmptyView(View.inflate(getBaseActivity(), R.layout.item_empty_layout, null));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("查询结果");

        mAdapter.setNewData(list);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
