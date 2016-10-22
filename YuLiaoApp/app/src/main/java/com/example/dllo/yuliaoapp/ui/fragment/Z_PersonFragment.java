package com.example.dllo.yuliaoapp.ui.fragment;

import android.os.Bundle;

import com.example.dllo.yuliaoapp.R;

/**
 * Created by dllo on 16/10/19.
 * @author 赵玲琳
 */
public class Z_PersonFragment extends C_AbsBaseFragment {
    public static Z_PersonFragment newInstance() {

        Bundle args = new Bundle();

        Z_PersonFragment fragment = new Z_PersonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.z_fragment_person;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
