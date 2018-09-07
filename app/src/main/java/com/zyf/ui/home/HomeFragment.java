package com.zyf.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biz.base.BaseLazyFragment;
import com.biz.base.BaseViewHolder;
import com.biz.util.IntentBuilder;
import com.biz.util.Lists;
import com.biz.util.ToastUtils;
import com.biz.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyf.driver.ui.R;
import com.zyf.ui.authentication.AuthenticationActivity;
import com.zyf.ui.user.UserInfoFragment;
import com.zyf.ui.user.order.UserOrderFragment;
import com.zyf.ui.user.settings.SettingsActivity;
import com.zyf.widget.GridSpacingItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseLazyFragment<HomeViewModel> {

    @BindView(R.id.home_title)
    TextView titleTV;
    @BindView(R.id.grid_recyclerview)
    RecyclerView recyclerView;

    HomeItemAdapter adapter;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(HomeViewModel.class,HomeViewModel.class.getName(),true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        titleTV.setText(R.string.app_name);
        mToolbar.getMenu().add(0, 0, 0, "设置").setIcon(R.mipmap.ic_setting).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        mToolbar.setNavigationIcon(R.mipmap.ic_user);
        mToolbar.setNavigationOnClickListener(v -> {
            IntentBuilder.Builder().startParentActivity(this.getActivity(), UserInfoFragment.class,true);
        });
        mToolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == 0) {
                IntentBuilder.Builder().setClass(getActivity(), SettingsActivity.class).startActivity();
            }
            return false;
        });
//        EventBus.getDefault().register(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, Utils.dip2px(10),Utils.dip2px(10)));
        recyclerView.setAdapter(adapter = new HomeItemAdapter(getContext()));
        adapter.setOnItemClickListener((adapter, view1, position) -> {

            switch (position){

                case 0:

                    break;
                case 1:

                    break;
                case 2:
                    IntentBuilder.Builder().startParentActivity(this.getActivity(), UserOrderFragment.class,true);
                    break;
                case 3:
                    ToastUtils.showLong(getBaseActivity(),"敬请期待...");
                    break;
                case 4:

                    break;
                case 5:
                    IntentBuilder.Builder().setClass(getActivity(), AuthenticationActivity.class).startActivity();
                    break;
            }

        });
    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
//        EventBus.getDefault().unregister(this);
    }



    @Override
    public void error(String error) {
        super.error(error);
    }

    public class HomeItemAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

        public HomeItemAdapter(Context context){
            super(R.layout.item_single_image_layout, Lists.newArrayList(R.mipmap.home_item_goods_receive,
                    R.mipmap.home_item_scan,
                    R.mipmap.home_item_pay,
                    R.mipmap.home_item_invoice,
                    R.mipmap.home_item_goods_list,
                    R.mipmap.home_item_authentication));
        }

        @Override
        protected void convert(BaseViewHolder helper, Integer item) {
            helper.setImageResource(R.id.imageView, item);
        }

    }
}
