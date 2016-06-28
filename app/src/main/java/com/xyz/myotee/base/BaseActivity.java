package com.xyz.myotee.base;

import android.app.Activity;
import android.os.Bundle;

import com.xyz.myotee.global.MyApplication;


public class BaseActivity extends Activity {


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
