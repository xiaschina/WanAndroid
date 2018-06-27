package com.bjhl.plugins.android.ui.presenter;

import android.text.TextUtils;

import com.bjhl.plugins.network.Request;
import com.bjhl.plugins.network.interfac.RxObserver;
import com.bjhl.plugins.android.base.IBasePresenter;
import com.bjhl.plugins.android.ui.view.LoginView;
import com.bjhl.plugins.android.util.Constants;
import com.bjhl.plugins.android.util.MessageEvent;
import com.bjhl.plugins.android.util.SharePreferenceUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by XIAS on 2018/6/27.
 */

public class LoginPresenter implements IBasePresenter {

    private LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

    public void login() {
        String name = view.getUserName();
        if (TextUtils.isEmpty(name)) {
            view.userNameNull();
            return;
        }
        String pass = view.getPassword();
        if (TextUtils.isEmpty(pass)) {
            view.passwordNull();
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", name);
        params.put("password", pass);
        Request.post(Constants.POSTLOGIN, params, new RxObserver() {
            @Override
            public void onSuccess(Object o) {
                new SharePreferenceUtil(view.getContext()).putString(Constants.USER, (String) o);
                EventBus.getDefault().post(new MessageEvent(Constants.LOGIN_SUCCESS));
                view.loginSucccess();
            }

            @Override
            public void onFailed(Throwable e) {
                view.loginError(e.getMessage());
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
