package com.zyf.ui.map;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.biz.base.BaseLiveDataActivity;
import com.biz.util.IntentBuilder;
import com.biz.util.MathUtil;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.biz.util.Utils;
import com.zyf.driver.ui.R;
import com.zyf.event.WebOrderEvent;
import com.zyf.model.entity.order.WebOrderEntity;
import com.zyf.ui.authentication.AuthenticationOrderActivity;
import com.zyf.ui.map.overlayutil.DrivingRouteOverlay;
import com.zyf.ui.order.FirstDriverFinishQRCodeFragment;
import com.zyf.ui.order.OrderViewModel;
import com.zyf.ui.scan.ScanActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Title: MapRouteActivity
 * Description:
 * Copyright:Copyright(c)2018
 * Company:同城酒库电子商务有限公司
 * CreateTime:2018/9/13  16:22
 *
 * @author liutong
 */
public class MapRouteActivity extends BaseLiveDataActivity<OrderViewModel> implements OnGetRoutePlanResultListener {

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
    @BindView(R.id.tv_route_distance)
    TextView tvRouteDistance;
    @BindView(R.id.tv_route_time)
    TextView tvRouteTime;
    @BindView(R.id.btn)
    Button btn;

    WebOrderEntity entity;
    int step = 0;
    boolean isLast = false;

