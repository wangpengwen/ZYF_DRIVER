package com.zyf.ui.info;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.biz.base.BaseLiveDataFragment;
import com.biz.util.PhotoUtil;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyf.app.GlideImageLoader;
import com.zyf.driver.ui.R;
import com.zyf.model.UserModel;
import com.zyf.ui.bottomsheet.BottomSheetBuilder;
import com.zyf.ui.bottomsheet.BottomSheetMultipleItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * Created by ltxxx on 2018/9/9.
 */

public class LicenseFragment extends BaseLiveDataFragment<ValidateViewModel> {

    @BindView(R.id.vehicle_info)
    TextView vehicleInfo;
    @BindView(R.id.id_card_front)
    RelativeLayout idCardFront;
    @BindView(R.id.id_card_oppsite)
    RelativeLayout idCardOppsite;
    @BindView(R.id.driver_license)
    RelativeLayout driverLicense;
    @BindView(R.id.vehicle_license)
    RelativeLayout vehicleLicense;
    @BindView(R.id.btn_commit)
    Button btnCommit;
//    @BindView(R.id.vehicle_info_title)
//    TextView vehicleInfoTitle;
//    @BindView(R.id.space)
//    View space;
//    @BindView(R.id.license_info_title)
//    TextView licenseInfoTitle;
    @BindView(R.id.icon1)
    AppCompatImageView icon1;
    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.img1)
    AppCompatImageView img1;
    @BindView(R.id.icon2)
    AppCompatImageView icon2;
    @BindView(R.id.txt2)
    TextView txt2;
    @BindView(R.id.img2)
    AppCompatImageView img2;
    @BindView(R.id.icon3)
    AppCompatImageView icon3;
    @BindView(R.id.txt3)
    TextView txt3;
    @BindView(R.id.img3)
    AppCompatImageView img3;
    @BindView(R.id.icon4)
    AppCompatImageView icon4;
    @BindView(R.id.txt4)
    TextView txt4;
    @BindView(R.id.img4)
    AppCompatImageView img4;

    private BottomSheetDialog mSheetDialog;
    String picUrl;
    int type = 0;

    String idFrontURL;
    String idOppsiteURL;
    String driverLicenseURL;
    String vehicleLicenseURL;

    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(ValidateViewModel.class, ValidateViewModel.class.getName(), false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_license, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("驾照及行驶证");

        vehicleInfo.setText(UserModel.getInstance().getUserAddress());

        RxUtil.click(idCardFront).subscribe(o -> {
            type = 1;
            createBottomSheet();
        });

        RxUtil.click(idCardOppsite).subscribe(o -> {
            type = 2;
            createBottomSheet();
        });

        RxUtil.click(driverLicense).subscribe(o -> {
            type = 3;
            createBottomSheet();
        });

        RxUtil.click(vehicleLicense).subscribe(o -> {
            type = 4;
            createBottomSheet();
        });

        RxUtil.click(btnCommit).subscribe(o -> {
            mViewModel.uploadDriverLicense(idFrontURL,idOppsiteURL,driverLicenseURL,vehicleLicenseURL);
        });

        mViewModel.getLicenseLiveData().observe(this, o -> {
            ToastUtils.showLong(getActivity(),"上传成功");
            finish();
        });

        mViewModel.getImageLiveData().observe(this, image -> {
            setProgressVisible(false);
            switch (image.type) {

                case 1:
                    idFrontURL = GlideImageLoader.getOssImageUri(image.r);
                    icon1.setVisibility(View.GONE);
                    txt1.setVisibility(View.GONE);
                    img1.setVisibility(View.VISIBLE);
                    Glide.with(img1).load(GlideImageLoader.getOssImageUri(idFrontURL)).into(img1);
                    break;
                case 2:
                    idOppsiteURL = GlideImageLoader.getOssImageUri(image.r);
                    icon2.setVisibility(View.GONE);
                    txt2.setVisibility(View.GONE);
                    img2.setVisibility(View.VISIBLE);
                    Glide.with(img2).load(GlideImageLoader.getOssImageUri(idOppsiteURL)).into(img2);
                    break;
                case 3:
                    driverLicenseURL = GlideImageLoader.getOssImageUri(image.r);
                    icon3.setVisibility(View.GONE);
                    txt3.setVisibility(View.GONE);
                    img3.setVisibility(View.VISIBLE);
                    Glide.with(img3).load(GlideImageLoader.getOssImageUri(driverLicenseURL)).into(img3);
                    break;
                case 4:
                    vehicleLicenseURL = GlideImageLoader.getOssImageUri(image.r);
                    icon4.setVisibility(View.GONE);
                    txt4.setVisibility(View.GONE);
                    img4.setVisibility(View.VISIBLE);
                    Glide.with(img4).load(GlideImageLoader.getOssImageUri(vehicleLicenseURL)).into(img4);
                    break;
            }
        });
    }

    private void createBottomSheet() {
        mSheetDialog = BottomSheetBuilder.createPhotoBottomSheet(getActivity(),
                (BaseQuickAdapter adapter, View v, int index) -> {
                    BottomSheetMultipleItem item = (BottomSheetMultipleItem) adapter.getItem(index);
                    if (item != null) {
                        switch (item.getItemType()) {
                            case BottomSheetMultipleItem.CAMERA:
                                PhotoUtil.photo(this, uri -> {
                                    picUrl = uri.getPath();
                                });
                                mSheetDialog.dismiss();
                                break;
                            case BottomSheetMultipleItem.GALLERY:
                                PhotoUtil.gallery(this);
                                mSheetDialog.dismiss();
                                break;
                            case BottomSheetMultipleItem.CANCEL:
                                mSheetDialog.dismiss();
                                break;
                            default:
                                break;
                        }
                    }
                }
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == PhotoUtil.CAMERA_SUCCESS) {
            setProgressVisible(true);
            mViewModel.uploadImage(type,picUrl);
        } else if (requestCode == PhotoUtil.PHOTO_SUCCESS) {
            Uri originalUri = data.getData();
            setProgressVisible(true);
            mViewModel.uploadImage(type,PhotoUtil.getPath(getActivity(), originalUri));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
