package com.appcarestudio.arhitecture.mvvm;

import android.databinding.ViewDataBinding;

/**
 * Created by IGOR on 21.03.2018.
 */

public abstract class TabViewModelFragment<B extends BaseViewModel, VB extends ViewDataBinding> extends BaseViewModelFragment<B, VB> {

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count = 0;


    @Override
    public boolean isUseMakeToolbar() {
        return false;
    }
}
