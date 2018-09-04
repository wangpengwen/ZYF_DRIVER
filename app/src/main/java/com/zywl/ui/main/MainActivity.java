package com.zywl.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import com.biz.base.BaseLiveDataActivity;
import com.biz.base.FragmentAdapter;
import com.biz.util.ActivityStackManager;
import com.biz.util.IntentBuilder;
import com.biz.util.Lists;
import com.biz.util.RxUtil;
import com.biz.widget.BottomNavigationViewEx;
import com.zywl.ui.R;
import com.zywl.ui.hangye.HangyeFragment;
import com.zywl.ui.home.HomeFragment;
import com.zywl.ui.home.adv.AdvertisingFragment;
import com.zywl.ui.user.UserFragment;
import com.zywl.ui.user.achievement.AchievementFragment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends BaseLiveDataActivity implements ActivityStackManager.ActivityStackMain {

    private ViewPager mViewPager;
    BottomNavigationViewEx mBottomNavigationView;

    View advBtn;

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

        advBtn = findViewById(R.id.btn_ad);
        RxUtil.click(advBtn).subscribe(o -> {
            IntentBuilder.Builder().startParentActivity(this.getActivity(), HangyeFragment.class,true);
        });
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
