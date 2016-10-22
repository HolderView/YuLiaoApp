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
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.yuliaoapp.R;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


/**
 * Created by dllo on 16/10/21.
 * @author 赵玲琳
 * 视频详情页
 */
public class Z_VideoDetailsActivity extends C_AbsBaseActivity {

    private TextView titleTv,netTv,cacheTv;
    private VideoView videoView;
    private MediaController mediaController;
    private String url,title;
    private GestureDetector gestureDetector;
    private ImageView volumeImg,lightImg,progressImg;
    private RelativeLayout relativeLayout;
    private FrameLayout frameLayout;
    private Boolean isShow = false;
    private AudioManager mAudioManager;
    /**
     * 最大声音
     */
    private int mMaxVolume;
    /**
     * 当前声音
     */
    private int mVolume = -1;
    /**
     * 当前亮度
     */
    private float mBrightness = -1f;
    /**
     * 当前缩放模式
     */
    private int mLayout = VideoView.VIDEO_LAYOUT_ZOOM;

    @Override
    protected int setLayout() {
        return R.layout.z_activity_video_details;
    }

    @Override
    protected void initViews() {
        videoView = byView(R.id.video_details_videoview);
        titleTv = byView(R.id.video_details_title_tv);
        netTv = byView(R.id.video_details_net_tv);
        cacheTv = byView(R.id.video_details_cache_tv);
        volumeImg = byView(R.id.video_details_volume_img);
        lightImg = byView(R.id.video_details_light_img);
        progressImg =byView(R.id.video_details_progress_img);
        relativeLayout = byView(R.id.video_details_rl);
        frameLayout = byView(R.id.video_details_fl);

        // 强制横屏
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Override
    protected void initData() {

        mAudioManager = (AudioManager) getSystemService(this.AUDIO_SERVICE);
        mMaxVolume = mAudioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getExtras();
            url = bundle.getString("url");
            title = bundle.getString("title");
        }
        titleTv.setText(title);
        videoView.setVideoURI(Uri.parse(url));
        videoView.start();

        showCacheAndNet();

        gestureDetector = new GestureDetector(this, new MyGestureListener() );
    }

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
        mVolume = -1;
        mBrightness = -1f;

        // 隐藏
        mDismissHandler.removeMessages(0);
        mDismissHandler.sendEmptyMessageDelayed(0, 500);
    }

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

        @Override
        public boolean onDown(MotionEvent e) {
            if (isShow == false){
                relativeLayout.setVisibility(View.GONE);
                isShow = true;
            }else {
                relativeLayout.setVisibility(View.VISIBLE);
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
            relativeLayout.setVisibility(View.GONE);
        }
    };

    /**
     * 滑动改变声音大小
     *
     * @param percent
     */
    private void onVolumeSlide(float percent) {
        if (mVolume == -1) {
            mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (mVolume < 0)
                mVolume = 0;

            // 显示
            volumeImg.setImageResource(R.mipmap.ic_launcher);
            frameLayout.setVisibility(View.VISIBLE);
        }

        int index = (int) (percent * mMaxVolume) + mVolume;
        if (index > mMaxVolume)
            index = mMaxVolume;
        else if (index < 0)
            index = 0;

        // 变更声音
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);

        // 变更进度条
        ViewGroup.LayoutParams lp = volumeImg.getLayoutParams();
        lp.width = findViewById(R.id.video_details_light_img).getLayoutParams().width
                * index / mMaxVolume;
        volumeImg.setLayoutParams(lp);
    }

    /**
     * 滑动改变亮度
     *
     * @param percent
     */
    private void onBrightnessSlide(float percent) {
        if (mBrightness < 0) {
            mBrightness = getWindow().getAttributes().screenBrightness;
            if (mBrightness <= 0.00f)
                mBrightness = 0.50f;
            if (mBrightness < 0.01f)
                mBrightness = 0.01f;

            // 显示
            lightImg.setImageResource(R.mipmap.ic_launcher);
            frameLayout.setVisibility(View.VISIBLE);
        }
        WindowManager.LayoutParams lpa = getWindow().getAttributes();
        lpa.screenBrightness = mBrightness + percent;
        if (lpa.screenBrightness > 1.0f)
            lpa.screenBrightness = 1.0f;
        else if (lpa.screenBrightness < 0.01f)
            lpa.screenBrightness = 0.01f;
        getWindow().setAttributes(lpa);

        ViewGroup.LayoutParams lp = lightImg.getLayoutParams();
        lp.width = (int) (findViewById(R.id.video_details_light_img).getLayoutParams().width * lpa.screenBrightness);
        lightImg.setLayoutParams(lp);
    }


}
