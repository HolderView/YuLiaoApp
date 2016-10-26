package com.example.dllo.yuliaoapp.ui.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.dllo.yuliaoapp.model.db.EaseUser;
import com.example.dllo.yuliaoapp.model.db.UserDao;
import com.example.dllo.yuliaoapp.tools.C_Constant;
import com.fuqianla.paysdk.FuQianLa;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/10/19.
 * 项目的Application类 做一些项目的初始化操作 比如sdk的初始化等
 * @author 陈思宇
 */
public class C_MyApp extends Application {
//    private static C_MyApp instance;
//    private String username = "";
//    private static Context context;
//    //记录是否已经初始化
//    private boolean isInit = false;
//    private Map<String, EaseUser> contactList;
//    private UserDao userDao;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        context = getApplicationContext();
//        instance=this;
//        //初始化环信sdk
//        initEasemob();
//    }
//    public static C_MyApp getInstance() {
//        return instance;
//    }
//    public String getCurrentUserName() {
//        if (TextUtils.isEmpty(username)) {
//            username = Myinfo.getInstance(instance).getUserInfo(C_Constant.KEY_USERNAME);
//
//        }
//        return username;
//
//    }
//    private void initDbDao(Context context) {
//        userDao = new UserDao(context);
//    }
//    private void initEasemob() {
//        //获取当前进程id并取得程序名
//        int pid = android.os.Process.myPid();
//        String processAppName = getAppName(pid);
//        /**
//         * 如果app启动了远程的service 此application:onCreate会被调用2次
//         * 为了防止环信sdk被初始化2此 加此判断会保证sdk被初始化1次
//         * 默认的app会在以包名为默认的process name下运行 如果查到的process name不是app的process name就立即返回
//         */
//        if (processAppName == null || !processAppName.equalsIgnoreCase(context.getPackageName())) {
//            //则此application的onCreate是被service调用的直接返回
//            return;
//        }
//        if (isInit) {
//            return;
//        }
//
//        /**
//         * sdk初始化的一些配置
//         * 关于EMOptions 可以参考官方的API文档
//         * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1chat_1_1_e_m_options.html
//         */
//        EMOptions options = new EMOptions();
//        boolean success = initSDK(context, options);
//        if (success) {
//            // 设为调试模式，打成正式包时，最好设为false，以免消耗额外的资源
//            EMClient.getInstance().setDebugMode(true);
//            // 初始化数据库
//            initDbDao(context);
//        }
//        //设置自动登录
//        options.setAutoLogin(true);
//        //设置是否需要发送已读回执
//        options.setRequireAck(true);
//        //设置是否需要发送回执 TODO这个暂时有bug 上层收不到发送回执
//        options.setRequireDeliveryAck(true);
//        //设置是否需要服务器收到消息确认
//        options.setRequireServerAck(true);
//        //收到好友申请是否自动同意 如果是自动同意就不会收到好友请求的回调 因为sdk会自动处理 默认为true
//        options.setAcceptInvitationAlways(true);
//        //设置是否自动接收加群邀请 如果设置了当收到群邀请会自动同意加入
//        options.setAutoAcceptGroupInvitation(false);
//        //设置(主动或被动)退出群组时,是否删除群聊聊天记录
//        options.setDeleteMessagesAsExitGroup(false);
//        //设置是否允许聊天室的Owner 离开并删除聊天室会话
//        options.allowChatroomOwnerLeave(true);
//        //调用初始化方法初始化sdk
//        EMClient.getInstance().init(context, options);
//        //设置开启debug模式
//        //在做打包混淆时 关闭debug模式 避免消耗不必要的资源
//        EMClient.getInstance().setDebugMode(true);
//        //设置初始化已经完成
//        isInit = true;
//    }
//    private EMOptions initChatOptions() {
//
//        // 获取到EMChatOptions对象
//        EMOptions options = new EMOptions();
//        // 默认添加好友时，是不需要验证的，改成需要验证
//        options.setAcceptInvitationAlways(false);
//        // 设置是否需要已读回执
//        options.setRequireAck(true);
//        // 设置是否需要已送达回执
//        options.setRequireDeliveryAck(false);
//        return options;
//    }
//    private boolean sdkInited = false;
//    public synchronized boolean initSDK(Context context, EMOptions options) {
//        if (sdkInited) {
//            return true;
//        }
//
//        int pid = android.os.Process.myPid();
//        String processAppName = getAppName(pid);
//
//        // 如果app启用了远程的service，此application:onCreate会被调用2次
//        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
//        // 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process
//        // name就立即返回
//        if (processAppName == null || !processAppName.equalsIgnoreCase(context.getPackageName())) {
//
//            // 则此application::onCreate 是被service 调用的，直接返回
//            return false;
//        }
//        if (options == null) {
//            EMClient.getInstance().init(context, initChatOptions());
//        } else {
//            EMClient.getInstance().init(context, options);
//        }
//        sdkInited = true;
//        return true;
//    }
//
//    public static Context getContext() {
//        return context;
//    }
//
//    /**
//     * 根据Pid获取当前继承的名字 一般就是当前app的包名
//     *
//     * @param pID 进程的id
//     * @return 返回进程的名字
//     */
//    private String getAppName(int pID) {
//        String processName = null;
//        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
//        List l = am.getRunningAppProcesses();
//        Iterator i = l.iterator();
//        PackageManager pm = this.getPackageManager();
//        while (i.hasNext()) {
//            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) i.next();
//            try {
//                if (info.pid == pID) {
//                    //根据进程的信息获取当前进度的名字
//                    processName = info.processName;
//                    //返回当前进程名
//                    return processName;
//                }
//            } catch (Exception e) {
//                Log.d("Process", "Error>> :" + e.toString());
//            }
//        }
//        //没有匹配的项 返回null
//        return null;
//    }
//    public Map<String, EaseUser> getContactList() {
//
//        if (contactList == null) {
//
//            contactList = userDao.getContactList();
//
//        }
//        return contactList;
//
//    }
public static Context applicationContext;
    private static C_MyApp instance;
    private String username = "";
    private Map<String, EaseUser> contactList;

