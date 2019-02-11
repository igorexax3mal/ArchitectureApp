package com.appcarestudio.arhitecture.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appcarestudio.arhitecture.R;


/**
 * Created by REIGOR on 12.10.2015.
 */
public abstract class BaseFragment<VB extends ViewDataBinding> extends Fragment {

    public View rootView;
    public LayoutInflater inflater;
    private PageManager pageManager;

    public abstract void subscribe();


    public boolean isHideBottomNavigationView() {
        return true;
    }

    public ActionBar getActionBar() {
        if (getActivity() != null) {
            return ((PageManagerActivity) getActivity()).getSupportActionBar();
        }
        return null;
    }


    public void hideBottomNavigationView() {
      /*  if (getActivity() != null) {
            ((MainActivity) getActivity()).hideShowBottomNavigation(isHideBottomNavigationView());
        }*/
    }

    public PageManagerActivity getPageManagerActivity() {
        if (getActivity() != null) {
            return ((PageManagerActivity) getActivity());
        }
        return null;
    }


/*    public MainActivity getMainActivity() {
        if (getActivity() != null) {
            if (getActivity() instanceof MainActivity) {
                return ((MainActivity) getActivity());
            }

        }
        return null;
    }*/

    public Toolbar getToolBar() {
        if (getActivity() != null) {
            return ((PageManagerActivity) getActivity()).getToolBar();
        }
        return null;
    }


    public void setToolBarTitle() {
        if (getActivity() != null) {
            Toolbar mActionToolbar = getToolBar();
            if(toolbarTitleId!=0){
                TextView mTitle = (TextView) mActionToolbar.findViewById(toolbarTitleId);
                mTitle.setText(getTitle());
            }else{
                mActionToolbar.setTitle(getTitle());
            }

        }
    }
    @IdRes
    public int toolbarTitleId ;

    public AppBarLayout getAppBarLayout() {
        if (getActivity() != null) {
            return ((PageManagerActivity) getActivity()).getAppBarLayout();
        }
        return null;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //TODO for center title if menu icon is present
     //   inflater.inflate(R.menu.dummy_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    public boolean isUseDataBinding() {
        return true;
    }

    public boolean isHideToolbar() {
        return false;
    }

    public void hideShowToolbar() {
        if (getActivity() != null) {
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                if (isHideToolbar()) {
                    actionBar.hide();
                } else {
                    actionBar.show();
                }
            }
        }

    }

    public boolean isUseMakeToolbar() {
        return true;
    }

    public void setBack(boolean back) {
        isBack = back;
    }

    private boolean isBack;

    public VB getDataBinding() {
        if (dataBinding != null) {
            return (VB) dataBinding;
        } else {
            return null;
        }

    }

    public ViewDataBinding dataBinding;

    public boolean isBack() {
        return isBack;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String title = getString(R.string.app_name);

    public PageManager getPageManager() {
        return pageManager;
    }

    public void setPageManager(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        try {
            rootView = inflater.inflate(getRootViewRes(), null, false);
            if (isUseDataBinding()) {
                dataBinding = DataBindingUtil.bind(rootView);
            }
            hideShowToolbar();
            if (isUseMakeToolbar()) {
                makeToolbars();
            }
            setupViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
        subscribe();
        return rootView;
    }

    public abstract void setupViews();

    @LayoutRes
    public abstract int getRootViewRes();


    public void onBackPressed() {
        ((PageManagerActivity) getActivity()).onBackPressed();
    }

    public boolean isWhiteToolbar() {
        return false;
    }


    public Drawable getDrawableForArrow() {
        return getResources().getDrawable(R.drawable.ic_arrow_back);
    }

    public Drawable getDrawableForArrowWhite() {
        return getResources().getDrawable(R.drawable.ic_arrow_back_white);
    }

    public Drawable getDrawableForMenu() {
        return getResources().getDrawable(R.drawable.ic_menu);
    }

    public Drawable getDrawableForMenuWhite() {
        return getResources().getDrawable(R.drawable.ic_menu_white);
    }


    @Override
    public void onResume() {
        super.onResume();
        hideBottomNavigationView();
        if (isUseMakeToolbar()) {
            Toolbar toolbar = getToolBar();
            if (toolbar != null) {
                toolbar.setTitle("");
                if (getTitle() != null) {
                    setToolBarTitle();
                }
                if (isBack()) {
                    if (isWhiteToolbar()) {
                        toolbar.setNavigationIcon(getDrawableForArrowWhite());
                    } else {
                        toolbar.setNavigationIcon(getDrawableForArrow());
                    }

                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onBackPressed();
                        }
                    });
                } else {
                    // toolbar.setNavigationIcon(null);

                    if (isWhiteToolbar()) {
                        toolbar.setNavigationIcon(getDrawableForMenuWhite());
                    } else {
                        toolbar.setNavigationIcon(getDrawableForMenu());
                    }
                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getPageManager().openMenu();
                        }
                    });
                }
               /* if (isHideActionBar()) {
                    actionBar.hide();
                } else {
                    actionBar.show();
                }*/

            }
        }

    }


    public void makeToolbars() {

       /* mActionToolbar = getPageManager().getToolbar();
        setupActionBar(false, false);
        if (mActionToolbar != null) {

            if (mActionToolbar.getMenu() != null) {
                mActionToolbar.getMenu().clear();
            }
            if (getInfoBlock() != null) {
                if (getInfoBlock().getTitle() != null) {
                    mActionToolbar.setTitle(getInfoBlock().getTitle());
                }
            }
            if (getTitle() != null) {
                mActionToolbar.setTitle(getTitle());
            }
            mActionToolbar.inflateMenu(R.menu.menu_infos_page_fragment);
            MenuItem menuButton = (MenuItem) mActionToolbar.getMenu().findItem(R.id.action_back_menu);
            if (isFirst) {
                mActionToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
                mActionToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((MainActivity) getActivity()).showMenu(true);
                    }
                });
                menuButton.setVisible(false);

            } else {
                mActionToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
                mActionToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBack();
                    }
                });
                menuButton.setVisible(false);
                menuButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_back_menu:
                                ((MainActivity) getActivity()).showMenu(true);
                                break;
                        }

                        return true;
                    }
                });
            }
        }*/

    }

    public void setRequest(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getRequestCode() {
        return requestCode;
    }

    int requestCode;

    public void onFragmentResult(int requestCode, int resultCode, Intent data) {

    }

    public void pushPageInside(BaseFragment pageFragment, @IdRes int frameId) {
        pageFragment.setPageManager(getPageManager());
        getChildFragmentManager().beginTransaction().replace(frameId, pageFragment).commit();
    }

}
