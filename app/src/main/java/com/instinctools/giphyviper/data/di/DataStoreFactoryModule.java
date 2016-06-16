package com.instinctools.giphyviper.data.di;

import com.instinctools.giphyviper.data.giphy.api.GiphyObjectMapper;
import com.instinctools.giphyviper.data.giphy.api.GiphyService;
import com.instinctools.giphyviper.data.giphy.datastore.GifDataStoreFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class DataStoreFactoryModule {

    @Provides
    public GifDataStoreFactory providesGifDataStoreFactory(GiphyService giphyService, GiphyObjectMapper giphyObjectMapper) {
        return new GifDataStoreFactory(giphyService, giphyObjectMapper);
    }
}
