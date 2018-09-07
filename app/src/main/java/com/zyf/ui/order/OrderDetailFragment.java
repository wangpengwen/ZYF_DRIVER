package com.zyf.ui.order;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.biz.base.BaseLiveDataFragment;
import com.biz.base.FragmentBackHelper;
import com.biz.util.DialogUtil;
import com.biz.util.IntentBuilder;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.zyf.event.PayTypeCodeEvent;
import com.zyf.event.UserOrderEvent;
import com.zyf.model.entity.order.GoodsInfoEntity;
import com.zyf.model.entity.order.OrderRecipientsEntity;
import com.zyf.model.entity.order.OrderSenderEntity;
import com.zyf.driver.ui.R;
import com.zyf.util.PayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import rx.functions.Action1;

/**
 * Created by TCJK on 2018/5/30.
 */

public class OrderDetailFragment extends BaseLiveDataFragment<OrderViewModel> implements FragmentBackHelper {

    public static final int TYPE_PRE_CREATE = 9001;
    public static final int TYPE_REVIEW = 9002;

    int type;
    String orderId;

    Unbinder unbinder;

    @BindView(R.id.order_id_layout)
    View orderIDLayout;
    @BindView(R.id.tv_order_id)
    TextView orderIDTV;

    @BindView(R.id.tv_sender)
    TextView tvSender;
    @BindView(R.id.tv_sender_contact)
    TextView tvSenderContact;
    @BindView(R.id.tv_sender_address)
    TextView tvSenderAddress;
    @BindView(R.id.tv_receipients)
    TextView tvReceipients;
    @BindView(R.id.tv_receipients_contact)
    TextView tvReceipientsContact;
    @BindView(R.id.tv_receipients_address)
    TextView tvReceipientsAddress;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;
    @BindView(R.id.order_price_layout)
    View orderPriceLayout;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.et_order_price_input)
    EditText etOrderPrice;
    @BindView(R.id.tv_additional_cost)
    TextView addtionalCostTV;

    @BindView(R.id.btn_pay)
    View payBtn;
    @BindView(R.id.btn_update)
    View updateBtn;
    @BindView(R.id.btn_cancel)
    View cancelBtn;
    @BindView(R.id.btn_confirm)
    View confirmBtn;
    @BindView(R.id.btn_print)
    View printBtn;
    @BindView(R.id.tv_cod)
    TextView codTV;
    @BindView(R.id.tv_remark)
    TextView remarkTV;

    OrderSenderEntity senderEntity;
    OrderRecipientsEntity recipientsEntity;
    GoodsInfoEntity goodsInfoEntity;
    String remarkStr;
    String codPriceStr;
    float orderPrice;
    String addtionalCostPrice;

    String isPay;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(OrderViewModel.class, OrderViewModel.class.getName(), true);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setTitle("订单详情");

        type = getActivity().getIntent().getIntExtra(IntentBuilder.KEY_TYPE, 0);

        initView();

        if (type == TYPE_PRE_CREATE) {

            senderEntity = getActivity().getIntent().getParcelableExtra(IntentBuilder.KEY_KEY1);
            recipientsEntity = getActivity().getIntent().getParcelableExtra(IntentBuilder.KEY_KEY2);
            goodsInfoEntity = getActivity().getIntent().getParcelableExtra(IntentBuilder.KEY_KEY3);
            remarkStr = getActivity().getIntent().getStringExtra(IntentBuilder.KEY_KEY);
            codPriceStr = getActivity().getIntent().getStringExtra(IntentBuilder.KEY_VALUE);
            addtionalCostPrice = getActivity().getIntent().getStringExtra(IntentBuilder.KEY_KEY4);

            initData();
        }else {

            orderId = getActivity().getIntent().getStringExtra(IntentBuilder.KEY_ORDER_ID);
            request(orderId);
        }
        RxUtil.click(payBtn).subscribe(o -> {

            if(orderPrice > 0){

                PayUtil.createBottomPayDialog(getActivity(),orderId,0);
            }else {

                float inputPrice;
                try {

                    inputPrice = Float.parseFloat(etOrderPrice.getText().toString());
                }catch (Exception e){
                    ToastUtils.showLong(getActivity(),"请输入正确的订单金额");
                    return;
                }
                PayUtil.createBottomPayDialog(getActivity(),orderId,inputPrice);
            }
        });

        RxUtil.click(updateBtn).subscribe(o -> {
            finish();
        });

        RxUtil.click(confirmBtn).subscribe(o -> {

            DialogUtil.createDialogView(getActivity(), "是否核实完毕确认该订单？", (dialog, which) -> {
                dialog.dismiss();
            }, R.string.text_cancel, (dialog, which) -> {
                // 创建订单
                setProgressVisible(true);
                mViewModel.createOrder(senderEntity, recipientsEntity, goodsInfoEntity, remarkStr, codPriceStr,addtionalCostPrice);
            }, R.string.text_create_order);

        });

        RxUtil.click(cancelBtn).subscribe(o -> {
            getBaseActivity().setResult(Activity.RESULT_OK);
            finish();
        });

        RxUtil.click(printBtn).subscribe(o -> {
//            // 打印二维码
//            IntentBuilder.Builder().setClass(getActivity(), OrderQRCodeActivity.class)
//                    .putExtra(IntentBuilder.KEY_ORDER_ID,orderId)
//                    .startActivity();
        });

        mViewModel.getCreateOrderLiveData().observe(this, obj -> {
            setProgressVisible(false);
            if(obj!=null){
                ToastUtils.showLong(getActivity(),"订单创建成功！");
                orderId = obj.getOrderNum();
                request(obj.getOrderNum());
                getBaseActivity().setResult(Activity.RESULT_OK);
            }
        });

        mViewModel.getOrderDetailLiveData().observe(this, orderDetailEntity -> {
            if(orderDetailEntity!=null){

                type = TYPE_REVIEW;
                senderEntity = orderDetailEntity.getSender();
                recipientsEntity = orderDetailEntity.getReciver();
                goodsInfoEntity = orderDetailEntity.getCargo();
                isPay = orderDetailEntity.getOrderManifestState();
                remarkStr = orderDetailEntity.getOrderNotes();
                codPriceStr = orderDetailEntity.getOrderCod();
                orderPrice = orderDetailEntity.getCarriage();
                addtionalCostPrice = orderDetailEntity.getOrderAddedFee();
                initView();
                initData();
            }
        });

        mViewModel.getCodLiveData().observe(this, o -> {
            // cod成功
            if(o){
                request(orderId);
                EventBus.getDefault().post(new UserOrderEvent());
            }else {
                ToastUtils.showLong(getActivity(),"请求失败，请重试");
            }

        });

    }

    private void initView(){

        if (type == TYPE_PRE_CREATE) {
            orderIDLayout.setVisibility(View.GONE);

            updateBtn.setVisibility(View.VISIBLE);
            cancelBtn.setVisibility(View.VISIBLE);
            confirmBtn.setTag(View.VISIBLE);
            payBtn.setVisibility(View.GONE);
            printBtn.setVisibility(View.GONE);
            orderPriceLayout.setVisibility(View.GONE);
        } else {
            orderIDLayout.setVisibility(View.VISIBLE);
            orderPriceLayout.setVisibility(View.VISIBLE);

            updateBtn.setVisibility(View.GONE);
            cancelBtn.setVisibility(View.GONE);
            confirmBtn.setVisibility(View.GONE);

            if("1".equals(isPay) || "4".equals(isPay)){

                // 已支付  显示打印二维码按钮
                payBtn.setVisibility(View.GONE);
                printBtn.setVisibility(View.VISIBLE);
            }else if("0".equals(isPay)){

                //未支付  显示支付按钮
                payBtn.setVisibility(View.VISIBLE);
                printBtn.setVisibility(View.GONE);
            }
        }
    }

    private void initData(){

        orderIDTV.setText(orderId);

        tvSender.setText(senderEntity.getSenderName());
        tvSenderContact.setText(senderEntity.getSenderPhone());
        tvSenderAddress.setText(senderEntity.getSenderProvince()+senderEntity.getSenderCity()+senderEntity.getSenderArea()+senderEntity.getSenderAddr());

        tvReceipients.setText(recipientsEntity.getReciverName());
        tvReceipientsContact.setText(recipientsEntity.getReciverPhone());
        tvReceipientsAddress.setText(recipientsEntity.getReciverProvince()+recipientsEntity.getReciverCity()+recipientsEntity.getReciverArea()+recipientsEntity.getReciverAddr());

        tvGoodsName.setText(goodsInfoEntity.cargoName);
        tvGoodsNum.setText(goodsInfoEntity.cargoAmount);

        if(orderPrice > 0){

            tvOrderPrice.setVisibility(View.VISIBLE);
            etOrderPrice.setVisibility(View.GONE);
            tvOrderPrice.setText(orderPrice+"元");
        }else {

            tvOrderPrice.setVisibility(View.GONE);
            etOrderPrice.setVisibility(View.VISIBLE);
        }

        if(!TextUtils.isEmpty(codPriceStr))
            codTV.setText(codPriceStr+"元");
        if(!TextUtils.isEmpty(remarkStr))
            remarkTV.setText(remarkStr);
        if(!TextUtils.isEmpty(addtionalCostPrice))
            addtionalCostTV.setText(addtionalCostPrice+"元");
    }

    private void request(String orderNum){

        mViewModel.requestOrder(orderNum);
    }

    private void payTypeCOD(){

        if(orderPrice > 0){

            mViewModel.orderTypeCOD(orderId,0);
        }else {

            float inputPrice;
            try {

                inputPrice = Float.parseFloat(etOrderPrice.getText().toString());
            }catch (Exception e){

                return;
            }
            mViewModel.orderTypeCOD(orderId,inputPrice);
        }
    }

    public void onEventMainThread(UserOrderEvent event){

        request(orderId);
    }

    public void onEventMainThread(PayTypeCodeEvent event){

        payTypeCOD();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void error(int code, String error) {
        super.error(code, error);
        setProgressVisible(false);
    }

    @Override
    public boolean onBackPressed() {

        if("0".equals(isPay)){

            //未支付
            DialogUtil.createDialogView(getActivity(), "是否确认放弃支付返回主页面？未支付订单可在电子支付里继续支付", (dialog, which) -> {
                dialog.dismiss();
            }, R.string.text_cancel, (dialog, which) -> {
                finish();
            }, R.string.text_giveup_pay);
            return true;
        }
        return false;
    }
}
