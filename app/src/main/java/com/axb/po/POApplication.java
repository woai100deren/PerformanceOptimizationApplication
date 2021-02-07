package com.axb.po;

import android.app.Application;
import android.os.Debug;
import android.util.Log;

public class POApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Debug.startMethodTracing("AXIBA");
        Log.e("TAG","123");
        Debug.stopMethodTracing();
    }
}
