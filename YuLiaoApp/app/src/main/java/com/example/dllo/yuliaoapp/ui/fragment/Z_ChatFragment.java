package com.example.dllo.yuliaoapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.ui.activity.ec.C_ECLoginActivityN;
import com.example.dllo.yuliaoapp.ui.activity.ec.C_ECMainActivity;
import com.example.dllo.yuliaoapp.ui.activity.ec.C_ECMainActivityN;

/**
 * Created by dllo on 16/10/19.
 * @author 赵玲琳
 * 聊天页面
 */
public class Z_ChatFragment extends C_AbsBaseFragment {
    private Button mBtnLogin;
    public static Z_ChatFragment newInstance() {
        Bundle args = new Bundle();
        Z_ChatFragment fragment = new Z_ChatFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.z_fragment_chat;
    }

    @Override
    protected void initViews() {
        mBtnLogin=byView(R.id.btn_chat_login);
    }

    @Override
    protected void initDatas() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,C_ECLoginActivityN.class));
            }
        });
    }
}
