package com.bjhl.plugins.network.util;

import com.bjhl.plugins.network.model.BaseResult;

import io.reactivex.functions.Function;

/**
 * Created by XIAS on 2018/6/21.
 */

public class RxMapFunction implements Function<BaseResult, String> {

    @Override
    public String apply(BaseResult baseResult) throws Exception {
        if (baseResult.errorCode < 0) {
            throw new Exception(baseResult.errorMsg);
        }
        return baseResult.data;
    }
}
