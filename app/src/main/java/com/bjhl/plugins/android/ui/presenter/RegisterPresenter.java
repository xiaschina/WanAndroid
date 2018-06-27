package com.bjhl.plugins.android.ui.presenter;

import android.text.TextUtils;

import com.bjhl.plugins.network.Request;
import com.bjhl.plugins.network.interfac.RxObserver;
import com.bjhl.plugins.android.base.IBasePresenter;
import com.bjhl.plugins.android.ui.view.RegisterView;
import com.bjhl.plugins.android.util.Constants;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by XIAS on 2018/6/27.
 */

public class RegisterPresenter implements IBasePresenter {

    private RegisterView view;

    public RegisterPresenter(RegisterView view) {
        this.view = view;
    }


    public void register() {
        if (TextUtils.isEmpty(view.getUserName())) {
            view.userNameNull();
            return;
        }

        if (view.getUserName().length() <= 6) {
            view.userNameLength();
            return;
        }

        if (TextUtils.isEmpty(view.getPassword())) {
            view.passWordNull();
            return;
        }

        if (TextUtils.isEmpty(view.getRePassword())) {
            view.rePassWordNull();
            return;
        }

        if (!view.getPassword().equals(view.getRePassword())) {
            view.passwordNotEquals();
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put("username", view.getUserName());
        params.put("password", view.getPassword());
        params.put("repassword", view.getRePassword());

        Request.post(Constants.POSTREGISTER, params, new RxObserver() {
            @Override
            public void onSuccess(Object o) {
                view.registerSuccess();
            }

            @Override
            public void onFailed(Throwable e) {
                view.registerError(e.getMessage());
            }

            @Override
            public void onSubscribes(Disposable d) {

            }
        });

    }

    @Override
    public void onDestory() {

    }
}
