package com.xyz.myotee.global;

import android.app.Application;

import java.io.File;


public class MyApplication extends Application {

    private ActivityCollector activityCollector;

    @Override
    public void onCreate() {
        super.onCreate();
        activityCollector = new ActivityCollector();
        File file = new File(Constant.APP_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public ActivityCollector getActivityCollector() {
        return activityCollector;
    }



}
