package com.example.dllo.yuliaoapp.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dllo.yuliaoapp.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.NetUtils;

/**
 * Created by dllo on 16/10/19.
 * 环信聊天主页面
 */
public class C_ECMainActivity extends C_AbsBaseActivity {
    private Button mBtnOutLogin;

    @Override
    protected int setLayout() {
        //判断sdk是否登录成功过 并没有退出和被踢 否则跳转到登录界面
        if (!EMClient.getInstance().isLoggedInBefore()) {
            goTo(C_ECMainActivity.this, C_ECLoginActivity.class);
            finish();
        }
        return R.layout.c_activity_ecmain;
    }

    @Override
    protected void initViews() {
        mBtnOutLogin = byView(R.id.btn_ecmain_out_login);
    }

    @Override
    protected void initData() {
        EMClient.getInstance().addConnectionListener(new MyConnectionListener());
        mBtnOutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EMClient.getInstance().logout(true);
                EMClient.getInstance().logout(true, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        goTo(C_ECMainActivity.this,C_ECLoginActivity.class);
                        finish();
                    }

                    @Override
                    public void onError(int i, String s) {

                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });

    }

    //实现conncectionListener接口 注册连接状态监听
    private class MyConnectionListener implements EMConnectionListener {

        @Override
        public void onConnected() {

        }

        @Override
        public void onDisconnected(final int i) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (i == EMError.USER_REMOVED) {
                        Toast.makeText(C_ECMainActivity.this, "账号已经被移除", Toast.LENGTH_SHORT).show();
                        goTo(C_ECMainActivity.this, C_ECLoginActivity.class);
                    } else if (i == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                        Toast.makeText(C_ECMainActivity.this, "账号已经在其它设备登录", Toast.LENGTH_SHORT).show();
                        goTo(C_ECMainActivity.this, C_ECLoginActivity.class);
                    } else {
                        if (NetUtils.hasNetwork(C_ECMainActivity.this)) {
                            Toast.makeText(C_ECMainActivity.this, "连接不到聊天服务器", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(C_ECMainActivity.this, "当前网络不可用,请检查网络设置", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
}
