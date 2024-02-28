package com.tommyhumaxcar.myphone.controller;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tommyhumaxcar.myphone.common.AppInfo;

public class SetRank {
    private final String TAG = AppInfo.APP + "SetRank";
    private String pkgPhone = "com.tommyhumaxcar.myphone";
    private static SetRank mInstance = null;
    private SetRank() {};
    public static SetRank getInstance() {
        if (mInstance == null) {
            mInstance = new SetRank();
        }
        return mInstance;
    }

    private void sendBroadcastSetRank() {
        Intent intent = new Intent("ACTION_SET_RANK");
        intent.putExtra("KEY_SET_RANK", pkgPhone);
        AppInfo.getInstance().getApplicationContext().sendBroadcast(intent);
    }

    public View.OnClickListener onButtonClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button)v;
                Log.d(TAG, "ButtonClick: " + btn.getText().toString());
                sendBroadcastSetRank();
            }
        };
    }
}
