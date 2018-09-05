package com.zyf.ui.login;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biz.base.BaseLazyFragment;
import com.biz.http.ParaConfig;
import com.zyf.driver.ui.R;

/**
 * Created by zanezhao on 2017/11/17 上午9:45.
 *
 * @author: zanezhao
 * @version: V1.0
 */
public class PasswordLoginFragment extends BaseLazyFragment<LoginViewModel> {


    LoginViewHolder mViewHolder;

    public PasswordLoginFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(LoginViewModel.class,false,false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().getWindow().setBackgroundDrawableResource(R.color.white);
        mViewHolder = new LoginViewHolder(view);
        mViewHolder.setEditUsernameChangeListener();
//        mViewHolder.setForgetPwdListener();
        mViewHolder.setLoginListener(this::login);
//        mViewHolder.setShowPwdListener();
        setTitle(R.string.title_activity_login);
        mViewModel.getHisLoginUserLiveData().observe(this,s->{
            if(!TextUtils.isEmpty(s)&&TextUtils.isEmpty(mViewHolder.editUsername.getText().toString())){
                mViewHolder.editUsername.setText(s);
            }
        });
//        if(BuildConfig.DEBUG) {
//            mViewHolder.editUsername.setText(R.string.debug_login);
//            mViewHolder.editPwd.setText(R.string.debug_pwd);
//        }
    }

    @Override
    public void lazyLoad() {
        setTitle(R.string.title_activity_login);
    }

    private void login(Object o){
        setProgressVisible(true);
        ParaConfig.initialize();
        mViewModel.login(mViewHolder.getUsername(), mViewHolder.getPwd());

//        startActivity(new Intent(getActivity(), MainActivity.class));
//        finish();

//        RxPermissions rxPermissions = new RxPermissions(baseActivity);
//        rxPermissions.request(Manifest.permission.READ_PHONE_STATE).subscribe(granted -> {
//            // 在android 6.0之前会默认返回true
//            if (granted) {
//                // 已经获取权限
//
//                if (ActivityCompat.checkSelfPermission(baseActivity, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
//                                   }
//            } else {
//                // 未获取权限
//                ToastUtils.showLong(baseActivity, "拨打电话无权限");
//            }
//        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mViewHolder.onDestroy();
    }


}
