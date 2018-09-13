package com.zyf.ui.user.order;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.biz.base.BaseViewHolder;
import com.biz.util.IntentBuilder;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.zyf.driver.ui.R;
import com.zyf.model.entity.order.WebOrderEntity;
import com.zyf.ui.info.LicenseActivity;
import com.zyf.ui.map.MapRouteActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

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

    public void bindData(WebOrderEntity item){

        tvName.setText(item.getStartName());
        tvTime.setText(item.webInsDate);
        tvDistance.setText(item.webDistance+"公里");
        tvFromAddressDetail.setText(item.startAddr);
        tvToAddressDetail.setText(item.endAddr);
        tvPrice.setText(item.webCarriage+"元");

        RxUtil.click(btnTakingOrder).subscribe(o -> {

            IntentBuilder.Builder(getActivity(), MapRouteActivity.class)
                    .overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
                    .startActivity();
        });
    }
}
