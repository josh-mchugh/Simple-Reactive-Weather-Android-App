package com.example.jmchugh.cleanarchitectureexample.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jmchugh.cleanarchitectureexample.CleanArchitectureApplication;
import com.example.jmchugh.cleanarchitectureexample.weather.dagger.DaggerWeatherComponent;
import com.example.jmchugh.cleanarchitectureexample.weather.dagger.WeatherModule;
import com.example.jmchugh.cleanarchitectureexample.weather.mvp.presenter.WeatherPresenter;
import com.example.jmchugh.cleanarchitectureexample.weather.mvp.view.WeatherRecyclerAdapter;
import com.example.jmchugh.cleanarchitectureexample.R;
import com.example.jmchugh.cleanarchitectureexample.weather.mvp.view.WeatherView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class WeatherActivity extends AppCompatActivity {

    @Inject
    WeatherView view;

    @Inject
    WeatherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerWeatherComponent.builder()
                .appComponent(CleanArchitectureApplication.get(this).component())
                .weatherModule(new WeatherModule(this))
                .build().inject(this);

        setContentView(view);
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
