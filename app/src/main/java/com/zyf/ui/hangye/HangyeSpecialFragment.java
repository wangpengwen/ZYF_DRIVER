package com.zyf.ui.hangye;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ListPopupWindow;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.biz.base.BaseLazyFragment;
import com.biz.util.IntentBuilder;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.zyf.driver.ui.R;
import com.zyf.ui.adapter.FilterAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by TCJK on 2018/6/25.
 */

public class HangyeSpecialFragment extends BaseLazyFragment<HangyeViewModel> {

    Unbinder unbinder;
    @BindView(R.id.line_et)
    EditText lineET;
    @BindView(R.id.reset_btn)
    Button resetBtn;
    @BindView(R.id.find_btn)
    Button findBtn;

    ListPopupWindow popupWindow;
    FilterAdapter adapter;

    boolean isReset = false;
    boolean isSelect = false;
    boolean isFirst = true;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initViewModel(HangyeViewModel.class, HangyeViewModel.class.getName(), true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hangye_special, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setTitle("行业查询");

        popupWindow = new ListPopupWindow(getContext());
        adapter = new FilterAdapter(getContext());
        popupWindow.setAdapter(adapter);

        mViewModel.getLineLiveData().observe(this, list -> {
            adapter.setList(list);
            adapter.notifyDataSetChanged();
            popupWindow.setAnchorView(lineET);
            popupWindow.setOnItemClickListener(
                    (AdapterView<?> parent, View itemView, int position, long id)-> {
                        isSelect = true;
                        lineET.setText(adapter.getItem(position));
                        lineET.setSelection(lineET.getText().length());
                        popupWindow.dismiss();
                    });
            if(list.isEmpty()) {
                popupWindow.dismiss();
            }else {
                popupWindow.show();
            }
        });


        mViewModel.getLineListLiveData().observe(this, list -> {

            IntentBuilder.Builder()
                    .putParcelableArrayListExtra(IntentBuilder.KEY_LIST,list)
                    .startParentActivity(this.getActivity(), HangyeResultFragment.class,true);
        });

        RxUtil.textChanges(lineET).subscribe(s -> {
            if(!isSelect && !isFirst && !isReset){

                if(TextUtils.isEmpty(s)) return;

                mViewModel.findLine(s);
            }
            isSelect = false;
            isFirst = false;
            isReset = false;
        });

        RxUtil.click(resetBtn).subscribe(o -> {
            isReset = true;
            lineET.setText("");
        });

        RxUtil.click(findBtn).subscribe(o -> {

            if(TextUtils.isEmpty(lineET.getText().toString().trim())){
                ToastUtils.showLong(getActivity(),"请选择专线");
                return;
            }

            mViewModel.getListByLine(lineET.getText().toString());
        });


    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
