package com.bjhl.plugins.android.base;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.bjhl.plugins.android.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;

/**
 * Created by XIAS on 2018/6/22.
 */

public class RxRecyclerView extends SmartRefreshLayout {

    private RecyclerView recyclerView;

    public RxRecyclerView(Context context) {
        this(context, null);
    }

    public RxRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RxRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_view_recycler, null);
        recyclerView = view.findViewById(R.id.view_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addView(view);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    public void setHeader(RefreshHeader header) {
        if (header != null)
            setRefreshHeader(header);
    }

    public void setFooter(RefreshFooter footer) {
        if (footer != null)
            setRefreshFooter(footer);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
