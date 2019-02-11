package com.appcarestudio.arhitecture.base;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

public abstract class BaseViewPagerAdapter extends FragmentPagerAdapter {

    public PageManager getPageManager() {
        return pageManager;
    }

    List<TabFragment> tabFragmentsList = getTabFragments();

    public void setPageManager(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    PageManager pageManager;

    public BaseViewPagerAdapter(FragmentManager fm, PageManager pageManager) {
        super(fm);
        setPageManager(pageManager);
    }


    @Override
    public TabFragment getItem(int position) {
        TabFragment tabFragment = tabFragmentsList.get(position);
        tabFragment.setPageManager(getPageManager());
        return tabFragment;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabFragmentsList.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return tabFragmentsList.size();
    }


    public abstract List<TabFragment> getTabFragments();

    public abstract void updateOneTabFragment(TabFragment tabFragment);


    public void updateTabFragments() {
        Log.d("mylog900", " updateTabFragments()=");
        for (TabFragment tabFragment : tabFragmentsList) {
            updateOneTabFragment(tabFragment);
        }
    }
}
