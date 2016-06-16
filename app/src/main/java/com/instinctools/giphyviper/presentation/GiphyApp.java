package com.instinctools.giphyviper.presentation;

import android.app.Application;
import android.content.Context;

import com.instinctools.giphyviper.BuildConfig;
import com.instinctools.giphyviper.data.di.DataStoreFactoryModule;
import com.instinctools.giphyviper.data.di.GiphyApiModule;
import com.instinctools.giphyviper.data.di.RepositoryModule;
import com.instinctools.giphyviper.domain.di.UseCaseModule;
import com.instinctools.giphyviper.presentation.common.di.component.ApplicationComponent;
import com.instinctools.giphyviper.presentation.common.di.component.DaggerApplicationComponent;
import com.instinctools.giphyviper.presentation.common.di.module.ApplicationModule;

import timber.log.Timber;

public class GiphyApp extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        applicationComponent = DaggerApplicationComponent.builder()
                .repositoryModule(new RepositoryModule())
                .dataStoreFactoryModule(new DataStoreFactoryModule())
                .giphyApiModule(new GiphyApiModule())
                .useCaseModule(new UseCaseModule())
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static GiphyApp get(Context context) {
        return (GiphyApp) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
