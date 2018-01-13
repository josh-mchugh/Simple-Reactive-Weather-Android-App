package com.example.jmchugh.cleanarchitectureexample;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import com.crashlytics.android.Crashlytics;
import com.example.jmchugh.cleanarchitectureexample.app.dagger.AppComponent;
import com.example.jmchugh.cleanarchitectureexample.app.dagger.AppModule.AppModule;
import com.example.jmchugh.cleanarchitectureexample.app.dagger.DaggerAppComponent;
import com.example.jmchugh.cleanarchitectureexample.app.logging.CrashReportTree;
import com.google.firebase.analytics.FirebaseAnalytics;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by jmchugh on 1/11/2018.
 */

public class CleanArchitectureApplication extends Application {

    public static CleanArchitectureApplication get(Activity activity){

         return (CleanArchitectureApplication) activity.getApplication();
    }

    public static CleanArchitectureApplication get(Service service){

        return (CleanArchitectureApplication) service.getApplication();
    }

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(BuildConfig.DEBUG ? new Timber.DebugTree() : new CrashReportTree());

        Fabric.with(this, new Crashlytics());

        FirebaseAnalytics.getInstance(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent component() {

        return appComponent;
    }
}
