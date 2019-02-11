package com.appcarestudio.arhitecture.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public abstract class BaseActivity extends AppCompatActivity {

    public static final String EXTRA_INTENT_BASE = "extra";

    public String getExtra() {
        return extra;
    }

    private String extra;

    @LayoutRes
    public abstract int getRootViewId();


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getRootViewId());
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getStringExtra(EXTRA_INTENT_BASE) != null) {
                extra = intent.getStringExtra(EXTRA_INTENT_BASE);
            }
        }
        setupViews();
    }


    public abstract void setupViews();

    public abstract Toolbar getToolBar();

    public abstract AppBarLayout getAppBarLayout();

    public void showActivity(Class classActivity) {
        showActivity(classActivity, false);
    }


    public void showActivity(Class classActivity, boolean withAnimation) {
        Intent intent = new Intent(this, classActivity);
        // intent.putExtra(EXTRA, message);
        startActivity(intent);
    }

    public void showActivity(Class classActivity, String extra) {
        Intent intent = new Intent(this, classActivity);
        intent.putExtra(EXTRA_INTENT_BASE, extra);
        startActivity(intent);
    }

}
