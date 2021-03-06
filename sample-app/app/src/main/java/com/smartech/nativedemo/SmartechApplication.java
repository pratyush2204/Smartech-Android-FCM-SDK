package com.smartech.nativedemo;

import android.app.Application;
import android.graphics.Color;

import com.smartech.nativedemo.Utils.Netcore;

import in.netcore.smartechfcm.NetcoreSDK;

public class SmartechApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Netcore.register(this);
        Netcore.setPushIconColor(this, Color.RED);
    }
}
