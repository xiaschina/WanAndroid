package com.bjhl.plugins.android.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bjhl.plugins.android.R;
import com.bjhl.plugins.android.base.BaseActivity;
import com.bjhl.plugins.android.ui.presenter.LoginPresenter;
import com.bjhl.plugins.android.ui.view.LoginView;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {

    @BindView(R.id.login_username)
    TextInputLayout userName;
    @BindView(R.id.login_password)
    TextInputLayout password;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.register)
    TextView register;

    private LoginPresenter presenter;

    @Override
    public void initView() {
        presenter = new LoginPresenter(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        userName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString()))
                    userName.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString()))
                    password.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                presenter.login();
                break;
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.color_ea986c)
                .init();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getUserName() {
        return userName.getEditText().getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return password.getEditText().getText().toString().trim();
    }

    @Override
    public void userNameNull() {
        userName.setError(getResources().getString(R.string.user_name_null));
    }

    @Override
    public void passwordNull() {
        password.setError(getResources().getString(R.string.user_password_null));
    }

    @Override
    public void loginSucccess() {
        finish();
    }

    @Override
    public void loginError(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
    }
}
