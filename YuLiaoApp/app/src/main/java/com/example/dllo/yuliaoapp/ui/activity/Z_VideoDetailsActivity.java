package com.example.dllo.yuliaoapp.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.yuliaoapp.R;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


/**
 * Created by dllo on 16/10/21.
 * @author 赵玲琳
 * 视频详情页
 */
public class Z_VideoDetailsActivity extends C_AbsBaseActivity implements View.OnClickListener {

    private TextView titleTv,netTv,cacheTv;
    private VideoView videoView;
    private MediaController mediaController;
    private String url,title;
    private GestureDetector gestureDetector;
    private ImageView longImg,shortImg,typeBgImg,backImg,moreImg;
    private RelativeLayout relativeLayout;
    private RelativeLayout frameLayout;
    private Boolean isShow = false;
    private AudioManager audioManager;
    public static final String KEY_URL = "url";
    public static final String KEY_TITLE = "title";
    /**
     * 最大声音
     */
    private int maxVolume;
    /**
     * 当前声音
     */
    private int volume = -1;
    /**
     * 当前亮度
     */
    private float brightness = -1f;
    /**
     * 当前缩放模式
     */
    private int mLayout = VideoView.VIDEO_LAYOUT_ZOOM;

    @Override
    protected int setLayout() {

        //隐藏状态栏
        //定义全屏参数  去掉电量栏
        int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=Z_VideoDetailsActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);

