package com.bjhl.plugins.android.ui.view;

import com.bjhl.plugins.android.base.IBaseView;

/**
 * Created by XIAS on 2018/6/27.
 */

public interface LoginView extends IBaseView {

    String getUserName();

    String getPassword();

    void userNameNull();

    void passwordNull();

    void loginSucccess();

    void loginError(String msg);
}
