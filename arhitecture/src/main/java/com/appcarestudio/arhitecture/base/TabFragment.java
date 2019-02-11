package com.appcarestudio.arhitecture.base;

import android.databinding.ViewDataBinding;

import com.appcarestudio.arhitecture.models.BaseModel;


/**
 * Created by IGOR on 21.03.2018.
 */

public abstract class TabFragment<VB extends ViewDataBinding, BM extends BaseModel> extends BaseFragment<VB> {

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count = 0;

    public BM getBaseModel() {
        return baseModel;
    }

    public void setBaseModel(BM baseModel) {
        this.baseModel = baseModel;
    }

    private BM baseModel;


    @Override
    public boolean isUseMakeToolbar() {
        return false;
    }

    public void updateTabFragment() {

    }
}
