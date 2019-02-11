package com.appcarestudio.arhitecture.base;

import android.databinding.ViewDataBinding;
import android.support.v4.view.ViewPager;


public abstract class BaseViewPagerFragment<VB extends ViewDataBinding> extends BaseFragment<VB> {
    public ViewPager getViewPager() {
        return viewPager;
    }

    ViewPager viewPager;
    BaseViewPagerAdapter adapter;

    public BaseViewPagerAdapter getAdapter() {
        if (adapter == null) {
            adapter = createAdapter();
        }
        return adapter;
    }


    @Override
    public void setupViews() {
        //viewPager = rootView.findViewById(R.id.viewPager);
        viewPager.setAdapter(getAdapter());
    }

    public abstract BaseViewPagerAdapter createAdapter();


}
