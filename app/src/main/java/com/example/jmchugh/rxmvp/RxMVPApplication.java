package com.example.jmchugh.rxmvp;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.example.jmchugh.rxmvp.app.dagger.AppComponent;
import com.example.jmchugh.rxmvp.app.dagger.AppModule.AppModule;
import com.example.jmchugh.rxmvp.app.dagger.DaggerAppComponent;
import com.example.jmchugh.rxmvp.app.logging.CrashReportTree;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by jmchugh on 1/11/2018.
 *
 * https://github.com/MindorksOpenSource/android-mvp-architecture
 */

public class RxMVPApplication extends Application {

    private AppComponent appComponent;

    private RefWatcher refWatcher;

    public static RxMVPApplication get(Activity activity){

        return (RxMVPApplication) activity.getApplication();
    }

    public static RxMVPApplication get(Service service){

        return (RxMVPApplication) service.getApplication();
    }

    public static RefWatcher getRefWater(Context context) {

        RxMVPApplication application = (RxMVPApplication) context.getApplicationContext();

        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //Init the logging
        Timber.plant(BuildConfig.DEBUG ? new Timber.DebugTree() : new CrashReportTree());

        //Init Fire base reporting.
        Fabric.with(this, new Crashlytics());
        FirebaseAnalytics.getInstance(this);

        //Init Application level dependency injection.
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        //Init LeakCanary
        if(LeakCanary.isInAnalyzerProcess(this)){

            return;
        }

        refWatcher = LeakCanary.install(this);
    }

    public AppComponent component() {

        return appComponent;
    }
}
