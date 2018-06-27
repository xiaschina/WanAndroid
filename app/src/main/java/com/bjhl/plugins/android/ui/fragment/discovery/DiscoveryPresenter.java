package com.bjhl.plugins.android.ui.fragment.discovery;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.bjhl.plugins.network.Request;
import com.bjhl.plugins.network.interfac.RxObserver;
import com.bjhl.plugins.android.base.IBasePresenter;
import com.bjhl.plugins.android.bean.ChapterModel;
import com.bjhl.plugins.android.util.Constants;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by XIAS on 2018/6/25.
 */

public class DiscoveryPresenter implements IBasePresenter {

    private DiscoveryView view;

    public DiscoveryPresenter(DiscoveryView discoveryView) {
        this.view = discoveryView;
    }

    public void getChapters() {
        Request.get(Constants.GETTREEJSON, new RxObserver() {
            @Override
            public void onSuccess(Object o) {
                try {
                    List<ChapterModel> chapters = JSONObject.parseArray((String) o, ChapterModel.class);
                    if (chapters != null && chapters.size() > 0) {
                        view.bindChapter(chapters);
                    }
                } catch (JSONException e) {

                }
            }

            @Override
            public void onFailed(Throwable e) {

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
