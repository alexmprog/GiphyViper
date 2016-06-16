package com.instinctools.giphyviper.presentation.ui.trending;

import android.support.annotation.NonNull;

import com.instinctools.giphyviper.data.giphy.model.Gif;
import com.instinctools.giphyviper.presentation.common.view.model.ViewModel;

import java.util.List;

public class TrendingModel implements ViewModel {

    @NonNull
    private List<Gif> gifList;

    public TrendingModel(@NonNull List<Gif> gifList) {
        this.gifList = gifList;
    }

    @NonNull
    public List<Gif> getGifList() {
        return gifList;
    }
}
