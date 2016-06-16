package com.instinctools.giphyviper.data.giphy.datastore;

import com.instinctools.giphyviper.data.giphy.api.GiphyObjectMapper;
import com.instinctools.giphyviper.data.giphy.api.GiphyService;

public class GifDataStoreFactory {

    private GiphyService giphyService;
    private GiphyObjectMapper giphyObjectMapper;

    private NetworkGifDataStore networkGifDataStore;

    public GifDataStoreFactory(GiphyService giphyService, GiphyObjectMapper giphyObjectMapper) {
        this.giphyService = giphyService;
        this.giphyObjectMapper = giphyObjectMapper;
    }

    public NetworkGifDataStore getNetworkGifStorage() {
        if (networkGifDataStore == null) {
            networkGifDataStore = new NetworkGifDataStore(giphyService, giphyObjectMapper);
        }
        return networkGifDataStore;
    }
}
