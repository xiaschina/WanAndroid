package com.bjhl.plugins.network;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by XIAS on 2018/6/21.
 */

public class RxLifeManager {

    private static class RxManager {
        private static final RxLifeManager rxLifeManager = new RxLifeManager();
    }

    private Map<String, CompositeDisposable> map;

    private RxLifeManager() {
        map = new HashMap<>();
    }

    public static RxLifeManager getInstance() {
        return RxManager.rxLifeManager;
    }

    public void add(String key, Disposable disposable) {
        if (map.containsKey(key)) {
            CompositeDisposable compositeDisposable = map.get(key);
            compositeDisposable.add(disposable);
        } else {
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(disposable);
            map.put(key, compositeDisposable);
        }
    }

    public void remove(String key) {
        if (map.containsKey(key)) {
            CompositeDisposable compositeDisposable = map.get(key);
            compositeDisposable.clear();
            map.remove(key);
        }
    }
}
