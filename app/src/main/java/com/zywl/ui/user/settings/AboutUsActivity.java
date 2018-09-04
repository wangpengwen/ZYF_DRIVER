package com.zywl.ui.user.settings;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.biz.base.BaseActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zywl.ui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.updateBtn)
    LinearLayout updateBtn;
    @BindView(R.id.iv_bg)
    ImageView bgIV;
//    private UpgradeViewModel mUpgradeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
//        mUpgradeViewModel = new UpgradeViewModel();
        findViewById(R.id.line).setVisibility(View.GONE);
        mToolbar.setBackgroundColor(Color.TRANSPARENT);
        mAppBarLayout.setBackgroundColor(Color.TRANSPARENT);
        mToolbar.setTitle(R.string.text_about_us);
        Glide.with(bgIV).load(R.mipmap.about_bg).apply(RequestOptions.centerCropTransform()).into(bgIV);
    }

    @OnClick({R.id.updateBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.updateBtn:
//                setProgressVisible(true);
//                mUpgradeViewModel.updateAbout(b -> {
//                    setProgressVisible(false);
//                    if (b) {
//                        mUpgradeViewModel.getUpgrade(upgradeEntity -> {
//                            new UpgradeManager().showUpgradeDialog(upgradeEntity, getActivity());
//                        });
//                    }
//                });
                break;
        }
    }
}
