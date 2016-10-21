package com.example.dllo.yuliaoapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.model.bean.Z_VideoBean;
import com.example.dllo.yuliaoapp.model.net.UrlValues;
import com.example.dllo.yuliaoapp.ui.activity.Z_VideoDetailsActivity;
import com.example.dllo.yuliaoapp.ui.adapter.Z_VideoAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dllo on 16/10/19.
 */
public class Z_VideoFragment extends C_AbsBaseFragment implements AdapterView.OnItemClickListener {

    private ListView videoListView;
    private Z_VideoAdapter z_videoAdapter;
    private OkHttpClient okHttpClient;

    // 在主线程中刷新UI界面
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what==100){
                String s= (String) msg.obj;
                Gson gson = new Gson();
                Z_VideoBean videoBean = gson.fromJson(s,Z_VideoBean.class);
                List<Z_VideoBean.视频Bean> datas = videoBean.get视频();
                Log.d("www", "datas:" + datas);
                z_videoAdapter.setDatas(datas);
            }

            return false;
        }
    });

    public static Z_VideoFragment newInstance() {
        Bundle args = new Bundle();
        Z_VideoFragment fragment = new Z_VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.z_fragment_video;
    }

    @Override
    protected void initViews() {
        videoListView = byView(R.id.video_listview);
    }

    @Override
    protected void initDatas() {
        z_videoAdapter = new Z_VideoAdapter(context);
        videoListView.setAdapter(z_videoAdapter);

        okHttpClient = new OkHttpClient();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doSyncGet();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        videoListView.setOnItemClickListener(this);
    }

    private void doSyncGet() throws IOException {
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(UrlValues.VIDEO_URL).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()){
            String string = response.body().string();
            Log.d("Z_VideoFragment", string);
            // 子线程不能刷新UI界面
            // handler发送到主线程
            Message message=new Message();
            message.what=100;
            message.obj=string;
            handler.sendMessage(message);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Z_VideoBean.视频Bean datas = (Z_VideoBean.视频Bean) parent.getItemAtPosition(position);
        Bundle bundle = new Bundle();
        bundle.putString("url",datas.getMp4_url());
        goToActivity(Z_VideoDetailsActivity.class,bundle);
    }
}
