package com.instinctools.giphyviper.presentation.ui.details.di;

import com.instinctools.giphyviper.presentation.ui.details.DetailsPresenter;
import com.instinctools.giphyviper.presentation.ui.details.DetailsRouter;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailsModule {

    @Provides
    public DetailsPresenter providesDetailsPresenter() {
        return new DetailsPresenter();
    }

    @Provides
    public DetailsRouter providesDetailsRouter() {
        return new DetailsRouter();
    }
}
