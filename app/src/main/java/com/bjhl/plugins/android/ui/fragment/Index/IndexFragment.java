package com.bjhl.plugins.android.ui.fragment.Index;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bjhl.plugins.android.R;
import com.bjhl.plugins.android.base.BaseFragment;
import com.bjhl.plugins.android.base.RxRecyclerView;
import com.bjhl.plugins.android.bean.BannerModel;
import com.bjhl.plugins.android.bean.NewsModel;
import com.bjhl.plugins.android.ui.view.BannerView;
import com.bjhl.plugins.android.util.Constants;
import com.bjhl.plugins.android.util.RecyclerManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by XIAS on 2018/6/22.
 */

public class IndexFragment extends BaseFragment implements IndexView {

    private IndexPresenter presenter;

    @BindView(R.id.index_recycler_view)
    RxRecyclerView rxRecyclerView;

    private IndexAdapter adapter;
    private BannerView bannerView;

    //private IndexViewModel viewModel;

    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentIndexBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_index, container, false);
        viewModel = new IndexViewModel();
        binding.setViewModel(viewModel);
        initView(binding);
        return binding.getRoot();
    }

    private void initView(FragmentIndexBinding binding) {
        binding.indexRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildAdapterPosition(view) > 0) {
                    outRect.top = getActivity().getResources().getDimensionPixelOffset(R.dimen.dp_10);
                }
            }
        });
    }*/

    @Override
    public void initView() {
        rxRecyclerView.getRecyclerView().addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildAdapterPosition(view) > 1) {
                    outRect.top = getActivity().getResources().getDimensionPixelOffset(R.dimen.dp_10);
                }
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        adapter = new IndexAdapter(new ArrayList());
        presenter = new IndexPresenter(this);
        presenter.getBanner();
        RecyclerManager.creat(rxRecyclerView).setAdapter(adapter).url(Constants.GETARTICLE).dataClass(NewsModel.class).headersParam(true).start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    public void bindBanner(Object o) {
        List<BannerModel.Banner> banners = (List<BannerModel.Banner>) o;
        if (bannerView == null) {
            bannerView = new BannerView(getContext());
        }
        bannerView.bindData(banners);
        adapter.addHeaderView(bannerView);
    }

    @Override
    public void bindRecycler(Object o) {
        List<NewsModel.News> news = (List<NewsModel.News>) o;
        rxRecyclerView.setAdapter(new IndexAdapter(news));
    }

    @Override
    public void isRefresh() {

    }

    @Override
    public void loadMore(boolean isCan) {
        rxRecyclerView.setEnableLoadMore(isCan);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
