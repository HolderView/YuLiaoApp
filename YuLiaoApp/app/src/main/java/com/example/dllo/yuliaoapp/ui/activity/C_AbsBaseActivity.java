package com.example.dllo.yuliaoapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.dllo.yuliaoapp.R;

/**
 * Created by dllo on 16/10/19.
 * @author 陈思宇
 * Activity基类
 */
public abstract class C_AbsBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //替换状态栏背景颜色的方法
        Window window=getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        //Color.parseColor 将不是int类型的颜色转换为int类型
        //为状态栏添加新的颜色
        window.setStatusBarColor(Color.parseColor("#00000000"));
        setContentView(setLayout());
        initViews();
        initData();
    }

    protected abstract int setLayout();

    protected abstract void initViews();

    protected abstract void initData();

    protected <T extends View> T byView(int resId) {
        return (T) findViewById(resId);
    }

    protected void goTo(Context from, Class<? extends C_AbsBaseActivity> to) {
        startActivity(new Intent(from, to));
        overridePendingTransition(R.anim.c_activity_anim_in,R.anim.c_activity_anim_out);
    }

    protected void goTo(Context from, Class<? extends C_AbsBaseActivity> to, Bundle bundle) {
        Intent intent = new Intent(from, to);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.c_activity_anim_in,R.anim.c_activity_anim_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.c_activity_anim_in,R.anim.c_activity_anim_out);
    }
}
