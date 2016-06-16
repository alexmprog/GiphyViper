package com.instinctools.giphyviper.data.di;

import com.instinctools.giphyviper.data.giphy.api.GiphyObjectMapper;
import com.instinctools.giphyviper.data.giphy.api.GiphyService;
import com.instinctools.giphyviper.data.giphy.api.GiphyServiceFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class GiphyApiModule {

    @Provides
    GiphyService provideGiphyService() {
        return GiphyServiceFactory.createGiphyService();
    }

    @Provides
    GiphyObjectMapper provideGiphyObjectMapper() {
        return new GiphyObjectMapper();
    }
}
