package com.bjhl.plugins.network.interfac;

import com.bjhl.plugins.network.model.BaseResult;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by XIAS on 2018/6/20.
 */

public interface ApiService {

    @POST()
    @FormUrlEncoded
    Observable<BaseResult> post(@Url String url, @FieldMap Map<String, String> maps);

    @GET
    Observable<BaseResult> get(@Url String url, @QueryMap Map<String, String> maps);

    @GET
    Observable<BaseResult> get(@Url String url);

    @POST()
    Observable<BaseResult> post(@Url String url);
}
