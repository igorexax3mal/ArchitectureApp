package com.appcarestudio.arhitecture.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseRecyclerView getBaseRecyclerView() {
        return baseRecyclerView;
    }

    public void setBaseRecyclerView(BaseRecyclerView baseRecyclerView) {
        this.baseRecyclerView = baseRecyclerView;
    }

    BaseRecyclerView baseRecyclerView;

    public BaseViewHolder(View itemView, BaseRecyclerView baseRecyclerView) {
        super(itemView);
        this.baseRecyclerView = baseRecyclerView;
       /* if (isUseDataBinding()) {
            dataBinding = DataBindingUtil.bind(itemView);
        }*/
    }

    public BaseViewHolder(View itemView) {
        super(itemView);
      //  this.baseRecyclerView = baseRecyclerView;
       /* if (isUseDataBinding()) {
            dataBinding = DataBindingUtil.bind(itemView);
        }*/
    }
}
