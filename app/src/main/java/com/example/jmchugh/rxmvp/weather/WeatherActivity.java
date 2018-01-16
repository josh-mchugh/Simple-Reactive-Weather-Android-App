package com.example.jmchugh.rxmvp.weather;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jmchugh.rxmvp.R;
import com.example.jmchugh.rxmvp.RxMVPApplication;
import com.example.jmchugh.rxmvp.leak.LeakTestActivity;
import com.example.jmchugh.rxmvp.weather.dagger.DaggerWeatherComponent;
import com.example.jmchugh.rxmvp.weather.dagger.WeatherModule;
import com.example.jmchugh.rxmvp.weather.mvp.presenter.WeatherPresenter;
import com.example.jmchugh.rxmvp.weather.mvp.view.WeatherView;

import javax.inject.Inject;

public class WeatherActivity extends AppCompatActivity {

    private static Activity leak;
    private static String LEAK_TEST = "TEST";

    @Inject
    WeatherView view;

    @Inject
    WeatherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerWeatherComponent.builder()
                .appComponent(RxMVPApplication.get(this).component())
                .weatherModule(new WeatherModule(this))
                .build().inject(this);

        setContentView(view);
        presenter.onCreate();

        this.leak = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();

        RxMVPApplication.getRefWater(getApplicationContext()).watch(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_item_test) {

            Intent intent = new Intent(this, LeakTestActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
