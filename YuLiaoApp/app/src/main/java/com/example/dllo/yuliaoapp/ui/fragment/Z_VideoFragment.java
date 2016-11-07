package com.example.dllo.yuliaoapp.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.model.bean.Z_VideoBean;
import com.example.dllo.yuliaoapp.model.net.UrlValues;
import com.example.dllo.yuliaoapp.tools.Z_OkHttpClientManagerUtils;
import com.example.dllo.yuliaoapp.tools.z_refresh.RefreshLayout;
import com.example.dllo.yuliaoapp.ui.activity.Z_VideoDetailsActivity;
import com.example.dllo.yuliaoapp.ui.adapter.Z_VideoAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Request;

/**
 * Created by dllo on 16/10/19.
 *
 * @author 赵玲琳
 *         视频页
 */
public class Z_VideoFragment extends C_AbsBaseFragment implements AdapterView.OnItemClickListener {

    private ListView videoListView;
    private Z_VideoAdapter z_videoAdapter;
    private RefreshLayout refreshLayout;
    private List<Z_VideoBean.视频Bean> datas;
    private List<Z_VideoBean.视频Bean> refreshDatas;
    private List<Z_VideoBean.视频Bean> loadDatas;

    private int size = 10;
    int pageSize = 10;

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
        refreshLayout = byView(R.id.fragment_video_rl);
    }

    @Override
    protected void initDatas() {
        z_videoAdapter = new Z_VideoAdapter(context);
        videoListView.setAdapter(z_videoAdapter);

        datas = new ArrayList<>();
        refreshDatas = new ArrayList<>();
        loadDatas = new ArrayList<>();

        videoListView.setOnItemClickListener(this);

        viodeDatas();
        onRefreshListener();
        onLoadListener();

    }
    /**
     * 视频页数据
     */
    private void viodeDatas(){
        try {
            Z_OkHttpClientManagerUtils.getInstance().getRequestAsyn(UrlValues.VIDEO_URL, new Z_OkHttpClientManagerUtils.ResultCallback() {
                @Override
                public void onError(Request request, Exception e) {
                }
                @Override
                public void onResponse(Object response) {
                    Gson gson = new Gson();
                    Z_VideoBean videoBean = gson.fromJson((String) response, Z_VideoBean.class);
                    datas = videoBean.get视频();
                    z_videoAdapter.setDatas(datas);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 行布局点击跳转详情
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Z_VideoBean.视频Bean datas = (Z_VideoBean.视频Bean) parent.getItemAtPosition(position);
        Bundle bundle = new Bundle();
        bundle.putString(Z_VideoDetailsActivity.KEY_URL, datas.getMp4_url());
        bundle.putString(Z_VideoDetailsActivity.KEY_TITLE, datas.getTitle());
        bundle.putString(Z_VideoDetailsActivity.KEY_PHOTO, datas.getTopicImg());
        bundle.putString(Z_VideoDetailsActivity.KEY_AUTHOR, datas.getTopicName());
        goToActivity(Z_VideoDetailsActivity.class, bundle);

    }

    /**
     * 下拉刷新
     */
    private void onRefreshListener() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Z_OkHttpClientManagerUtils.getInstance().getRequestAsyn(UrlValues.VIDEO_URL, new Z_OkHttpClientManagerUtils.ResultCallback() {
                                @Override
                                public void onError(Request request, Exception e) {
                                }
                                @Override
                                public void onResponse(Object response) {

                                    Gson gson = new Gson();
                                    Z_VideoBean videoBean = gson.fromJson((String) response, Z_VideoBean.class);
                                    refreshDatas = videoBean.get视频();
                                    z_videoAdapter.setDatas(refreshDatas);
                                    refreshLayout.setRefreshing(false);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }, 2000);
            }
        });
    }

    /**
     * 上拉加载
     */
    private void onLoadListener() {
        refreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Z_OkHttpClientManagerUtils.getInstance().getRequestAsyn(UrlValues.VIDEO_LEFT_URL + pageSize + UrlValues.VIDEO_RIGHT_URL, new Z_OkHttpClientManagerUtils.ResultCallback() {
                                @Override
                                public void onError(Request request, Exception e) {
                                }

                                @Override
                                public void onResponse(Object response) {
                                    Gson gson = new Gson();
                                    Z_VideoBean videoBean = gson.fromJson((String) response, Z_VideoBean.class);
                                    loadDatas = videoBean.get视频();
                                    loadDatas = loadDatas.subList(loadDatas.size() - size,loadDatas.size());
                                    datas.addAll(loadDatas);
                                    z_videoAdapter.setDatas(datas);
                                    refreshLayout.setLoading(false);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, 1500);
                pageSize = pageSize + size;
            }
        });
    }
}