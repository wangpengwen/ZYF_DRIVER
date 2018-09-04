package com.zywl.ui.goods;

import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.biz.base.BaseViewHolder;
import com.biz.util.IntentBuilder;
import com.biz.util.MathUtil;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.biz.util.Utils;
import com.zywl.ui.R;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by TCJK on 2018/5/31.
 */

public class GoodsInfoViewHolder extends BaseViewHolder {

    BottomSheetDialog mDialog;

    @BindView(R.id.iv_close)
    View closeIV;
    @BindView(R.id.radioButton1)
    RadioButton radioButton1;
    @BindView(R.id.radioButton2)
    RadioButton radioButton2;
    @BindView(R.id.radioButton3)
    RadioButton radioButton3;
    @BindView(R.id.radioButton4)
    RadioButton radioButton4;
    @BindView(R.id.radioButton5)
    RadioButton radioButton5;
    @BindView(R.id.radioButton6)
    RadioButton radioButton6;

    @BindView(R.id.layout_goods_name)
    View nameLayout;
    @BindView(R.id.layout_other_name)
    View otherNameLayout;
    @BindView(R.id.et_name)
    EditText nameET;
    @BindView(R.id.btn_name_confirm)
    View nameConfirmBtn;

    @BindView(R.id.layout_goods_info)
    View goodsInfoLayout;
    @BindView(R.id.layout_goods_name_title)
    View goodsNameTitleLayout;
    @BindView(R.id.tv_goods_name)
    TextView goodsNameTV;
    @BindView(R.id.et_weight)
    EditText weightET;
    @BindView(R.id.et_num)
    EditText numET;
    @BindView(R.id.et_length)
    EditText lengthET;
    @BindView(R.id.et_width)
    EditText widthET;
    @BindView(R.id.et_height)
    EditText heightET;
    @BindView(R.id.tv_size_weight)
    TextView sizeWeightTV;
    @BindView(R.id.btn_confirm)
    View confirmBtn;

    public GoodsInfoViewHolder(View view, BottomSheetDialog dialog, Action0 action0) {
        super(view);
        mDialog = dialog;
        ButterKnife.bind(this,view);

        RxUtil.click(closeIV).subscribe(o -> {
            mDialog.dismiss();
        });

        RxUtil.click(goodsNameTitleLayout).subscribe(o -> {
            nameLayout.setVisibility(View.VISIBLE);
            goodsInfoLayout.setVisibility(View.GONE);
        });

        RxUtil.click(nameConfirmBtn).subscribe(o -> {

            if(TextUtils.isEmpty(nameET.getText().toString().trim())){
                ToastUtils.showLong(getActivity(),"请输入货物名称");
                return;
            }

            nameLayout.setVisibility(View.GONE);
            goodsInfoLayout.setVisibility(View.VISIBLE);
            goodsNameTV.setText(nameET.getText().toString());
        });

        RxUtil.click(confirmBtn).subscribe(o -> {

            if(TextUtils.isEmpty(goodsNameTV.getText().toString())){
                ToastUtils.showLong(getActivity(),"请选择物品名称");
                return;
            }
//            if(TextUtils.isEmpty(weightET.getText().toString())){
//                ToastUtils.showLong(getActivity(),"请输入物品重量");
//                return;
//            }
            if(TextUtils.isEmpty(numET.getText().toString())){
                ToastUtils.showLong(getActivity(),"请输入物品件数");
                return;
            }
//            if(TextUtils.isEmpty(lengthET.getText().toString())){
//                ToastUtils.showLong(getActivity(),"请输入物品长度");
//                return;
//            }
//            if(TextUtils.isEmpty(widthET.getText().toString())){
//                ToastUtils.showLong(getActivity(),"请输入物品宽度");
//                return;
//            }
//            if(TextUtils.isEmpty(heightET.getText().toString())){
//                ToastUtils.showLong(getActivity(),"请输入物品高度");
//                return;
//            }

            action0.call();
            mDialog.dismiss();
        });

        Observable.combineLatest(RxUtil.textChanges(lengthET), RxUtil.textChanges(widthET),RxUtil.textChanges(heightET),
                (length, width, height)->{
                    return !TextUtils.isEmpty(length) && !TextUtils.isEmpty(width) && !TextUtils.isEmpty(height);
                }).subscribe(b->{
                    if(b){

                        try {
                            BigDecimal lengthBD = new BigDecimal(lengthET.getText().toString());
                            BigDecimal widthBD = new BigDecimal(widthET.getText().toString());
                            BigDecimal heightBD = new BigDecimal(heightET.getText().toString());

                            BigDecimal result = lengthBD.multiply(widthBD).multiply(heightBD).divide(new BigDecimal(6), 2, BigDecimal.ROUND_HALF_UP);
                            sizeWeightTV.setText(result.toPlainString());
                        }catch (Exception e){
                            sizeWeightTV.setText("");
                        }
                    } else{
                        sizeWeightTV.setText("");
                    }
                });
    }

    @OnClick({R.id.radioButton1, R.id.radioButton2, R.id.radioButton3, R.id.radioButton4, R.id.radioButton5, R.id.radioButton6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radioButton1:
                setRadioButtonChecked(1);
                nameLayout.setVisibility(View.GONE);
                goodsInfoLayout.setVisibility(View.VISIBLE);
                otherNameLayout.setVisibility(View.GONE);
                goodsNameTV.setText(radioButton1.getText());
                break;
            case R.id.radioButton2:
                setRadioButtonChecked(2);
                nameLayout.setVisibility(View.GONE);
                goodsInfoLayout.setVisibility(View.VISIBLE);
                otherNameLayout.setVisibility(View.GONE);
                goodsNameTV.setText(radioButton2.getText());
                break;
            case R.id.radioButton3:
                setRadioButtonChecked(3);
                nameLayout.setVisibility(View.GONE);
                goodsInfoLayout.setVisibility(View.VISIBLE);
                otherNameLayout.setVisibility(View.GONE);
                goodsNameTV.setText(radioButton3.getText());
                break;
            case R.id.radioButton4:
                setRadioButtonChecked(4);
                nameLayout.setVisibility(View.GONE);
                goodsInfoLayout.setVisibility(View.VISIBLE);
                otherNameLayout.setVisibility(View.GONE);
                goodsNameTV.setText(radioButton4.getText());
                break;
            case R.id.radioButton5:
                setRadioButtonChecked(5);
                nameLayout.setVisibility(View.GONE);
                goodsInfoLayout.setVisibility(View.VISIBLE);
                otherNameLayout.setVisibility(View.GONE);
                goodsNameTV.setText(radioButton5.getText());
                break;
            case R.id.radioButton6:
                setRadioButtonChecked(6);
                otherNameLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setRadioButtonChecked(int checked) {
        radioButton1.setChecked(checked == 1);
        radioButton2.setChecked(checked == 2);
        radioButton3.setChecked(checked == 3);
        radioButton4.setChecked(checked == 4);
        radioButton5.setChecked(checked == 5);
        radioButton6.setChecked(checked == 6);
        nameLayout.setVisibility(checked == 6 ? View.VISIBLE : View.GONE);
    }
}
