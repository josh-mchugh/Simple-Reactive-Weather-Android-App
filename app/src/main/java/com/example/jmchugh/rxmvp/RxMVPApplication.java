package com.example.jmchugh.rxmvp;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import com.crashlytics.android.Crashlytics;
import com.example.jmchugh.rxmvp.app.dagger.AppComponent;
import com.example.jmchugh.rxmvp.app.dagger.AppModule.AppModule;
import com.example.jmchugh.rxmvp.app.dagger.DaggerAppComponent;
import com.example.jmchugh.rxmvp.app.logging.CrashReportTree;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.leakcanary.LeakCanary;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by jmchugh on 1/11/2018.
 */

public class RxMVPApplication extends Application {

    public static RxMVPApplication get(Activity activity){

         return (RxMVPApplication) activity.getApplication();
    }

    public static RxMVPApplication get(Service service){

        return (RxMVPApplication) service.getApplication();
    }

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //Init the logging
        Timber.plant(BuildConfig.DEBUG ? new Timber.DebugTree() : new CrashReportTree());

        //Init Fire base reporting.
        Fabric.with(this, new Crashlytics());
        FirebaseAnalytics.getInstance(this);

        //Init Lead Canary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        //Init Application level dependency injection.
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent component() {

        return appComponent;
    }
}
