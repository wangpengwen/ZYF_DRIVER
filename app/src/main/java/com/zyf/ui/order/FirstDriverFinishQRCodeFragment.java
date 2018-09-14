package com.zyf.ui.order;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.biz.base.BaseLiveDataFragment;
import com.biz.util.DialogUtil;
import com.biz.util.IntentBuilder;
import com.biz.util.RxUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zyf.driver.ui.R;
import com.zyf.event.UserOrderEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * Created by TCJK on 2018/4/3.
 */

public class FirstDriverFinishQRCodeFragment extends BaseLiveDataFragment<OrderViewModel> {

    Unbinder unbinder;

    @BindView(R.id.imageView)
    ImageView qrCodeIV;
    @BindView(R.id.btn_validate)
    Button btn;

    String webDrvId;
    String type;
    float inputPrice;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(OrderViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_qrcode, container, false);

        webDrvId = getActivity().getIntent().getStringExtra(IntentBuilder.KEY_ID);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("扫码验收");

        RxUtil.click(btn).subscribe(o -> {
            DialogUtil.createDialogView(getActivity(), "是否确认验收完毕", (dialog, which) -> {
                dialog.dismiss();
            }, R.string.text_cancel, (dialog, which) -> {
                getActivity().setResult(Activity.RESULT_OK);
                finish();
            }, R.string.text_validate_finish);
        });

        mViewModel.firstDriverFinish(webDrvId);
        mViewModel.getFirstFinishLiveData().observe(this, url -> {

            if(!TextUtils.isEmpty(url)){

                Glide.with(this).load(url)
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
