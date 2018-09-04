package com.zywl.ui.goods;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.biz.base.BaseLiveDataFragment;
import com.biz.util.IntentBuilder;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.zywl.model.entity.order.GoodsInfoEntity;
import com.zywl.model.entity.order.OrderRecipientsEntity;
import com.zywl.model.entity.order.OrderSenderEntity;
import com.zywl.ui.R;
import com.zywl.ui.goods.address.AddressAddActivity;
import com.zywl.ui.order.OrderDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action0;

/**
 * Created by TCJK on 2018/5/21.
 */

public class GoodsAcquiringFragment extends BaseLiveDataFragment<GoodsListViewModel> {

    public static final int ORDER_CONFIRM_REQUEST = 9001;
    public static final int ORDER_SENDER_REQUEST = 9002;
    public static final int ORDER_RECIPIENTS_REQUEST = 9003;

    Unbinder unbinder;

    BottomSheetDialog mDialog;

    @BindView(R.id.btnConfirm)
    Button confirmBtn;
    @BindView(R.id.goods_layout)
    View goodsLayout;
    @BindView(R.id.sender_tv)
    TextView senderTV;
    @BindView(R.id.recipients_tv)
    TextView recipientsTV;
    @BindView(R.id.tv_goods_info)
    TextView goodsInfoTV;
    @BindView(R.id.et_additional_cost)
    TextView additionalCostET;
    @BindView(R.id.et_remark)
    EditText remarkET;
    @BindView(R.id.protocol_checkbox)
    CheckBox protocolCB;
    @BindView(R.id.et_collection_on_delivery)
    EditText codET;

    GoodsInfoViewHolder holder;

    OrderSenderEntity senderEntity;
    OrderRecipientsEntity recipientsEntity;
    GoodsInfoEntity goodsInfoEntity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(GoodsListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_acquiring, container, false);
        unbinder = ButterKnife.bind(this, view);

        RxUtil.click(senderTV).subscribe(o -> {
            IntentBuilder.Builder().setClass(getActivity(),AddressAddActivity.class)
                    .putExtra(IntentBuilder.KEY_TYPE,AddressAddActivity.TYPE_SENDER)
                    .putExtra(IntentBuilder.KEY_DATA,senderEntity)
                    .startActivity(this,ORDER_SENDER_REQUEST);
        });

        RxUtil.click(recipientsTV).subscribe(o -> {
            IntentBuilder.Builder().setClass(getActivity(),AddressAddActivity.class)
                    .putExtra(IntentBuilder.KEY_TYPE,AddressAddActivity.TYPE_RECIPIENTS)
                    .putExtra(IntentBuilder.KEY_DATA,recipientsEntity)
                    .startActivity(this,ORDER_RECIPIENTS_REQUEST);
        });

        RxUtil.click(confirmBtn).subscribe(o -> {

            if(TextUtils.isEmpty(recipientsTV.getText())){
                ToastUtils.showLong(getActivity(),"请输入寄件人");
                return;
            }

            if(TextUtils.isEmpty(senderTV.getText())){
                ToastUtils.showLong(getActivity(),"请输入收件人");
                return;
            }

            if(TextUtils.isEmpty(goodsInfoTV.getText())){
                ToastUtils.showLong(getActivity(),"请输入货物信息");
                return;
            }

            if(!protocolCB.isChecked()){
                ToastUtils.showLong(getActivity(),"请阅读并同意遵守《服务条款》");
                return;
            }

            IntentBuilder.Builder()
                    .putExtra(IntentBuilder.KEY_TYPE,OrderDetailFragment.TYPE_PRE_CREATE)
                    .putExtra(IntentBuilder.KEY_KEY1,senderEntity)
                    .putExtra(IntentBuilder.KEY_KEY2,recipientsEntity)
                    .putExtra(IntentBuilder.KEY_KEY3,goodsInfoEntity)
                    .putExtra(IntentBuilder.KEY_KEY,remarkET.getText().toString())
                    .putExtra(IntentBuilder.KEY_VALUE,codET.getText().toString())
                    .putExtra(IntentBuilder.KEY_KEY4,additionalCostET.getText().toString())
                    .startParentActivity(getBaseActivity(), OrderDetailFragment.class,ORDER_CONFIRM_REQUEST,true);
        });

        RxUtil.click(goodsLayout).subscribe(o -> {
            if(mDialog == null){
                mDialog = createSheet();
            }else {
                mDialog.show();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("货物收单");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ORDER_CONFIRM_REQUEST && resultCode == Activity.RESULT_OK){

            finish();
        }else if(requestCode == ORDER_SENDER_REQUEST && resultCode == Activity.RESULT_OK){

            senderEntity = data.getParcelableExtra(IntentBuilder.KEY_DATA);
            senderTV.setText(senderEntity.getSenderName()+" "+senderEntity.getSenderPhone()+"\n"+senderEntity.getSenderProvince()+senderEntity.getSenderCity()+senderEntity.getSenderArea()+senderEntity.getSenderAddr());
        }else if(requestCode == ORDER_RECIPIENTS_REQUEST && resultCode == Activity.RESULT_OK){

            recipientsEntity = data.getParcelableExtra(IntentBuilder.KEY_DATA);
            recipientsTV.setText(recipientsEntity.getReciverName()+" "+recipientsEntity.getReciverPhone()+"\n"+recipientsEntity.getReciverProvince()+recipientsEntity.getReciverCity()+recipientsEntity.getReciverArea()+recipientsEntity.getReciverAddr());
        }
    }

    private BottomSheetDialog createSheet(){
        View view = getLayoutInflater().inflate(R.layout.goods_add_dialog,null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        holder = new GoodsInfoViewHolder(view, bottomSheetDialog, fillGoodsInfo());

        bottomSheetDialog.setContentView(holder.itemView);
        bottomSheetDialog.show();
        return bottomSheetDialog;
    }

    private Action0 fillGoodsInfo(){

        return () -> {
            goodsInfoEntity = new GoodsInfoEntity();
            goodsInfoEntity.cargoName = holder.goodsNameTV.getText().toString();
            goodsInfoEntity.cargoWeight = holder.weightET.getText().toString();
            goodsInfoEntity.cargoAmount = holder.numET.getText().toString();
            goodsInfoEntity.cargoLong = holder.lengthET.getText().toString();
            goodsInfoEntity.cargoWide = holder.widthET.getText().toString();
            goodsInfoEntity.cargoHeight = holder.heightET.getText().toString();

            goodsInfoTV.setText(goodsInfoEntity.cargoName + " " + goodsInfoEntity.cargoAmount + "件");
        };
    }
}
