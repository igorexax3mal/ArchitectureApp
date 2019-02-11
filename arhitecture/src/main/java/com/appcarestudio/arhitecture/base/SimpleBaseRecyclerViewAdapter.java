package com.appcarestudio.arhitecture.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;




public abstract class SimpleBaseRecyclerViewAdapter<T, H extends RecyclerView.ViewHolder> extends BaseRecyclerViewAdapter<T, H> {


    public SimpleBaseRecyclerViewAdapter(Context context, PageManager pageManager) {
        super(context, pageManager);
    }

    public SimpleBaseRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(parent, getLayoutRes(), viewType);
    }

    @Override
    public void onBindViewHolder(H holder, int position) {
        onBind(holder, position);
    }

    public View getInflatedView(@LayoutRes int layoutRes) {
        return inflater.inflate(layoutRes, null, false);
    }

    public View getInflatedView(@LayoutRes int layoutRes, ViewGroup parent, boolean attach) {
        return inflater.inflate(layoutRes, parent, attach);
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public abstract H getViewHolder(ViewGroup parent, @LayoutRes int layoutRes, int viewType);


    public abstract void onBind(H holder, int position);

    public BaseFragment getParentFragment() {
        return parentFragment;
    }

    BaseFragment parentFragment;

    public void setParentFragment(BaseFragment parentFragment) {
        this.parentFragment = parentFragment;
    }


}
