package com.bjhl.plugins.network;

import com.bjhl.plugins.network.fastjson.FastJsonConverterFactory;
import com.bjhl.plugins.network.util.NetWorkConstants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by XIAS on 2018/6/20.
 */

class RetrofitUtil {

    private Retrofit retrofit;

    private RetrofitUtil() {
        retrofit = new Retrofit.Builder()
                .baseUrl(NetWorkConstants.baseUrl)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitUtil newInstantce() {
        return RetrofitInstance.instance;
    }

    private static class RetrofitInstance {
        private static final RetrofitUtil instance = new RetrofitUtil();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
