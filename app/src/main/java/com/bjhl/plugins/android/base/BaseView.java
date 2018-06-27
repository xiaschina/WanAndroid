package com.bjhl.plugins.android.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by XIAS on 2018/6/25.
 */

public abstract class BaseView extends LinearLayout {

    public View v;

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    public BaseView(Context context) {
        super(context);
        onCreate();
    }

    public abstract void onCreate();

    public void setContentView(int id) {
        LayoutInflater mLayoutInflater = LayoutInflater.from(getContext());
        v = mLayoutInflater.inflate(id, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        v.setLayoutParams(params);
        addView(v);
    }
}

