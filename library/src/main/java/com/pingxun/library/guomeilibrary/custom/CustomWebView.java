package com.pingxun.library.guomeilibrary.custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.pingxun.library.guomeilibrary.R;


/**
 * Created by hbl on 2017/5/9.
 */

public class CustomWebView extends LinearLayout {

    static final String TAG = CustomWebView.class.getSimpleName();
    WebView mWebView;
    Context mContext;
    ProgressBar mProgerssBar;

    public CustomWebView(Context context) {
        super(context);
        init(context);
    }


    public CustomWebView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomWebView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setOrientation(LinearLayout.VERTICAL);
        initProcessBar();
        initWebView();
        mWebView.addView(mProgerssBar, 0);
        addView(mWebView, new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }


    private void initWebView() {
        mWebView = new WebView(mContext);
        WebSettings settings = mWebView.getSettings();
        settings.setSupportZoom(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        // 判断系统版本是不是5.0或之上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //让系统不屏蔽混合内容
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        mWebView.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        mWebView.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
                        mWebView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });
        mWebView.setWebChromeClient(new CustomWebChormeClient(mProgerssBar));
        mWebView.setWebViewClient(new CustomWebViewClient());
    }

    private void initProcessBar() {
        mProgerssBar = new ProgressBar(mContext, null, android.R.attr.progressBarStyleHorizontal);
        mProgerssBar.setBackgroundColor(Color.WHITE);
        Drawable drawable1 = getResources().getDrawable(R.drawable.progressbar_drawable_update);
        mProgerssBar.setProgressDrawable(drawable1);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.height = 10;
        mProgerssBar.setLayoutParams(params);
    }

    public void addProgressBar(ProgressBar progressBar) {
        mProgerssBar = progressBar;
        mWebView.removeViewAt(0);
        mWebView.addView(mProgerssBar, 0);
        mWebView.setWebChromeClient(new CustomWebChormeClient(mProgerssBar));
    }

    public WebView getWebView() {
        return mWebView;
    }
}
