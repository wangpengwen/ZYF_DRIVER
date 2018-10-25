package com.zyf.ui.order;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.biz.base.BaseLiveDataFragment;
import com.biz.util.IntentBuilder;
import com.zyf.driver.ui.R;
import com.zyf.model.entity.order.WebOrderEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by TCJK on 2018/5/30.
 */

public class OrderDetailFragment extends BaseLiveDataFragment<OrderViewModel> {

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(OrderViewModel.class, OrderViewModel.class.getName(), true);
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

        initView();

        orderId = getActivity().getIntent().getStringExtra(IntentBuilder.KEY_ORDER_ID);
        request(orderId);

        mViewModel.getOrderDetailLiveData().observe(this, orderDetailEntity -> {
            if(orderDetailEntity!=null){

                initView();
                initData(orderDetailEntity);
            }
        });
    }

    private void initView(){

            orderIDLayout.setVisibility(View.VISIBLE);
    }

    private void initData(WebOrderEntity webOrderEntity){

        orderIDTV.setText(orderId);

        tvSender.setText(webOrderEntity.getStartName());
        tvSenderContact.setText(webOrderEntity.getStartPhone());
        tvSenderAddress.setText(webOrderEntity.getStartAddr());

        tvReceipients.setText(webOrderEntity.getEndName());
        tvReceipientsContact.setText(webOrderEntity.getEndPhone());
        tvReceipientsAddress.setText(webOrderEntity.getEndAddr());

        tvGoodsName.setText(webOrderEntity.cargoName);
        tvGoodsNum.setText(webOrderEntity.cargoAmount+"");
    }

    private void request(String orderNum){
        mViewModel.requestOrder(orderNum);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void error(int code, String error) {
        super.error(code, error);
        setProgressVisible(false);
    }
}
