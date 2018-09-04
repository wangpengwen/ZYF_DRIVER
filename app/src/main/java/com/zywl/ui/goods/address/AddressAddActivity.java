package com.zywl.ui.goods.address;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.biz.base.BaseLiveDataActivity;
import com.biz.util.DrawableHelper;
import com.biz.util.IntentBuilder;
import com.biz.util.LogUtil;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.example.neolixlibrary.NeolixIdRead;
import com.zywl.model.entity.order.OrderRecipientsEntity;
import com.zywl.model.entity.order.OrderSenderEntity;
import com.zywl.ui.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;

/**
 * Created by ltxxx on 2018/5/31.
 */

public class AddressAddActivity extends BaseLiveDataActivity implements OnAddressSelectedListener {

    public static final String TYPE_SENDER = "TYPE_SENDER";
    public static final String TYPE_RECIPIENTS = "TYPE_RECIPIENTS";

    Unbinder unbinder;

    String type;

    @BindView(R.id.tv_name)
    EditText nameET;
    @BindView(R.id.tv_mobile)
    EditText phoneET;

    String province = "";
    String city = "";
    String distrinct = "";
    String street = "";
    @BindView(R.id.tv_address_detail)
    EditText detailAddrET;


    @BindView(R.id.layout_id_card_read)
    View idCardReadLayout;
    @BindView(R.id.btn_id_card_read)
    View idCardReadBtn;

    @BindView(R.id.tv_city_title)
    TextView cityTitleTV;
    @BindView(R.id.tv_city)
    TextView cityTV;


