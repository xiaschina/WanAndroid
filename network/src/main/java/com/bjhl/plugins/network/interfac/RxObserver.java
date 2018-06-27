package com.bjhl.plugins.network.interfac;

import com.bjhl.plugins.network.RxLifeManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by XIAS on 2018/6/21.
 */

public abstract class RxObserver implements Observer {
    @Override
    public void onSubscribe(Disposable d) {
        onSubscribes(d);
    }

    @Override
    public void onNext(Object o) {
        onSuccess(o);
    }

    @Override
    public void onError(Throwable e) {
        onFailed(e);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(Object o);

    public abstract void onFailed(Throwable e);

    public abstract void onSubscribes(Disposable d);
}
