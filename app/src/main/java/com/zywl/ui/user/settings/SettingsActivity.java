package com.zywl.ui.user.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.biz.base.BaseLiveDataActivity;
import com.biz.util.ActivityStackManager;
import com.zywl.model.UserModel;
import com.zywl.ui.R;
import com.zywl.ui.SettingsViewModel;
import com.zywl.ui.login.LoginActivity;
import com.zywl.util.VersionUtil;
import com.zywl.widget.SettingsItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Title: SettingsActivity
 * Description:
 * Copyright:Copyright(c)2016
 * Company:博智维讯信息技术有限公司
 * CreateTime:26/12/2017  14:33
 *
 * @author johnzheng
 * @version 1.0
 */

public class SettingsActivity extends BaseLiveDataActivity<SettingsViewModel> {
    @BindView(R.id.clearCacheBtn)
    SettingsItemView clearCacheBtn;
    @BindView(R.id.versionBtn)
    SettingsItemView versionBtn;
    @BindView(R.id.aboutBtn)
    SettingsItemView aboutBtn;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    Unbinder unbinder;
//    CompositeSubscription mSubscription;

//    ShareHelper mShareHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar_layout);
        getLayoutInflater().inflate(R.layout.fragment_settings, findViewById(R.id.frame_holder));
        unbinder = ButterKnife.bind(this);
        initViewModel(SettingsViewModel.class);
//        mSubscription = new CompositeSubscription();
        versionBtn.setSubTitleText(VersionUtil.getVersionName(getActivity()) + "");
        mToolbar.setTitle(R.string.text_setting);
//        mShareHelper =new ShareHelper(this);
//        mShareHelper.imageUrl(InitModel.getInstance().getShareImageUrl());
//        mShareHelper.message(InitModel.getInstance().getShareContent());
//        mShareHelper.shareTitle(InitModel.getInstance().getShareTitle());
//        mShareHelper.url(InitModel.getInstance().getShareUrl());
        mViewModel.getCacheLiveData().observe(this,s -> {
            setProgressVisible(false);
            clearCacheBtn.setSubTitleText(s==null?"":s);
        });
        mViewModel.getClearCacheLiveData().observe(this,b->{
            error("清除缓存成功！");
        });
//        versionBtn.setOnClickListener(v -> {
//            toTest(v);
//        });
        request();
    }
    private void request(){
        setProgressVisible(true);
        mViewModel.request();
    }

//    private void toTest(View view){
//        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//        builder.setTitle("测试内容");
//        builder.setItems(new String[]{"test"}, (dialog, which) -> {
//            switch (which) {
//                case 0:
//                    IntentBuilder.Builder().startParentActivity(getActivity(),CommentSuccessFragment.class);
//                    break;
//                default:
//                    break;
//            }
//        });
//        builder.show();
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mSubscription.clear();
        unbinder.unbind();
    }

    @OnClick({R.id.clearCacheBtn, R.id.versionBtn, R.id.aboutBtn, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clearCacheBtn:
//                progressView();
                setProgressVisible(true);
                mViewModel.clearCache();
                break;
            case R.id.versionBtn:

                break;
            case R.id.aboutBtn:
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
//            case R.id.shareBtn:
//
//                BottomSheetBuilder.showShareDialog(getActivity(), 4, (adapter, view1, position) -> {
//                    int itemType = ((BottomSheetShareMultipleItem) adapter.getItem(position)).getItemType();
//                    if (itemType == R.drawable.ic_wechat_friend) {
//                        mShareHelper.shareWeiXin();
//                    } else if (itemType == R.drawable.ic_wechat_circle) {
//                        mShareHelper.shareWeiXinTimeLine();
//                    } else if (itemType == R.drawable.ic_qq) {
//                        mShareHelper.shareQQ();
//                    } else if (itemType == R.drawable.ic_qq_zone) {
//                        mShareHelper.shareQQzone();
//                    }
//
//                });
//
//                break;
//            case R.id.nearbyStoresBtn:
//                //                IntentBuilder.Builder().startParentActivity(getActivity(), NearbyStoresFragment.class);
//                startActivity(new Intent(getActivity(), NearbyStoresActivity.class));
//                break;
            case R.id.btn_logout:
                UserModel.getInstance().loginOut();
                ActivityStackManager.finish();
                LoginActivity.goLoginOut(view.getContext());
                break;
        }
    }

//    private BottomShareDialog createBottomShareDialog(BottomShareDialog.OnShareClickListener onShareClickListener) {
//        BottomShareDialog dialog = new BottomShareDialog(getContext());
//        dialog.setOnShareClickListener(onShareClickListener);
//        dialog.setCanceledOnTouchOutside(true);
//        Window dialogWindow = dialog.getWindow();
//        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
//        dialogWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        return dialog;
//    }

//    void progressView() {
//        ProgressDialog dialog = new ProgressDialog(getActivity());
//        dialog.setProgress(100);
//        dialog.setMessage(getString(R.string.text_clear_cache));
//        dialog.show();
//        dialog.setCancelable(false);
//
//        mSubscription.add(rx.Observable.just(1).delay(1000, TimeUnit.MILLISECONDS).observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
//            dialog.dismiss();
//        }));
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        mShareHelper.onActivityResult(requestCode, resultCode, data);
    }
}
