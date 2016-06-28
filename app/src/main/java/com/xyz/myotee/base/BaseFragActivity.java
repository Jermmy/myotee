package com.xyz.myotee.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.xyz.myotee.global.MyApplication;

public class BaseFragActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication)getApplication()).getActivityCollector().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((MyApplication)getApplication()).getActivityCollector().removeActivity(this);
    }
}
