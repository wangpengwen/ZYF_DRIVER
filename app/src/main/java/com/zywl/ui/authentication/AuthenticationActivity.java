package com.zywl.ui.authentication;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.biz.base.BaseLiveDataActivity;
import com.biz.util.LogUtil;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.biz.widget.CountEditText;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.neolixlibrary.NeolixIdRead;
import com.zywl.ui.R;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by TCJK on 2018/5/28.
 */
public class AuthenticationActivity extends BaseLiveDataActivity<AuthenticationViewModel> {

    @BindView(R.id.tv_nfc_tip)
    View tvNFCtip;

    Unbinder unbinder;
    @BindView(R.id.tv_name)
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
    @BindView(R.id.avatar)
    AppCompatImageView avatar;
    @BindView(R.id.confirm_btn)
    View confirmBtn;
    @BindView(R.id.tv_dn_code)
    TextView tvDnCode;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.edit_search)
    CountEditText editSearch;
    @BindView(R.id.button)
    Button button;

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
                    tvName.setText(map.containsKey("name") ? map.get("name") : "");
                    tvGender.setText(map.containsKey("sex") ? map.get("sex") : "");
                    tvNation.setText(map.containsKey("nation") ? map.get("nation") : "");
                    tvBirthday.setText(map.containsKey("birthDate") ? map.get("birthDate") : "");
                    tvAddress.setText(map.containsKey("address") ? map.get("address") : "");
                    tvIdCard.setText(map.containsKey("idnum") ? map.get("idnum") : "");
                    tvOrganization.setText(map.containsKey("signingOrganization") ? map.get("signingOrganization") : "");
                    tvValidity.setText(map.containsKey("endTime") ? map.get("endTime") : "");

                    try{
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[]  bytes=baos.toByteArray();
                        Glide.with(avatar).load(bytes)
                                .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.vector_ic_user_default).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                                .into(avatar);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    idRead.validNfc();
                }
            });
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar_layout);
        findViewById(R.id.line).setVisibility(View.GONE);
        getLayoutInflater().inflate(R.layout.fragment_authentication, findViewById(R.id.frame_holder));
        unbinder = ButterKnife.bind(this);
        initViewModel(AuthenticationViewModel.class);
        mToolbar.setTitle("实名认证");

        editSearch.setHint("请输入要和身份证绑定的运单号");
        button.setVisibility(View.GONE);

        idRead = new NeolixIdRead(this, myListing);
        idRead.validNfc();
        RxUtil.click(tvNFCtip).subscribe(o -> {
            idRead.validNfc();
        });

        RxUtil.click(confirmBtn).subscribe(o -> {

            if(TextUtils.isEmpty(editSearch.getText().toString())){
                ToastUtils.showLong(this,"请输入运单号");
                return;
            }

            if(TextUtils.isEmpty(tvName.getText().toString())){
                ToastUtils.showLong(this,"请验证身份证");
                return;
            }

            String orderID = editSearch.getText().toString();
            String name = tvName.getText().toString();
            String address = tvAddress.getText().toString();
            String idCard = tvIdCard.getText().toString();
            int gender = "男".equals(tvGender.getText().toString()) ? 1 : "女".equals(tvGender.getText().toString()) ? 2 : 0;
            String birthday = tvBirthday.getText().toString();

            mViewModel.bindIDCard(orderID,name,address,idCard,gender,birthday);
        });

        mViewModel.getBindLiveData().observe(this, aBoolean -> {

            if(aBoolean){
                ToastUtils.showLong(this,"绑定成功");
                finish();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        idRead.validNfcButton(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mSubscription.clear();
        unbinder.unbind();
        idRead.disconnectDevice();
    }
}