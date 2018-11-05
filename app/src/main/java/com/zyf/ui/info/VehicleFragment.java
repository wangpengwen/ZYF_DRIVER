package com.zyf.ui.info;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.biz.base.BaseLiveDataFragment;
import com.biz.base.BaseViewHolder;
import com.biz.util.IntentBuilder;
import com.biz.util.Lists;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.biz.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyf.driver.ui.R;
import com.zyf.model.UserModel;
import com.zyf.model.entity.vehicle.VehicleEntity;
import com.zyf.ui.authentication.AuthenticationActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by TCJK on 2018/5/29.
 */

public class VehicleFragment extends BaseLiveDataFragment<ValidateViewModel> {

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

    String vehicleTypeStr = "";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(ValidateViewModel.class, ValidateViewModel.class.getName(), false);
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
        data.add(new VehicleEntity("中型","中型","规格 4.2*1.8*1.8/4.2*1.8*0.4\n载重量 蓝牌：1.5-1.9T，黄牌：5-6T",R.drawable.ic_zhongxing));
        data.add(new VehicleEntity("小型","小型","规格 2.5*1.5*0.4\n载重量 1.5T",R.drawable.ic_xiaoxing));
        data.add(new VehicleEntity("微型","微型","规格 1.8*1.3*1.1/2.4*1.4*1.2\n载重量 500KG-1000KG",R.drawable.ic_weixing));

        VehicleAdapter adapter = new VehicleAdapter(R.layout.item_vehicle,data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((adapter1, view1, position) -> {
            for (VehicleEntity entity:data) {
                entity.isChecked = false;
            }
            data.get(position).isChecked = true;
            vehicleTypeStr = data.get(position).type;
            adapter.notifyDataSetChanged();
        });

        tvWelcome.setText(getString(R.string.text_welcome, UserModel.getInstance().getRealUserName()));

        mViewModel.getVehicleLiveData().observe(this, o -> {
            IntentBuilder.Builder().setClass(getActivity(), AuthenticationActivity.class).startActivity();
            finish();
        });

        RxUtil.click(btnConfirm).subscribe(o -> {

            if(TextUtils.isEmpty(vehicleTypeStr)){
                ToastUtils.showLong(getActivity(),"请选择车型");
                return;
            }

            if(!Utils.checkVehicleNum(etVehicleNum.getText().toString())){
                ToastUtils.showLong(getActivity(),"请输入正确的车牌号");
                return;
            }

            mViewModel.uploadVehicle(vehicleTypeStr,etVehicleNum.getText().toString());
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
            ImageView icon = helper.getView(R.id.icon);
            Glide.with(icon).load(item.iconDrawable)
                    .apply(RequestOptions.circleCropTransform())
                    .into(icon);
            helper.setTextView(R.id.tv_vehicle_name,item.name);
            helper.setTextView(R.id.tv_desc,item.desc);
            helper.setViewVisible(R.id.iv_checked,item.isChecked ? View.VISIBLE:View.GONE);
        }
    }
}
