package com.example.dllo.yuliaoapp.ui.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        fragments.add(Z_ChatFragment.newInstance());
        fragments.add(Z_MapFragment.newInstance());
        fragments.add(Z_VideoFragment.newInstance());
        fragments.add(Z_PersonFragment.newInstance());

        VpSetAdapter();
        mainTl.setupWithViewPager(mainVp);
        mainTl.setTabTextColors(Color.BLACK,Color.parseColor("#56abe4"));
        mainTl.setSelectedTabIndicatorColor(Color.parseColor("#56abe4"));
        mainTl.getTabAt(0).setText(getResources().getString(R.string.chat)).setIcon(R.drawable.selector_chat);
        mainTl.getTabAt(1).setText(getResources().getString(R.string.map)).setIcon(R.drawable.selector_map);
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
     * 最后按下的时间
     */
    private long lastTime ;

    /**
     * 按二次返回键退出应用
     */
    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();

        if(currentTime-lastTime<2*1000){
            super.onBackPressed();
        }else {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            lastTime=currentTime;
        }

    }

}
