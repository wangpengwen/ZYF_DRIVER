package com.biz.base;

import com.biz.util.LogUtil;

import android.arch.lifecycle.ViewModelProviders;


/**
 * Created by wangwei on 2016/3/19.
 */
public class BaseLiveDataActivity<T extends BaseViewModel> extends BaseActivity {
    protected T mViewModel;

    protected void observeErrorLiveData(BaseViewModel viewModel) {
        if (viewModel != null) {
            viewModel.getErrorLiveData().observe(this, e -> {
                if (e != null)
                    error(e.status, e.message);
            });
        }
    }

    public void initViewModel(Class<T> modelClass) {
        this.mViewModel = registerViewModel(modelClass, false,true);
    }

    public void initViewModel(Class<T> modelClass, boolean isSingle) {
        this.mViewModel = registerViewModel(modelClass, isSingle,true);
    }

    public void initViewModel(Class<T> modelClass, String viewModelName) {
        this.mViewModel = registerViewModel(modelClass, viewModelName, true);
    }

    public void initViewModel(Class<T> modelClass, String viewModelName, boolean isRegisterError) {
        this.mViewModel = registerViewModel(modelClass, viewModelName, isRegisterError);
    }
    public void initViewModel(Class<T> modelClass, boolean isSingle, boolean isRegisterError) {
        this.mViewModel = registerViewModel(modelClass, isSingle, isRegisterError);
    }
    public <Q extends BaseViewModel> Q registerViewModel(Class<Q> modelClass, boolean isSingle) {
        return registerViewModel(modelClass, isSingle, true);
    }

    public <Q extends BaseViewModel> Q registerViewModel(Class<Q> modelClass, boolean isSingle, boolean isRegisterError) {
        String key = modelClass.getCanonicalName();
        if (key == null) {
            key = getClass().getCanonicalName();
        }
        return registerViewModel(modelClass, isSingle ? (this.toString() + ":" + key) : key, isRegisterError);
    }

    public <Q extends BaseViewModel> Q registerViewModel(Class<Q> modelClass, String viewModelName, boolean isRegisterError) {
        Q mViewModel = ViewModelProviders.of(this).get(viewModelName, modelClass);
        LogUtil.print("-----------------A-----------------------");
        LogUtil.print("-A--modelClass:"+modelClass+" viewModelName:"+viewModelName+" :isRegisterError:"+isRegisterError+" obj:"+(mViewModel!=null?mViewModel.toString():null));
        LogUtil.print("----------------------------------------");
        if (isRegisterError)
            observeErrorLiveData(mViewModel);
        return mViewModel;
    }

    public <Q extends BaseViewModel> Q registerViewModel(Class<Q> modelClass) {
        return registerViewModel(modelClass, true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
