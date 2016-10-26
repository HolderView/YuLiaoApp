package com.example.dllo.yuliaoapp.model.db;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

/**
 * Created by dllo on 16/10/24.
 */
public class InviteMessgeDao {
    static final String TABLE_NAME = "new_friends_msgs";
    static final String COLUMN_NAME_ID = "id";
    static final String COLUMN_NAME_FROM = "username";
    static final String COLUMN_NAME_GROUP_ID = "groupid";
    static final String COLUMN_NAME_GROUP_Name = "groupname";

    static final String COLUMN_NAME_TIME = "time";
    static final String COLUMN_NAME_REASON = "reason";
    public static final String COLUMN_NAME_STATUS = "status";
    static final String COLUMN_NAME_ISINVITEFROMME = "isInviteFromMe";
    static final String COLUMN_NAME_GROUPINVITER = "groupinviter";

    static final String COLUMN_NAME_UNREAD_MSG_COUNT = "unreadMsgCount";


    public InviteMessgeDao(Context context){
    }

    /**
     * 保存message
     * @param message
     * @return  返回这条messaged在db中的id
     */
    public Integer saveMessage(InviteMessage message){
        return EcDbManager.getInstance().saveMessage(message);
    }

    /**
     * 更新message
     * @param msgId
     * @param values
     */
    public void updateMessage(int msgId,ContentValues values){
        EcDbManager.getInstance().updateMessage(msgId, values);
    }

    /**
     * 获取messges
     * @return
     */
    public List<InviteMessage> getMessagesList(){
        return EcDbManager.getInstance().getMessagesList();
    }

    public void deleteMessage(String from){
        EcDbManager.getInstance().deleteMessage(from);
    }

    public int getUnreadMessagesCount(){
        return EcDbManager.getInstance().getUnreadNotifyCount();
    }

    public void saveUnreadMessageCount(int count){
        EcDbManager.getInstance().setUnreadNotifyCount(count);
    }
}