    @BindView(R.id.tv_id_name)
    TextView tvName;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.tv_nation)
    TextView tvNation;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_id_card)
    TextView tvIdCard;
    @BindView(R.id.tv_organization)
    TextView tvOrganization;
    @BindView(R.id.tv_validity)
    TextView tvValidity;

    @BindView(R.id.btn_confirm)
    View confirmBtn;

    BottomDialog dialog;

    OrderSenderEntity senderEntity;
    OrderRecipientsEntity recipientsEntity;

    private NeolixIdRead idRead;

    NeolixIdRead.returnListing myListing = new NeolixIdRead.returnListing() {
        @Override
        public void reStatus(String status) {
            LogUtil.print(status);
        }

        @Override
        public void reInfo(HashMap<String, String> map, Bitmap bitmap) {
            LogUtil.print(map.toString());
            //beginTime,address,idnum,signingOrganization,birthDate,nation,name,endTime,sex

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvName.setText(map.containsKey("name")?map.get("name"):"");
                    nameET.setText(map.containsKey("name")?map.get("name"):"");
                    tvGender.setText(map.containsKey("sex")?map.get("sex"):"");
                    tvNation.setText(map.containsKey("nation")?map.get("nation"):"");
                    tvBirthday.setText(map.containsKey("birthDate")?map.get("birthDate"):"");
                    tvAddress.setText(map.containsKey("address")?map.get("address"):"");
                    tvIdCard.setText(map.containsKey("idnum")?map.get("idnum"):"");
                    tvOrganization.setText(map.containsKey("signingOrganization")?map.get("signingOrganization"):"");
                    tvValidity.setText(map.containsKey("endTime")?map.get("endTime"):"");
                }
            });
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar_layout);
        getLayoutInflater().inflate(R.layout.fragment_add_address, findViewById(R.id.frame_holder));
        unbinder = ButterKnife.bind(this);

        type = getActivity().getIntent().getStringExtra(IntentBuilder.KEY_TYPE);

        if(TYPE_SENDER.equals(type)){

            mToolbar.setTitle("寄件人地址");
            idCardReadLayout.setVisibility(View.VISIBLE);
            senderEntity = getActivity().getIntent().getParcelableExtra(IntentBuilder.KEY_DATA);
            cityTitleTV.setCompoundDrawables(null, null, null, null);


            if (senderEntity!=null){
                nameET.setText(senderEntity.getSenderName());
                phoneET.setText(senderEntity.getSenderPhone());
                province = senderEntity.getSenderProvince();
                city = senderEntity.getSenderCity();
                distrinct = senderEntity.getSenderArea();
                cityTV.setText(province+city+distrinct);
                detailAddrET.setText(senderEntity.getSenderAddr());
            }

        }else{
            mToolbar.setTitle("收件人地址");
            idCardReadLayout.setVisibility(View.GONE);
            cityTitleTV.setCompoundDrawables(null, null, DrawableHelper.getDrawableWithBounds(this, R.drawable.must_mark), null);
            recipientsEntity = getActivity().getIntent().getParcelableExtra(IntentBuilder.KEY_DATA);
            if (recipientsEntity!=null){
                nameET.setText(recipientsEntity.getReciverName());
                phoneET.setText(recipientsEntity.getReciverPhone());
                province = recipientsEntity.getReciverProvince();
                city = recipientsEntity.getReciverCity();
                distrinct = recipientsEntity.getReciverArea();
                cityTV.setText(province+city+distrinct);
                detailAddrET.setText(recipientsEntity.getReciverAddr());
            }
        }



        RxUtil.click(idCardReadBtn).subscribe(o -> {
            ToastUtils.showLong(this,"开始识别，请刷身份证...");
            idRead.validNfc();
        });

        RxUtil.click(confirmBtn).subscribe(o -> {

            if(TextUtils.isEmpty(nameET.getText().toString())){
                ToastUtils.showLong(this,"请输入名字");
                return;
            }

            if(TYPE_SENDER.equals(type) && !TextUtils.isEmpty(tvName.getText()) && !TextUtils.equals(nameET.getText().toString(),tvName.getText())){
                ToastUtils.showLong(this,"所填姓名与身份证姓名不符，请重新输入");
                return;
            }

            if(TextUtils.isEmpty(phoneET.getText().toString())){
                ToastUtils.showLong(this,"请输入手机号");
                return;
            }

            Intent intent = new Intent();

            if(TYPE_SENDER.equals(type)){

                if(!TextUtils.isEmpty(detailAddrET.getText().toString()) && TextUtils.isEmpty(city)){
                    ToastUtils.showLong(this,"请选择地址");
                    return;
                }

                OrderSenderEntity orderSenderEntity = new OrderSenderEntity();
                orderSenderEntity.setSenderName(nameET.getText().toString());
                orderSenderEntity.setSenderPhone(phoneET.getText().toString());
                orderSenderEntity.setSenderProvince(province);
                orderSenderEntity.setSenderCity(city);
                orderSenderEntity.setSenderArea(distrinct);
                orderSenderEntity.setSenderAddr(detailAddrET.getText().toString());
                orderSenderEntity.setSenderIdcard(tvIdCard.getText().toString());
                int gender = "男".equals(tvGender.getText().toString()) ? 1 : "女".equals(tvGender.getText().toString()) ? 2 : 0;
                orderSenderEntity.setSenderSexual(gender+"");

                intent.putExtra(IntentBuilder.KEY_DATA, orderSenderEntity);
            }else if(TYPE_RECIPIENTS.equals(type)){

                if(TextUtils.isEmpty(city)){
                    ToastUtils.showLong(this,"请选择地址");
                    return;
                }
//                if(TextUtils.isEmpty(detailAddrET.getText().toString())){
//                    ToastUtils.showLong(this,"请输入详细地址");
//                    return;
//                }

                OrderRecipientsEntity recipientsEntity = new OrderRecipientsEntity();
                recipientsEntity.setReciverName(nameET.getText().toString());
                recipientsEntity.setReciverPhone(phoneET.getText().toString());
                recipientsEntity.setReciverProvince(province);
                recipientsEntity.setReciverCity(city);
                recipientsEntity.setReciverArea(distrinct);
                recipientsEntity.setReciverAddr(detailAddrET.getText().toString());

                intent.putExtra(IntentBuilder.KEY_DATA, recipientsEntity);
            }

            getActivity().setResult(Activity.RESULT_OK,intent);
            finish();
        });

        RxUtil.click(cityTV).subscribe(o -> {

            dialog = new BottomDialog(this);
            dialog.setOnAddressSelectedListener(this);
            dialog.show();

        });

        idRead = new NeolixIdRead(getActivity(), myListing);
        idRead.validNfc();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        idRead.validNfcButton(intent);
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        StringBuffer sb = new StringBuffer();
        if(province!=null){

            sb.append(province.name);
            this.province = province.name;
        }
        if(city!=null){

            sb.append(city.name);
            this.city = city.name;
        }
        if(county!=null){

            sb.append(county.name);
            this.distrinct = county.name;
        }
        if(street!=null){

            sb.append(street.name);
            this.street = street.name;
        }

        cityTV.setText(sb.toString());
        if(dialog!=null)
            dialog.dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        idRead.disconnectDevice();
    }
}
