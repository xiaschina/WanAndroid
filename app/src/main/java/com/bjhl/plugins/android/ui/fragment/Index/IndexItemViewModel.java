package com.bjhl.plugins.android.ui.fragment.Index;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.bjhl.plugins.android.base.IViewModel;
import com.bjhl.plugins.android.bean.NewsModel;

/**
 * Created by XIAS on 2018/6/25.
 */

public class IndexItemViewModel implements IViewModel {

    private NewsModel.News news = new NewsModel.News();

    public ObservableField<String> author = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> belongTo = new ObservableField<>();
    public ObservableBoolean collect = new ObservableBoolean();

    public IndexItemViewModel(Context context, NewsModel.News news) {
        //news.publishTime = DateUtil.format(news.publishTime);
        this.news= news;

        author.set(news.author);
        //time.set(news.publishTime);
        title.set(news.title);
        belongTo.set(news.superChapterName);
        collect.set(news.collect);
    }

    @Override
    public void onDestroy() {

    }
}
