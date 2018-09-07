package com.zyf.ui.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.biz.base.BaseLiveDataActivity;
import com.zyf.driver.ui.R;
import com.zyf.ui.vehicle.VehicleFragment;

import butterknife.ButterKnife;

/**
 * Created by ltxxx on 2018/9/7.
 */

public class ValidateActivity extends BaseLiveDataActivity<ValidateViewModel> {

    VehicleFragment vehicleFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar_layout);
//        findViewById(R.id.line).setVisibility(View.GONE);
        getLayoutInflater().inflate(R.layout.fragment_authentication, findViewById(R.id.frame_holder));
        initViewModel(ValidateViewModel.class);
//        mToolbar.setTitle("实名认证");

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_holder, vehicleFragment =new VehicleFragment(),
                        VehicleFragment.class.getName())
//                .add(R.id.frame_holder, mShortcutLoginFragment =new ShortcutLoginFragment(),
//                        ShortcutLoginFragment.class.getName())
//                .hide(mPasswordLoginFragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {

    }
}
