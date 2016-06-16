package com.instinctools.giphyviper.presentation.ui.details.di;

import com.instinctools.giphyviper.presentation.common.di.component.ActivityComponent;
import com.instinctools.giphyviper.presentation.common.di.component.ApplicationComponent;
import com.instinctools.giphyviper.presentation.common.di.module.ActivityModule;
import com.instinctools.giphyviper.presentation.common.di.scope.ActivityScope;
import com.instinctools.giphyviper.presentation.ui.details.DetailsActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class, DetailsModule.class})
public interface DetailsComponent extends ActivityComponent {

    void inject(DetailsActivity detailsActivity);
}
