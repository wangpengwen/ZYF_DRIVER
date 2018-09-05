package com.zyf.ui.authentication;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biz.base.BaseLiveDataFragment;
import com.biz.util.LogUtil;
import com.biz.util.RxUtil;
import com.example.neolixlibrary.NeolixIdRead;
import com.zyf.driver.ui.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by TCJK on 2018/5/28.
 */

public class AuthenticationFragment extends BaseLiveDataFragment {

    @BindView(R.id.tv_nfc_tip)
    View tvNFCtip;

    Unbinder unbinder;

    private NeolixIdRead idRead;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        initViewModel(HomeViewModel.class,HomeViewModel.class.getName(),true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_authentication, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setTitle("实名认证");

        NeolixIdRead.returnListing myListing = new NeolixIdRead.returnListing() {
            @Override
            public void reStatus(String status) {
                LogUtil.print(status);
            }

            @Override
            public void reInfo(HashMap<String, String> map, Bitmap bitmap) {
                LogUtil.print(map.toString());
            }
        };

        idRead = new NeolixIdRead(getActivity(),myListing);

        RxUtil.click(tvNFCtip).subscribe(o -> {
            idRead.validNfc();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        idRead.disconnectDevice();
    }
}
