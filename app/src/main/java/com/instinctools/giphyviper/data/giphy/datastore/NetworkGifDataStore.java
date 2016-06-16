package com.instinctools.giphyviper.data.giphy.datastore;

import com.instinctools.giphyviper.BuildConfig;
import com.instinctools.giphyviper.data.giphy.api.GiphyObjectMapper;
import com.instinctools.giphyviper.data.giphy.api.GiphyService;
import com.instinctools.giphyviper.data.giphy.model.Gif;
import com.instinctools.giphyviper.data.giphy.model.remote.TrendingResponse;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class NetworkGifDataStore implements GifDataStore {

    private GiphyService giphyService;

    private GiphyObjectMapper giphyObjectMapper;

    public NetworkGifDataStore(GiphyService giphyService, GiphyObjectMapper giphyObjectMapper) {
        this.giphyService = giphyService;
        this.giphyObjectMapper = giphyObjectMapper;
    }

    @Override
    public Observable<List<Gif>> getTrendingGifs() {
        return giphyService.latestTrending(BuildConfig.GIPHY_ACCESS_TOKEN, "g").map(new Func1<TrendingResponse, List<Gif>>() {
            @Override
            public List<Gif> call(TrendingResponse trendingResponse) {
                return giphyObjectMapper.toListGif(trendingResponse.gifs());
            }
        });
    }
}
