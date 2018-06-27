package com.bjhl.plugins.android.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by XIAS on 2018/6/25.
 */

public class DisplayUtils {
    private static int mScreenWidth = 0;
    private static int mScreenHeight = 0;
    private static float mScreenDensity = 0.0f;

    public static int getScreenWidthPixels(Context context) {
        if (mScreenWidth == 0) {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            mScreenWidth = dm.widthPixels;
        }
        return mScreenWidth;
    }

    public static int getScreenHeightPixels(Context context) {
        if (mScreenHeight == 0) {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            mScreenHeight = dm.heightPixels;
        }
        return mScreenHeight;
    }

    public static float getScreenDensity(Context context) {
        if (mScreenDensity == 0.0f) {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            mScreenDensity = dm.density;
        }
        return mScreenDensity;
    }
}
