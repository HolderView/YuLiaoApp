package com.example.dllo.yuliaoapp.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.tools.C_ScreenSizeUtil;
import com.example.dllo.yuliaoapp.tools.Z_UniverImageLoaderUtils;
import com.example.dllo.yuliaoapp.tools.z_pay.NormalActivity;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


/**
 * Created by dllo on 16/10/21.
 *
 * @author 赵玲琳
 *         视频详情页
 */
public class Z_VideoDetailsActivity extends C_AbsBaseActivity implements View.OnClickListener {

    private TextView titleTv, netTv, cacheTv, authorTv;
    private VideoView videoView;
    private MediaController mediaController;
    private String url, title, photo, author;
    private GestureDetector gestureDetector;
    private ImageView showImg, typeBgImg, backImg, moreImg, phptoImg;
    private RelativeLayout relativeLayout;
    private RelativeLayout controllerRl;
    private Boolean isShow = false;
    private AudioManager audioManager;
    public static final String KEY_URL = "url";
    public static final String KEY_TITLE = "title";
    public static final String KEY_PHOTO = "photo";
    public static final String KEY_AUTHOR = "author";
    private ProgressDialog progressDialog;
    private LinearLayout topicLl;
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
    private int vWidth;
    private int vHeight;

    private Button payBtn;

    @Override
    protected void initData(Bundle savedInstanceState) {
        // 接收跳转传的 url 和 标题
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            url = bundle.getString(KEY_URL);
            title = bundle.getString(KEY_TITLE);
            photo = bundle.getString(KEY_PHOTO);
            author = bundle.getString(KEY_AUTHOR);
            Log.d("asdasd", photo);
        }
        authorTv.setText(author);

        ViewGroup.LayoutParams layoutParams = phptoImg.getLayoutParams();
        layoutParams.height = C_ScreenSizeUtil.getScreenSize(this, C_ScreenSizeUtil.ScreenState.HEIGHT) / 12;
        layoutParams.width = C_ScreenSizeUtil.getScreenSize(this, C_ScreenSizeUtil.ScreenState.HEIGHT) / 20;
        phptoImg.setLayoutParams(layoutParams);


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        Z_UniverImageLoaderUtils.loadImage(photo, phptoImg);
//            }
//        }).start();


        // 视频控制器
        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        titleTv.setText(title);
        videoView.setVideoURI(Uri.parse(url));
        videoView.setFocusable(true);

        // AudioManager音量管理类
        audioManager = (AudioManager) getSystemService(this.AUDIO_SERVICE);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        // 手势监听
        gestureDetector = new GestureDetector(this, new MyGestureListener());

        // 缓存中显示dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("缓存中...");
        progressDialog.show();

        // 显示缓存进度和网速
        showCacheAndNet();

        payBtn.setOnClickListener(this);
        backImg.setOnClickListener(this);
        moreImg.setOnClickListener(this);

        // 检测当前网络状态
        netState();
    }

    @Override
    protected int setLayout() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return R.layout.z_activity_video_details;
    }

    @Override
    protected void initViews() {
        videoView = byView(R.id.video_details_videoview);
        titleTv = byView(R.id.video_details_title_tv);
        netTv = byView(R.id.video_details_net_tv);
        cacheTv = byView(R.id.video_details_cache_tv);
        showImg = byView(R.id.video_details_short_img);
        typeBgImg = byView(R.id.video_details_type_img);
        relativeLayout = byView(R.id.video_details_rl);
        controllerRl = byView(R.id.video_details_controller_rl);
        backImg = byView(R.id.vide_details_back_img);
        moreImg = byView(R.id.video_details_more_img);
        payBtn = byView(R.id.video_details_btn);
        authorTv = byView(R.id.video_details_author_tv);
        phptoImg = byView(R.id.video_details_photo_img);
        topicLl = byView(R.id.video_details_topic_ll);
    }



    /**
     * 检测当前网络状态
     */
    private void netState() {
        // 网络连接管理
        ConnectivityManager mConnectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // 网络连接状态
        NetworkInfo info = mConnectivity.getActiveNetworkInfo();
        if (info == null) {
            Toast.makeText(this, "当前无网络连接,请连接网络", Toast.LENGTH_SHORT).show();
        } else {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(this, "当前为wifi连接", Toast.LENGTH_SHORT).show();
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("播放");
                builder.setIcon(R.mipmap.video);
                builder.setMessage("当前为移动网络,确定使用移动网络播放么?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        videoView.start();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        videoView.pause();
                    }
                });

            }
        }
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
                        videoView.pause();
                        break;
                    //缓冲结束
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        cacheTv.setVisibility(View.GONE);
                        netTv.setVisibility(View.GONE);
                        progressDialog.dismiss();
                        mp.start();
                        videoView.start();
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
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vide_details_back_img:
                finish();
                break;
            case R.id.video_details_more_img:
                // 弹出popupMenu
                PopupMenu popupMenu = new PopupMenu(Z_VideoDetailsActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_video_details, popupMenu.getMenu());
                popupMenu.show();
                break;
            case R.id.video_details_btn:
                Intent intent = new Intent(this, NormalActivity.class);
                startActivity(intent);
