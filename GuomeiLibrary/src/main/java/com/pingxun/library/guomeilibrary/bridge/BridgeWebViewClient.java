package com.pingxun.library.guomeilibrary.bridge;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 *
 * Created by hbl on 2017/5/9.
 */
public class BridgeWebViewClient extends WebViewClient {
    final String TAG = BridgeWebViewClient.class.getSimpleName();
    private WebViewBridge webViewBridge;

    public BridgeWebViewClient(WebViewBridge webViewBridge) {
        this.webViewBridge = webViewBridge;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (url.indexOf("tel:") == 0) {
            return true;
        }
        if (url.startsWith(BridgeUtil.RETURN_DATA)) { // 如果是返回数据
            webViewBridge.handlerReturnData(url);
            return true;
        } else if (url.startsWith(BridgeUtil.OVERRIDE_SCHEMA)) { //
            webViewBridge.flushMessageQueue();
            return true;
        } else {
            view.loadUrl(url);
            return true;
        }
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        InjectJS(view);
    }
    private void InjectJS(WebView view) {
        if (WebViewBridge.toLoadJs != null) {
            BridgeUtil.webViewLoadLocalJs(view, WebViewBridge.toLoadJs);
        }
        if (webViewBridge.getStartupMessage() != null) {
            for (Message m : webViewBridge.getStartupMessage()) {
                webViewBridge.dispatchMessage(m);
            }
            webViewBridge.setStartupMessage(null);
        }
    }

}