        return R.layout.z_activity_video_details;
    }

    @Override
    protected void initViews() {
        videoView = byView(R.id.video_details_videoview);
        titleTv = byView(R.id.video_details_title_tv);
        netTv = byView(R.id.video_details_net_tv);
        cacheTv = byView(R.id.video_details_cache_tv);
        longImg = byView(R.id.video_details_long_img);
        shortImg = byView(R.id.video_details_short_img);
        typeBgImg =byView(R.id.video_details_type_img);
        relativeLayout = byView(R.id.video_details_rl);
        frameLayout = byView(R.id.video_details_fl);
        backImg =byView(R.id.vide_details_back_img);
        moreImg = byView(R.id.video_details_more_img);

        // 强制横屏
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Override
    protected void initData() {

        // 接收跳转传的 url 和 标题
        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getExtras();
            url = bundle.getString(KEY_URL);
            title = bundle.getString(KEY_TITLE);
        }

        // 视频控制器
        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        titleTv.setText(title);
        videoView.setVideoURI(Uri.parse(url));
        videoView.setFocusable(true);
        videoView.start();

        backImg.setOnClickListener(this);
        moreImg.setOnClickListener(this);

        // AudioManager音量管理类
        audioManager = (AudioManager) getSystemService(this.AUDIO_SERVICE);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        // 显示缓存进度和网速
        showCacheAndNet();
        // 手势监听
        gestureDetector = new GestureDetector(this, new MyGestureListener() );
    }

    /**
     * 显示缓存进度和网速
     */
    private void showCacheAndNet() {
        videoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                cacheTv.setText("已缓冲：" + percent + "%");
            }
        });
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    //开始缓冲
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        cacheTv.setVisibility(View.VISIBLE);
                        netTv.setVisibility(View.VISIBLE);
                        mp.pause();
                        break;
                    //缓冲结束
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        cacheTv.setVisibility(View.GONE);
                        netTv.setVisibility(View.GONE);
                        mp.start();
                        break;
                    //正在缓冲
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        netTv.setText("当前网速:" + extra + "kb/s");
                        break;
                }
                return true;
            }
        });
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event))
            return true;

        // 处理手势结束
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                endGesture();
                break;
        }

        return super.onTouchEvent(event);
    }

    /**
     * 手势结束
     */
    private void endGesture() {
        volume = -1;
        brightness = -1f;

        // 隐藏
        mDismissHandler.removeMessages(0);
        mDismissHandler.sendEmptyMessageDelayed(0, 500);
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.vide_details_back_img:
                finish();
                break;
            case R.id.video_details_more_img:
                Toast.makeText(this, "该功能尚待优化", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    /**
     * 手势监听
     */
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        /**
         * 双击
         */
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (mLayout == VideoView.VIDEO_LAYOUT_ZOOM)
                mLayout = VideoView.VIDEO_LAYOUT_ORIGIN;
            else
                mLayout++;
            if (videoView != null)
                videoView.setVideoLayout(mLayout, 0);
            return true;
        }

        /**
         * 滑动
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            float mOldX = e1.getX(), mOldY = e1.getY();
            int y = (int) e2.getRawY();
            Display disp = getWindowManager().getDefaultDisplay();
            int windowWidth = disp.getWidth();
            int windowHeight = disp.getHeight();

            if (mOldX > windowWidth * 4.0 / 5)// 右边滑动
                onVolumeSlide((mOldY - y) / windowHeight);
            else if (mOldX < windowWidth / 5.0)// 左边滑动
                onBrightnessSlide((mOldY - y) / windowHeight);

            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        // 点击 标题栏显示/隐藏
        @Override
        public boolean onDown(MotionEvent e) {
            if (isShow == false){
                relativeLayout.setVisibility(View.GONE);

                isShow = true;
            }else {
                relativeLayout.setVisibility(View.VISIBLE);
                // 定时3s隐藏
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        relativeLayout.setVisibility(View.GONE); //view是要隐藏的控件
                    }
                }, 3000);  //3000毫秒后执行
                isShow = false;
            }
            return super.onDown(e);
        }
    }

    /**
     * 定时隐藏
     */
    private Handler mDismissHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            frameLayout.setVisibility(View.GONE);
//            relativeLayout.setVisibility(View.GONE);
        }
    };

    /**
     * 滑动改变声音大小
     *
     * @param percent
     */
    private void onVolumeSlide(float percent) {
        if (volume == -1) {
            volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (volume < 0)
                volume = 0;

            // 显示
            typeBgImg.setImageResource(R.mipmap.volume);
            frameLayout.setVisibility(View.VISIBLE);
        }

        int index = (int) (percent * maxVolume) + volume;
        if (index > maxVolume)
            index = maxVolume;
        else if (index < 0)
            index = 0;

        // 变更声音
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);

        // 变更进度条
        ViewGroup.LayoutParams lp = shortImg.getLayoutParams();
        lp.width = findViewById(R.id.video_details_long_img).getLayoutParams().width
                * index / maxVolume;
        shortImg.setLayoutParams(lp);
    }

    /**
     * 滑动改变亮度
     *
     * @param percent
     */
    private void onBrightnessSlide(float percent) {
        if (brightness < 0) {
            brightness = getWindow().getAttributes().screenBrightness;
            if (brightness <= 0.00f)
                brightness = 0.50f;
            if (brightness < 0.01f)
                brightness = 0.01f;

            // 显示
            typeBgImg.setImageResource(R.mipmap.light);
            frameLayout.setVisibility(View.VISIBLE);
        }
        WindowManager.LayoutParams lpa = getWindow().getAttributes();
        lpa.screenBrightness = brightness + percent;
        if (lpa.screenBrightness > 1.0f)
            lpa.screenBrightness = 1.0f;
        else if (lpa.screenBrightness < 0.01f)
            lpa.screenBrightness = 0.01f;
        getWindow().setAttributes(lpa);

        ViewGroup.LayoutParams lp = shortImg.getLayoutParams();
        lp.width = (int) (findViewById(R.id.video_details_long_img).getLayoutParams().width * lpa.screenBrightness);
        shortImg.setLayoutParams(lp);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (videoView != null)
            videoView.setVideoLayout(mLayout, 0);
        super.onConfigurationChanged(newConfig);
//        //切换为竖屏
//        if (newConfig.orientation == this.getResources().getConfiguration().ORIENTATION_PORTRAIT) {
//
//        }
//        //切换为横屏
//        else if (newConfig.orientation == this.getResources().getConfiguration().ORIENTATION_LANDSCAPE) {
//
//        }
    }
}
