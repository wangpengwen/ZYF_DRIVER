package com.zyf.ui.user.order;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.biz.base.BaseViewHolder;
import com.biz.util.RxUtil;
import com.zyf.driver.ui.R;
import com.zyf.model.entity.order.WebOrderEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TCJK on 2018/5/30.
 */

public class WebOrderHolder extends BaseViewHolder {

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
//    @BindView(R.id.tv_sender)
//    TextView tvSender;
    @BindView(R.id.tv_to_address_detail)
    TextView tvToAddressDetail;
//    @BindView(R.id.tv_recipients)
//    TextView tvRecipients;
    @BindView(R.id.btn_taking_order)
    Button btnTakingOrder;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    public WebOrderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bindData(String type,WebOrderEntity item,WebOrderViewModel viewModel){

        switch (type){

            case WebOrderListFragment.TYPE_FIRST:
            case WebOrderListFragment.TYPE_LAST:
                tvOrderType.setText("即时订单");
                break;
            case UserOrderListFragment.TYPE_FINISHED:
                tvOrderType.setText("完成订单");
                break;
            case UserOrderListFragment.TYPE_UNFINISHED:
                tvOrderType.setText("未完成订单");
                break;
        }

        if(item.getWebIsLast() == 1){
            //最后一公里
            tvName.setText(item.getEndName());
            tvTime.setText(item.getEndPhone());
        }else {
            //第一公里
            tvName.setText(item.getStartName());
            tvTime.setText(item.startPhone);
        }
        tvDistance.setText(item.webDistance+"公里");
        tvFromAddressDetail.setText(item.startAddr);
        tvToAddressDetail.setText(item.endAddr);
        tvPrice.setText(item.webCarriage+"元");

        if(viewModel!=null){
            btnTakingOrder.setVisibility(View.VISIBLE);
            RxUtil.click(btnTakingOrder).subscribe(o -> {
                viewModel.takingWebOrder(item);
            });
        }else {
            btnTakingOrder.setVisibility(View.GONE);
        }
    }
}
