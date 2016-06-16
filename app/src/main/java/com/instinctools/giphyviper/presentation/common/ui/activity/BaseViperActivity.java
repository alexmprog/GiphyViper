package com.instinctools.giphyviper.presentation.common.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.instinctools.giphyviper.presentation.common.di.component.ActivityComponent;
import com.instinctools.giphyviper.presentation.common.presenter.ViperPresenter;
import com.instinctools.giphyviper.presentation.common.router.ViperRouter;
import com.instinctools.giphyviper.presentation.common.view.ViperView;

import javax.inject.Inject;

public abstract class BaseViperActivity<V extends ViperView, R extends ViperRouter, P extends ViperPresenter<V, R>, T extends ActivityComponent> extends AppCompatActivity implements ActivityComponent.Injector<T> {

    @Inject
    protected ViperActivityDelegate<V, R, P> activityDelegate;

    private T activityComponent;

    protected P getViperPresenter() {
        return activityDelegate.getViperPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        activityDelegate.initPresenter(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        activityDelegate.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityDelegate.onDestroy();
    }

    public T getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = createActivityComponent();
        }
        return activityComponent;
    }
}


