package com.bjhl.plugins.android.ui.fragment.Index;

import com.bjhl.plugins.android.base.IBaseView;

/**
 * Created by XIAS on 2018/6/22.
 */

public interface IndexView extends IBaseView {

    void bindBanner(Object o);

    void bindRecycler(Object o);

    void isRefresh();

    void loadMore(boolean isCan);
}
