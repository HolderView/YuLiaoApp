package com.example.dllo.yuliaoapp.ui.fragment;

import android.os.Bundle;

import com.example.dllo.yuliaoapp.R;

/**
 * Created by dllo on 16/10/19.
 */
public class Z_VideoFragment extends C_AbsBaseFragment {
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

    }

    @Override
    protected void initDatas() {

    }
}
