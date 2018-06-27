package com.bjhl.plugins.android.util;

/**
 * Created by XIAS on 2018/6/22.
 */

public class Constants {
    public static final String GETBANNER = "banner/json";
    public static final String GETARTICLE = "article/list/{index}/json";
    public static final String GETTREEJSON = "tree/json";
    public static final String POSTLOGIN = "user/login";
    public static final String POSTREGISTER = "user/register";
    public static final String GETCOLLECT = "lg/collect/list/{index}/json";

    public static final String UNKNOW_TIME = "时间未知";
    public static final String TODAY_TIME = "今天";


    public static final String USER = "user";


    public static final int INDEX = 0;
    public static final int EYE = 1;
    public static final int MINE = 2;

    //EventBus

    public static final int LOGIN_SUCCESS = 100000;
    public static final int LOGIN_QUIT = 100001;
}
