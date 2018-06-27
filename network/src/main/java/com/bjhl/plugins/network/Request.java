package com.bjhl.plugins.network;

import com.bjhl.plugins.network.model.BaseResult;
import com.bjhl.plugins.network.util.RxMapFunction;

import java.util.Map;

import io.reactivex.Observer;

/**
 * Created by XIAS on 2018/6/21.
 */

public class Request {

    public static void post(String url, Map<String, String> params, Observer observer) {
        RequestUtil.getInstance().getApiService().post(url, params).map(new RxMapFunction())
                .compose(RxSchedulesUtil.toMain())
                .subscribe(observer);
    }

    public static void post(String url, Observer observer) {
        RequestUtil.getInstance().getApiService().post(url).map(new RxMapFunction())
                .compose(RxSchedulesUtil.toMain())
                .subscribe(observer);
    }

    public static void post(String url, Map<String, String> headers, Map<String, String> params, Observer observer) {
        if (headers == null)
            return;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            url.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        if (params != null) {
            post(url, params, observer);
        } else {
            post(url, observer);
        }
    }

    public static void get(String url, Map<String, String> params, Observer observer) {
        RequestUtil.getInstance().getApiService().get(url, params).map(new RxMapFunction())
                .compose(RxSchedulesUtil.toMain())
                .subscribe(observer);
    }

    public static void get(String url, Observer observer) {
        RequestUtil.getInstance().getApiService().get(url).map(new RxMapFunction())
                .compose(RxSchedulesUtil.toMain())
                .subscribe(observer);
    }

    public static void get(String url, Map<String, String> headers, Map<String, String> params, Observer observer) {
        if (headers == null)
            return;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            url = url.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        if (params != null) {
            get(url, params, observer);
        } else {
            get(url, observer);
        }
    }
}
