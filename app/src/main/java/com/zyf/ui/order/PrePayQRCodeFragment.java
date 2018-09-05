package com.zyf.ui.order;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.biz.base.BaseLiveDataFragment;
import com.biz.util.IntentBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zyf.event.UserOrderEvent;
import com.zyf.driver.ui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * Created by TCJK on 2018/4/3.
 */

public class PrePayQRCodeFragment extends BaseLiveDataFragment<PrePayViewModel> {

    Unbinder unbinder;

    @BindView(R.id.imageView)
    ImageView qrCodeIV;
    @BindView(R.id.price_tv)
    TextView priceTV;

    String orderNum;
    String type;
    float inputPrice;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(PrePayViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pre_pay_qrcode, container, false);

        orderNum = getActivity().getIntent().getStringExtra(IntentBuilder.KEY_ID);
        type = getActivity().getIntent().getStringExtra(IntentBuilder.KEY_TYPE);
        inputPrice = getActivity().getIntent().getFloatExtra(IntentBuilder.KEY_VALUE,0f);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(R.string.text_scan_pay);

        mViewModel.prePayInfo(orderNum,type,inputPrice);
        mViewModel.getPrePayLiveData().observe(this, o -> {

            if(o!=null && !TextUtils.isEmpty(o.getUrl())){

                Glide.with(this).load(getString(R.string.qrcode_pre_url) + o.getUrl())
                        .apply(RequestOptions.centerInsideTransform())
                        .into(qrCodeIV);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().post(new UserOrderEvent());
    }

}
