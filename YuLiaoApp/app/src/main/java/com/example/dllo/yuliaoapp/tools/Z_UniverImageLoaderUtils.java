package com.example.dllo.yuliaoapp.tools;

import android.widget.ImageView;

import com.example.dllo.yuliaoapp.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by dllo on 16/10/27.
 * 封装的UniverLoader
 * @author 赵玲琳
 */
public class Z_UniverImageLoaderUtils {

    // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
    public static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.loading) // 设置图片下载期间显示的图片
            .showImageForEmptyUri(R.mipmap.error) // 设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.mipmap.error) // 设置图片加载或解码过程中发生错误显示的图片
            .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
            .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
            .build(); // 构建完成

    public static void loadImage(String url, ImageView imageView) {
        ImageLoader.getInstance().displayImage(url, imageView,options);
    }
}
