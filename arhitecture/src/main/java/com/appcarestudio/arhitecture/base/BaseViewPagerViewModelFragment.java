package com.appcarestudio.arhitecture.base;

import android.databinding.ViewDataBinding;
import android.support.v4.view.ViewPager;

import com.appcarestudio.arhitecture.mvvm.BaseViewModel;
import com.appcarestudio.arhitecture.mvvm.BaseViewModelFragment;


public abstract class BaseViewPagerViewModelFragment<B extends BaseViewModel, VB extends ViewDataBinding> extends BaseViewModelFragment<B, VB> {
    public ViewPager getViewPager() {
        return viewPager;
    }

    ViewPager viewPager;
    BaseViewPagerViewModelAdapter adapter;

    public BaseViewPagerViewModelAdapter getAdapter() {
        if (adapter == null) {
            adapter = createAdapter();
        }
        return adapter;
    }


    @Override
    public void setupViews() {
      //  viewPager = rootView.findViewById(R.id.viewPager);
        viewPager.setAdapter(getAdapter());
    }

    public abstract BaseViewPagerViewModelAdapter createAdapter();


}
