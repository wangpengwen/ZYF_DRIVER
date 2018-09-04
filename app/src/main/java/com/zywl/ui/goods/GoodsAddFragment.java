package com.zywl.ui.goods;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.biz.base.BaseLiveDataFragment;
import com.biz.util.IntentBuilder;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.zywl.event.GoodsListEvent;
import com.zywl.model.entity.car.CarEntity;
import com.zywl.model.entity.goods.StationEntity;
import com.zywl.ui.R;
import com.zywl.ui.car.CarFindFragment;
import com.zywl.ui.goods.station.StationFindFragment;
import com.zywl.ui.order.GoodsAddOrderFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import rx.functions.Action1;

/**
 * Created by TCJK on 2018/6/1.
 */

public class GoodsAddFragment extends BaseLiveDataFragment<GoodsListViewModel> {

    public static final int TYPE_START_STATION = 9001;
    public static final int TYPE_END_STATION = 9002;
    public static final int TYPE_CAR = 9003;

    Unbinder unbinder;

    @BindView(R.id.btn_confirm)
    View confirmBtn;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.tv_car_num)
    TextView tvCarNum;

    StationEntity startStation;
    StationEntity endStation;
    CarEntity carEntity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(GoodsListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_add, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("新建货单");

        RxUtil.click(tvStart).subscribe(o -> {
            IntentBuilder.Builder()
                    .startParentActivity(getBaseActivity(), StationFindFragment.class,TYPE_START_STATION,true);
        });

        RxUtil.click(tvEnd).subscribe(o -> {
            IntentBuilder.Builder()
                    .startParentActivity(getBaseActivity(), StationFindFragment.class,TYPE_END_STATION,true);
        });

        RxUtil.click(tvCarNum).subscribe(o -> {
            IntentBuilder.Builder()
                    .startParentActivity(getBaseActivity(), CarFindFragment.class,TYPE_CAR,true);

        });

        RxUtil.click(confirmBtn).subscribe(o -> {

            if(TextUtils.isEmpty(tvName.getText().toString())){
                ToastUtils.showLong(getActivity(),"请输入货单名称");
                return;
            }

            if(startStation==null){
                ToastUtils.showLong(getActivity(),"请输入起点");
                return;
            }

            if(endStation==null){
                ToastUtils.showLong(getActivity(),"请输入终点");
                return;
            }

//            if(carEntity==null){
//                ToastUtils.showLong(getActivity(),"请指定车辆");
//                return;
//            }

            String carCode = "";
            if(carEntity!=null){
                carCode = carEntity.getTruckNum();
            }

            mViewModel.createGoods(tvName.getText().toString(),
                    startStation.getStationNum(),
                    endStation.getStationNum(),
                    carCode);
            mViewModel.getCreateLiveData().observe(this, s -> {
                // 跳转添加订单   用终点的city传给添加页面, 拿city查询区域列表, 再根据区域列表筛选订单,添加

                IntentBuilder.Builder()
                        .putExtra(IntentBuilder.KEY_ID,s)
                        .putExtra(IntentBuilder.KEY_VALUE,endStation.getStationCity())
                        .startParentActivity(getBaseActivity(), GoodsAddOrderFragment.class,true);

                EventBus.getDefault().post(new GoodsListEvent());
                finish();
            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TYPE_START_STATION && resultCode == Activity.RESULT_OK){

            if(data!=null){

                startStation = data.getParcelableExtra(IntentBuilder.KEY_DATA);
                tvStart.setText(startStation.getStationName());
            }
        }else if(requestCode == TYPE_END_STATION && resultCode == Activity.RESULT_OK){

            if(data!=null){

                endStation = data.getParcelableExtra(IntentBuilder.KEY_DATA);
                tvEnd.setText(endStation.getStationName());
            }
        }else if(requestCode == TYPE_CAR && resultCode == Activity.RESULT_OK){

            if(data!=null){

                carEntity = data.getParcelableExtra(IntentBuilder.KEY_DATA);
                tvCarNum.setText(carEntity.getTruckCarnum());
            }
        }
    }
}
