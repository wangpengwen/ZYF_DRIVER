package com.zyf.ui.map;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.biz.base.BaseLiveDataActivity;
import com.biz.util.IntentBuilder;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.biz.util.Utils;
import com.zyf.driver.ui.R;
import com.zyf.model.entity.order.WebOrderEntity;
import com.zyf.ui.authentication.AuthenticationOrderActivity;
import com.zyf.ui.order.FirstDriverFinishQRCodeFragment;
import com.zyf.ui.order.OrderViewModel;
import com.zyf.ui.scan.ScanActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Title: MapRouteActivity
 * Description:
 * Copyright:Copyright(c)2018
 * Company:同城酒库电子商务有限公司
 * CreateTime:2018/9/13  16:22
 *
 * @author liutong
 */
public class MapRouteActivity extends BaseLiveDataActivity<OrderViewModel> {

    public static final int TYPE_FIRST_RECEIVE_VALIDATE = 9001;
    public static final int TYPE_FIRST_FINISH_QRCODE = 9002;
    public static final int TYPE_LAST_RECEIVE_QRCODE = 9003;
    public static final int TYPE_LAST_FINISH_VALIDATE = 9004;

    @BindView(R.id.mapview)
    MapView mMapView = null;
    @BindView(R.id.tv_order_type)
    TextView tvOrderType;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.tv_from_address_detail)
    TextView tvFromAddressDetail;
    @BindView(R.id.tv_sender)
    TextView tvSender;
    @BindView(R.id.tv_to_address_detail)
    TextView tvToAddressDetail;
    @BindView(R.id.tv_recipients)
    TextView tvRecipients;
    @BindView(R.id.btn_taking_order)
    Button btnTakingOrder;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.btn)
    Button btn;

    private BaiduMap mBaiduMap;
    WebOrderEntity entity;
    int step = 0;
    boolean isLast = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar_layout);
        mToolbar.setTitle("路线");
        getLayoutInflater().inflate(R.layout.activity_route_map, findViewById(R.id.frame_holder));

        entity = getActivity().getIntent().getParcelableExtra(IntentBuilder.KEY_DATA);
        step = getActivity().getIntent().getIntExtra(IntentBuilder.KEY_VALUE,0);

        initViewModel(OrderViewModel.class);
        ButterKnife.bind(this);
        mBaiduMap = mMapView.getMap();
        initView();

        if(entity!=null){
            isLast = entity.getWebIsLast() != 0;
            initData();
        }

        mViewModel.getFirstReceiveLiveData().observe(this, o -> {
            step = 2;
            initData();
        });

        mViewModel.getLastFinishLiveData().observe(this, s -> {
            ToastUtils.showLong(this,"订单配送完成");
            finish();
        });
    }

    private void initView(){
        tvOrderType.setVisibility(View.GONE);
        btnTakingOrder.setVisibility(View.GONE);
    }

    private void initData(){

        if(!isLast){
            //第一公里
            switch (step){

                case 1:
                    btn.setText("我已到达指定地点");
                    RxUtil.click(btn).subscribe(o -> {
                        startActivityForResult(new Intent(getActivity(), AuthenticationOrderActivity.class), TYPE_FIRST_RECEIVE_VALIDATE);
                    });
                    break;
                case 2:
                    btn.setText("确认配送完成");
                    RxUtil.click(btn).subscribe(o -> {
                        IntentBuilder.Builder()
                                .putExtra(IntentBuilder.KEY_ID,entity.webDrvId)
                                .startParentActivity(this, FirstDriverFinishQRCodeFragment.class,TYPE_FIRST_FINISH_QRCODE,true);
                    });
                    break;
            }

            tvName.setText(entity.getStartName());
            tvTime.setText(entity.getStartPhone());
            RxUtil.click(tvTime).subscribe(o -> {
                Utils.call(this,entity.getStartPhone());
            });
        }else {
            //最后一公里
            switch (step){

                case 1:
                    btn.setText("我已到达物流园");

                    RxUtil.click(btn).subscribe(o -> {
                        startActivityForResult(new Intent(getActivity(), ScanActivity.class), TYPE_LAST_RECEIVE_QRCODE);
                    });
                    break;
                case 2:
                    btn.setText("确认配送完成");
                    RxUtil.click(btn).subscribe(o -> {

                        Intent intent = new Intent(getActivity(), AuthenticationOrderActivity.class);
                        intent.putExtra(IntentBuilder.KEY_BOOLEAN,false);//是否必须识别身份证
                        startActivityForResult(intent, TYPE_LAST_FINISH_VALIDATE);
                    });
                    break;
            }

            tvName.setText(entity.getEndName());
            tvTime.setText(entity.getEndPhone());
            RxUtil.click(tvTime).subscribe(o -> {
                Utils.call(this,entity.getEndPhone());
            });
        }

        tvTime.setTextColor(getColors(R.color.colorPrimary));
        tvTime.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        tvDistance.setText(entity.webDistance+"公里");
        tvFromAddressDetail.setText(entity.startAddr);
        tvToAddressDetail.setText(entity.endAddr);
        tvPrice.setText(entity.webCarriage+"元");
        tvDesc.setText(entity.endAddr);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==TYPE_FIRST_RECEIVE_VALIDATE&&resultCode==RESULT_OK){
            mViewModel.firstDriverReceive(entity.webDrvId,data.getStringExtra(IntentBuilder.KEY_KEY2));
        }else if(requestCode==TYPE_FIRST_FINISH_QRCODE&&resultCode==RESULT_OK) {
            finish();
        }else if(requestCode==TYPE_LAST_RECEIVE_QRCODE&&resultCode==RESULT_OK){
            step = 2;
            initData();
        }else if(requestCode==TYPE_LAST_FINISH_VALIDATE&&resultCode==RESULT_OK){
            //17接口 TODO orderDrvId
            mViewModel.lastDriverFinish(entity.webDrvId,"");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }


}
