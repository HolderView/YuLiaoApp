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
import com.example.dllo.yuliaoapp.tools.Z_UniverImageLoaderUtils;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by dllo on 16/10/19.
 * @author 赵玲琳
 * 视频页适配器
 */
public class Z_VideoAdapter extends BaseAdapter {

    private Context context;
    private List<Z_VideoBean.视频Bean> datas;
    private VideoViewHolder videoViewHolder;
    private Z_VideoBean.视频Bean videoBean;

    public Z_VideoAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<Z_VideoBean.视频Bean> datas) {
        this.datas = datas;
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

        videoViewHolder.videoTopicNameTv.setText(videoBean.getTopicName());
        videoViewHolder.videoTitleTv.setText(videoBean.getTitle());
        // 加载图片
        // 获取图片宽高并重新设置宽高
        ViewGroup.LayoutParams layoutParams = videoViewHolder.videoImg.getLayoutParams();
        layoutParams.width = C_ScreenSizeUtil.getScreenSize(context, C_ScreenSizeUtil.ScreenState.WIDTH);
        layoutParams.height = C_ScreenSizeUtil.getScreenSize(context, C_ScreenSizeUtil.ScreenState.HEIGHT)/3;
        videoViewHolder.videoImg.setLayoutParams(layoutParams);
        Z_UniverImageLoaderUtils.loadImage(videoBean.getCover(),videoViewHolder.videoImg);

        ViewGroup.LayoutParams lp = videoViewHolder.videoTopicImg.getLayoutParams();
        lp.width = C_ScreenSizeUtil.getScreenSize(context, C_ScreenSizeUtil.ScreenState.WIDTH)/12;
        lp.height = C_ScreenSizeUtil.getScreenSize(context, C_ScreenSizeUtil.ScreenState.HEIGHT)/20;
        videoViewHolder.videoTopicImg.setLayoutParams(lp);
        Z_UniverImageLoaderUtils.loadImage(videoBean.getTopicImg(),videoViewHolder.videoTopicImg);

        return convertView;
    }

    class VideoViewHolder {
        TextView videoTitleTv,videoTopicNameTv;
        ImageView videoImg,videoTopicImg;

        public VideoViewHolder(View itemView) {
            videoTitleTv = (TextView) itemView.findViewById(R.id.item_video_title_tv);
            videoImg = (ImageView) itemView.findViewById(R.id.item_video_img);
            videoTopicNameTv = (TextView) itemView.findViewById(R.id.item_video_topic_name_tv);
            videoTopicImg = (ImageView) itemView.findViewById(R.id.item_video_topic_img);
        }
    }
}


