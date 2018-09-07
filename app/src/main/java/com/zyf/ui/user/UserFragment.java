package com.zyf.ui.user;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biz.base.BaseLazyFragment;
import com.biz.base.BaseViewHolder;
import com.biz.util.DrawableHelper;
import com.biz.util.IntentBuilder;
import com.biz.util.Lists;
import com.biz.util.Utils;
import com.biz.widget.recyclerview.SuperRefreshManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyf.model.UserModel;
import com.zyf.driver.ui.R;
import com.zyf.ui.user.achievement.AchievementFragment;
import com.zyf.ui.user.order.UserOrderFragment;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

/**
 * Title: UserFragment
 * Description:
 * Copyright:Copyright(c)2016
 * Company:博智维讯信息技术有限公司
 * CreateTime:17/11/2017  11:17
 *
 * @author liutong
 * @version 1.0
 */
public class UserFragment extends BaseLazyFragment<UserViewModel> {
    private UserHeaderViewHolder mUserHeaderViewHolder;
    UserAdapter mAdapter;

    private SuperRefreshManager mSuperRefreshManager;
    protected List<String> list;

    boolean isTodaySign = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(UserViewModel.class, this.toString() + "" + UserViewModel.class.getCanonicalName(), true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(Color.WHITE);
        mSuperRefreshManager = new SuperRefreshManager(view);
        mUserHeaderViewHolder = UserHeaderViewHolder.createViewHolder(getContext());
        mAdapter = new UserAdapter();
        mSuperRefreshManager.setAdapter(mAdapter);
        mAdapter.addHeaderView(mUserHeaderViewHolder.itemView);
        mAdapter.setNewData(Lists.newArrayList(getResources().getStringArray(R.array.array_user)));
        mSuperRefreshManager.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getBaseActivity()).colorResId(R.color.color_divider)
                .size(1)
                .margin(Utils.dip2px(getActivity(), 12), Utils.dip2px(getActivity(), 12))
                .visibilityProvider((position, parent) -> {
                    if (position == 0) {
                        return true;
                    }
                    return false;
                })
                .showLastDivider()
                .build());

        mAdapter.setOnItemClickListener((adapter, view1, position) -> {

            if (getString(R.string.text_user_info).equalsIgnoreCase(mAdapter.getItem(position))) {
                IntentBuilder.Builder().startParentActivity(this.getActivity(), UserInfoFragment.class,true);
            }else if(getString(R.string.text_my_achievement).equalsIgnoreCase(mAdapter.getItem(position))){
                IntentBuilder.Builder().startParentActivity(this.getActivity(), AchievementFragment.class,true);
            }else if(getString(R.string.text_order_management).equalsIgnoreCase(mAdapter.getItem(position))){
                IntentBuilder.Builder().startParentActivity(this.getActivity(), UserOrderFragment.class,true);
            }
        });

        mUserHeaderViewHolder.mUserName.setText(UserModel.getInstance().getUserName());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void error(String error) {
        super.error(error);
    }

    @Override
    public void lazyLoad() {

    }

    class UserAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        protected List<Integer> icons = Lists.newArrayList(R.mipmap.ic_user_info, R.mipmap.ic_user_achievement,R.mipmap.ic_user_order);

        public UserAdapter() {
            super(R.layout.item_my_layout);

            list = Lists.newArrayList(getResources().getStringArray(R.array.array_user));
            setNewData(list);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            int p = helper.getLayoutPosition() - getHeaderLayoutCount();
            TextView title = helper.itemView.findViewById(R.id.title);
            TextView text = helper.itemView.findViewById(R.id.text);
            title.setCompoundDrawables(DrawableHelper.getDrawableWithBounds(getContext(), icons.get(p)), null, null, null);
            title.setText(getItem(p));

        }

        private boolean equals(String s, @StringRes int stringRes) {
            return getString(stringRes).equalsIgnoreCase(s);
        }

    }
}
