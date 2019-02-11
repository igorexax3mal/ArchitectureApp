package com.appcarestudio.arhitecture.mvvm;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.appcarestudio.arhitecture.base.BaseFragment;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by IGOR on 26.03.2018.
 */

public abstract class BaseViewModelFragment<B extends BaseViewModel, VB extends ViewDataBinding> extends BaseFragment<VB> {

    Map<Class, BaseViewModel> modelMap = new HashMap<>();

    public abstract Class[] viewModelArray();

    public void addModels() {
        if (viewModelArray() != null) {
            for (Class classe : viewModelArray()) {
                modelMap.put(classe, null);
            }
        }
    }




    public B getViewModel() {
        if (viewModelArray().length > 0) {
            return (B) modelMap.get(viewModelArray()[0]);
        }
        return null;
    }


    public abstract void subscribe();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        addModels();
        for (Class classes : modelMap.keySet()) {
            modelMap.put(classes, (BaseViewModel) ViewModelProviders.of(this).get(classes));
        }
        super.onCreateView(inflater, container, savedInstanceState);
        subscribe();
        return rootView;
    }

}
