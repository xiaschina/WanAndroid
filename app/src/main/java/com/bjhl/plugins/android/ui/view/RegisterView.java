package com.bjhl.plugins.android.ui.view;

import com.bjhl.plugins.android.base.IBaseView;

/**
 * Created by XIAS on 2018/6/27.
 */

public interface RegisterView extends IBaseView {
    String getUserName();

    String getPassword();

    String getRePassword();

    void userNameNull();

    void userNameLength();

    void passWordNull();

    void rePassWordNull();

    void passwordNotEquals();

    void registerSuccess();

    void registerError(String msg);
}
