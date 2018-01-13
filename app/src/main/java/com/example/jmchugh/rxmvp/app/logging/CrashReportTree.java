package com.example.jmchugh.rxmvp.app.logging;

import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.crash.FirebaseCrash;

import timber.log.Timber;

/**
 * Created by jmchugh on 1/11/2018.
 */

public class CrashReportTree extends Timber.Tree {

    private static final String CRASHLYTICS_KEY_PRIORITY = "priority";
    private static final String CRASHLYTICS_KEY_TAG = "tag";
    private static final String CRASHLYTICS_KEY_MESSAGE = "message";

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {

        if(priority == Log.VERBOSE || priority == Log.DEBUG) {

            return;
        }

        Crashlytics.setInt(CRASHLYTICS_KEY_PRIORITY, priority);
        Crashlytics.setString(CRASHLYTICS_KEY_TAG, tag);
        Crashlytics.setString(CRASHLYTICS_KEY_MESSAGE, message);

        Throwable throwable = t != null ? t : new Exception(message);

        FirebaseCrash.logcat(priority, tag, message);
        FirebaseCrash.report(throwable);
    }
}
