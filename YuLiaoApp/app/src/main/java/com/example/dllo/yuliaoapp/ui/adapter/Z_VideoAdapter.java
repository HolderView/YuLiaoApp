package com.example.dllo.yuliaoapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.model.bean.Z_VideoBean;
import com.example.dllo.yuliaoapp.tools.C_ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by dllo on 16/10/19.
 */
//public class Z_VideoAdapter extends Z_AbsBaseAdapter<Z_VideoBean.视频Bean, Z_AbsBaseAdapter.BaseHolder> {
//    public Z_VideoAdapter(Context context) {
//        super(context);
//    }
//
//    @Override
//    protected int setItemLayout() {
////        Vitamio.isInitialized(C_MyApp.getContext());
//        return R.layout.z_item_video_listview;
//    }
//
//    @Override
//    protected BaseHolder onCreateViewHolder(View convertView) {
//        return new VideoViewHolder(convertView);
//    }
//
//    @Override
//    protected void onBindViewHolder(BaseHolder baseHolder, final Z_VideoBean.视频Bean itemData, int position) {
//        final VideoViewHolder videoViewHolder = (VideoViewHolder) baseHolder;
//
//        final Z_VideoBean.视频Bean videoBean = itemData;
//        videoViewHolder.videoTitleTv.setText(itemData.getTitle());
//
////        Picasso.with(context).load(datas.getCover()).
////                resize(C_ScreenSizeUtil.getScreenSize(context, C_ScreenSizeUtil.ScreenState.WIDTH),
////                        C_ScreenSizeUtil.getScreenSize(context, C_ScreenSizeUtil.ScreenState.HEIGHT) / 4)
////                .into(videoViewHolder.videoImg);
//
////        videoViewHolder.videoView.pause();
//
////        videoViewHolder.videoView.setVisibility(View.GONE);
////        videoViewHolder.videoView.stopPlayback();
////        videoViewHolder.videoView.setMinimumHeight(200);
//
////                videoViewHolder.videoImg.setVisibility(View.GONE);
////                videoViewHolder.videoView.setVisibility(View.VISIBLE);
//
//
//        // 控制器
//        MediaController mediaController = new MediaController(context);
//        videoViewHolder.videoView.setMediaController(mediaController);
//
//        videoViewHolder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                Toast.makeText(context, "播放完成", Toast.LENGTH_SHORT).show();
//            }
//        });
//        videoViewHolder.videoView.setVideoURI(Uri.parse(videoBean.getMp4_url()));
//        Log.d("eee", videoBean.getMp4_url());
//        // 获得焦点
////        videoViewHolder.videoView.requestFocus();
//        // 开始播放
//        videoViewHolder.videoView.start();
//        // 是否播放
//        Log.d("eee", "videoViewHolder.videoView.isPlaying():" + videoViewHolder.videoView.isPlaying());
//        // 是否缓冲完毕
//        Log.d("eee", "videoViewHolder.videoView.isBuffering():" + videoViewHolder.videoView.isBuffering());
//    }
//
//
//    class VideoViewHolder extends Z_AbsBaseAdapter.BaseHolder {
//        io.vov.vitamio.widget.VideoView videoView;
//        TextView videoTitleTv;
//
//        public VideoViewHolder(View itemView) {
//            super(itemView);
//            videoView = (io.vov.vitamio.widget.VideoView) itemView.findViewById(R.id.item_video_view);
//            videoTitleTv = (TextView) itemView.findViewById(R.id.item_video_title_tv);
//        }
//
//    }
//
//}
public class Z_VideoAdapter extends BaseAdapter {

    private Context context;
    private List<Z_VideoBean.视频Bean> datas;
    private VideoViewHolder videoViewHolder;
    private Z_VideoBean.视频Bean videoBean;

    Map<Integer, Boolean> isPlay;

    public Z_VideoAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<Z_VideoBean.视频Bean> datas) {
        this.datas = datas;
        isPlay = new HashMap<>();
        for (int i = 0; i < datas.size(); i++) {
            isPlay.put(i, false);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        videoViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.z_item_video_listview, parent, false);
            videoViewHolder = new VideoViewHolder(convertView);
            convertView.setTag(videoViewHolder);
        } else {
            videoViewHolder = (VideoViewHolder) convertView.getTag();
        }
        videoBean = datas.get(position);

        videoViewHolder.videoTitleTv.setText(videoBean.getTitle());
        // 加载图片
        Picasso.with(context).load(videoBean.getCover())
                .resize(C_ScreenSizeUtil.getScreenSize(context, C_ScreenSizeUtil.ScreenState.WIDTH)
                        , C_ScreenSizeUtil.getScreenSize(context, C_ScreenSizeUtil.ScreenState.HEIGHT) / 4)
                .into(videoViewHolder.videoImg);
        return convertView;
    }

    class VideoViewHolder {
        TextView videoTitleTv;
        ImageView videoImg;

        public VideoViewHolder(View itemView) {
            videoTitleTv = (TextView) itemView.findViewById(R.id.item_video_title_tv);
            videoImg = (ImageView) itemView.findViewById(R.id.item_video_img);
        }
    }
}


