package com.tommyhumaxcar.myphone.controller;

import static com.tommyhumaxcar.myphone.common.AppInfo.*;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastReceiverHandler extends BroadcastReceiver {
    private static String TAG = "BroadcastReceiverHandler";
    @Override
    public void onReceive(Context context, Intent intent) {
        String act = intent.getAction().toString();
        Log.d(TAG, "onReceive: BroadcastReceiverHandler action: " + act);

        if (act.equals(ACTION_PHONE_SEND_NOTI)) {
            NotiSender.getInstance().onReceiveBroadcast(act);
        } else {
            Log.d(TAG, "do nothing!!!");
        }
    }
}
