package com.zywl.ui.order;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.biz.base.BaseLiveDataActivity;
import com.biz.util.IntentBuilder;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zywl.ui.R;
import com.zywl.ui.order.printer.Activity_DeviceList;

import KMAndroidSDK.KMPrinterHelper;
import KMAndroidSDK.PublicFunction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * Title: SettingsActivity
 * Description:
 * Copyright:Copyright(c)2016
 * Company:博智维讯信息技术有限公司
 * CreateTime:26/12/2017  14:33
 *
 * @author johnzheng
 * @version 1.0
 */

public class OrderQRCodeActivity extends BaseLiveDataActivity<OrderViewModel> {

    Unbinder unbinder;

    String orderNum;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.num_tv)
    TextView numTv;
    @BindView(R.id.qr_code_layout)
    LinearLayout qrCodeLayout;
    @BindView(R.id.tv_reciver_name)
    TextView tvReciverName;
    @BindView(R.id.tv_reciver_phone)
    TextView tvReciverPhone;
    @BindView(R.id.tv_reciver_city)
    TextView tvReciverCity;
    @BindView(R.id.tv_goods_type)
    TextView tvGoodsType;
    @BindView(R.id.tv_goods_amount)
    TextView tvGoodsAmount;
    @BindView(R.id.tv_sender_name)
    TextView tvSenderName;
    @BindView(R.id.tv_sender_phone)
    TextView tvSenderPhone;
    @BindView(R.id.tv_sender_address)
    TextView tvSenderAddress;
    @BindView(R.id.btn_connect)
    Button btnConnect;
    @BindView(R.id.btn_print)
    Button btnPrint;

    private PublicFunction PFun=null;
    public static String paper="0";
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar_layout);
        getLayoutInflater().inflate(R.layout.activity_order_qrcode, findViewById(R.id.frame_holder));
        unbinder = ButterKnife.bind(this);
        initViewModel(OrderViewModel.class);
        mToolbar.setTitle("打印货物二维码");

        orderNum = getIntent().getStringExtra(IntentBuilder.KEY_ORDER_ID);

        mViewModel.qrCode(orderNum);
        mViewModel.getQrcodeLiveData().observe(this, o -> {

            if(o==null) return;

            if(o.getUrls()!=null && o.getUrls().size()>0){

                Glide.with(this).load(getString(R.string.qrcode_pre_url) + o.getUrls().get(0))
                        .apply(RequestOptions.centerInsideTransform())
                        .into(imageView);

                numTv.setText("共"+o.getUrls().size()+"张");
            }

            tvReciverName.setText(o.getReciverName());
            tvReciverPhone.setText(o.getReciverPhone());
            tvReciverCity.setText(o.getReciverCity());
            tvGoodsType.setText("");
            tvGoodsAmount.setText(o.getCargoAmount());
            tvSenderName.setText(o.getSenderName());
            tvSenderPhone.setText(o.getSenderPhone());
            tvSenderAddress.setText(o.getSenderaddr());

            RxUtil.click(btnPrint).subscribe(object -> {
                ToastUtils.showLong(this,"打印中，请稍后...");
                try
                {
                    if(o.getUrls()!=null && o.getUrls().size()>0){

                        for (String data :o.getUrls()) {

                            KMPrinterHelper.printAreaSize("0", "0","0","460","1");
                            //		KMPrinterHelper.printqrcode("123");
                            KMPrinterHelper.PrintQR(KMPrinterHelper.BARCODE,"0", "0","1", "12", data);

                            KMPrinterHelper.LanguageEncode="GBK";
                            KMPrinterHelper.Text(KMPrinterHelper.TEXT, "24", "0", "300", "30", "收件人：");
                            KMPrinterHelper.Text(KMPrinterHelper.TEXT, "24", "0", "300", "60", tvReciverName.getText().toString() + "  "+tvReciverPhone.getText());
                            KMPrinterHelper.Text(KMPrinterHelper.TEXT, "24", "0", "300", "90", "发件人：");
                            KMPrinterHelper.Text(KMPrinterHelper.TEXT, "24", "0", "300", "120", tvSenderName.getText().toString()+ "  "+tvSenderPhone.getText());

                            KMPrinterHelper.Print();
                        }
                    }
                }
                catch (Exception e)
                {
                    Log.d("HPRTSDKSample", (new StringBuilder("Activity_QRCode --> onClickPrint ")).append(e.getMessage()).toString());
                }
            });
        });

        RxUtil.click(btnConnect).subscribe(o -> deviceList());

        //打印机驱动
        PFun=new PublicFunction(getApplicationContext());
        InitSetting();
        //Enable Bluetooth
        EnableBluetooth();
    }

    private void InitSetting() {
        String SettingValue="";
        SettingValue=PFun.ReadSharedPreferencesData("Codepage");
        if(SettingValue.equals(""))
            PFun.WriteSharedPreferencesData("Codepage", "0,PC437(USA:Standard Europe)");

        SettingValue=PFun.ReadSharedPreferencesData("Cut");
        if(SettingValue.equals(""))
            PFun.WriteSharedPreferencesData("Cut", "0");	//0:???,1:????,2:?????

        SettingValue=PFun.ReadSharedPreferencesData("Cashdrawer");
        if(SettingValue.equals(""))
            PFun.WriteSharedPreferencesData("Cashdrawer", "0");

        SettingValue=PFun.ReadSharedPreferencesData("Buzzer");
        if(SettingValue.equals(""))
            PFun.WriteSharedPreferencesData("Buzzer", "0");

        SettingValue=PFun.ReadSharedPreferencesData("Feeds");
        if(SettingValue.equals(""))
            PFun.WriteSharedPreferencesData("Feeds", "0");
        String paper = PFun.ReadSharedPreferencesData("papertype");
        if (!"".equals(paper)) {
            OrderQRCodeActivity.paper=paper;
        }
    }

    private void deviceList(){
        Intent serverIntent = new Intent(this,Activity_DeviceList.class);
        startActivityForResult(serverIntent, KMPrinterHelper.ACTIVITY_CONNECT_BT);
    }

    //EnableBluetooth
    private boolean EnableBluetooth()
    {
        boolean bRet = false;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter != null)
        {
            if(mBluetoothAdapter.isEnabled())
                return true;
            mBluetoothAdapter.enable();
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            if(!mBluetoothAdapter.isEnabled())
            {
                bRet = true;
                Log.d("PRTLIB", "BTO_EnableBluetooth --> Open OK");
            }
        }
        else
        {
            Log.d("HPRTSDKSample", (new StringBuilder("Activity_Main --> EnableBluetooth ").append("Bluetooth Adapter is null.")).toString());
        }
        return bRet;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data)
    {
        try
        {
            String strIsConnected;
            switch(resultCode)
            {
                case KMPrinterHelper.ACTIVITY_CONNECT_BT:
                    String strBTAddress="";
                    strIsConnected=data.getExtras().getString("is_connected");
                    if (strIsConnected.equals("NO"))
                    {
                        ToastUtils.showLong(this,getString(R.string.activity_main_scan_error));
                        return;
                    }
                    else
                    {
                        ToastUtils.showLong(this,getString(R.string.activity_main_connected));
                        return;
                    }
            }
        }
        catch(Exception e)
        {
            Log.e("HPRTSDKSample", (new StringBuilder("Activity_Main --> onActivityResult ")).append(e.getMessage()).toString());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
