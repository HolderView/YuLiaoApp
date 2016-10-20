package com.example.dllo.yuliaoapp.ui.activity;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dllo.yuliaoapp.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by dllo on 16/10/19.
 * 环信注册界面
 * @author 陈思宇
 */

public class C_ECRegisterActivity extends C_AbsBaseActivity {
    private ProgressDialog mDialog;
    private Button mBtnRegister;
    private EditText mEtUserNum;
    private EditText mEtPassword;
    private EditText mEtPasswordConfirm;

    @Override
    protected int setLayout() {
        return R.layout.c_activity_ecregister;
    }

    @Override
    protected void initViews() {
        mBtnRegister=byView(R.id.btn_ecregister);
        mEtUserNum=byView(R.id.et_ecregister_user_num);
        mEtPassword=byView(R.id.et_ecregister_password);
        mEtPasswordConfirm=byView(R.id.et_ecregister_confirm_password);
    }

    @Override
    protected void initData() {
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        mDialog=new ProgressDialog(this);
        mDialog.setMessage("注册中,请稍后...");
        mDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String userName=mEtUserNum.getText().toString().trim();
                String password=mEtPassword.getText().toString().trim();
                String passwordConfirm=mEtPasswordConfirm.getText().toString().trim();
                if (password.equals(passwordConfirm)){
                    try {
                        EMClient.getInstance().createAccount(userName,password);
                        mDialog.dismiss();
                        finish();
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        mDialog.dismiss();
                        Toast.makeText(C_ECRegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    mDialog.dismiss();
                    Toast.makeText(C_ECRegisterActivity.this, "两次输入的密码不一致,请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }
}
