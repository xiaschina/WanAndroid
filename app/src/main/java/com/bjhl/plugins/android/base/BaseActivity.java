package com.bjhl.plugins.android.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;

/**
 * Created by XIAS on 2018/6/22.
 */

public abstract class BaseActivity extends FragmentActivity {

    public ImmersionBar mImmersionBar;

    public abstract void initView();

    public abstract void initData(Bundle savedInstanceState);

    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initImmersionBar();
        initView();
        initData(savedInstanceState);
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }


}
