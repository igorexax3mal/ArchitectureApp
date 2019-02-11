package com.appcarestudio.arhitecture.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import java.util.List;


/**
 * Created by IGOR on 20.03.2018.
 */

public abstract class PageManagerActivity extends BaseActivity implements PageManager {


    public PageManager getPageManager() {
        return pageManager;
    }

    private PageManager pageManager;

    private static PageManagerActivity instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pageManager = this;
        instance = this;
        super.onCreate(savedInstanceState);
    }

    public static PageManagerActivity getInstance() {
        return instance;
    }

    public View getRootView() {
        return getWindow().getDecorView().getRootView();
    }


    @IdRes
    public abstract int getRootFragmentId();


    @Override
    public void pushPage(BaseFragment page) {
        pushPage(page, false);
    }

    @Override
    public void pushPage(BaseFragment page, boolean withAnimation) {
        page.setPageManager(getPageManager());
        page.setBack(true);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (withAnimation) {
            ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        }
        ft.addToBackStack(null).replace(getRootFragmentId(), page).commit();
    }


    @Override
    public void pushPageAdd(BaseFragment page, boolean withAnimation) {
        page.setPageManager(getPageManager());
        page.setBack(true);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (withAnimation) {
            ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        }
        ft.addToBackStack(null).add(getRootFragmentId(), page).commit();
    }

    @Override
    public void showPage(BaseFragment page) {
        try {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        page.setBack(false);
        page.setPageManager(getPageManager());
        getSupportFragmentManager().beginTransaction().replace(getRootFragmentId(), page).commit();
    }

    @Override
    public void popPage(BaseFragment page) {
        try {
            getSupportFragmentManager().popBackStackImmediate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // page.setBack(false);
        // page.setPageManager(getPageManager());
        //getSupportFragmentManager().beginTransaction().replace(getRootFragmentId(), page).commit();
    }


    @Override
    public void popPage() {
        onBackPressed();
    }


    public static void setData(int requestCode, int result, Intent data) {
        Log.d("mylog900", "setData requestCode" + requestCode + " result=" + result);
        getInstance().getPageManager().popPage();
        Log.d("mylog900", "setData getPageManager().popPage();");
        getInstance().onFragmentResult(requestCode, result, data);
        Log.d("mylog900", "setData onFragmentResult");
    }

    public static void setDataWithoutPopPage(int requestCode, int result, Intent data) {
        Log.d("mylog900", "setData requestCode" + requestCode + " result=" + result);

        Log.d("mylog900", "setData getPageManager().popPage();");
        getInstance().onFragmentResult(requestCode, result, data);
        Log.d("mylog900", "setData onFragmentResult");
    }


    @Override
    public void pushPageForResult(BaseFragment page, int requestCode, Intent data) {
        page.setRequest(requestCode);
        pushPage(page, false);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        List<Fragment> listFragment = getSupportFragmentManager().getFragments();
        if (listFragment != null) {
            for (Fragment fragmnet : listFragment) {
                fragmnet.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        List<Fragment> listFragment = getSupportFragmentManager().getFragments();
        if (listFragment != null) {
            for (Fragment fragmnet : listFragment) {
                fragmnet.onActivityResult(requestCode, resultCode, data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void onFragmentResult(int requestCode, int resultCode, @Nullable Intent data) {
        List<Fragment> listFragment = getSupportFragmentManager().getFragments();
        if (listFragment != null) {
            for (Fragment fragmnet : listFragment) {
                Log.d("mylog900", "fragmnet=" + fragmnet.getClass());
                if (fragmnet instanceof BaseFragment) {
                    ((BaseFragment) fragmnet).onFragmentResult(requestCode, resultCode, data);
                }

            }
        }
    }
}
