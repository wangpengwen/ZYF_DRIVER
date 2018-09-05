package com.zyf.ui.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.biz.base.BaseViewHolder;
import com.biz.util.RxUtil;
import com.zyf.driver.ui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.functions.Action1;

/**
 * Title: LoginViewHolder
 * Description:
 * Copyright:Copyright(c)2016
 * Company:博智维讯信息技术有限公司
 * CreateTime:06/12/2017  17:10
 *
 * @author johnzheng
 * @version 1.0
 */

public class LoginViewHolder extends BaseViewHolder {

    @BindView(R.id.username)
    EditText editUsername;
    @BindView(R.id.password)
    EditText editPwd;
    @BindView(R.id.sign_in_button)
    Button btnLogin;

    Unbinder unbinder;

    private boolean isPasswordShown = false;
    public LoginViewHolder(View itemView) {
        super(itemView);
        unbinder = ButterKnife.bind(this, itemView);
        btnLogin.setEnabled(false);

//        editUsername.setText("sysuser");
//        editPwd.setText("111111");
    }

    public void setEditUsernameChangeListener(){
        Observable.combineLatest(RxUtil.textChanges(editUsername), RxUtil.textChanges(editPwd),
                (username, pwd)->{
                    return !TextUtils.isEmpty(getUsername()) && !TextUtils.isEmpty(getPwd());
                }).subscribe(b->{
                    btnLogin.setEnabled(b);
        });

    }


//    public void setForgetPwdListener(){
//        RxUtil.click(findViewById(R.id.btn_forget_pwd)).subscribe(s->{
//                    IntentBuilder.Builder().startParentActivity(getActivity(), PwdForgetFragment.class);
//                }
//        );
//    }
//
//    public void setCodeListener(Action1<Object> status){
//        RxUtil.click(verificationCode).subscribe(s->{
//            Observable.just(new Object()).subscribe(status);
//        });
//    }
//
//
//    public CustomCountDownTimer getDownTimer() {
//        return mDownTimer;
//    }

    public String getUsername(){
        return editUsername.getText().toString();
    }

    public String getPwd(){
        return editPwd.getText().toString();
    }



    public void setLoginListener(Action1<Object> login){
        RxUtil.click(btnLogin).subscribe(login);
    }

//    public void setShowPwdListener(){
//        if (showPassword!=null)
//            RxUtil.click(showPassword).subscribe(this::showPassword);
//    }

    public void onDestroy() {
        unbinder.unbind();
    }


//    private void showPassword(Object o) {
//        if (isPasswordShown) {
//            isPasswordShown = false;
//            //隐藏密码
//            editPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
//            editPwd.setSelection(editPwd.getText().length());
//            showPassword.setImageResource(R.drawable.vector_password_unvisible_black);
//            getFocus(editPwd);
//        } else {
//            isPasswordShown = true;
//            //显示密码
//            editPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//            editPwd.setSelection(editPwd.getText().length());
//            showPassword.setImageResource(R.drawable.vector_password_visible);
//            getFocus(editPwd);
//
//        }
//    }

    private void verifyIsCanClickButton() {
        if (!TextUtils.isEmpty(editUsername.getText().toString()) && !TextUtils.isEmpty(editPwd.getText().toString())) {
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setEnabled(false);
        }
    }

    /**
     * 控件获取焦点
     */
    private void getFocus(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }
}
