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

public class CompanyFragment extends Fragment  {
    private View view;
    public static CompanyFragment instance = null;
    private WebView mWebView;
    public PullToRefreshWebView mPullWebView;
    private final static int menuService=1002;
    private long lastBackPressTime;
    private MyWebChromeClient myWebChromeClient;
    private String url;
    private TextView txtRight;

    public static CompanyFragment getInstance() {
        if (instance == null) {
            instance = new CompanyFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_company, null);
        initViews();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initViews() {
        mPullWebView = (PullToRefreshWebView) view.findViewById(R.id.webview_help);//new PullToRefreshWebView(this);
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
        url = "http://api2.zhailiw.com/home/getnews?newsId=2";
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
        txtTitle.setText("合作品牌");
        view.findViewById(R.id.img_left).setVisibility(View.INVISIBLE);
        mWebView.loadUrl(url);
    }

}
