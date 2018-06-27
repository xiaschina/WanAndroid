package com.bjhl.plugins.android.ui.fragment.Index;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.bjhl.plugins.network.Request;
import com.bjhl.plugins.network.interfac.RxObserver;
/*import com.bjhl.plugins.rxjava2.BR;
import com.bjhl.plugins.rxjava2.R;
import com.bjhl.plugins.rxjava2.base.IViewModel;
import com.bjhl.plugins.rxjava2.bean.NewsModel;*/
import com.bjhl.plugins.android.base.IViewModel;
import com.bjhl.plugins.android.bean.NewsModel;
import com.bjhl.plugins.android.util.Constants;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by XIAS on 2018/6/25.
 */

public class IndexViewModel implements IViewModel {

    private int index = -1;

    /*public ObservableList<IndexItemViewModel> newList = new ObservableArrayList<>();

    public ItemBinding<IndexItemViewModel> itemView = ItemBinding.of(BR.news, R.layout.item_index);

    public IndexViewModel() {
        getData();
    }*/

    public void getData() {
        Map<String, String> headers = new HashMap<>();
        index++;
        headers.put("index", String.valueOf(index));
        Request.get(Constants.GETARTICLE, headers, null, new RxObserver() {
            @Override
            public void onSuccess(Object o) {
                NewsModel newsModel = JSONObject.parseObject((String)o, NewsModel.class);
                if (newsModel != null && newsModel.datas != null && newsModel.datas.size() > 0) {
                    Observable.fromIterable(newsModel.datas).subscribe(new Consumer<NewsModel.News>() {
                        @Override
                        public void accept(NewsModel.News news) throws Exception {
                           // newList.add(new IndexItemViewModel(null, news));
                        }
                    });
                }
            }

            @Override
            public void onFailed(Throwable e) {
                Log.d("onFailed", e.getMessage());
            }

            @Override
            public void onSubscribes(Disposable d) {
                //RxLifeManager.getInstance().add("", d);
            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
