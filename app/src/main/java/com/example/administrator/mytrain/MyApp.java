package com.example.administrator.mytrain;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Administrator on 2017/11/17 0017.
 */

public class MyApp  extends Application {
    private static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        CrashReport.initCrashReport(getApplicationContext(), "fa240d2d55", true);
    }

    public static MyApp getApp() {
        return app;
    }
}
