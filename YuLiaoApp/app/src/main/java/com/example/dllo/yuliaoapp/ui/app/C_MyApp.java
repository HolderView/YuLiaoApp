package com.example.dllo.yuliaoapp.ui.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import java.util.Iterator;
import java.util.List;

/**
 * Created by dllo on 16/10/19.
 * 项目的Application类 做一些项目的初始化操作 比如sdk的初始化等
 * @author 陈思宇
 */
public class C_MyApp extends Application {
    private static Context context;
    //记录是否已经初始化
    private boolean isInit = false;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //初始化环信sdk
        initEasemob();
    }

    private void initEasemob() {
        //获取当前进程id并取得程序名
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        /**
         * 如果app启动了远程的service 此application:onCreate会被调用2次
         * 为了防止环信sdk被初始化2此 加此判断会保证sdk被初始化1次
         * 默认的app会在以包名为默认的process name下运行 如果查到的process name不是app的process name就立即返回
         */
        if (processAppName == null || !processAppName.equalsIgnoreCase(context.getPackageName())) {
            //则此application的onCreate是被service调用的直接返回
            return;
        }
        if (isInit) {
            return;
        }
        /**
         * sdk初始化的一些配置
         * 关于EMOptions 可以参考官方的API文档
         * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1chat_1_1_e_m_options.html
         */
        EMOptions options = new EMOptions();
        //设置自动登录
        options.setAutoLogin(true);
        //设置是否需要发送已读回执
        options.setRequireAck(true);
        //设置是否需要发送回执 TODO这个暂时有bug 上层收不到发送回执
        options.setRequireDeliveryAck(true);
        //设置是否需要服务器收到消息确认
        options.setRequireServerAck(true);
        //收到好友申请是否自动同意 如果是自动统一就不会收到好友请求的回调 因为sdk会自动处理 默认为true
        options.setAcceptInvitationAlways(false);
        //设置是否自动接收加群邀请 如果设置了当收到群邀请会自动同意加入
        options.setAutoAcceptGroupInvitation(false);
        //设置(主动或被动)退出群组时,是否删除群聊聊天记录
        options.setDeleteMessagesAsExitGroup(false);
        //设置是否允许聊天室的Owner 离开并删除聊天室会话
        options.allowChatroomOwnerLeave(true);
        //调用初始化方法初始化sdk
        EMClient.getInstance().init(context, options);
        //设置开启debug模式
        //在做打包混淆时 关闭debug模式 避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
        //设置初始化已经完成
        isInit = true;
    }

    public static Context getContext() {
        return context;
    }

    /**
     * 根据Pid获取当前继承的名字 一般就是当前app的包名
     *
     * @param pID 进程的id
     * @return 返回进程的名字
     */
    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) i.next();
            try {
                if (info.pid == pID) {
                    //根据进程的信息获取当前进度的名字
                    processName = info.processName;
                    //返回当前进程名
                    return processName;
                }
            } catch (Exception e) {
                Log.d("Process", "Error>> :" + e.toString());
            }
        }
        //没有匹配的项 返回null
        return null;
    }
}
