package com.example.dllo.yuliaoapp.ui.activity.ec;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.ui.activity.C_AbsBaseActivity;
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
    private ImageView mIvFinish;
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 200:
                    String i= (String) msg.obj;
                    Toast.makeText(C_ECRegisterActivity.this, i, Toast.LENGTH_SHORT).show();
                    break;
                case 201:
                    String r= (String) msg.obj;
                    Toast.makeText(C_ECRegisterActivity.this, r, Toast.LENGTH_SHORT).show();
                    break;
                case 202:
                    String u= (String) msg.obj;
                    Toast.makeText(C_ECRegisterActivity.this, u, Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    });

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
        mIvFinish=byView(R.id.iv_ecregister_finish);
    }

    @Override
    protected void initData() {
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        mIvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void register() {
        final Message message=new Message();
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
                        message.what=200;
                        message.obj="注册成功";
                        handler.sendMessage(message);
                        finish();
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        mDialog.dismiss();
                        message.what=201;
                        message.obj="注册失败,用户名已存在";
                        handler.sendMessage(message);
                        //此处不可以设置toast  利用handler传回主线程 然后进行toast
                    }
                }else {
                    mDialog.dismiss();
                    message.what=202;
                    message.obj="两次输入的密码不一致,请重新输入";
                    handler.sendMessage(message);
                }
            }
        }).start();
    }
}
