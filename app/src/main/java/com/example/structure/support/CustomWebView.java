package com.example.structure.support;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Rajesh Pradeep G on 11-11-2019
 */
public class CustomWebView extends WebViewClient {

    private String TAG = getClass().getSimpleName();

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    /**
     * selecting the link will redirect to web browser or load the local HTML file
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.i(TAG, "shouldOverrideUrlLoading: " + url);
            /*if (url.startsWith("mailto:")) {
                //Handle mail Urls
                startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse(url)));
            } else if (url.startsWith("tel:")) {
                //Handle telephony Urls
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(url)));
            } else {
                if (url.contains("file:///")) {
                    view.loadUrl(url);
                    webview_nscroll.scrollTo(0, 0);
                } else {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                }
            }*/
        view.loadUrl(url);
        return true;
    }

    /**
     * selecting the link will redirect to web browser or load the local HTML file
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Log.i(TAG, "shouldOverrideUrlLoading:request.getUrl() " + request.getUrl());
        final Uri uri = request.getUrl();
            /*if (uri.toString().startsWith("mailto:")) {
                //Handle mail Urls
                startActivity(new Intent(Intent.ACTION_SENDTO, uri));
            } else if (uri.toString().startsWith("tel:")) {
                //Handle telephony Urls
                startActivity(new Intent(Intent.ACTION_DIAL, uri));
            } else {
                if (request.getUrl().toString().contains("file:///")) {
                    view.loadUrl(request.getUrl().toString());
                    webview_nscroll.scrollTo(0, 0);
                } else {
                    //Handle Web Urls
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(request.getUrl().toString()));
                    startActivity(i);
                }
            }*/
        view.loadUrl(uri.toString());
        return true;
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return super.shouldInterceptRequest(view, request);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
        Log.i(TAG, "onReceivedSslError: " + handler.toString());
        Log.i(TAG, "onReceivedSslError: " + error.toString());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }
}
