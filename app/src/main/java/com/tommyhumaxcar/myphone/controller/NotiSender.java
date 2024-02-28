package com.tommyhumaxcar.myphone.controller;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.core.app.NotificationCompat;

import com.tommyhumaxcar.myphone.common.AppInfo;

import java.util.HashSet;

public class NotiSender {
    private final String TAG = AppInfo.APP + "NotiSender";
    private HashSet<String> notiCheckBox = new HashSet<>();
    private static NotiSender mInstance = null;
    private NotiSender() {};
    public static NotiSender getInstance() {
        if (mInstance == null) {
            mInstance = new NotiSender();
        }
        return mInstance;
    }

    private void createNotiChannel() {
        NotificationChannel channel = new NotificationChannel(AppInfo.CHANNEL_ID,
                AppInfo.CHANNEL_NAME, AppInfo.CHANNEL_IMPORTANT);
        channel.setDescription(AppInfo.CHANNEL_DESCRIPTION);

        NotificationManager notificationManager =
                AppInfo.getInstance().getApplicationContext().getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(channel);
        } else {
            Log.e(TAG, "Can not create channel");
        }
    }

    private void buidNoti() {
        NotificationManager notificationManager =
                (NotificationManager) AppInfo.getInstance().getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                AppInfo.getInstance().getApplicationContext(),AppInfo.CHANNEL_ID);

        builder.setSmallIcon(AppInfo.APP_ICON);
        builder.setContentTitle(AppInfo.APP_TITLE);
        builder.setContentText(AppInfo.APP_TEXT);

        notificationManager.notify(1, builder.build());
    }
    private void noti() {
        createNotiChannel();
        buidNoti();
    }

    public View.OnClickListener onButtonClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button)v;
                Log.d(TAG, "ButtonClick: " + btn.getText().toString());
                noti();
            }
        };
    }

    public CompoundButton.OnCheckedChangeListener onCheck() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, buttonView.getText().toString() + " isCheck = " + isChecked);
                if (isChecked) {
                    Log.d(TAG, "check!!!");
                    notiCheckBox.add(buttonView.getText().toString());
                } else {
                    Log.d(TAG, "uncheck!!!");
                    notiCheckBox.remove(buttonView.getText().toString());
                }
                Log.d(TAG, "get notiCheckBox" + notiCheckBox.toString());
            }
        };
    }

    public void onReceiveBroadcast (String act) {
        Log.d(TAG, "onReceiveBroadcast: act: " + act + " notiCheckBox: " + notiCheckBox.toString());
        if (notiCheckBox.toString().equals("[ACTION_PHONE_SEND_NOTI]")) {
            noti();
        } else {
            Log.d(TAG, "do nothing!!!");
        }
    }
}
