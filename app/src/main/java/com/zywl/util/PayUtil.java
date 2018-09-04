package com.zywl.util;

import android.app.Activity;
import android.support.design.widget.BottomSheetDialog;

import com.biz.util.IntentBuilder;
import com.zywl.event.PayTypeCodeEvent;
import com.zywl.event.UserOrderEvent;
import com.zywl.model.OrderModel;
import com.zywl.ui.adapter.PayTypeAdapter;
import com.zywl.ui.bottomsheet.BottomSheetBuilder;
import com.zywl.ui.order.OrderDetailFragment;
import com.zywl.ui.order.PrePayQRCodeFragment;

import de.greenrobot.event.EventBus;
import rx.functions.Action1;

/**
 * Created by TCJK on 2018/4/14.
 */

public class PayUtil {

    public static final int PAY_QRCODE_REQUEST = 9001;

    public static void createBottomPayDialog(Activity context, String id,float price){

        PayTypeAdapter adapter = new PayTypeAdapter(context);
        BottomSheetDialog dialog = BottomSheetBuilder.createBottomSheet(context,adapter);
        dialog.show();
        adapter.setOnItemClickListener((adapter1, view, position) -> {

            String type = "";

            switch (position){

                case 0:

                    // 微信支付
                    type = OrderModel.PAY_TYPE_WXPAY;
                    break;
                case 1:

                    // 支付宝支付
                    type = OrderModel.PAY_TYPE_ALIPAY;
                    break;
                case 2:

                    // 平安支付
                    type = OrderModel.PAY_TYPE_PINGAN;
                    break;
                case 3:

                    // 货到付款
                    type = OrderModel.PAY_TYPE_COD;

                    // 发送更新订单状态请求
                    EventBus.getDefault().post(new PayTypeCodeEvent());
                    dialog.dismiss();
                    return;
            }

            dialog.dismiss();
            IntentBuilder.Builder()
                    .putExtra(IntentBuilder.KEY_ID,id)
                    .putExtra(IntentBuilder.KEY_TYPE, type)
                    .putExtra(IntentBuilder.KEY_VALUE, price)
                    .startParentActivity(context, PrePayQRCodeFragment.class,PAY_QRCODE_REQUEST,true);
        });
    }
}
