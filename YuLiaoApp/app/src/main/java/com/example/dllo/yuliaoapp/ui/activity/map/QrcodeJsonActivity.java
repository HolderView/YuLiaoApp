package com.example.dllo.yuliaoapp.ui.activity.map;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.ui.activity.C_AbsBaseActivity;

/**
 * Created by Yu on 16/11/4.
 */
public class QrcodeJsonActivity extends C_AbsBaseActivity {

    private WebView QrcodeWb;



    @Override
    protected int setLayout() {
        return R.layout.l_qrcode_activity;
    }

    @Override
    protected void initViews() {
        QrcodeWb = byView(R.id.l_qrcode_activity_wb);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
       String strurl =  intent.getStringExtra("wb");
        QrcodeWb.loadUrl(strurl);
        WebSettings set = QrcodeWb.getSettings();
//
//        webView.loadUrl(url2);
//
//
        // 让WebView能够执行javaScript
          set.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        set.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        set.setAppCacheEnabled(true);
        // 设置缓存模式,一共有四种模式
        set.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置缓存路径
//        webSettings.setAppCachePath("");
        // 支持缩放(适配到当前屏幕)
        set.setSupportZoom(true);
        // 将图片调整到合适的大小
        set.setUseWideViewPort(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        set.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        set.setDisplayZoomControls(true);
        // 设置默认字体大小
        set.setDefaultFontSize(12);




    }
}
