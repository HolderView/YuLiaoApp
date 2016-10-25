package com.example.dllo.yuliaoapp.ui.activity.ec;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.ui.activity.C_AbsBaseActivity;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

/**
 * Created by dllo on 16/10/22.
 * 环信好友列表
 * @author 陈思宇
 */
public class C_ECFriendListActivity extends C_AbsBaseActivity {
    private EditText mEtAddFriends;
    private Button mBtnAddFriends;
    @Override
    protected int setLayout() {
        return R.layout.c_activity_friend_list;
    }

    @Override
    protected void initViews() {
        mEtAddFriends=byView(R.id.et_ecfriends);
        mBtnAddFriends=byView(R.id.btn_ecfriends_add);
    }

    @Override
    protected void initData() {
        try {
            List<String> usernames= EMClient.getInstance().contactManager().getAllContactsFromServer();
            Log.d("C_ECFriendListActivity", "usernames.size():" + usernames.size());
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        mBtnAddFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=mEtAddFriends.getText().toString().trim();
                try {
                    //添加好友
                    EMClient.getInstance().contactManager().addContact(userName,"");
                    Toast.makeText(C_ECFriendListActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Toast.makeText(C_ECFriendListActivity.this, "未找到此id", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //监听好友状态事件
        EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {
            @Override
            public void onContactAdded(String s) {
                //好友请求被同意
            }

            @Override
            public void onContactDeleted(String s) {
                //好友请求被拒绝
            }

            @Override
            public void onContactInvited(String s, String s1) {
                //收到好友邀请
            }

            @Override
            public void onContactAgreed(String s) {
                //被删除时回调此方法
            }

            @Override
            public void onContactRefused(String s) {
                //增加了联系人时回调此方法
                Log.d("qqq", s);
            }
        });

    }
}
