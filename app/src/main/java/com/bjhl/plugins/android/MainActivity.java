package com.bjhl.plugins.android;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.bjhl.plugins.android.base.BaseActivity;
import com.bjhl.plugins.android.ui.fragment.Index.IndexFragment;
import com.bjhl.plugins.android.ui.fragment.MineFragment;
import com.bjhl.plugins.android.ui.fragment.discovery.DiscoveryFragment;
import com.bjhl.plugins.android.util.Constants;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.main_frame)
    FrameLayout frameLayout;
    @BindView(R.id.main_index)
    RadioButton index;
    @BindView(R.id.main_eye)
    RadioButton eye;
    @BindView(R.id.main_mine)
    RadioButton mine;

    private IndexFragment indexFragment;
    private DiscoveryFragment discoveryFragment;
    private MineFragment mineFragment;

    @Override
    public void initView() {
        selectFragment(Constants.INDEX);
        setCheck(index);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        index.setOnClickListener(this);
        eye.setOnClickListener(this);
        mine.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.keyboardEnable(true).init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_index:
                selectFragment(Constants.INDEX);
                setCheck(index);
                mImmersionBar.fitsSystemWindows(false).transparentStatusBar().init();
                break;
            case R.id.main_eye:
                selectFragment(Constants.EYE);
                setCheck(eye);
                mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.color_ea986c).init();
                break;
            case R.id.main_mine:
                selectFragment(Constants.MINE);
                setCheck(mine);
                mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.color_ea986c).init();
                break;
        }
    }

    private void selectFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case Constants.INDEX:
                if (indexFragment == null) {
                    indexFragment = new IndexFragment();
                    transaction.add(R.id.main_frame, indexFragment);
                } else {
                    transaction.show(indexFragment);
                }
                break;
            case Constants.EYE:
                if (discoveryFragment == null) {
                    discoveryFragment = new DiscoveryFragment();
                    transaction.add(R.id.main_frame, discoveryFragment);
                } else {
                    transaction.show(discoveryFragment);
                }
                break;
            case Constants.MINE:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.main_frame, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (transaction != null) {
            if (indexFragment != null && indexFragment.isAdded() && indexFragment.isVisible())
                transaction.hide(indexFragment);
            if (discoveryFragment != null && discoveryFragment.isAdded() && discoveryFragment.isVisible())
                transaction.hide(discoveryFragment);
            if (mineFragment != null && mineFragment.isAdded() && mineFragment.isVisible())
                transaction.hide(mineFragment);
        }
    }

    private void setCheck(RadioButton button) {
        index.setChecked(false);
        eye.setChecked(false);
        mine.setChecked(false);
        button.setChecked(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
