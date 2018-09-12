package com.zyf.ui.info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.biz.base.BaseLiveDataActivity;
import com.biz.util.IntentBuilder;
import com.zyf.driver.ui.R;
import com.zyf.ui.authentication.AuthenticationActivity;

/**
 * Created by ltxxx on 2018/9/9.
 */

public class LicenseActivity extends BaseLiveDataActivity<ValidateViewModel> {

    LicenseFragment licenseFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar_layout);
//        findViewById(R.id.line).setVisibility(View.GONE);
        initViewModel(ValidateViewModel.class,false,true);
//        mToolbar.setTitle("实名认证");

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_holder, licenseFragment =new LicenseFragment(),
                        LicenseFragment.class.getName())
//                .add(R.id.frame_holder, mShortcutLoginFragment =new ShortcutLoginFragment(),
//                        ShortcutLoginFragment.class.getName())
//                .hide(mPasswordLoginFragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        IntentBuilder.Builder(this, AuthenticationActivity.class)
                .addFlag(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                .overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
                .finish(this)
                .startActivity();
    }

}
