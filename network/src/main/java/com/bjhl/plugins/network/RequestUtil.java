package com.bjhl.plugins.network;

import com.bjhl.plugins.network.interfac.ApiService;
import com.bjhl.plugins.network.model.BaseResult;

import retrofit2.Retrofit;

/**
 * Created by XIAS on 2018/6/20.
 */

class RequestUtil {

    private Retrofit mRetrofit;
    private ApiService mApiService;

    private RequestUtil() {
        mRetrofit = RetrofitUtil.newInstantce().getRetrofit();
        mApiService = mRetrofit.create(ApiService.class);
    }

    private static class RequestInstantce {
        public static final RequestUtil instance = new RequestUtil();
    }

    public static RequestUtil getInstance() {
        return RequestInstantce.instance;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
