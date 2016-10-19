package com.example.dllo.yuliaoapp.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.yuliaoapp.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by dllo on 16/10/19.
 * @author 陈思宇
 * 环信聊天登录页面
 */
public class C_ECLoginActivity extends C_AbsBaseActivity {
    //登录
    private Button mBtnLogin;
    //注册
    private TextView mTvRegister;
    //用户名输入
    private EditText mEtUserName;
    //密码输入
    private EditText mEtPassword;
    //弹出框
    private ProgressDialog mDialog;

    @Override
    protected int setLayout() {
        return R.layout.c_activity_eclogin;
    }

    @Override
    protected void initViews() {
        mBtnLogin=byView(R.id.btn_ecmain_login);
        mTvRegister=byView(R.id.tv_ecmain_new_user);
        mEtUserName=byView(R.id.et_ecmain_user_num);
        mEtPassword=byView(R.id.et_ecmain_password);
    }

    @Override
    protected void initData() {
        //点击登录
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        //点击跳转到注册页面
        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    //注册
    private void register() {

    }
    //登录
    private void login() {
        mDialog=new ProgressDialog(this);
        mDialog.setMessage("正在登录,请稍后....");
        mDialog.show();
        String userName=mEtUserName.getText().toString().trim();
        String password=mEtPassword.getText().toString().trim();
        EMClient.getInstance().login(userName, password, new EMCallBack() {
            /**
             * 登录成功后的回调
             */
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDialog.dismiss();
                        //加载所有会话到内存
                        EMClient.getInstance().chatManager().loadAllConversations();
                        //加载所有群组到内存 如果使用了群组的话
                        //EMClient.getInstance().groupManager().loadAllGroups();
                        //登录成功跳转界面
                        goTo(C_ECLoginActivity.this,C_ECMainActivity.class);
                        finish();
                    }
                });
            }

            /**
             * 登录错误的回调
             * @param i 返回的错误码
             * @param s 返回的错误信息
             */
            @Override
            public void onError(final int i, final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDialog.dismiss();
                        Log.d("C_ECLoginActivity", "登录失败 Error code:" + i + ",message:" + s);
                        /**
                         * 关于错误码可以参考官方api
                         */
                        switch (i){
                            //网络异常
                            case 2:
                                Toast.makeText(C_ECLoginActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                                break;
                            //无效的用户名
                            case 101:
                                Toast.makeText(C_ECLoginActivity.this, "无效的用户名", Toast.LENGTH_SHORT).show();
                                break;
                            //无效的密码
                            case 102:
                                Toast.makeText(C_ECLoginActivity.this, "无效的密码", Toast.LENGTH_SHORT).show();
                                break;
                            //用户认证失败
                            case 202:
                                Toast.makeText(C_ECLoginActivity.this, "用户认证失败,用户名或密码错误", Toast.LENGTH_SHORT).show();
                                break;
                            //用户不存在
                            case 204:
                                Toast.makeText(C_ECLoginActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                                break;
                            //无法访问到服务器
                            case 300:
                                Toast.makeText(C_ECLoginActivity.this, "无法访问到服务器", Toast.LENGTH_SHORT).show();
                                break;
                            //等待服务器响应超时
                            case 301:
                                Toast.makeText(C_ECLoginActivity.this, "等待服务器响应超时", Toast.LENGTH_SHORT).show();
                                break;
                            //服务器繁忙
                            case 302:
                                Toast.makeText(C_ECLoginActivity.this, "服务器繁忙", Toast.LENGTH_SHORT).show();
                                break;
                            //未知的服务器异常
                            case 303:
                                Toast.makeText(C_ECLoginActivity.this, "未知的服务器异常", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

}
