package com.appcarestudio.arhitecture.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.appcarestudio.arhitecture.models.BaseModel;


public abstract class BaseRecyclerView<VB extends ViewDataBinding, BM extends BaseModel> {

    public BaseRecyclerView(PageManager pageManager) {
        setPageManager(pageManager);
    }

    public BaseRecyclerView() {

    }

    public boolean isUseDataBinding() {
        return true;
    }

    public VB getDataBinding() {
        return dataBinding;
    }

    public PageManager getPageManager() {
        return pageManager;
    }

    public void setPageManager(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    PageManager pageManager;

    VB dataBinding;

    public abstract int getViewType();

    @LayoutRes
    public abstract int getLayoutRes();


    public void bindDataBinding(View view) {
        dataBinding = DataBindingUtil.bind(view);
    }

    public abstract void onBind(View view, BM model, int position);
}
