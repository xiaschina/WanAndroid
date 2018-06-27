package com.bjhl.plugins.android.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by XIAS on 2018/6/22.
 */

public class ImageLoader {

    private RequestOptions options;

    private RequestManager glide;

    public static ImageLoader with(Context context) {
        return new ImageLoader(context);
    }

    public static ImageLoader with(FragmentActivity activity) {
        return new ImageLoader(activity);
    }

    public static ImageLoader with(Fragment fragment) {
        return new ImageLoader(fragment);
    }

    private ImageLoader() {

    }

    private ImageLoader(Context context) {
        glide = Glide.with(context);
        init();
    }

    private ImageLoader(FragmentActivity activity) {
        glide = Glide.with(activity);
        init();
    }

    private ImageLoader(Fragment fragment) {
        glide = Glide.with(fragment);
        init();
    }

    private void init() {
        options = RequestOptions
                .diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC);
    }

    public ImageLoader placeholder(Drawable drawable) {
        options.placeholder(drawable);
        return this;
    }

    public ImageLoader placeholder(int resId) {
        options.placeholder(resId);
        return this;
    }

    public ImageLoader error(Drawable drawable) {
        options.error(drawable);
        return this;
    }

    public ImageLoader error(int resId) {
        options.error(resId);
        return this;
    }

    public ImageLoader circleCrop() {
        options.circleCrop();
        return this;
    }

    public ImageLoader override(int width, int height) {
        options.override(width, height);
        return this;
    }

    public ImageLoader load(String url, ImageView view) {
        glide.load(url).apply(options).into(view);
        return this;
    }

    public ImageLoader load(Drawable url, ImageView view) {
        glide.load(url).apply(options).into(view);
        return this;
    }
}
