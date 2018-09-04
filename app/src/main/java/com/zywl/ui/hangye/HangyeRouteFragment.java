package com.zywl.ui.hangye;

import android.arch.lifecycle.Observer;
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
import android.widget.TextView;

import com.biz.base.BaseArrayListAdapter;
import com.biz.base.BaseLazyFragment;
import com.biz.util.IntentBuilder;
import com.biz.util.Lists;
import com.biz.util.RxUtil;
import com.biz.util.ToastUtils;
import com.zywl.ui.R;
import com.zywl.ui.adapter.FilterAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * Created by TCJK on 2018/6/25.
 */

public class HangyeRouteFragment extends BaseLazyFragment<HangyeViewModel> {

    Unbinder unbinder;
    @BindView(R.id.start_et)
    EditText startEt;
    @BindView(R.id.end_et)
    EditText endEt;
    @BindView(R.id.reset_btn)
    Button resetBtn;
    @BindView(R.id.find_btn)
    Button findBtn;

    ListPopupWindow popupWindow;
    FilterAdapter adapter;

    boolean isReset = false;
    boolean isSelect = false;
    boolean isFirst = true;

    boolean isEndReset = false;
    boolean isEndSelect = false;
    boolean isEndFirst = true;

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
        View view = inflater.inflate(R.layout.fragment_hangye_route, container, false);
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

        mViewModel.getStartLiveData().observe(this, list -> {
            adapter.setList(list);
            adapter.notifyDataSetChanged();
            popupWindow.setAnchorView(startEt);
            popupWindow.setOnItemClickListener(
                    (AdapterView<?> parent, View itemView, int position, long id)-> {
                        isSelect = true;
                        startEt.setText(adapter.getItem(position));
                        startEt.setSelection(startEt.getText().length());
                        popupWindow.dismiss();
                    });
            if(list.isEmpty()) {
                popupWindow.dismiss();
            }else {
                popupWindow.show();
            }
        });

        mViewModel.getEndLiveData().observe(this, list -> {
            adapter.setList(list);
            adapter.notifyDataSetChanged();
            popupWindow.setAnchorView(endEt);
            popupWindow.setOnItemClickListener(
                    (AdapterView<?> parent, View itemView, int position, long id)-> {
                        isEndSelect = true;
                        endEt.setText(adapter.getItem(position));
                        endEt.setSelection(endEt.getText().length());
                        popupWindow.dismiss();
                    });
            if(list.isEmpty()) {
                popupWindow.dismiss();
            }else {
                popupWindow.show();
            }
        });

        mViewModel.getSeListLiveData().observe(this, list -> {

            IntentBuilder.Builder()
                    .putParcelableArrayListExtra(IntentBuilder.KEY_LIST,list)
                    .startParentActivity(this.getActivity(), HangyeResultFragment.class,true);
        });

        RxUtil.textChanges(startEt).subscribe(s -> {
            if(!isSelect && !isFirst && !isReset){

                mViewModel.findStart(s);
            }
            isSelect = false;
            isFirst = false;
            isReset = false;
        });

        RxUtil.textChanges(endEt).subscribe(s -> {

            if(!isEndSelect && !isEndFirst && !isEndSelect){

                mViewModel.findEnd(s);
            }
            isEndSelect = false;
            isEndFirst = false;
            isEndReset = false;
        });

        RxUtil.click(resetBtn).subscribe(o -> {
            isReset = true;
            isEndSelect = true;
            startEt.setText("");
            endEt.setText("");
        });

        RxUtil.click(findBtn).subscribe(o -> {

            if(TextUtils.isEmpty(startEt.getText().toString().trim())){
                ToastUtils.showLong(getActivity(),"请选择起点");
                return;
            }
            if(TextUtils.isEmpty(endEt.getText().toString().trim())){
                ToastUtils.showLong(getActivity(),"请选择终点");
                return;
            }

            mViewModel.getListByStartEnd(startEt.getText().toString(),endEt.getText().toString());
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

    @OnClick({R.id.reset_btn, R.id.find_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reset_btn:
                break;
            case R.id.find_btn:
                break;
        }
    }
}
