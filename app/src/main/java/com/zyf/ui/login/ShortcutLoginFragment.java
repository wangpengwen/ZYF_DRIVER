package com.zyf.ui.login;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biz.base.BaseLazyFragment;
import com.biz.util.RxUtil;
import com.zyf.driver.ui.R;


/**
 *
 * Created by zanezhao on 2017/11/17 上午9:45.
 * @author: zanezhao
 * @version: V1.0
 */

public class ShortcutLoginFragment extends BaseLazyFragment<LoginViewModel> {


    LoginViewHolder mViewHolder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(LoginViewModel.class,false,false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.activity_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewHolder = new LoginViewHolder(view);

        mViewModel.getSmsLiveData().observe(this,o -> {
            setProgressVisible(false);
            mViewHolder.getDownTimer().start();
        });
        mViewHolder.setEditUsernameChangeListener();
//        mViewHolder.setForgetPwdListener();
        mViewHolder.setLoginListener(this::login);
        mViewHolder.setCodeListener(o->{
            mViewModel.setMobile(mViewHolder.getUsername());
            mViewModel.sendSms();});

//        RxUtil.click(findViewById(R.id.btn_login_change))
//                .subscribe(s->{
//                    Fragment fragment = getActivity().getSupportFragmentManager()
//                            .findFragmentByTag(this.getClass().getName());
//                    Fragment fragment1 = getActivity().getSupportFragmentManager()
//                            .findFragmentByTag(PasswordLoginFragment.class.getName());
//                    getActivity().getSupportFragmentManager().beginTransaction()
//                            .hide(fragment)
//                            .show(fragment1)
//                            .commitAllowingStateLoss();
//                    if(fragment1 instanceof BaseLazyFragment){
//                        ((BaseLazyFragment) fragment1).lazyLoad();
//                    }
//                });
        mViewModel.getHisLoginUserLiveData().observe(this,s->{
            if(!TextUtils.isEmpty(s)&& TextUtils.isEmpty(mViewHolder.editUsername.getText().toString())){
                mViewHolder.editUsername.setText(s);
            }
        });
//        mViewHolder.editPwd.setOnKeyListener((View v, int keyCode, KeyEvent event) -> {
//            if ((keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_ENTER)
//                    && event.getAction() == KeyEvent.ACTION_UP) {
//                v.clearFocus();
//                login(v);
//            }
//            return false;
//        });
    }

    @Override
    public void lazyLoad() {
        setTitle(R.string.title_activity_login);
    }

    private void login(Object o){
        setProgressVisible(true);
        mViewModel.setMobile(mViewHolder.getUsername());
        mViewModel.setSmsCode(mViewHolder.getPwd());
        mViewModel.smsLogin();
    }



    @Override
    public void onDestroyView() {
        mViewHolder.onDestroy();
        super.onDestroyView();
    }


}
