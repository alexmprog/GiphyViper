package com.instinctools.giphyviper.presentation.common.di.component;

import android.app.Application;
import android.content.Context;

import com.instinctools.giphyviper.data.di.DataStoreFactoryModule;
import com.instinctools.giphyviper.data.di.GiphyApiModule;
import com.instinctools.giphyviper.data.di.RepositoryModule;
import com.instinctools.giphyviper.data.giphy.api.GiphyObjectMapper;
import com.instinctools.giphyviper.data.giphy.api.GiphyService;
import com.instinctools.giphyviper.data.giphy.datastore.GifDataStoreFactory;
import com.instinctools.giphyviper.data.giphy.repository.GifRepository;
import com.instinctools.giphyviper.domain.di.UseCaseModule;
import com.instinctools.giphyviper.domain.usecase.gif.GetTrendingGifsUseCase;
import com.instinctools.giphyviper.presentation.common.di.module.ApplicationModule;
import com.instinctools.giphyviper.presentation.common.presenter.PresenterHolder;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class,
        GiphyApiModule.class, DataStoreFactoryModule.class, UseCaseModule.class})
public interface ApplicationComponent {

    Context provideContext();

    Application provideApplication();

    PresenterHolder providesPresenterHolder();

    GiphyService provideGiphyService();

    GiphyObjectMapper provideGiphyObjectMapper();

    GifDataStoreFactory provideGifDataStoreFactory();

    GetTrendingGifsUseCase provideGetTrendingGifsUseCase();

    GifRepository provideGifRepository();

}
