package com.example.dllo.yuliaoapp.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dllo.yuliaoapp.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.util.List;

/**
 * Created by dllo on 16/10/20.
 *
 * @author 陈思宇
 *         环信聊天界面
 */
public class C_ECChatActivity extends C_AbsBaseActivity implements EMMessageListener{
    //发送按钮
    private Button mBtnSendMsg;
    //聊天消息框
    private EditText mEtMsg;
    //显示内容的TextView
    private TextView mTvShow;
    //消息监听器
    private EMMessageListener mMessageListener;
    //当前聊天的ID
    private String mChatId;
    //当前会话对象
    private EMConversation mConversation;

    @Override
    protected int setLayout() {
        return R.layout.c_activity_ecchat;
    }

    @Override
    protected void initViews() {
        mBtnSendMsg = byView(R.id.btn_ecchat_send_msg);
        mEtMsg = byView(R.id.et_ecchat_msg);
        mTvShow = byView(R.id.tv_ecchat_show);
        //设置textview可滚动 需要配合xml布局设置
        mTvShow.setMovementMethod(new ScrollingMovementMethod());


    }

    @Override
    protected void initData() {
        mChatId = getIntent().getStringExtra("user_name");
        Log.d("xxx", mChatId);
        mMessageListener =this;
        mBtnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEtMsg.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    mEtMsg.setText("");
                    //创建一条新消息 第一个参数为消息内容 第二个为接收者的id
                    EMMessage message = EMMessage.createTxtSendMessage(content, mChatId);
                    //将新的消息内容和时间加入到下边
                    mTvShow.setText(mTvShow.getText() + "\n" + content + " ->" + message.getMsgTime());
                    //调用发送信息的方法
                    EMClient.getInstance().chatManager().sendMessage(message);
                    //为消息设置回调
                    message.setMessageStatusCallback(new EMCallBack() {
                        @Override
                        public void onSuccess() {
                            //消息发送成功 打印下日志 正常操作应该去刷新ui
                            Log.d("C_ECChatActivity", "send message on success");
                        }

                        @Override
                        public void onError(int i, String s) {
                            //消息发送失败 打印下失败信息 正常操作应该去刷新ui
                            Log.d("C_ECChatActivity", "send message on error" + i + "-" + s);

                        }

                        @Override
                        public void onProgress(int i, String s) {
                            //消息发送进度 一般只有在发送图片和文件等消息才会有回调 txt不回调
                        }
                    });
                }
            }
        });
        initConversation();
    }

    /**
     * 初始化会话对象 并且根据需要加载更多信息
     */
    private void initConversation() {
        /**
         * 初始化会话对象 这里有3个参数
         * 参数1:表示会话的当前聊天的username或者groupid
         * 参数2:是会话类型  可以为空
         * 参数3:表示如果会话不存在 是否创建
         */
        mConversation = EMClient.getInstance().chatManager().getConversation(mChatId, null, true);
        //设置当前会话未读数为0
        mConversation.markAllMessagesAsRead();
        int count = mConversation.getAllMessages().size();
        if (count < mConversation.getAllMsgCount() && count < 20) {
            //获取已经在列表中的最上边的一条信息id
            String msgId = mConversation.getAllMessages().get(0).getMsgId();
            //分页加载更多信息 需要传递已经加载的消息的最上边一条信息的id 以及需要加载的信息的条数
            mConversation.loadMoreMsgFromDB(msgId, 20 - count);
        }
        //打开聊天界面获取最后一条消息内容并显示
        if (mConversation.getAllMessages().size()>0){
            EMMessage message=mConversation.getLastMessage();
            EMTextMessageBody body= (EMTextMessageBody) message.getBody();
            //将消息内容和时间显示出来
            mTvShow.setText(body.getMessage()+ " - "+mConversation.getLastMessage().getMsgTime());
        }
    }
    /**
     * 自定义实现Handler  主要用于刷新UI操作
     */
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    EMMessage message= (EMMessage) msg.obj;
                    //这里只是简单的demo  也只是测试文字消息的手法 所以直接将body转为EMTextMessageBody获取内容
                    EMTextMessageBody body= (EMTextMessageBody) message.getBody();
                    //将新的消息内容和时间加入到下边
                    mTvShow.setText(mTvShow.getText()+"\n"+body.getMessage()+"<- "+message.getMsgTime());
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        //添加消息监听
        EMClient.getInstance().chatManager().addMessageListener(mMessageListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //移除消息监听
        EMClient.getInstance().chatManager().removeMessageListener(mMessageListener);
    }


    /**
     * -----------Message Listener-------------
     * 环信消息监听主要方法
     */
    /**
     * 收到新消息
     *
     */

    @Override
    public void onMessageReceived(List<EMMessage> list) {
        //循环遍历当前收到的消息
        for (EMMessage message:list) {
            if (message.getFrom().equals(mChatId)){
                //设置消息为已读
                mConversation.markMessageAsRead(message.getMsgId());
                //因为消息监听回调这里是非ui线程 所以要用handler去更新ui
                Message msg=mHandler.obtainMessage();
                msg.what=0;
                msg.obj=message;
                mHandler.sendMessage(msg);
            }else {
                //如果消息不是当前会话的消息发送通知栏通知
            }
        }
    }

    /**
     * 收到新的CMD消息
     * @param list
     */
    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {
        for (int i = 0; i <list.size() ; i++) {
            //透传消息
            EMMessage cmdMessage=list.get(i);
            EMCmdMessageBody body= (EMCmdMessageBody) cmdMessage.getBody();
            Log.d("C_ECChatActivity", body.action());
        }
    }

    /**
     * 收到新的已读回执
     * @param list
     */
    @Override
    public void onMessageReadAckReceived(List<EMMessage> list) {

    }

    /**
     * 收到新的发送回执
     * TODO 无效 暂时有bug
     * @param list 收到发送回执的消息集合
     */
    @Override
    public void onMessageDeliveryAckReceived(List<EMMessage> list) {

    }

    /**
     * 消息的状态改变
     * @param emMessage 发生改变的消息
     * @param o 包含改变的消息
     */
    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {

    }

}
