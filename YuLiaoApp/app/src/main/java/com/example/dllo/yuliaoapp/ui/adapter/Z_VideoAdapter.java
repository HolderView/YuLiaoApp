package com.example.dllo.yuliaoapp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.VideoView;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.model.bean.Z_VideoBean;

/**
 * Created by dllo on 16/10/19.
 */
public class Z_VideoAdapter extends Z_AbsBaseAdapter<Z_VideoBean,Z_AbsBaseAdapter.BaseHolder>{

    public Z_VideoAdapter(Context context) {
        super(context);
    }

    @Override
    protected int setItemLayout() {
        return R.layout.z_item_video_listview;
    }

    @Override
    protected BaseHolder onCreateViewHolder(View convertView) {
        return new VideoViewHolder(convertView);
    }

    @Override
    protected void onBindViewHolder(BaseHolder baseHolder, Z_VideoBean itemData, int position) {
        VideoViewHolder videoViewHolder = (VideoViewHolder) baseHolder;
    }
    class VideoViewHolder extends Z_AbsBaseAdapter.BaseHolder{
        VideoView videoView;
        public VideoViewHolder(View itemView) {
            super(itemView);
            videoView = (VideoView) itemView.findViewById(R.id.item_video_videoview);
        }
    }
}
