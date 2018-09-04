package com.zywl.ui.authentication;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.biz.base.BaseLiveDataFragment;
import com.biz.util.IntentBuilder;
import com.biz.util.LogUtil;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.biz.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.neolixlibrary.NeolixIdRead;
import com.zywl.ui.R;
import com.zywl.ui.goods.GoodsAcquiringFragment;
import com.zywl.ui.goods.GoodsListFragment;
import com.zywl.ui.home.HomeFragment;
import com.zywl.ui.home.HomeViewModel;
import com.zywl.ui.scan.ScanActivity;
import com.zywl.widget.GridSpacingItemDecoration;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

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
