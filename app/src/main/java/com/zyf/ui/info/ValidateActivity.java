package com.zyf.ui.info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.biz.base.BaseLiveDataActivity;
import com.zyf.driver.ui.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ltxxx on 2018/9/7.
 */

public class ValidateActivity extends BaseLiveDataActivity<ValidateViewModel> {

    VehicleFragment vehicleFragment;

    protected Boolean isFirst = true;
    private CompositeSubscription mSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar_layout);
//        findViewById(R.id.line).setVisibility(View.GONE);
        initViewModel(ValidateViewModel.class,false,true);
//        mToolbar.setTitle("实名认证");

        mSubscription = new CompositeSubscription();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_holder, vehicleFragment =new VehicleFragment(),
                        VehicleFragment.class.getName())
//                .add(R.id.frame_holder, mShortcutLoginFragment =new ShortcutLoginFragment(),
//                        ShortcutLoginFragment.class.getName())
//                .hide(mPasswordLoginFragment)
                .commitAllowingStateLoss();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSubscription.clear();
    }

    @Override
    public void onBackPressed() {
        for (int i = fragmentBackHelperList.size() - 1; i > -1; i--) {
            if (fragmentBackHelperList.get(i).onBackPressed()) return;
        }
        if (isFirst) {
            showToast(getWindow().getDecorView(), R.string.toast_back_again);
            isFirst = false;
            mSubscription.add(Observable.just(1).delay(3500, TimeUnit.MILLISECONDS).subscribe(s -> {
                isFirst = true;
            }));
            return;
        }
        ActivityCompat.finishAffinity(this);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }
}
