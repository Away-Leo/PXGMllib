package com.pingxun.library.guomeilibrary.custom;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * Created by hbl on 2017/5/9.
 */

public class CustomWebChormeClient extends WebChromeClient {
    private ProgressBar mProgressBar;

    public CustomWebChormeClient(ProgressBar progressBar) {
        mProgressBar = progressBar;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (mProgressBar == null)  throw new IllegalArgumentException("Cannot add a null child view to a ViewGroup");
        if (newProgress == 100) {
            mProgressBar.setVisibility(View.GONE);
        } else {
            if (mProgressBar.getVisibility() == View.GONE) {
                mProgressBar.setVisibility(View.VISIBLE);
            }
            mProgressBar.setProgress(newProgress);
        }
    }
}
