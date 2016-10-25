package com.example.dllo.yuliaoapp.ui.activity.ec;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;
import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.model.db.EaseUser;
import com.example.dllo.yuliaoapp.model.db.InviteMessage;
import com.example.dllo.yuliaoapp.model.db.InviteMessgeDao;
import com.example.dllo.yuliaoapp.model.db.UserDao;
import com.example.dllo.yuliaoapp.ui.activity.C_AbsBaseActivity;
import com.example.dllo.yuliaoapp.ui.app.C_MyApp;
import com.example.dllo.yuliaoapp.ui.fragment.ec.C_ECAddressFragment;
import com.example.dllo.yuliaoapp.ui.fragment.ec.C_ECConversationFragment;
import com.example.dllo.yuliaoapp.ui.fragment.ec.C_ECMeFragment;
import com.example.dllo.yuliaoapp.ui.fragment.ec.C_ECNewsFriendsMsgFragment;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.NetUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/10/24.
 */
public class C_ECMainActivityN extends C_AbsBaseActivity {
    private InviteMessgeDao inviteMessgeDao;
    private UserDao userDao;
    private List<Fragment> fragments = new ArrayList<>();
    private String[] titles = {"聊天", "联系人", "申请", "个人"};
    private ViewPager mVpMain;
    private TabLayout mTlMain;
    private ECMainVpAdapter adapter=new ECMainVpAdapter(getSupportFragmentManager());

    @Override
    protected int setLayout() {
        //注册联系人变动监听
        EMClient.getInstance().contactManager().setContactListener(new MyContactListener());
        return R.layout.c_activity_ec_main;
    }

    @Override
    protected void initViews() {
        mVpMain = byView(R.id.vp_ec_main);
        mTlMain = byView(R.id.tl_ec_main);
        inviteMessgeDao = new InviteMessgeDao(C_ECMainActivityN.this);
        userDao = new UserDao(C_ECMainActivityN.this);

    }

    @Override
    protected void initData() {
        /**
         * 绑定Fragment
         */
        initVp();
        /**
         * 实现实时监听服务器状态的接口
         */
        EMClient.getInstance().addConnectionListener(new MyConnectionListener());

    }

    /**
     * viewPager 4个页面Fragment
     */
    private void initVp() {
        fragments.add(C_ECConversationFragment.newInstance());
        fragments.add(C_ECAddressFragment.newInstance());
        fragments.add(C_ECNewsFriendsMsgFragment.newInstance());
        fragments.add(C_ECMeFragment.newInstance());
        mVpMain.setOffscreenPageLimit(0);
        mVpMain.setAdapter(adapter);

        mTlMain.setupWithViewPager(mVpMain);
        mTlMain.setTabTextColors(Color.BLACK,Color.parseColor("#56abe4"));
        mTlMain.setSelectedTabIndicatorColor(Color.parseColor("#56abe4"));
        mTlMain.getTabAt(0).setIcon(R.drawable.c_selector_ec_conversation);
        mTlMain.getTabAt(1).setIcon(R.drawable.c_selector_ec_address);
        mTlMain.getTabAt(2).setIcon(R.drawable.c_selector_ec_new_friends);
        mTlMain.getTabAt(3).setIcon(R.drawable.c_selector_ec_me);
    }

    public class ECMainVpAdapter extends FragmentPagerAdapter {

        public ECMainVpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments == null ? null : fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
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
                        Toast.makeText(C_ECMainActivityN.this, "账号已经被移除", Toast.LENGTH_SHORT).show();
                        goTo(C_ECMainActivityN.this, C_ECLoginActivityN.class);
                    } else if (i == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                        Toast.makeText(C_ECMainActivityN.this, "账号已经在其它设备登录", Toast.LENGTH_SHORT).show();
                        goTo(C_ECMainActivityN.this, C_ECLoginActivityN.class);
                    } else {
                        if (NetUtils.hasNetwork(C_ECMainActivityN.this)) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1000);
                                        Toast.makeText(C_ECMainActivityN.this, "连接不到聊天服务器", Toast.LENGTH_SHORT).show();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(C_ECMainActivityN.this, "当前网络不可用,请检查网络设置", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
    /***
     * 好友变化listener
     *
     */
    public class MyContactListener implements EMContactListener {

        @Override
        public void onContactAdded(final String username) {
            // 保存增加的联系人
            Map<String, EaseUser> localUsers = C_MyApp.getInstance().getContactList();
            Map<String, EaseUser> toAddUsers = new HashMap<String, EaseUser>();
            EaseUser user = new EaseUser(username);
            // 添加好友时可能会回调added方法两次
            if (!localUsers.containsKey(username)) {
                userDao.saveContact(user);
            }
            toAddUsers.put(username, user);
            localUsers.putAll(toAddUsers);
            runOnUiThread(new Runnable(){

                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "增加联系人：+"+username, Toast.LENGTH_SHORT).show();
                }


            });


        }

