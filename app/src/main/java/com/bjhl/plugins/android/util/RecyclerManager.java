package com.bjhl.plugins.android.util;


import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONException;
import com.bjhl.plugins.network.Request;
import com.bjhl.plugins.network.interfac.RxObserver;
import com.bjhl.plugins.android.base.BaseAdapter;
import com.bjhl.plugins.android.base.RxRecyclerView;
import com.bjhl.plugins.android.bean.ListData;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by XIAS on 2018/6/25.
 */

public class RecyclerManager {
    private WeakReference<RxRecyclerView> rxRecyclerView;
    private String url;
    private boolean isHeader;
    private Map<String, String> params;
    private Map<String, String> headerparams;
    private Class dataClass;
    private int index = -1;
    private BaseAdapter adapter;

    private RecyclerManager(RxRecyclerView rxRecyclerView) {
        this.rxRecyclerView = new WeakReference<>(rxRecyclerView);
        if (rxRecyclerView != null) {
            this.rxRecyclerView.get().setOnRefreshLoadMoreListener(refreshLoadMoreListener);
        }
    }

    public static RecyclerManager creat(RxRecyclerView rxRecyclerView) {
        return new RecyclerManager(rxRecyclerView);
    }

    public RecyclerManager url(String url) {
        this.url = url;
        return this;
    }

    public RecyclerManager headersParam(boolean flag) {
        this.isHeader = flag;
        return this;
    }

    public RecyclerManager params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public RecyclerManager setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
        rxRecyclerView.get().setAdapter(adapter);
        return this;
    }

    public <T extends ListData> RecyclerManager dataClass(Class<T> dataClass) {
        this.dataClass = dataClass;
        return this;
    }

    public void start() {
        request(true);
    }


    OnRefreshLoadMoreListener refreshLoadMoreListener = new OnRefreshLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            request(false);
        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            request(true);
        }
    };

    private void request(final boolean isRefresh) {
        if (TextUtils.isEmpty(url))
            return;
        if (dataClass == null)
            return;
        if (isHeader) {
            if (isRefresh) {
                index = -1;
            }
            index++;
            headerparams = new HashMap<>();
            headerparams.put("index", String.valueOf(index));
            Request.get(url, headerparams, params, new RxObserver() {
                @Override
                public void onSuccess(Object o) {
                    ListData obj;
                    try {
                        obj = (ListData) com.alibaba.fastjson.JSONObject.parseObject((String) o, dataClass);
                    } catch (JSONException e) {
                        return;
                    }
                    if (obj == null) {
                        return;
                    }
                    bindData(isRefresh, obj);
                }

                @Override
                public void onFailed(Throwable e) {

                }

                @Override
                public void onSubscribes(Disposable d) {

                }
            });
        } else {
            Request.get(url, params, new RxObserver() {
                @Override
                public void onSuccess(Object o) {
                    ListData obj;
                    try {
                        obj = (ListData) com.alibaba.fastjson.JSONObject.parseObject((String) o, dataClass);
                    } catch (JSONException e) {
                        return;
                    }
                    if (obj == null) {
                        return;
                    }
                    bindData(isRefresh, obj);
                }

                @Override
                public void onFailed(Throwable e) {

                }

                @Override
                public void onSubscribes(Disposable d) {

                }
            });
        }
    }

    private void bindData(final boolean isRefresh, final ListData listData) {

        if (rxRecyclerView.get() != null) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    if (isRefresh)
                        rxRecyclerView.get().finishRefresh();
                    else
                        rxRecyclerView.get().finishLoadMore();
                    if (listData == null)
                        return;
                    if (listData.getList() == null || listData.getList().size() < 1) {
                        if (listData.isOver())
                            rxRecyclerView.get().finishLoadMoreWithNoMoreData();
                    } else {
                        if (isRefresh)
                            adapter.replaceData(listData.getList());
                        else {
                            adapter.addData(listData.getList());
                        }
                        if (listData.isOver())
                            rxRecyclerView.get().finishLoadMoreWithNoMoreData();
                    }
                }
            });
        }

    }
}
