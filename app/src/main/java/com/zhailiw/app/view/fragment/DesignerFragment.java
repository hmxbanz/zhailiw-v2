package com.zhailiw.app.view.fragment;

import android.webkit.WebView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;
import com.zhailiw.app.R;
import com.zhailiw.app.webview.MyWebChromeClient;
import com.zhailiw.app.webview.MyWebViewClient;

public class DesignerFragment extends BaseFragment  {
    public static DesignerFragment instance = null;
    private WebView mWebView;
    public PullToRefreshWebView mPullWebView;
    private MyWebChromeClient myWebChromeClient;
    private String url;

    public static DesignerFragment getInstance() {
        if (instance == null) {
            instance = new DesignerFragment();
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_designer;
    }

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
//        mWebView.getSettings().setLoadWithOverviewMode(false);
//        mWebView.getSettings().setLoadWithOverviewMode(false);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setAllowContentAccess(true);
        //mWebView.getSettings().setUserAgentString("Android Chrome/37.0.0.0 Mobile Safari/537.36");
        mWebView.getSettings().setAppCacheEnabled(true);
        //设置缓存模式
        url = "http://api2.zhailiw.com/home/getnews?newsId=3";

        mWebView.loadUrl(url);
    }

}
