package com.bjhl.plugins.android.ui.view;


import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.bjhl.plugins.android.R;
import com.bjhl.plugins.android.base.BaseView;
import com.bjhl.plugins.android.bean.BannerModel;
import com.bjhl.plugins.android.ui.activity.NewsDetailActivity;
import com.bjhl.plugins.android.util.DisplayUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XIAS on 2018/6/25.
 */

public class BannerView extends BaseView {

    private Banner banner;

    public BannerView(Context context) {
        super(context);
    }

    @Override
    public void onCreate() {
        setContentView(R.layout.view_banner);
        initView();
    }

    private void initView() {
        banner = v.findViewById(R.id.view_banners);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, DisplayUtils.getScreenWidthPixels(getContext()) / 16 * 9);
        banner.setLayoutParams(params);
    }

    public void bindData(final List<BannerModel.Banner> banners) {
        List<String> images = new ArrayList<>();
        List<String> title = new ArrayList<>();
        for (BannerModel.Banner banner : banners) {
            images.add(banner.getImagePath());
            title.add(banner.getTitle());
        }
        banner.setImages(images).setImageLoader(new GlideImageLoader()).setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE).setBannerTitles(title).start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getContext(), NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.URL, banners.get(position).getUrl());
                getContext().startActivity(intent);
            }
        });
    }

    private static class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            com.bjhl.plugins.android.util.ImageLoader.with(context).load((String) path, imageView);
        }
    }
}