        @Override
        public void onContactDeleted(final String username) {
            // 被删除
            Map<String, EaseUser> localUsers = C_MyApp.getInstance().getContactList();
            localUsers.remove(username);
            userDao.deleteContact(username);
            inviteMessgeDao.deleteMessage(username);

            runOnUiThread(new Runnable(){

                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "删除联系人：+"+username, Toast.LENGTH_SHORT).show();
                }


            });

        }

        @Override
        public void onContactInvited(final String username, String reason) {
            // 接到邀请的消息，如果不处理(同意或拒绝)，掉线后，服务器会自动再发过来，所以客户端不需要重复提醒
            List<InviteMessage> msgs = inviteMessgeDao.getMessagesList();

            for (InviteMessage inviteMessage : msgs) {
                if (inviteMessage.getGroupId() == null && inviteMessage.getFrom().equals(username)) {
                    inviteMessgeDao.deleteMessage(username);
                }
            }
            // 自己封装的javabean
            InviteMessage msg = new InviteMessage();
            msg.setFrom(username);
            msg.setTime(System.currentTimeMillis());
            msg.setReason(reason);

            // 设置相应status
            msg.setStatus(InviteMessage.InviteMesageStatus.BEINVITEED);
            notifyNewIviteMessage(msg);
            runOnUiThread(new Runnable(){

                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "收到好友申请：+"+username, Toast.LENGTH_SHORT).show();
                }


            });

        }

        @Override
        public void onContactAgreed(final String username) {
            List<InviteMessage> msgs = inviteMessgeDao.getMessagesList();
            for (InviteMessage inviteMessage : msgs) {
                if (inviteMessage.getFrom().equals(username)) {
                    return;
                }
            }
            // 自己封装的javabean
            InviteMessage msg = new InviteMessage();
            msg.setFrom(username);
            msg.setTime(System.currentTimeMillis());

            msg.setStatus(InviteMessage.InviteMesageStatus.BEAGREED);
            notifyNewIviteMessage(msg);
            runOnUiThread(new Runnable(){

                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "好友申请同意：+"+username, Toast.LENGTH_SHORT).show();
                }


            });

        }

        @Override
        public void onContactRefused(String username) {
            // 参考同意，被邀请实现此功能,demo未实现
            Log.d(username, username + "拒绝了你的好友请求");
        }
    }
    /**
     * 保存并提示消息的邀请消息
     * @param msg
     */
    private void notifyNewIviteMessage(InviteMessage msg){
        if(inviteMessgeDao == null){
            inviteMessgeDao = new InviteMessgeDao(C_ECMainActivityN.this);
        }
        inviteMessgeDao.saveMessage(msg);
        //保存未读数，这里没有精确计算
        inviteMessgeDao.saveUnreadMessageCount(1);
        // 提示有新消息
        //响铃或其他操作
    }
    private void logout() {
        final ProgressDialog pd = new ProgressDialog(C_ECMainActivityN.this);
        String st = getResources().getString(R.string.Are_logged_out);
        pd.setMessage(st);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        C_MyApp.getInstance().logout(false,new EMCallBack() {

            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        pd.dismiss();
                        // 重新显示登陆页面
                        finish();
                        startActivity(new Intent(C_ECMainActivityN.this, C_ECLoginActivityN.class));

                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        pd.dismiss();
                        Toast.makeText(C_ECMainActivityN.this, "unbind devicetokens failed", Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });
    }
}
