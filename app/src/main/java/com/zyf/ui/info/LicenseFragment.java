package com.zyf.ui.info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.biz.base.BaseLiveDataFragment;
import com.biz.util.RxUtil;
import com.zyf.driver.ui.R;
import com.zyf.model.UserModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * Created by ltxxx on 2018/9/9.
 */

public class LicenseFragment extends BaseLiveDataFragment<ValidateViewModel> {

    @BindView(R.id.vehicle_info)
    TextView vehicleInfo;
    @BindView(R.id.id_card_front)
    RelativeLayout idCardFront;
    @BindView(R.id.id_card_oppsite)
    RelativeLayout idCardOppsite;
    @BindView(R.id.driver_license)
    RelativeLayout driverLicense;
    @BindView(R.id.vehicle_license)
    RelativeLayout vehicleLicense;
    @BindView(R.id.btn_commit)
    Button btnCommit;

    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(ValidateViewModel.class, ValidateViewModel.class.getName(), false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_license, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("驾照及行驶证");

        vehicleInfo.setText(UserModel.getInstance().getUserAddress());

        RxUtil.click(idCardFront).subscribe(o -> {

        });

        RxUtil.click(idCardOppsite).subscribe(o -> {

        });

        RxUtil.click(driverLicense).subscribe(o -> {

        });

        RxUtil.click(vehicleLicense).subscribe(o -> {

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
