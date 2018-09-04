package com.biz.base;

import android.arch.lifecycle.ViewModelProviders;

import com.biz.util.LogUtil;


/**
 * Created by wangwei on 2016/3/19.
 */
public class BaseLiveDataFragment<T extends BaseViewModel> extends BaseFragment {
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
        this.mViewModel = registerViewModel(modelClass, false,true);//ViewModelProviders.of(getBaseActivity()).get(this.toString() + ":" + modelClass, modelClass);
    }
    public void initViewModel(Class<T> modelClass,boolean isSingle) {
        this.mViewModel = registerViewModel(modelClass, isSingle,true);
    }
    public void initViewModel(Class<T> modelClass,String viewModelName) {
        this.mViewModel = registerViewModel(modelClass, viewModelName,true);
    }
    public void initViewModel(Class<T> modelClass,String viewModelName,boolean isRegisterError) {
        this.mViewModel = registerViewModel(modelClass, viewModelName,isRegisterError);
    }
    public void initViewModel(Class<T> modelClass,boolean isSingle,boolean isRegisterError) {
        this.mViewModel = registerViewModel(modelClass, isSingle,isRegisterError);
    }
    public <Q extends BaseViewModel> Q registerViewModel(Class<Q> modelClass, boolean isSingle) {
        return registerViewModel(modelClass, isSingle, false);
    }

    public <Q extends BaseViewModel> Q registerViewModel(Class<Q> modelClass, boolean isSingle, boolean isRegisterError) {
        String key = modelClass.getCanonicalName();
        if (key == null) {
            key = getClass().getCanonicalName();
        }
        return registerViewModel(modelClass,isSingle ? (this.toString() + ":" + key) : key,isRegisterError);
    }
    public <Q extends BaseViewModel> Q registerViewModel(Class<Q> modelClass,String viewModelName,boolean isRegisterError) {
        Q mViewModel = ViewModelProviders.of(getBaseActivity()).get(viewModelName, modelClass);
        LogUtil.print("----------------------------------------");
        LogUtil.print("---modelClass:"+modelClass+" viewModelName:"+viewModelName+" :isRegisterError:"+isRegisterError+" obj:"+(mViewModel!=null?mViewModel.toString():null));
        LogUtil.print("----------------------------------------");
        if (isRegisterError)
            observeErrorLiveData(mViewModel);
        return mViewModel;
    }

    public <Q extends BaseViewModel> Q registerViewModel(Class<Q> modelClass) {
        return registerViewModel(modelClass, true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
