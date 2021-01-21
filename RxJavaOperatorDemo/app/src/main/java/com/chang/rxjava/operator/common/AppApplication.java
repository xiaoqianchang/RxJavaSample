package com.chang.rxjava.operator.common;

import android.app.Application;
import android.content.Context;

import com.chang.rxjava.operator.utils.BuildConfig;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2016/12/5.
 *
 * @version 1.0
 */

public class AppApplication extends Application {

    private static final String TAG = AppApplication.class.getSimpleName();

    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();

        // Logger Settings
        // Note: Use LogLevel.NONE for the release versions.
        Logger
                .init(TAG)                       // default PRETTYLOGGER or use just init()
                .methodCount(2)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE) // default LogLevel.FULL
                .methodOffset(2);               // default 0
    }
}
