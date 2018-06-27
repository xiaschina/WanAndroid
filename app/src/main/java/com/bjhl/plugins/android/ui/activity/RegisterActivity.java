package com.bjhl.plugins.android.ui.activity;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bjhl.plugins.android.R;
import com.bjhl.plugins.android.base.BaseActivity;
import com.bjhl.plugins.android.ui.presenter.RegisterPresenter;
import com.bjhl.plugins.android.ui.view.RegisterView;

import butterknife.BindView;

public class RegisterActivity extends BaseActivity implements RegisterView {

    @BindView(R.id.register_username)
    TextInputLayout userName;
    @BindView(R.id.register_password)
    TextInputLayout password;
    @BindView(R.id.register_again_password)
    TextInputLayout rePassword;
    @BindView(R.id.register)
    Button register;

    private RegisterPresenter presenter;

    @Override
    public void initView() {
        presenter = new RegisterPresenter(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
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
                if (!TextUtils.isEmpty(s.toString())) {
                    password.setErrorEnabled(false);
                    rePassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        rePassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    password.setErrorEnabled(false);
                    rePassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.register();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
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
    public String getRePassword() {
        return rePassword.getEditText().getText().toString().trim();
    }

    @Override
    public void userNameNull() {
        userName.setErrorEnabled(true);
        userName.setError(getString(R.string.user_name_null));
    }

    @Override
    public void userNameLength() {
        userName.setErrorEnabled(true);
        userName.setError(getString(R.string.user_name_length));
    }

    @Override
    public void passWordNull() {
        password.setErrorEnabled(true);
        password.setError(getString(R.string.user_password_null));
    }

    @Override
    public void rePassWordNull() {
        rePassword.setErrorEnabled(true);
        rePassword.setError(getString(R.string.user_password_null));
    }

    @Override
    public void passwordNotEquals() {
        password.setErrorEnabled(true);
        password.setError(getString(R.string.password_not_equal));
        rePassword.setErrorEnabled(true);
        rePassword.setError(getString(R.string.password_not_equal));
    }

    @Override
    public void registerSuccess() {
        Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void registerError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
