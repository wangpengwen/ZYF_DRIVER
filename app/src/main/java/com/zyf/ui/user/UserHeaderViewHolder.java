package com.zyf.ui.user;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.biz.base.BaseViewHolder;
import com.biz.util.IntentBuilder;
import com.zyf.driver.ui.R;

/**
 * Title: UserHeaderViewHolder
 * Description:
 * Copyright:Copyright(c)2016
 * Company:博智维讯信息技术有限公司
 * CreateTime:17/11/2017  14:27
 *
 * @author liutong
 * @version 1.0
 */

 class UserHeaderViewHolder extends BaseViewHolder {

    public TextView mUserName;
    public View mAccountLayout;
    public ImageView mAvatar;

    public UserHeaderViewHolder(View itemView) {
        super(itemView);
        mAccountLayout = itemView.findViewById(R.id.rl_account);
        mUserName = itemView.findViewById(R.id.et_username);
        mAvatar = itemView.findViewById(R.id.avatar);
//        Glide.with(headerBgIV).load(R.mipmap.mine_bg).apply(RequestOptions.centerCropTransform()).into(headerBgIV);
        mAccountLayout.setOnClickListener(this::info);
    }

    private void info(View v){
        IntentBuilder.Builder().startParentActivity(this.getActivity(), UserInfoFragment.class,true);
    }

    public static UserHeaderViewHolder createViewHolder(Context context){
        View view = View.inflate(context, R.layout.item_usercenter_header_avatar_layout, null);
        return new UserHeaderViewHolder(view);
    }

}