    private BaiduMap mBaiduMap;
    private PlanNode stNode;
    private PlanNode enNode;
    private LatLng from;
    private LatLng to;
    private RoutePlanSearch mSearch;
    private LocationHelper mLocationHelper;
    private DrivingRouteResult nowResultDriving;
    public static final float DEFAULT_ZOOM = 15f;      //默认缩放比例
    public static final LatLng ZHONGYUN = new LatLng(36.677490d,117.134830d);//中云总部


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
        initRoutePlanSearch();

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
            EventBus.getDefault().post(new WebOrderEvent());
            finish();
        });

        mViewModel.getLastBeforeFinishLiveData().observe(this, s -> {
            mViewModel.lastDriverFinish(s.webDrvId,"");
        });
    }

    private void initView(){
        tvOrderType.setVisibility(View.GONE);
        btnTakingOrder.setVisibility(View.GONE);
    }

    private void initRoutePlanSearch() {
        mLocationHelper = new LocationHelper(this, (bdLocation) -> {
            if (null != bdLocation) {
                //from = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                mLocationHelper.setUserLatLon(bdLocation.getLatitude(), bdLocation.getLongitude());
                mLocationHelper.setCity(bdLocation.getCity());
                setMapViewCenter();
                moveToLocation(new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude()));
                initData();
//                stNode = PlanNode.withLocation(from);
//                enNode = PlanNode.withLocation(to);
//                mSearch.drivingSearch(new DrivingRoutePlanOption().from(stNode).to(enNode));
            }
        }
        );
    }

    private void setMapViewCenter() {

        QueryLocUtil query = new QueryLocUtil(this);
        query.queryLoc(b -> {
            LatLng cenpt = new LatLng(mLocationHelper.getUserLat(), mLocationHelper.getUserLon());
            MapStatus mMapStatus = new MapStatus.Builder()
                    .target(cenpt)
                    .zoom(15)
                    .build();
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            mBaiduMap.setMapStatus(mMapStatusUpdate);
        });
    }

    public void moveToLocation(LatLng latLng) {
        try {
            if (latLng == null || (MathUtil.compareEquals(latLng.latitude, 0.0f)
                    && MathUtil.compareEquals(latLng.longitude, 0.0f))) {
                latLng = ZHONGYUN;
            }
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(
                    latLng, DEFAULT_ZOOM);
            mBaiduMap.animateMapStatus(u);
        } catch (Exception e) {
        }
    }

    private void initData(){

        if(!isLast){
            //第一公里
            switch (step){

                case 1:
                    btn.setText("我已到达指定地点");
                    if(mLocationHelper.getUserLat() > 0){
                        from = new LatLng(mLocationHelper.getUserLat(), mLocationHelper.getUserLon());
                        to = new LatLng(Double.parseDouble(entity.startX),Double.parseDouble(entity.startY));
                        stNode = PlanNode.withLocation(from);
                        enNode = PlanNode.withLocation(to);
                        mSearch = RoutePlanSearch.newInstance();
                        mSearch.setOnGetRoutePlanResultListener(this);
                        mSearch.drivingSearch(new DrivingRoutePlanOption().from(stNode).to(enNode));
                    }
                    RxUtil.click(btn).subscribe(o -> {
                        startActivityForResult(new Intent(getActivity(), AuthenticationOrderActivity.class), TYPE_FIRST_RECEIVE_VALIDATE);
                    });
                    break;
                case 2:
                    btn.setText("确认配送完成");
                    from = new LatLng(Double.parseDouble(entity.startX),Double.parseDouble(entity.startY));
                    to = new LatLng(Double.parseDouble(entity.endX),Double.parseDouble(entity.endY));
                    stNode = PlanNode.withLocation(from);
                    enNode = PlanNode.withLocation(to);
                    mSearch = RoutePlanSearch.newInstance();
                    mSearch.setOnGetRoutePlanResultListener(this);
                    mSearch.drivingSearch(new DrivingRoutePlanOption().from(stNode).to(enNode));
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
                    if(mLocationHelper.getUserLat() > 0){
                        from = new LatLng(mLocationHelper.getUserLat(), mLocationHelper.getUserLon());
                        to = new LatLng(Double.parseDouble(entity.startX),Double.parseDouble(entity.startY));
                        stNode = PlanNode.withLocation(from);
                        enNode = PlanNode.withLocation(to);
                        mSearch = RoutePlanSearch.newInstance();
                        mSearch.setOnGetRoutePlanResultListener(this);
                        mSearch.drivingSearch(new DrivingRoutePlanOption().from(stNode).to(enNode));

                    }
                    RxUtil.click(btn).subscribe(o -> {
                        startActivityForResult(new Intent(getActivity(), ScanActivity.class), TYPE_LAST_RECEIVE_QRCODE);
                    });
                    break;
                case 2:
                    btn.setText("确认配送完成");
                    from = new LatLng(Double.parseDouble(entity.startX),Double.parseDouble(entity.startY));
                    to = new LatLng(Double.parseDouble(entity.endX),Double.parseDouble(entity.endY));
                    stNode = PlanNode.withLocation(from);
                    enNode = PlanNode.withLocation(to);
                    mSearch = RoutePlanSearch.newInstance();
                    mSearch.setOnGetRoutePlanResultListener(this);
                    mSearch.drivingSearch(new DrivingRoutePlanOption().from(stNode).to(enNode));
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
            EventBus.getDefault().post(new WebOrderEvent());
            finish();
        }else if(requestCode==TYPE_LAST_RECEIVE_QRCODE&&resultCode==RESULT_OK){
            step = 2;
            initData();
        }else if(requestCode==TYPE_LAST_FINISH_VALIDATE&&resultCode==RESULT_OK){
            //15接口 orderDrvId
            mViewModel.lastBeforeFinish();
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


    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {

            Toast.makeText(MapRouteActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            if (result.getRouteLines().size() > 0) {
                mBaiduMap.clear();
                nowResultDriving = result;
                DrivingRouteLine line = nowResultDriving.getRouteLines().get(0);
                DrivingRouteOverlay overlay = new DrivingRouteOverlay(mBaiduMap);
                mBaiduMap.setOnMarkerClickListener(overlay);
//                routeOverlay = overlay;
                overlay.setData(line);
                overlay.addToMap();
                overlay.zoomToSpan();
                tvRouteDistance.setText(distanceUtil(line.getDistance()));
                tvRouteTime.setText(durationUtil(line.getDuration()));
            } else {

                return;
            }
        }
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult result) {

    }

    private String distanceUtil(int dis) {
        String s;
        if (dis < 1000) {
            s = getString(R.string.text_meter, String.valueOf(dis));
        } else {
            s = getString(R.string.text_kilometer, String.valueOf(new BigDecimal(dis).divide(new BigDecimal(1000), 2, RoundingMode.DOWN)));
        }
        return s;
    }

    private String durationUtil(int time) {
        String s;
        s = getString(R.string.text_duration, String.valueOf(time / 60));
        return s;
    }
}
