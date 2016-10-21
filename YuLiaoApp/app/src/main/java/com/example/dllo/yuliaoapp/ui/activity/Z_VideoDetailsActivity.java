package com.example.dllo.yuliaoapp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.dllo.yuliaoapp.R;


/**
 * Created by dllo on 16/10/21.
 */
public class Z_VideoDetailsActivity extends C_AbsBaseActivity {

    private VideoView videoView;
    private MediaController mediaController;
    private String string;

    @Override
    protected int setLayout() {
        return R.layout.z_activity_video_details;
    }

    @Override
    protected void initViews() {
        videoView = byView(R.id.video_details_videoview);

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
    }
}
