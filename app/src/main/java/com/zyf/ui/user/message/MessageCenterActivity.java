package com.zyf.ui.user.message;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.biz.base.BaseActivity;
import com.zyf.driver.ui.R;

/**
 * Title: MessageCenterActivity
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/12/22  12:34
 *
 * @author wangwei
 * @version 1.0
 */
public class MessageCenterActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_toolbar_layout);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_holder, new MessageCenterFragment(),MessageCenterFragment.class.getSimpleName())
                .commitAllowingStateLoss();
    }
}
