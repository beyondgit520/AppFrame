package com.app.base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.app.R;

public class WebActivity extends BaseActivity {
    private ProgressBar progressBar;
    private WebView webView;
    private WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra("title"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        webView = (WebView) findViewById(R.id.webView);
        webSettings = webView.getSettings();
        init();
        webView.setWebViewClient(new WebViewClient() {
            @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }

            @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        String url = getIntent().getStringExtra("url");
        webView.loadUrl(url);
    }

    private void init() {
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setGeolocationEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
    }

    @Override public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
