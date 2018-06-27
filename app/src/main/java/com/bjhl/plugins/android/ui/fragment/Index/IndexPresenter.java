package com.bjhl.plugins.android.ui.fragment.Index;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.bjhl.plugins.network.Request;
import com.bjhl.plugins.network.interfac.RxObserver;
import com.bjhl.plugins.android.base.IBasePresenter;
import com.bjhl.plugins.android.bean.BannerModel;
import com.bjhl.plugins.android.util.Constants;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by XIAS on 2018/6/25.
 */

public class IndexPresenter implements IBasePresenter {

    private IndexView view;

    public IndexPresenter(IndexView view) {
        this.view = view;
    }

    public void getBanner() {
        Request.get(Constants.GETBANNER, new RxObserver() {
            @Override
            public void onSuccess(Object o) {
                List<BannerModel.Banner> list = JSONObject.parseArray((String) o, BannerModel.Banner.class);
                if (list != null && list.size() > 0)
                    view.bindBanner(list);
            }

            @Override
            public void onFailed(Throwable e) {
                Log.d("onFailed", e.getMessage());
            }

            @Override
            public void onSubscribes(Disposable d) {
            }
        });
    }

    @Override
    public void onDestory() {

    }
}
