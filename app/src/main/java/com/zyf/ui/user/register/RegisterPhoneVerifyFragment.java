package com.zyf.ui.user.register;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.biz.base.BaseLiveDataFragment;
import com.biz.util.ToastUtils;
import com.biz.widget.CustomCountDownTimer;
import com.biz.widget.MaterialEditText;
import com.zyf.driver.ui.R;
import com.zyf.ui.login.LoginViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 *
 * Created by zanezhao on 2017/11/17 上午9:38.
 * @author: zanezhao
 * @version: V1.0
 */
public class RegisterPhoneVerifyFragment extends BaseLiveDataFragment<RegisterPhoneVerifyViewModel> implements TextWatcher {
    @BindView(R.id.edit_phone)
    MaterialEditText editPhone;
    @BindView(R.id.btn_code)
    TextView btnCode;
    @BindView(R.id.edit_code)
    MaterialEditText editCode;
    @BindView(R.id.btn_verify)
    Button btnVerify;
    Unbinder unbinder;
    CustomCountDownTimer mDownTimer ;

    LoginViewModel loginViewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(RegisterPhoneVerifyViewModel.class);
        loginViewModel = registerViewModel(LoginViewModel.class,false,false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regist_phone_verify_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(R.string.text_register);
        btnVerify.setEnabled(false);
        editPhone.addTextChangedListener(this);
        editCode.addTextChangedListener(this);
        mDownTimer = new CustomCountDownTimer(getActivity(),
                btnCode, R.string.text_send_code, R.string.btn_resend_count, 60000, 1000);
        mViewModel.getSmsLiveData().observe(this,o->{
            setProgressVisible(false);
            mDownTimer.start();
        });
        mViewModel.getRegisterLiveData().observe(this,userEntity -> {
            //注册成功
            setProgressVisible(false);
            getActivity().setResult(Activity.RESULT_OK);
            ToastUtils.showLong(getContext(), "注册成功！");

//            loginViewModel.login(mViewModel.mobile,mViewModel.mobile.substring(mViewModel.mobile.length()-6));
        });

        loginViewModel.getLoginStatus().observe(this, aBoolean -> {
            finish();
//            EventBus.getDefault().post(new LoginResponseEvent(true));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_code, R.id.btn_verify, R.id.hasAccount})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_verify:
                dismissKeyboard();
                setProgressVisible(true);
                mViewModel.setSmsCode(editCode.getText().toString());
                mViewModel.setMobile(editPhone.getText().toString());
                mViewModel.register();
                break;
            case R.id.hasAccount:
                finish();
                break;
            case R.id.btn_code:
                dismissKeyboard();
                if (TextUtils.isEmpty(editPhone.getText().toString())) {
                    getBaseActivity().showToast(editPhone, R.string.toast_input_phone);
                    return;
                }
                mViewModel.setMobile(editPhone.getText().toString());
                setProgressVisible(true);
                mViewModel.sendSms();
                break;
        }
    }

    private void verifyIsCanClickButton() {
        if (!TextUtils.isEmpty(editPhone.getText().toString()) && !TextUtils.isEmpty(editCode.getText().toString())) {
            btnVerify.setEnabled(true);
        } else {
            btnVerify.setEnabled(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        verifyIsCanClickButton();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
