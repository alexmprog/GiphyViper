package com.instinctools.giphyviper.data.di;

import com.instinctools.giphyviper.data.giphy.datastore.GifDataStoreFactory;
import com.instinctools.giphyviper.data.giphy.repository.GifRepository;
import com.instinctools.giphyviper.data.giphy.repository.GifRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    GifRepository providesGifRepository(GifDataStoreFactory gifDataStoreFactory) {
        return new GifRepositoryImpl(gifDataStoreFactory);
    }
}
