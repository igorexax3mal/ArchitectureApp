package com.appcarestudio.arhitecture.base;

import android.content.Intent;

public interface PageManager {
    public void pushPage(BaseFragment page);

    public void pushPageForResult(BaseFragment page, int requestCode, Intent data);

    public void pushPage(BaseFragment page, boolean withAnimation);

    public void pushPageAdd(BaseFragment page, boolean withAnimation);

    public void showPage(BaseFragment page);

    public void popPage();

    public void popPage(BaseFragment page);


    public void openMenu();
}
