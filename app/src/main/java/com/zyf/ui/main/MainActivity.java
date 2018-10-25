package com.zyf.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.biz.base.BaseLiveDataActivity;
import com.biz.base.FragmentAdapter;
import com.biz.util.ActivityStackManager;
import com.biz.util.DialogUtil;
import com.biz.util.IntentBuilder;
import com.biz.util.Lists;
import com.biz.widget.BottomNavigationViewEx;
import com.zyf.driver.ui.R;
import com.zyf.model.UserModel;
import com.zyf.ui.home.HomeFragment;
import com.zyf.ui.info.ValidateActivity;
import com.zyf.ui.info.ValidateViewModel;
import com.zyf.ui.map.MapRouteActivity;
import com.zyf.ui.user.UserFragment;
import com.zyf.ui.user.order.WebOrderViewModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends BaseLiveDataActivity<ValidateViewModel> implements ActivityStackManager.ActivityStackMain {

    private ViewPager mViewPager;
    BottomNavigationViewEx mBottomNavigationView;

    private WebOrderViewModel webOrderViewModel;

    protected Boolean isFirst = true;
    private CompositeSubscription mSubscription;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel(ValidateViewModel.class, ValidateViewModel.class.getName(), true);
        webOrderViewModel = registerViewModel(WebOrderViewModel.class,false,false);

        mSubscription = new CompositeSubscription();

        mBottomNavigationView = (BottomNavigationViewEx) findViewById(R.id.design_navigation_view);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mBottomNavigationView = findViewById(R.id.design_navigation_view);
        mBottomNavigationView.enableAnimation(false);
        mBottomNavigationView.enableItemShiftingMode(false);
        mBottomNavigationView.enableShiftingMode(false);
        mBottomNavigationView.setItemIconTintList(null);

        mViewPager = findViewById(R.id.viewpager);
        List<String> titles = Lists.newArrayList(
                getString(R.string.action_home),
                getString(R.string.action_mine));

        List<Fragment> fragments = Lists.newArrayList(
                new HomeFragment(),
                new UserFragment());

        mViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments, titles));
        mBottomNavigationView.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setAnimationCacheEnabled(false);

        webOrderViewModel.getUnfinishOrderLiveData().observe(this, webOrderEntity -> {

            DialogUtil.createDialogView(getActivity(), R.string.text_recover_order,
                    ((dialog, which) -> {
                        dialog.dismiss();
                    }), R.string.text_cancel,
                    ((dialog, which) -> {
                        IntentBuilder.Builder(getActivity(), MapRouteActivity.class)
                                .putExtra(IntentBuilder.KEY_DATA,webOrderEntity)
                                .overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
                                .startActivity();
                    }), R.string.text_recover);
        });

        if(!UserModel.getInstance().isValidate()){

            mViewModel.getDriverInfo();
            mViewModel.getDriverInfoLiveData().observe(this, o -> {

                UserModel.getInstance().setUserEntity(o);

                if(!UserModel.getInstance().isValidate()){
                    //未验证完，进入验证activity
                    IntentBuilder.Builder(this, ValidateActivity.class)
                            .addFlag(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                            .overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
                            .startActivity();
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(UserModel.getInstance().isValidate())
            webOrderViewModel.recoverOrder();
    }

    public void onBackPressed() {
        for (int i = fragmentBackHelperList.size() - 1; i > -1; i--) {
            if (fragmentBackHelperList.get(i).onBackPressed()) return;
        }
        if (isFirst) {
            showToast(mViewPager, R.string.toast_back_again);
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

    @Override
    protected void onPause() {
        super.onPause();
        mSubscription.clear();
    }

    @Override
    public void error(String error) {
        setProgressVisible(false);
    }
}
