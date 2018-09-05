package com.zyf.ui.user.message;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biz.base.BaseLiveDataFragment;
import com.biz.base.BaseViewHolder;
import com.biz.util.TimeUtil;
import com.biz.widget.recyclerview.SuperRefreshManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyf.model.entity.user.MessageEntity;
import com.zyf.driver.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2017 http://www.biz-united.com.cn All rights reserved.
 * Created by zanezhao on 2017/11/18.
 *
 * @Project: TCJK
 * @Package: com.biz.ui.user
 * @Description: 消息中心
 * @author: zanezhao
 * @version: V1.0
 */

public class MessageCenterFragment extends BaseLiveDataFragment<MessageViewModel> {

    private SuperRefreshManager mSuperRefreshManager;
    private MessageAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(MessageViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.recyclerview, container, false);
        mSuperRefreshManager = new SuperRefreshManager();
        mSuperRefreshManager.init(view);
        mSuperRefreshManager.setEnableLoadMore(true);
        mSuperRefreshManager.setEnableRefresh(true);
        mSuperRefreshManager.addDefaultItemDecoration();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle(R.string.text_message_center);
        adapter = new MessageAdapter();
        mSuperRefreshManager.setAdapter(adapter);

        //TODO 测试数据
        List<MessageEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            MessageEntity entity = new MessageEntity();
            entity.title = "已入场";
            entity.content = "车牌号：鲁A86956  2018/3/31 9:22:35已入场";
            entity.createTimestamp = 1522459355000L;
            entity.read = false;
            list.add(entity);
        }
        adapter.setNewData(list);
//        adapter.setOnItemClickListener((adapter1, view, position) -> {
//            MessageEntity messageEntity = adapter.getItem(position);
//            messageEntity.read = true;
//            mViewModel.updateMessageStatus(messageEntity._messageId);
//            adapter.notifyDataSetChanged();
//            if(!TextUtils.isEmpty(messageEntity.url)) {
//                SchemeUtil.startMainUri(getContext(), BaseRequest.builder().headUrl(getContext().getString(R.string.message_scheme_host))
//                        .url(messageEntity.url).getUrl(false));
//            }
//        });
//        mSuperRefreshManager.setOnRefreshListener(refreshlayout -> {
//            mViewModel.requestMessage();
//        });
//        mSuperRefreshManager.setOnLoadMoreListener(refreshlayout -> {
//            mViewModel.loadMore();
//        });
//        mViewModel.getMessageDataLiveData().observe(this, list -> {
//            setProgressVisible(false);
//            mSuperRefreshManager.finishLoadmore();
//            mSuperRefreshManager.finishRefresh();
//            adapter.setNewData(list);
//        });
//        mViewModel.getMessageDataLoadMoreLiveData().observe(this, list -> {
//            setProgressVisible(false);
//            mSuperRefreshManager.finishLoadmore();
//            mSuperRefreshManager.finishRefresh();
//            adapter.addData(list);
//        });
//        setProgressVisible(true);
//        mViewModel.requestMessage();
    }
    class MessageAdapter extends BaseQuickAdapter<MessageEntity, BaseViewHolder> {

        public MessageAdapter() {
            super(R.layout.item_message_center);
        }

        @Override
        protected void convert(BaseViewHolder helper, MessageEntity item) {
            helper.setTextView(R.id.text_title, item.title == null ? "" : item.title);
            helper.setTextView(R.id.text_content, item.content == null ? "" : item.content);
            helper.setTextView(R.id.text_time, TimeUtil.format(item.createTimestamp, TimeUtil.FORMAT_MM_DD_HHMM));
            View view = helper.findViewById(R.id.view_red_dot);
            if (view != null) {
                view.setVisibility(item.read ? View.GONE : View.VISIBLE);
            }
            AppCompatImageView imageView = helper.findViewById(R.id.icon);
            imageView.setImageResource(R.drawable.vector_message_system);
        }
    }
}
