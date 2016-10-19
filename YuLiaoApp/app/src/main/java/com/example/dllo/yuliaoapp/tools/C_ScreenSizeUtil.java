package com.example.dllo.yuliaoapp.tools;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by dllo on 16/10/19.
 * 处理图片宽高工具类
 * @author 陈思宇
 */
public class C_ScreenSizeUtil {
    //枚举:整型常量
    //一般用枚举来规定一些特殊状态
    //如横屏竖屏 全屏 窗口化
    //如播放暂停 停止
    //如 宽 高
    //关键字  enum 也是一种数据类型 就是一个类
    public enum ScreenState {
        WIDTH,HEIGHT
    }

    public static int getScreenSize(Context context, ScreenState state) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        if (state.equals(ScreenState.WIDTH)){
            return metrics.widthPixels;
        }else if (state.equals(ScreenState.HEIGHT)){
            return metrics.heightPixels;
        }
        return metrics.heightPixels;
    }
}
