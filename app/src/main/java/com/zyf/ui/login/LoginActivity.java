package com.zyf.ui.login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.biz.base.BaseLiveDataActivity;
import com.biz.util.DialogUtil;
import com.biz.util.IntentBuilder;
import com.biz.util.ToastUtils;
import com.zyf.driver.ui.R;
import com.zyf.ui.main.MainActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseLiveDataActivity<LoginViewModel> {

    Fragment mPasswordLoginFragment;
    private boolean isLoginOut=false;
    protected Boolean isFirst = true;

    private CompositeSubscription mSubscription;

    public static final void goLogin(Context context) {
        IntentBuilder.Builder().setClass(context, LoginActivity.class)
                .putExtra(IntentBuilder.KEY_BOOLEAN, true)
                .overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
                .startActivity();
    }

    public static final void goLoginOut(Context view,String message) {
        IntentBuilder.Builder().setClass(view, LoginActivity.class)
                .putExtra(IntentBuilder.KEY_BOOLEAN, true)
                .putExtra(IntentBuilder.KEY_BOOLEAN_LOGIN_OUT,true)
                .putExtra(IntentBuilder.KEY_TAG,message)
                .overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
                .startActivity();
    }
    public static final void goLoginOut(Context view) {
        IntentBuilder.Builder().setClass(view, LoginActivity.class)
                .putExtra(IntentBuilder.KEY_BOOLEAN, true)
                .putExtra(IntentBuilder.KEY_BOOLEAN_LOGIN_OUT,true)
                .overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
                .startActivity();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_activity_login);
        setContentView(R.layout.activity_with_toolbar_layout);
        mSubscription = new CompositeSubscription();

        mAppBarLayout.setVisibility(View.GONE);
        findViewById(R.id.line).setVisibility(View.GONE);
        initViewModel(LoginViewModel.class,false,true);
        isLoginOut=getIntent().getBooleanExtra(IntentBuilder.KEY_BOOLEAN_LOGIN_OUT,false);
//        mToolbar.getMenu().add(0, 0, 0, getResources().getString(R.string.text_register)).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        mToolbar.setOnMenuItemClickListener(item -> {
//            if (item.getItemId()==0){
//                IntentBuilder.Builder().startParentActivity(getActivity(), RegisterPhoneVerifyFragment.class,REQUEST_REGISTER);
//            }
//            return false;
//        });
//        mToolbar.setNavigationIcon(R.drawable.vector_close);
//        mToolbar.setNavigationOnClickListener(v -> {
//            onBackPressed();
//        });
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_holder, mPasswordLoginFragment =new PasswordLoginFragment(),
                        PasswordLoginFragment.class.getName())
                .commitAllowingStateLoss();
        mViewModel.getLoginStatus().observe(this, this::loginResponse);
        mViewModel.loadHisUser();

        String message=getIntent().getStringExtra(IntentBuilder.KEY_TAG);
        if(!TextUtils.isEmpty(message)){
            DialogUtil.createDialogView(this,message);
        }
    }
    private void loginResponse(boolean b){
        if (b) {
            //IntentBuilder.Builder().startParentActivity(getActivity(), TestFragment.class);
            startActivity(new Intent(getActivity(), MainActivity.class));
            finish();
        }
        setProgressVisible(false);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==REQUEST_REGISTER&&resultCode==RESULT_OK){
//            mViewModel.loadHisUser();
//        }
//    }

    @Override
    public void onBackPressed() {
        for (int i = fragmentBackHelperList.size() - 1; i > -1; i--) {
            if (fragmentBackHelperList.get(i).onBackPressed()) return;
        }
        if (isFirst) {
            ToastUtils.showLong(this, R.string.toast_back_again);
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

