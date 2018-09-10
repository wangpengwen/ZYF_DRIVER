package com.zyf.ui;

import android.text.TextUtils;

import com.biz.base.BaseViewModel;
import com.biz.image.upload.UploadImageUtil;
import com.zyf.driver.ui.R;

import rx.functions.Action1;

/**
 * Title: BaseUploadImageViewModel
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/12/22  16:40
 *
 * @author wangwei
 * @version 1.0
 */
public class BaseUploadImageViewModel extends BaseViewModel {
    protected void uploadImage(String image, Action1<String> onNext) {
        submitRequest(UploadImageUtil.upload(image, getString(R.string.oss_bucket), getString(R.string.oss_ak), getString(R.string.oss_sk), false), r -> {
            if (TextUtils.isEmpty(r)) {
                sendError(getError(R.string.text_error_upload_image));
                return;
            }
            if (onNext != null) {
                onNext.call(r);
            }
        });
    }
}
