package com.example.jmchugh.rxmvp.leak;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.jmchugh.rxmvp.R;
import com.example.jmchugh.rxmvp.RxMVPApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 *
 * https://github.com/square/leakcanary/blob/master/leakcanary-sample/src/main/java/com/example/leakcanary/MainActivity.java
 */
public class LeakTestActivity extends AppCompatActivity {

    @BindView(R.id.async_button)
    Button asyncButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_test);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        RxMVPApplication.getRefWater(getApplicationContext()).watch(this);
    }

    @OnClick(R.id.async_button)
    public void asyncButtonClick(){

        new AsyncTask<Void, Void, Void>(){

            // This async task is an anonymous class and therefore has a hidden reference to the outer
            // class MainActivity. If the activity gets destroyed before the task finishes (e.g. rotation),
            // the activity instance will leak.
            @Override
            protected Void doInBackground(Void... voids) {
                // Do some slow work in background
                SystemClock.sleep(20000);
                return null;
            }
        }.execute();
    }
}
