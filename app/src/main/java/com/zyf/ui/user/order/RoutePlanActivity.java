package com.zyf.ui.user.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteLine;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.biz.base.BaseActivity;
import com.biz.util.Lists;
import com.zyf.driver.ui.R;
import com.zyf.ui.map.LocationHelper;
import com.zyf.ui.map.QueryLocUtil;
import com.zyf.ui.map.overlayutil.BikingRouteOverlay;
import com.zyf.ui.map.overlayutil.OverlayManager;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by think on 2017/5/17.
 */

public class RoutePlanActivity extends BaseActivity implements OnGetRoutePlanResultListener {
    private MapView map;
    private TextView textDistance;
    private TextView textTime;
    private LatLng from = new LatLng(30.755948, 103.936686);
    private LatLng to = new LatLng(30.56608, 104.063167);
    private LocationHelper mLocationHelper;
    private BaiduMap mBaiduMap;
    private PlanNode stNode;
    private PlanNode enNode;
    private RoutePlanSearch mSearch;
    private BikingRouteResult nowResultBike;
    private OverlayManager routeOverlay;
//    private LBSTraceClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_route_paln_layout);
        map = (MapView) findViewById(R.id.map);
        mBaiduMap = map.getMap();
        textDistance = (TextView) findViewById(R.id.text_distance);
        textTime = (TextView) findViewById(R.id.text_time);
        initRoutePlanSearch();

    }


    private void initRoutePlanSearch() {
        setProgressVisible(true);
        mLocationHelper = new LocationHelper(this, (bdLocation) -> {
            if (null != bdLocation) {
                //from = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                mLocationHelper.setUserLatLon(bdLocation.getLatitude(), bdLocation.getLongitude());
                mLocationHelper.setCity(bdLocation.getCity());
                setMapViewCenter();
                mSearch = RoutePlanSearch.newInstance();
                mSearch.setOnGetRoutePlanResultListener(this);
                stNode = PlanNode.withLocation(from);
                enNode = PlanNode.withLocation(to);
                mSearch.bikingSearch(new BikingRoutePlanOption().from(stNode).to(enNode));
            }
        }
        );
    }

//    private void query() {
//        client= new LBSTraceClient(getActivity());
//        // 轨迹服务ID
//        long serviceId = 132138;
//    // entity标识列表
//        String entityNames = "test4";
//    //返回结果的类型（0 : 返回全部结果，1 : 只返回entityName的列表）
//        int returnType = 0;
//    // 活跃时间
//        int activeTime = 0;
//    // 分页大小
//        int pageSize = 100;
//    // 分页索引
//        int pageIndex =1;
//
//        EntityListRequest request = new EntityListRequest();
//        request.setServiceId(serviceId);
//        request.setPageIndex(pageIndex);
//        request.setPageSize(pageSize);
//        FilterCondition filterCondition =  new FilterCondition();
//        filterCondition.setEntityNames(Lists.newArrayList(entityNames));
//        request.setFilterCondition(filterCondition);
//    //查询entity
//        client.queryEntityList(request,  new MyLbsListener());
//    }

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
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult result) {
        setProgressVisible(false);
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {

            Toast.makeText(RoutePlanActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            if (result.getRouteLines().size() > 0) {
                nowResultBike = result;
                BikingRouteLine line = nowResultBike.getRouteLines().get(0);
                BikingRouteOverlay overlay = new BikingRouteOverlay(mBaiduMap);
                mBaiduMap.setOnMarkerClickListener(overlay);
                routeOverlay = overlay;
                overlay.setData(line);
                overlay.addToMap();
                overlay.zoomToSpan();
                textDistance.setText(distanceUtil(line.getDistance()));
                textTime.setText(durationUtil(line.getDuration()));
            } else {

                return;
            }
        }
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

//    class MyLbsListener extends OnEntityListener {
//
//
//        @Override
//        public void onReceiveLocation(TraceLocation traceLocation) {
//            super.onReceiveLocation(traceLocation);
//            traceLocation.getLatitude();
//            Log.e("TAG",traceLocation.getLatitude()+"     "+traceLocation.getLongitude());
//        }
//
//    }
}
