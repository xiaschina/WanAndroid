package com.bjhl.plugins.android.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by XIAS on 2018/6/26.
 */

public class SharePreferenceUtil {

    private SharedPreferences settings;

    public SharePreferenceUtil(Context context) {
        this(context, (String) null);
    }

    public SharePreferenceUtil(Context context, String sharePreFileName) {
        if (sharePreFileName == null) {
            sharePreFileName = context.getPackageName();
        }

        this.settings = context.getSharedPreferences(sharePreFileName, 0);
    }

    public String getStringValue(String key, String defValue) {
        return this.settings.getString(key, defValue);
    }

    public boolean putString(String key, String value) {
        return this.settings.edit().putString(key, value).commit();
    }
}
