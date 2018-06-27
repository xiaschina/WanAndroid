package com.bjhl.plugins.android.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by XIAS on 2018/6/22.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    //public ImmersionBar mImmersionBar;

    public abstract void initView();

    public abstract void initData(Bundle savedInstanceState);

    public abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(getLayoutId(), null);
        unbinder = ButterKnife.bind(this, view);
        //initImmersionBar();
        initView();
        initData(savedInstanceState);
        return view;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        //mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        //if (!hidden && mImmersionBar != null)
        //    mImmersionBar.init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
      //  if(mImmersionBar != null)
       //     mImmersionBar.destroy();
    }
}