    @Override
    public void onCreate() {
        super.onCreate();
        FuQianLa.getInstance().init(getApplicationContext());
        applicationContext = this;
        instance = this;
        // 初始化环信sdk
        init(applicationContext);
    }

    public static C_MyApp getInstance() {
        return instance;
    }

	/*
	 * 第一步：sdk的一些参数配置 EMOptions 第二步：将配置参数封装类 传入SDK初始化
	 */

    public void init(Context context) {
        // 第一步
        EMOptions options = initChatOptions();
        // 第二步
        boolean success = initSDK(context, options);
        if (success) {
            // 设为调试模式，打成正式包时，最好设为false，以免消耗额外的资源
            EMClient.getInstance().setDebugMode(true);
            // 初始化数据库
            initDbDao(context);
        }
    }

    private void initDbDao(Context context) {
        userDao = new UserDao(context);
    }

    public void setCurrentUserName(String username) {
        this.username = username;
        Myinfo.getInstance(instance).setUserInfo(C_Constant.KEY_USERNAME, username);
    }

    public String getCurrentUserName() {
        if (TextUtils.isEmpty(username)) {
            username = Myinfo.getInstance(instance).getUserInfo(C_Constant.KEY_USERNAME);

        }
        return username;

    }

    private UserDao userDao;

    private EMOptions initChatOptions() {

        // 获取到EMChatOptions对象
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        // 设置是否需要已读回执
        options.setRequireAck(true);
        // 设置是否需要已送达回执
        options.setRequireDeliveryAck(false);
        return options;
    }

    private boolean sdkInited = false;

    public synchronized boolean initSDK(Context context, EMOptions options) {
        if (sdkInited) {
            return true;
        }

        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);

        // 如果app启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process
        // name就立即返回
        if (processAppName == null || !processAppName.equalsIgnoreCase(applicationContext.getPackageName())) {

            // 则此application::onCreate 是被service 调用的，直接返回
            return false;
        }
        if (options == null) {
            EMClient.getInstance().init(context, initChatOptions());
        } else {
            EMClient.getInstance().init(context, options);
        }
        sdkInited = true;
        return true;
    }

    /**
     * check the application process name if process name is not qualified, then
     * we think it is a service process and we will not init SDK
     *
     * @param pID
     * @return
     */
    @SuppressWarnings("rawtypes")
    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) applicationContext.getSystemService(Context.ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {

                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
            }
        }
        return processName;
    }

    public void setContactList(Map<String, EaseUser> contactList) {

        this.contactList = contactList;

        userDao.saveContactList(new ArrayList<EaseUser>(contactList.values()));

    }

    public Map<String, EaseUser> getContactList() {

        if (contactList == null) {

            contactList = userDao.getContactList();

        }
        return contactList;

    }

    /**
     * 退出登录
     *
     * @param unbindDeviceToken
     *            是否解绑设备token(使用GCM才有)
     * @param callback
     *            callback
     */
    public void logout(boolean unbindDeviceToken, final EMCallBack callback) {

        EMClient.getInstance().logout(unbindDeviceToken, new EMCallBack() {

            @Override
            public void onSuccess() {

                if (callback != null) {
                    callback.onSuccess();
                }

            }

            @Override
            public void onProgress(int progress, String status) {
                if (callback != null) {
                    callback.onProgress(progress, status);
                }
            }

            @Override
            public void onError(int code, String error) {

                if (callback != null) {
                    callback.onError(code, error);
                }
            }
        });
    }
}
