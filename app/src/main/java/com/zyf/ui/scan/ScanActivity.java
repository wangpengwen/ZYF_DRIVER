package com.zyf.ui.scan;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import com.biz.base.BaseLiveDataActivity;
import com.biz.util.ToastUtils;
import com.zyf.driver.ui.R;
import com.zyf.ui.user.order.WebOrderViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * Title: ScanActivity
 * Description:
 * Copyright:Copyright(c)2016
 * Company:博智维讯信息技术有限公司
 * CreateTime:22/11/2017  14:53
 *
 * @author liutong
 * @version 1.0
 */

public class ScanActivity extends BaseLiveDataActivity<WebOrderViewModel> implements QRCodeView.Delegate {

    private ZXingView mZXingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_layout);
        mToolbar.setTitle(R.string.text_scan_input);
        mZXingView = findViewById(R.id.zxingview);
        initViewModel(WebOrderViewModel.class);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            initView();
        } else {
            getRxPermission().request(Manifest.permission.CAMERA).subscribe(b -> {
                if (b) {
                    initView();
                }
            });
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mZXingView != null) {
            mZXingView.startCamera();
            mZXingView.startSpot();
            mZXingView.showScanRect();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mZXingView != null) {
            mZXingView.stopCamera();
            mZXingView.hiddenScanRect();
        }
    }

    private void initView() {
        mZXingView.startCamera();
        mZXingView.setDelegate(this);
        mZXingView.showScanRect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mZXingView.onDestroy();
    }

    @Override
    public void error(String error) {
        super.error(error);
        mZXingView.startSpot();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        if (TextUtils.isEmpty(result)) {
            mZXingView.startSpot();
            return;
        }

        String orderNum = getOrderNum(result);

        if(TextUtils.isEmpty(orderNum)){

            ToastUtils.showLong(this,"二维码信息错误，请联系管理员");
        }else {

            mViewModel.lastDriverRecieve(orderNum);
        }

        mViewModel.getLastRecieveLiveData().observe(this, aBoolean -> {
            if(aBoolean){
                ToastUtils.showLong(this,"接货成功!");
                setResult(RESULT_OK);
                finish();
            }else {
                ToastUtils.showLong(this,"接货失败,请重新扫码");
            }
        });
    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }

    /**
     * http://www.zywl.com.cn/bZ7qOiSck4069+qgCJEJ+xnJ+pNOi33amd2sYymM/UE=/1
     * 截取中间的orderNum
     * @param qrcodeStr
     * @return
     */
    private String getOrderNum(String qrcodeStr){

        try {
            int start = getFromIndex(qrcodeStr,"/",3) + 1;
            int end = qrcodeStr.lastIndexOf("/");
            return qrcodeStr.substring(start,end);
        }catch (Exception e){
            return "";
        }
    }

    private int getFromIndex(String str, String modelStr, Integer count) {
        //对子字符串进行匹配
        Matcher slashMatcher = Pattern.compile(modelStr).matcher(str);
        int index = 0;
        //matcher.find();尝试查找与该模式匹配的输入序列的下一个子序列
        while(slashMatcher.find()) {
            index++;
            //当modelStr字符第count次出现的位置
            if(index == count){
                break;
            }
        }
        //matcher.start();返回以前匹配的初始索引。
        return slashMatcher.start();
    }

}
