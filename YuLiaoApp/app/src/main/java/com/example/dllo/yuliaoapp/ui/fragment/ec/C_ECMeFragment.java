package com.example.dllo.yuliaoapp.ui.fragment.ec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.ui.activity.ec.C_ECLoginActivityN;
import com.example.dllo.yuliaoapp.ui.fragment.C_AbsBaseFragment;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by dllo on 16/10/24.
 * @author 陈思宇
 * 环信个人页面
 */
public class C_ECMeFragment extends C_AbsBaseFragment{
    public static C_ECMeFragment newInstance() {
        Bundle args = new Bundle();
        C_ECMeFragment fragment = new C_ECMeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private Button mBtnMeOutLogin;
    @Override
    protected int setLayout() {
        return R.layout.c_fragment_ec_me;
    }

    @Override
    protected void  initViews() {
        mBtnMeOutLogin=byView(R.id.btn_ec_me_out_login);
    }

    @Override
    protected void initDatas() {
        /**
         * 退出登录
         */
        outLogin();
    }

    /**
     * 退出登录的方法
     */
    private void outLogin() {
        mBtnMeOutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EMClient.getInstance().logout(true);
                EMClient.getInstance().logout(true, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        startActivity(new Intent(context,C_ECLoginActivityN.class));
                        getActivity().finish();
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
}
