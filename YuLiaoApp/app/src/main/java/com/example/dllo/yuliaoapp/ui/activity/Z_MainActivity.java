package com.example.dllo.yuliaoapp.ui.activity;



import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.ui.fragment.Z_ChatFragment;
import com.example.dllo.yuliaoapp.ui.fragment.Z_MapFragment;
import com.example.dllo.yuliaoapp.ui.fragment.Z_PersonFragment;
import com.example.dllo.yuliaoapp.ui.fragment.Z_VideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页Activity
 * @author 赵玲琳
 */
public class Z_MainActivity extends C_AbsBaseActivity {

    private ViewPager mainVp;
    private TabLayout mainTl;
    private List<Fragment> fragments;

    private boolean isExit = false;





    @Override
    protected int setLayout() {
        return R.layout.z_activity_main;
    }

    @Override
    protected void initViews() {
        mainVp = byView(R.id.main_vp);
        mainTl = byView(R.id.main_tl);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(Z_MapFragment.newInstance());
        fragments.add(Z_ChatFragment.newInstance());
        fragments.add(Z_VideoFragment.newInstance());
        fragments.add(Z_PersonFragment.newInstance());

        VpSetAdapter();
        mainTl.setupWithViewPager(mainVp);
        mainTl.setTabTextColors(Color.BLACK,Color.parseColor("#56abe4"));
        mainTl.setSelectedTabIndicatorColor(Color.parseColor("#56abe4"));
        mainTl.getTabAt(1).setText(getResources().getString(R.string.chat)).setIcon(R.drawable.selector_chat);
        mainTl.getTabAt(0).setText(getResources().getString(R.string.map)).setIcon(R.drawable.selector_map);
        mainTl.getTabAt(2).setText(getResources().getString(R.string.video)).setIcon(R.drawable.selector_video);
        mainTl.getTabAt(3).setText(getResources().getString(R.string.person)).setIcon(R.drawable.selector_person);

    }

    private void VpSetAdapter() {
        mainVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }

    /**
     * 双击退出界面Handler
     * L-doing
     */

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    /**
     * 此方法为双击退出的方法 (调用返回键进行切换)
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 双击退出程序
     */
    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
//            System.exit(0);
        }
    }

}
