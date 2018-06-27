package com.bjhl.plugins.android.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bjhl.plugins.android.R;
import com.bjhl.plugins.android.base.BaseActivity;
import com.bjhl.plugins.android.base.RxRecyclerView;
import com.bjhl.plugins.android.bean.NewsModel;
import com.bjhl.plugins.android.ui.fragment.Index.IndexAdapter;
import com.bjhl.plugins.android.util.Constants;
import com.bjhl.plugins.android.util.RecyclerManager;

import java.util.ArrayList;

import butterknife.BindView;

public class CollectActivity extends BaseActivity {


    @BindView(R.id.collect_recycler_view)
    RxRecyclerView rxRecyclerView;
    private IndexAdapter adapter;

    @Override
    public void initView() {
        adapter = new IndexAdapter(new ArrayList());
        rxRecyclerView.setEnableRefresh(false);
        rxRecyclerView.getRecyclerView().addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildAdapterPosition(view) > 0) {
                    outRect.top = getResources().getDimensionPixelOffset(R.dimen.dp_10);
                }
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        RecyclerManager.creat(rxRecyclerView).setAdapter(adapter).url(Constants.GETCOLLECT).dataClass(NewsModel.class).headersParam(true).start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collect;
    }
}
