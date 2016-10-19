package com.example.dllo.yuliaoapp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.ui.activity.C_AbsBaseActivity;

/**
 * Created by dllo on 16/10/19.
 *
 * @author 陈思宇
 *         Fragment基类
 */
public abstract class C_AbsBaseFragment extends Fragment {
    protected Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();
    }

    protected abstract int setLayout();

    protected abstract void initViews();

    protected abstract void initDatas();

    protected <T extends View> T byView(int resId) {
        return (T) getView().findViewById(resId);
    }

    protected void goTo(Class<? extends C_AbsBaseFragment> to) {
        context.startActivity(new Intent(context, to));
        getActivity().overridePendingTransition(R.anim.c_activity_anim_in, R.anim.c_activity_anim_out);
    }

    protected void goTo(Class<? extends C_AbsBaseFragment> to, Bundle bundle) {
        Intent intent = new Intent(context, to);
        intent.putExtras(bundle);
        context.startActivity(intent);
        getActivity().overridePendingTransition(R.anim.c_activity_anim_in, R.anim.c_activity_anim_out);
    }

    protected void goToActivity(Class<? extends C_AbsBaseActivity> to) {
        context.startActivity(new Intent(context, to));
        getActivity().overridePendingTransition(R.anim.c_activity_anim_in, R.anim.c_activity_anim_out);
    }

    protected void goToActivity(Class<? extends C_AbsBaseActivity> to, Bundle bundle) {
        Intent intent = new Intent(context, to);
        intent.putExtras(bundle);
        context.startActivity(intent);
        getActivity().overridePendingTransition(R.anim.c_activity_anim_in, R.anim.c_activity_anim_out);
    }
}
