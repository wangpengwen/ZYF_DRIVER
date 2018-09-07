package com.zyf.ui.vehicle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.biz.base.BaseLiveDataFragment;
import com.biz.base.BaseViewHolder;
import com.biz.util.Lists;
import com.biz.util.RxUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyf.driver.ui.R;
import com.zyf.model.UserModel;
import com.zyf.model.entity.vehicle.VehicleEntity;
import com.zyf.ui.user.order.UserOrderViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * Created by TCJK on 2018/5/29.
 */

public class VehicleFragment extends BaseLiveDataFragment<UserOrderViewModel> {

    @BindView(R.id.tv_welcome)
    TextView tvWelcome;
    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    @BindView(R.id.et_vehicle_num)
    EditText etVehicleNum;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    Unbinder unbinder;

    List<VehicleEntity> data;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(UserOrderViewModel.class, UserOrderViewModel.class.getName(), true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_type, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("品牌");

        data = Lists.newArrayList();
        data.add(new VehicleEntity("中型","中型车","厢货车3m*3m"));
        data.add(new VehicleEntity("小型","小型车","厢式车2m*2m"));
        data.add(new VehicleEntity("微型","微型车","其它运输工具"));

        VehicleAdapter adapter = new VehicleAdapter(R.layout.item_vehicle,data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((adapter1, view1, position) -> {
            for (VehicleEntity entity:data) {
                entity.isChecked = false;
            }
            data.get(position).isChecked = true;
            adapter.notifyDataSetChanged();
        });

        tvWelcome.setText(getString(R.string.text_welcome, UserModel.getInstance().getUserName()));

        RxUtil.click(btnConfirm).subscribe(o -> {

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class VehicleAdapter extends BaseQuickAdapter<VehicleEntity, BaseViewHolder> {

        public VehicleAdapter(int layoutResId, @Nullable List<VehicleEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, VehicleEntity item) {
            helper.setTextView(R.id.tv_vehicle_name,item.name);
            helper.setTextView(R.id.tv_desc,item.desc);
            helper.setViewVisible(R.id.iv_checked,item.isChecked ? View.VISIBLE:View.GONE);
        }
    }
}
