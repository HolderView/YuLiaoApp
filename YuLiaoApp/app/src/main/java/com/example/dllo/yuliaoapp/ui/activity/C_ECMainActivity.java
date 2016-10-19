package com.example.dllo.yuliaoapp.ui.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dllo.yuliaoapp.R;

/**
 * Created by dllo on 16/10/19.
 * @author 陈思宇
 * 环信聊天主页面
 */
public class C_ECMainActivity extends C_AbsBaseActivity {
    private Button mBtnLogin;
    private TextView mTvRegister;
    private EditText mEtUserName;
    private EditText mEtPassword;

    @Override
    protected int setLayout() {
        return R.layout.c_activity_ecmain;
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

    }
}
