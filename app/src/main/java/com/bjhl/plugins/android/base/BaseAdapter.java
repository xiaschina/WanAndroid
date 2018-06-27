package com.bjhl.plugins.android.base;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by XIAS on 2018/6/22.
 */

public abstract class BaseAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public BaseAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public BaseAdapter(@Nullable List<T> data) {
        super(data);
    }

    public BaseAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        bind(item, helper);
    }

    public abstract void bind(T helper, BaseViewHolder item);
}
