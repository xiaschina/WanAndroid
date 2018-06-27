package com.bjhl.plugins.android.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bjhl.plugins.network.Request;
import com.bjhl.plugins.network.interfac.RxObserver;
import com.bjhl.plugins.android.R;
import com.bjhl.plugins.android.base.BaseFragment;
import com.bjhl.plugins.android.bean.UserModel;
import com.bjhl.plugins.android.ui.activity.CollectActivity;
import com.bjhl.plugins.android.ui.activity.LoginActivity;
import com.bjhl.plugins.android.util.Constants;
import com.bjhl.plugins.android.util.ImageLoader;
import com.bjhl.plugins.android.util.MessageEvent;
import com.bjhl.plugins.android.util.SharePreferenceUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

/**
 * Created by XIAS on 2018/6/22.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.mine_avatar)
    ImageView avatar;
    @BindView(R.id.mine_username)
    TextView name;
    @BindView(R.id.mine_collect_contrainer)
    RelativeLayout collectContrainer;
    @BindView(R.id.mine_about_contrainer)
    RelativeLayout aboutContrainer;
    @BindView(R.id.mine_quit_contrainer)
    RelativeLayout quitContrainer;

    private UserModel userModel;
    private boolean isLogin;

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        getData();
    }

    private void getData() {
        userModel = JSONObject.parseObject(new SharePreferenceUtil(getContext()).getStringValue(Constants.USER, null), UserModel.class);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (userModel == null || TextUtils.isEmpty(userModel.username) || TextUtils.isEmpty(userModel.password)) {
            unLogin();
        } else {
            unLogin();
            Map<String, String> params = new HashMap<>();
            params.put("username", userModel.username);
            params.put("password", userModel.password);
            Request.post(Constants.POSTLOGIN, params, new RxObserver() {
                @Override
                public void onSuccess(Object o) {
                    new SharePreferenceUtil(getContext()).putString(Constants.USER, (String) o);
                    Log.d("user", (String) o);
                    login();
                }

                @Override
                public void onFailed(Throwable e) {
                }

                @Override
                public void onSubscribes(Disposable d) {

                }
            });
        }
        name.setOnClickListener(this);
        collectContrainer.setOnClickListener(this);
        aboutContrainer.setOnClickListener(this);
        quitContrainer.setOnClickListener(this);
    }

    //未登录
    private void unLogin() {
        isLogin = false;
        ImageLoader.with(this).circleCrop().load(getResources().getDrawable(R.drawable.default_avatar), avatar);
        name.setText(R.string.login);
    }

    //登录
    private void login() {
        isLogin = true;
        if (TextUtils.isEmpty(userModel.icon))
            ImageLoader.with(this).circleCrop().load(getResources().getDrawable(R.drawable.avatar), avatar);
        else
            ImageLoader.with(this).circleCrop().load(userModel.icon, avatar);
        name.setText(userModel.username);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_username:
                if (!isLogin) {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.mine_collect_contrainer:
                /*if (isLogin) {

                }*/
                startActivity(new Intent(getContext(), CollectActivity.class));
                break;
            case R.id.mine_about_contrainer:
                break;
            case R.id.mine_quit_contrainer:
                if (!isLogin) {
                    Toast.makeText(getContext(), getResources().getString(R.string.not_login), Toast.LENGTH_SHORT).show();
                    return;
                }
                new SharePreferenceUtil(getContext()).putString(Constants.USER, null);
                unLogin();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getEventTyoe()) {
            case Constants.LOGIN_SUCCESS:
                getData();
                login();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
