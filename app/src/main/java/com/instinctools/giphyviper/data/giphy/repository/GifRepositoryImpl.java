package com.instinctools.giphyviper.data.giphy.repository;

import com.instinctools.giphyviper.data.giphy.datastore.GifDataStoreFactory;
import com.instinctools.giphyviper.data.giphy.datastore.NetworkGifDataStore;
import com.instinctools.giphyviper.data.giphy.model.Gif;

import java.util.List;

import rx.Observable;

public class GifRepositoryImpl implements GifRepository {

    private GifDataStoreFactory gifDataStoreFactory;

    public GifRepositoryImpl(GifDataStoreFactory gifDataStoreFactory) {
        this.gifDataStoreFactory = gifDataStoreFactory;
    }

    @Override
    public Observable<List<Gif>> getTrendingGifs() {
        NetworkGifDataStore networkGifStorage = gifDataStoreFactory.getNetworkGifStorage();
        return networkGifStorage.getTrendingGifs();
    }
}
