package com.kw.kw;

import android.app.Application;

import org.xutils.x;

public class KwApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.setDebug(true);
        x.Ext.init(this);
    }
}
