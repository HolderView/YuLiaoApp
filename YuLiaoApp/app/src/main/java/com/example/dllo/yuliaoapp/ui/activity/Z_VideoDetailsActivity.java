package com.example.dllo.yuliaoapp.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
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
    private String string;
    private GestureDetector gestureDetector;

    @Override
    protected int setLayout() {
        return R.layout.z_activity_video_details;
    }

    @Override
    protected void initViews() {
        videoView = byView(R.id.video_details_videoview);
//        titleTv = byView(R.id.video_details_title_tv);
        netTv = byView(R.id.video_details_net_tv);
        cacheTv = byView(R.id.video_details_cache_tv);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Override
    protected void initData() {
        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getExtras();
            string = bundle.getString("url");
        }
        videoView.setVideoURI(Uri.parse(string));
        videoView.start();

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
}
