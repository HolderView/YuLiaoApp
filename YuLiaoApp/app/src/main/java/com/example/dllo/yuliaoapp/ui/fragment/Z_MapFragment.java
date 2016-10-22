package com.example.dllo.yuliaoapp.ui.fragment;

import android.os.Bundle;

import com.example.dllo.yuliaoapp.R;

/**
 * Created by dllo on 16/10/19.
 * @author 赵玲琳
 * 地图页
 * 操作人:刘航
 */
public class Z_MapFragment extends C_AbsBaseFragment{

    public static Z_MapFragment newInstance() {

        Bundle args = new Bundle();

        Z_MapFragment fragment = new Z_MapFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.z_fragment_map;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
