package com.biz.base;

import com.biz.http.R;
import com.biz.util.DialogUtil;
import com.biz.util.LogUtil;
import com.umeng.analytics.MobclickAgent;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;


@SuppressWarnings("deprecation")
public class BaseFragment extends Fragment {


    protected RecyclerView.RecycledViewPool mPool;

    public void setPool(RecyclerView.RecycledViewPool pool) {
        this.mPool = pool;
    }

    @Nullable
    protected Toolbar mToolbar;
    @Nullable
    protected AppBarLayout mAppBarLayout;

    protected BaseActivity baseActivity;
    protected AlertDialog mErrorDialog;

    protected void setTextViewText(TextView textView, CharSequence text){
        if (textView!=null){
            textView.setText(TextUtils.isEmpty(text)?"":text);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            baseActivity = (BaseActivity) context;
        }
        if (this instanceof FragmentBackHelper) {
            getBaseActivity().setFragmentBackHelper((FragmentBackHelper) this);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mToolbar = view.findViewById(R.id.toolbar);
        if (mToolbar == null) {
            mToolbar = getActivity().findViewById(R.id.toolbar);
        }
        mAppBarLayout = view.findViewById(R.id.appbar);
        if (mAppBarLayout == null) {
            mAppBarLayout = getActivity().findViewById(R.id.appbar);
        }
        if (getActivity().getIntent() != null && getActivity().getIntent().hasExtra(Intent.EXTRA_TITLE)) {
            setTitle(getActivity().getIntent().getStringExtra(Intent.EXTRA_TITLE));
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this instanceof FragmentBackHelper) {
            getBaseActivity().removeFragmentBackHelper((FragmentBackHelper) this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTitle(@StringRes int resId) {
        if (null != mToolbar)
            mToolbar.setTitle(resId);
    }

    public void setTitle(String resId) {
        if (null != mToolbar)
            mToolbar.setTitle(resId);
    }

    public void setTitleStyle(@StyleRes int resId) {
        if (null != mToolbar)
            mToolbar.setTitleTextAppearance(getContext(), resId);
    }


    public int getColors(@ColorRes int resId) {
        return getResources().getColor(resId);
    }

    protected int getColor(@ColorRes int resId) {
        return getResources().getColor(resId);
    }

    public void setProgressVisible(boolean isVisible) {
        BaseActivity baseActivity = (BaseActivity) getActivity();
        if (baseActivity != null)
            baseActivity.setProgressVisible(isVisible);
    }

    public void setViewDisableDelay(final View view) {
        view.setEnabled(false);
        view.postDelayed(() -> {
            view.setEnabled(true);
        }, 600);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public void startActivity(Intent intent, boolean isBack) {
        super.startActivity(intent);
        if (isBack)
            getActivity().overridePendingTransition(R.anim.left_in, R.anim.right_out);
        else
            getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    protected void finish() {
        getBaseActivity().finish();
    }

    public void startActivity(Class clz) {
        Intent intent = new Intent(getActivity(), FragmentParentActivity.class);
        intent.putExtra(FragmentParentActivity.KEY_FRAGMENT, clz);
        startActivity(intent);
    }

    public void error(String error) {
        setProgressVisible(false);
        if (!TextUtils.isEmpty(error)) {
            if(mErrorDialog!=null){
                mErrorDialog.dismiss();
                mErrorDialog=null;
            }
            mErrorDialog=DialogUtil.createDialogView(getActivity(), error, (dialog, which) -> {
                dialog.dismiss();
            }, R.string.btn_confirm);
        }
    }

    public void error(int code, String error) {
        if (code == 2400 || code == 2401) {
            setProgressVisible(false);
            finish();
            return;
        }
        error(error);
    }

    protected void addItemDecorationLine(RecyclerView recyclerView) {
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(recyclerView.getContext())
                .colorResId(R.color.color_divider).size(1)
                .showLastDivider().build());
    }

    public void dismissKeyboard() {
        getBaseActivity().dismissKeyboard();
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getName());
        LogUtil.print(this.getClass().getSimpleName().toString());
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getName());
    }


    public <T extends View> T findViewById(@IdRes int id) {
        if (getView() != null)
            return getView().findViewById(id);
        return null;
    }

    protected <T extends View> T getView(Activity view, @IdRes int resId) {
        T t = (T) view.findViewById(resId);
        if (t == null) {
            return null;
        }
        return t;
    }

}
