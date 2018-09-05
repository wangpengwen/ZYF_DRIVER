package com.zyf.ui.user;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biz.base.BaseLiveDataFragment;
import com.zyf.driver.ui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by TCJK on 2018/5/29.
 */

public class UserInfoFragment extends BaseLiveDataFragment<UserViewModel> {

    Unbinder unbinder;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_id_card)
    TextView tvIdCard;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_inviter_mobile)
    TextView tvInviterMobile;
    @BindView(R.id.tv_company)
    TextView tvCompany;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(UserViewModel.class, UserViewModel.class.getName(), true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("个人信息");

        mViewModel.userInfo();
        mViewModel.getInfoLiveData().observe(this, userEntity -> {

            tvName.setText(userEntity.salesmanRealName);
            tvIdCard.setText("");
            tvCity.setText("");
            tvInviterMobile.setText("");
            tvCompany.setText(userEntity.salesmanComnum);
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
