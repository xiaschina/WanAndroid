package com.bjhl.plugins.android.ui.fragment.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.bjhl.plugins.android.R;
import com.bjhl.plugins.android.base.BaseFragment;
import com.bjhl.plugins.android.bean.ChapterModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by XIAS on 2018/6/25.
 */

public class DiscoveryFragment extends BaseFragment implements DiscoveryView {

    @BindView(R.id.discovery_parent_chapter)
    TabLayout parentView;
    @BindView(R.id.discovery_children_chapter)
    TabLayout childrenView;
    @BindView(R.id.discovery_children_pager)
    ViewPager viewPager;

    private DiscoveryPresenter presenter;

    @Override
    public void initView() {
        parentView.setTabMode(TabLayout.MODE_SCROLLABLE);
        parentView.setTabGravity(TabLayout.GRAVITY_CENTER);
        parentView.setSelectedTabIndicatorHeight(0);
        parentView.setTabTextColors(getContext().getResources().getColor(R.color.color_80FFFFFF), getContext().getResources().getColor(android.R.color.white));
        childrenView.setTabMode(TabLayout.MODE_SCROLLABLE);
        childrenView.setTabGravity(TabLayout.GRAVITY_CENTER);
        childrenView.setSelectedTabIndicatorHeight(0);
        childrenView.setTabTextColors(getContext().getResources().getColor(R.color.color_80FFFFFF), getContext().getResources().getColor(android.R.color.white));

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        presenter = new DiscoveryPresenter(this);
        presenter.getChapters();
    }

    @Override
    public void bindChapter(Object o) {
        final List<ChapterModel> models = (List<ChapterModel>) o;
        for (ChapterModel model : models) {
            parentView.addTab(parentView.newTab().setText(model.name));
        }
        if (models.get(0) != null && models.get(0).children != null && models.get(0).children.size() > 0)
            bindChildrenChapter(models.get(0).children);
        parentView.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (models.get(position) != null && models.get(position).children != null && models.get(position).children.size() > 0)
                    bindChildrenChapter(models.get(position).children);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void bindChildrenChapter(Object o) {
        childrenView.removeAllTabs();
        List<ChapterModel> models = (List<ChapterModel>) o;
        List<BaseFragment> list = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        BaseFragment baseFragment;
        for (ChapterModel model : models) {
            childrenView.addTab(childrenView.newTab().setText(model.name));
            baseFragment = DiscoveryItemFragment.newInstance(model.id);
            list.add(baseFragment);
            titles.add(model.name);
        }
        childrenView.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        childrenView.setupWithViewPager(viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getActivity().getSupportFragmentManager(), list, titles));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_discovery;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        /*mImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.color_ea986c)
                .init();*/
    }

    public static class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private List<BaseFragment> list;
        private List<String> titles;

        public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> list, List<String> titles) {
            super(fm);
            this.list = list;
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return (list != null && list.size() > 0) ? list.size() : 0;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
