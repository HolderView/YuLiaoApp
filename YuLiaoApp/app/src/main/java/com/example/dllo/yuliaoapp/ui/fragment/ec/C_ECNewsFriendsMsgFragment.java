package com.example.dllo.yuliaoapp.ui.fragment.ec;

import android.os.Bundle;
import android.widget.ListView;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.model.db.InviteMessage;
import com.example.dllo.yuliaoapp.model.db.InviteMessgeDao;
import com.example.dllo.yuliaoapp.ui.adapter.NewFriendsMsgAdapter;
import com.example.dllo.yuliaoapp.ui.fragment.C_AbsBaseFragment;

import java.util.List;

/**
 * Created by dllo on 16/10/24.
 * @author 陈思宇
 * 环信申请好友页面
 */
public class C_ECNewsFriendsMsgFragment extends C_AbsBaseFragment{
    public static C_ECNewsFriendsMsgFragment newInstance() {
        Bundle args = new Bundle();
        C_ECNewsFriendsMsgFragment fragment = new C_ECNewsFriendsMsgFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private ListView listView;
    @Override
    protected int setLayout() {
        return R.layout.c_fragment_ec_news_friends_msg;
    }

    @Override
    protected void initViews() {
        listView=byView(R.id.list);
        InviteMessgeDao dao = new InviteMessgeDao(context);
        List<InviteMessage> msgs = dao.getMessagesList();
        NewFriendsMsgAdapter adapter = new NewFriendsMsgAdapter(context, 1, msgs);
        listView.setAdapter(adapter);
        dao.saveUnreadMessageCount(0);
    }

    @Override
    protected void initDatas() {

    }
}
