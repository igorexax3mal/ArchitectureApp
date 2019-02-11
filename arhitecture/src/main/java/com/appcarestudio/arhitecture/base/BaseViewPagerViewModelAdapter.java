package com.appcarestudio.arhitecture.base;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.appcarestudio.arhitecture.mvvm.TabViewModelFragment;

import java.util.List;


public abstract class BaseViewPagerViewModelAdapter extends FragmentPagerAdapter {

    public PageManager getPageManager() {
        return pageManager;
    }

    public void setPageManager(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    PageManager pageManager;

    public BaseViewPagerViewModelAdapter(FragmentManager fm, PageManager pageManager) {
        super(fm);
        setPageManager(pageManager);
    }

    @Override
    public TabViewModelFragment getItem(int position) {
        TabViewModelFragment tabFragment = getTabFragments().get(position);
        tabFragment.setPageManager(getPageManager());
        return tabFragment;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return getTabFragments().get(position).getTitle();
    }

    @Override
    public int getCount() {
        return getTabFragments().size();
    }


    public abstract List<TabViewModelFragment> getTabFragments();
}
