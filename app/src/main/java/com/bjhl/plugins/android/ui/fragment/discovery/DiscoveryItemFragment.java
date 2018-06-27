package com.bjhl.plugins.android.ui.fragment.discovery;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.bjhl.plugins.android.R;
import com.bjhl.plugins.android.base.BaseFragment;
import com.bjhl.plugins.android.base.RxRecyclerView;
import com.bjhl.plugins.android.bean.NewsModel;
import com.bjhl.plugins.android.ui.fragment.Index.IndexAdapter;
import com.bjhl.plugins.android.util.Constants;
import com.bjhl.plugins.android.util.RecyclerManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by XIAS on 2018/6/26.
 */

public class DiscoveryItemFragment extends BaseFragment {

    @BindView(R.id.index_recycler_view)
    RxRecyclerView rxRecyclerView;
    private IndexAdapter adapter;
    private static final String ID = "cid";
    private String id;

    public DiscoveryItemFragment() {

    }

    public static DiscoveryItemFragment newInstance(String id) {
        DiscoveryItemFragment fragment = new DiscoveryItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            id = getArguments().getString(ID);
    }

    @Override
    public void initView() {
        adapter = new IndexAdapter(new ArrayList());
        rxRecyclerView.setEnableRefresh(false);
        rxRecyclerView.getRecyclerView().addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildAdapterPosition(view) > 0) {
                    outRect.top = getActivity().getResources().getDimensionPixelOffset(R.dimen.dp_10);
                }
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (!TextUtils.isEmpty(id)) {
            Map<String, String> params = new HashMap<>();
            params.put(ID, id);
            RecyclerManager.creat(rxRecyclerView).setAdapter(adapter).url(Constants.GETARTICLE).dataClass(NewsModel.class).headersParam(true).params(params).start();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_index;
    }
}