//                finish();
                Log.d("qwe", "支付");
                break;
        }
    }

    /**
     * 横屏手势处理
     */
    private class ShowOrHideGestureListener extends GestureDetector.SimpleOnGestureListener {
        // 点击 标题栏显示/隐藏
        @Override
        public boolean onDown(MotionEvent e) {
            if (isShow == false) {
                relativeLayout.setVisibility(View.GONE);

                isShow = true;
            } else {
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

        /**
         * 滑动
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
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

        /**
         * 双击切换横竖屏
         */
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            int screenOri = getRequestedOrientation();
            if (screenOri == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                // 当前为竖屏,获取videoView的宽高
                vWidth = videoView.getMeasuredWidth();
                vHeight = videoView.getMeasuredHeight();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else if (screenOri == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                // 当前是横屏  设置方向为竖屏
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }

            return true;
        }

    }

    /**
     * 竖屏手势监听
     */
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        /**
         * 双击切换横竖屏
         */
        @Override
        public boolean onDoubleTap(MotionEvent e) {
//            if (mLayout == VideoView.VIDEO_LAYOUT_ZOOM)
//                mLayout = VideoView.VIDEO_LAYOUT_ORIGIN;
//            else
//                mLayout++;
//            if (videoView != null)
//                videoView.setVideoLayout(mLayout, 0);
            int screenOri = getRequestedOrientation();

            if (screenOri == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                // 当前为竖屏
                vWidth = videoView.getMeasuredWidth();
                vHeight = videoView.getMeasuredHeight();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else if (screenOri == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                // 当前是横屏  设置方向为竖屏
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }

            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e2.getX() - e1.getX() > 50) {
                finish();
            }
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (e2.getX() - e1.getX() > 50) {
                finish();
            }
            return true;
        }
    }

    /**
     * 定时隐藏
     */
    private Handler mDismissHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            controllerRl.setVisibility(View.GONE);
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
            controllerRl.setVisibility(View.VISIBLE);
        }

        int index = (int) (percent * maxVolume) + volume;
        if (index > maxVolume)
            index = maxVolume;
        else if (index < 0)
            index = 0;

        // 变更声音
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);

        // 变更进度条
        ViewGroup.LayoutParams lp = showImg.getLayoutParams();
        lp.width = findViewById(R.id.video_details_long_img).getLayoutParams().width
                * index / maxVolume;
        showImg.setLayoutParams(lp);
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
            controllerRl.setVisibility(View.VISIBLE);
        }
        WindowManager.LayoutParams lpa = getWindow().getAttributes();
        lpa.screenBrightness = brightness + percent;
        if (lpa.screenBrightness > 1.0f)
            lpa.screenBrightness = 1.0f;
        else if (lpa.screenBrightness < 0.01f)
            lpa.screenBrightness = 0.01f;
        getWindow().setAttributes(lpa);

        ViewGroup.LayoutParams lp = showImg.getLayoutParams();
        lp.width = (int) (findViewById(R.id.video_details_long_img).getLayoutParams().width * lpa.screenBrightness);
        showImg.setLayoutParams(lp);
    }

    /**
     * 横竖屏监听
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (videoView != null)
            videoView.setVideoLayout(mLayout, 0);
        super.onConfigurationChanged(newConfig);
        // 获取屏幕方向
        int ori = newConfig.orientation;
        if (ori == Configuration.ORIENTATION_PORTRAIT) {
            ViewGroup.LayoutParams lp = videoView.getLayoutParams();
            lp.height = vHeight;
            lp.width = vWidth;
            videoView.setLayoutParams(lp);

            topicLl.setVisibility(View.VISIBLE);
            titleTv.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.VISIBLE);

            // 竖屏手势处理
            gestureDetector = new GestureDetector(this, new MyGestureListener());
        }
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            // 横屏手势处理
            gestureDetector = new GestureDetector(this, new ShowOrHideGestureListener());
            // 横屏时隐藏状态栏
            //定义全屏参数  去掉电量栏
//            int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
            //获得当前窗体对象
//            Window window=Z_VideoDetailsActivity.this.getWindow();
            //设置当前窗体为全屏显示
//            window.setFlags(flag, flag);
            titleTv.setVisibility(View.GONE);
            topicLl.setVisibility(View.GONE);

            // 横屏设置宽高为屏幕的宽高
            WindowManager windowManager = (WindowManager) this.getSystemService(this.WINDOW_SERVICE);
            int width = windowManager.getDefaultDisplay().getWidth();
            int height = windowManager.getDefaultDisplay().getHeight();

            // 重新设置宽高
            ViewGroup.LayoutParams lp = videoView.getLayoutParams();
            lp.width = width;
            lp.height = height;
            videoView.setLayoutParams(lp);
        }
    }
}
