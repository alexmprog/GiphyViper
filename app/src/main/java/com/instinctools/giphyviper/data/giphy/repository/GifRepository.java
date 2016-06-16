package com.instinctools.giphyviper.data.giphy.repository;

import com.instinctools.giphyviper.data.giphy.model.Gif;

import java.util.List;

import rx.Observable;

public interface GifRepository {

    Observable<List<Gif>> getTrendingGifs();
}
