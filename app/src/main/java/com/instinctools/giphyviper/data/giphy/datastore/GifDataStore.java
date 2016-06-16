package com.instinctools.giphyviper.data.giphy.datastore;

import com.instinctools.giphyviper.data.giphy.model.Gif;

import java.util.List;

import rx.Observable;

public interface GifDataStore {

    Observable<List<Gif>> getTrendingGifs();
}
