package com.zywl.ui.user.store;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biz.base.BaseLiveDataFragment;
import com.biz.util.Lists;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zywl.model.UserModel;
import com.zywl.ui.R;
import com.zywl.ui.adapter.StoreAdapter;
import com.zywl.ui.bottomsheet.BottomSheetBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * Created by TCJK on 2018/5/29.
 */

public class StoreFragment extends BaseLiveDataFragment<StoreViewModel> {

    @BindView(R.id.tv_store)
    TextView storeTV;
    @BindView(R.id.btn)
    TextView btn;

    BottomSheetDialog dialog;

    StoreAdapter adapter;
    Unbinder unbinder;

    String storeName;
    String storeNum;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(StoreViewModel.class,StoreViewModel.class.getName(),true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setTitle("仓库信息");
        adapter = new StoreAdapter(getContext());
        adapter.setOnItemClickListener((adapter, view12, position) -> {
            // 12 绑定仓库
            mViewModel.bindStore(this.adapter.getItem(position));
        });

        mViewModel.getStoreList();
        mViewModel.getStoreLiveData().observe(this, list -> {
            adapter.setNewData(list);
        });

        if(TextUtils.isEmpty(UserModel.getInstance().getStoreName())){

            storeTV.setText("");
            btn.setText("绑定仓库");

        }else {

            storeTV.setText(UserModel.getInstance().getStoreName());
            btn.setText("更换仓库");
        }

        RxUtil.click(btn).subscribe(o -> {

            dialog = BottomSheetBuilder.createBottomSheet(getContext(),adapter);
        });

        mViewModel.getBindStoreLiveData().observe(this, storeEntity -> {
            if(storeEntity!=null){
                ToastUtils.showLong(getActivity(),"绑定成功！");
                UserModel.getInstance().getUserEntity().storageName = storeEntity.getStorageName();
                UserModel.getInstance().getUserEntity().salesmanStorage = storeEntity.getStorageNum();
                UserModel.getInstance().setUserEntity(UserModel.getInstance().getUserEntity());
                dialog.dismiss();
                storeTV.setText(UserModel.getInstance().getUserEntity().storageName);
                btn.setText("更换仓库");
            }
        });
    }
}
