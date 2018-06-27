package com.bjhl.plugins.android.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bjhl.plugins.android.R;
import com.bjhl.plugins.android.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by XIAS on 2018/6/25.
 */

public class NewsDetailActivity extends BaseActivity {

    @BindView(R.id.news_detail_web_view)
    WebView webView;
    @BindView(R.id.news_detail_back)
    ImageView back;
    @BindView(R.id.news_detail_title)
    TextView tvTitle;
    @BindView(R.id.news_detail_progress)
    ProgressBar progressBar;

    private String url;

    public static final String URL = "url";

    @Override
    public void initView() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (getIntent() == null)
            finish();
        if (getIntent().getExtras().containsKey(URL))
            url = getIntent().getStringExtra(URL);
        if (TextUtils.isEmpty(url))
            finish();
        loadUrl();
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.color_ea986c)
                .init();
    }

    private void loadUrl() {
        webView.loadUrl(url);
        //设置不用系统浏览器打开,直接显示在当前Webview
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //设置WebChromeClient类
        webView.setWebChromeClient(new WebChromeClient() {
            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                tvTitle.setText(title);
            }

            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress >= 100) {
                    progressBar.setProgress(0);
                } else {
                    progressBar.setProgress(newProgress);
                }
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    //点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
