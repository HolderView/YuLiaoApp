package com.example.dllo.yuliaoapp.ui.activity;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.tools.L_QrCodeUtils;
import com.example.dllo.yuliaoapp.ui.activity.game.C_2048_Activity;
import com.example.dllo.yuliaoapp.ui.activity.game.C_Snake_Activity;
import com.example.dllo.yuliaoapp.ui.activity.map.L_GaodeActivity;
import com.example.dllo.yuliaoapp.ui.activity.map.L_QrCodeActivity;
import com.example.dllo.yuliaoapp.ui.activity.map.QrcodeJsonActivity;
import com.example.dllo.yuliaoapp.ui.app.C_MyApp;
import com.example.dllo.yuliaoapp.ui.fragment.Z_ChatFragment;
import com.example.dllo.yuliaoapp.ui.fragment.Z_MapFragment;
import com.example.dllo.yuliaoapp.ui.fragment.Z_PersonFragment;
import com.example.dllo.yuliaoapp.ui.fragment.Z_VideoFragment;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.detector.MultiDetector;
import com.hyphenate.util.Utils;
import com.xys.libzxing.zxing.activity.CaptureActivity;

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
    private ImageView qrcodeImg;
    private ImageView mapImg;

    private DrawerReceiver drawerReceiver;
    private MultiFormatReader multiFormatReader;
    private LinearLayout mL2048;
    private LinearLayout mLSnake;
    private static DrawerLayout mainDl;
    private static LinearLayout mainLl;

<<<<<<< HEAD
=======
    @Override
    protected int setLayout() {
        return R.layout.z_activity_main;
    }
>>>>>>> feature/天气优化


    @Override
    protected void initData(Bundle savedInstanceState) {
        DrawerControl();
        fragments = new ArrayList<>();
        fragments.add(Z_MapFragment.newInstance());
        fragments.add(Z_ChatFragment.newInstance());
        fragments.add(Z_VideoFragment.newInstance());
        //fragments.add(Z_PersonFragment.newInstance());

        VpSetAdapter();
        mainTl.setupWithViewPager(mainVp);
        mainTl.setTabTextColors(Color.BLACK,Color.parseColor("#56abe4"));
        mainTl.setSelectedTabIndicatorColor(Color.parseColor("#56abe4"));
        mainTl.getTabAt(1).setText(getResources().getString(R.string.chat)).setIcon(R.drawable.selector_chat);
        mainTl.getTabAt(0).setText(getResources().getString(R.string.map)).setIcon(R.drawable.selector_map);
        mainTl.getTabAt(2).setText(getResources().getString(R.string.video)).setIcon(R.drawable.selector_video);
        //mainTl.getTabAt(3).setText(getResources().getString(R.string.person)).setIcon(R.drawable.selector_person);
        mainVp.setOffscreenPageLimit(3);


        initGame();
        initTl();
    }


    /**
     * TabLayout的操作细节选项
     */
    private void initTl() {
        mainTl.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    mainDl.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                }else {
                    mainDl.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                }
                mainVp.setCurrentItem(tab.getPosition(),false);

            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }



    @Override
    protected void initViews() {
        mainVp = byView(R.id.main_vp);
        mainTl = byView(R.id.main_tl);
        qrcodeImg = byView(R.id.z_activity_main_l_qrcode_img);
        mapImg = byView(R.id.z_activity_main_l_map_img);
        mL2048=byView(R.id.z_activity_mail_c_game_2048);
        mLSnake=byView(R.id.z_activity_mail_c_game_snake);
        mainDl = byView(R.id.z_activity_main_Dl);
        mainLl = byView(R.id.newsLl);
    }



    /**
     * 去往游戏界面
     */
    private void initGame() {
        mL2048.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(Z_MainActivity.this, C_2048_Activity.class);
            }
        });
        mLSnake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(Z_MainActivity.this, C_Snake_Activity.class);
            }
        });
    }

    /**
     * 抽屉中的图片操作类
     */

    private void DrawerControl() {
        drawerReceiver = new DrawerReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Z_MapFragment.OPEN_DRAWER);
        registerReceiver(drawerReceiver,intentFilter);


        qrcodeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(Z_MainActivity.this, CaptureActivity.class), 0);
            }
        });
        mapImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Z_MainActivity.this,L_GaodeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();//通过Bundle获取扫描后的结果数据
            String uriStr = "";
            if (bundle != null) {
                String sResult = bundle.getString("result");
//                tv_ScanResult.setText(sResult);
                Intent intent = new Intent(Z_MainActivity.this, QrcodeJsonActivity.class);
                intent.putExtra("wb",sResult);
                startActivity(intent);



            } else {
                uriStr = data.getDataString();

                Uri imgUri = Uri.parse(uriStr);
                Bitmap bitmap = BitmapFactory.decodeFile(L_QrCodeUtils.getPath(Z_MainActivity.this, imgUri));
                if (requestCode == 0) {
                    // 开始对图像资源解码
                    Result rawResult = null;
                    try {
                /* 将Bitmap设定到ImageView */
                        rawResult = multiFormatReader
                                .decodeWithState(new BinaryBitmap(new HybridBinarizer(
                                        new L_QrCodeActivity(bitmap))));
                        /******************************************/
                        Toast.makeText(this, rawResult.getText(), Toast.LENGTH_SHORT).show();

                    } catch (NotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "图片不符合规则", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }

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

    Handler mHandler =  new Handler() {

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
        Log.d("XXX", "推");
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

    public class DrawerReceiver extends BroadcastReceiver{


        @Override
        public void onReceive(Context context, Intent intent) {
            mainDl.openDrawer(mainLl);

        }
    }

    public static void openDrawer(){

        mainDl.openDrawer(mainLl);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ddd", "drawerReceiver:" + drawerReceiver);
//        if (drawerReceiver!=null){
//            unregisterReceiver(drawerReceiver);
//        }
    }
}
