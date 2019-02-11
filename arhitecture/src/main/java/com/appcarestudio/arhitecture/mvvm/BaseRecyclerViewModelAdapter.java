package com.appcarestudio.arhitecture.mvvm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.appcarestudio.arhitecture.base.BaseRecyclerView;
import com.appcarestudio.arhitecture.base.BaseRecyclerViewAdapter;
import com.appcarestudio.arhitecture.base.BaseViewHolder;
import com.appcarestudio.arhitecture.base.PageManager;
import com.appcarestudio.arhitecture.models.BaseModel;

import java.util.List;


/**
 * Created by IGOR on 15.03.2017.
 */

public abstract class BaseRecyclerViewModelAdapter<T extends BaseModel> extends BaseRecyclerViewAdapter<T, RecyclerView.ViewHolder> {

    protected BaseRecyclerViewModelAdapter() {
        super();
    }

    protected BaseRecyclerViewModelAdapter(Context context) {
        super(context);
    }

    protected BaseRecyclerViewModelAdapter(Context context, PageManager pageManager) {
        super(context, pageManager);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (getViewList() != null) {
            for (int i = 0; i < getViewList().size(); i++) {
                BaseRecyclerView baseRecyclerView = getViewList().get(i);
                if (viewType == baseRecyclerView.getViewType()) {
                    View view = View.inflate(getContext(), baseRecyclerView.getLayoutRes(), null);
                    baseRecyclerView.bindDataBinding(view);
                    BaseViewHolder holderBase = new BaseViewHolder(view, baseRecyclerView);
                    holderBase.setBaseRecyclerView(baseRecyclerView);
                    holder = holderBase;
                }
            }
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        T user = getDataList().get(position);
        BaseViewHolder userViewHolder = (BaseViewHolder) holder;
        userViewHolder.getBaseRecyclerView().onBind(userViewHolder.itemView,user, position);
    }


    public abstract List<BaseRecyclerView> getViewList();

}
