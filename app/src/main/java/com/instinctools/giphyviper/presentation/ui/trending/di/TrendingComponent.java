package com.instinctools.giphyviper.presentation.ui.trending.di;

import com.instinctools.giphyviper.presentation.common.di.component.ActivityComponent;
import com.instinctools.giphyviper.presentation.common.di.component.ApplicationComponent;
import com.instinctools.giphyviper.presentation.common.di.module.ActivityModule;
import com.instinctools.giphyviper.presentation.common.di.scope.ActivityScope;
import com.instinctools.giphyviper.presentation.ui.trending.TrendingActivity;
import com.instinctools.giphyviper.presentation.ui.trending.di.TrendingModule;

import dagger.Component;

@ActivityScope
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class, TrendingModule.class})
public interface TrendingComponent extends ActivityComponent {

    void inject(TrendingActivity trendingActivity);
}
