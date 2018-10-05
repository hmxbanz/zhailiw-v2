package com.zhailiw.app.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;
import com.zhailiw.app.R;
import com.zhailiw.app.webview.MyWebChromeClient;
import com.zhailiw.app.webview.MyWebViewClient;

public class WorkFragment extends BaseFragment  {
    public static WorkFragment instance = null;
    private WebView mWebView;
    public PullToRefreshWebView mPullWebView;
    private MyWebChromeClient myWebChromeClient;
    private String url;

    public static WorkFragment getInstance() {
        if (instance == null) {
            instance = new WorkFragment();
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_company;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void initViews() {
        mPullWebView = (PullToRefreshWebView) findView(R.id.webview_help);//new PullToRefreshWebView(this);

    }

    @Override
    protected void initData() {
        mPullWebView.setMode(PullToRefreshBase.Mode.DISABLED);
        mWebView = mPullWebView.getRefreshableView();
        myWebChromeClient=new MyWebChromeClient(getActivity());
        mWebView.setWebChromeClient(myWebChromeClient);
        mWebView.setWebViewClient(new MyWebViewClient(getActivity()));
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setAllowContentAccess(true);
        //mWebView.getSettings().setUserAgentString("Android Chrome/37.0.0.0 Mobile Safari/537.36");
        mWebView.getSettings().setAppCacheEnabled(true);
        //设置缓存模式
        url = "http://api2.zhailiw.com/home/getnews?newsId=1";
        TextView txtTitle = (TextView) findView(R.id.txt_title);
        txtTitle.setText("工艺展示");
        findView(R.id.img_left).setVisibility(View.INVISIBLE);
        mWebView.loadUrl(url);
    }

}
