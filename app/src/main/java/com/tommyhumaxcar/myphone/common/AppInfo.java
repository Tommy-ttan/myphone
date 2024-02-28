package com.tommyhumaxcar.myphone.common;

import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.WindowManager;

import com.tommyhumaxcar.myphone.R;

public class AppInfo {
    public static String    APP                 =   "MY PHONE - ";
    private static String   TAG                 =   APP + "AppInfo";
    public static String    CHANNEL_ID          =   "TOMMY_CHANNEL_ID";
    public static String    CHANNEL_NAME        =   "TOMMY_CHANNEL_NAME";
    public static int       CHANNEL_IMPORTANT   =   NotificationManager.IMPORTANCE_DEFAULT;
    public static String    CHANNEL_DESCRIPTION =   "TOMMY_CHANNEL_DESCRIPTION";
    public static String    APP_TITLE           =   "My Phone";
    public static String    APP_TEXT            =   "This is text from My Phone";
    public static int       APP_ICON            =   R.drawable.my_phone;
    public static String    ACTION_PHONE_SEND_NOTI    =     "ACTION_PHONE_SEND_NOTI";
    private WindowManager mWindowManager = null;
    private Context mApplicationContext = null;
    private Resources mAppResource = null;
    private static AppInfo mInstance = null;
    private AppInfo() {};
    public static AppInfo getInstance() {
        if (mInstance == null) {
            mInstance = new AppInfo();
        }
        return mInstance;
    }

    public void setApplicationContext(Context context) {
        mApplicationContext = context;
    }

    public Context getApplicationContext() {
        return mApplicationContext;
    }

    public void setWindowManager(WindowManager windowManager) {
        mWindowManager = windowManager;
    }

    public WindowManager getWindowManager() {
        return mWindowManager;
    }

    public Resources getAppResource() {
        if (mAppResource == null) {
            mAppResource = mApplicationContext.getResources();
        }
        return mAppResource;
    }

    public Object getSystemService(String serviceName) {
        if(mApplicationContext == null) {
            Log.e(TAG, "App Context is null");
            return null;
        }
        return mApplicationContext.getSystemService(serviceName);
    }
}
