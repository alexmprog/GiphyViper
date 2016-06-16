package com.instinctools.giphyviper.domain.usecase.gif;

import com.instinctools.giphyviper.data.giphy.model.Gif;
import com.instinctools.giphyviper.data.giphy.repository.GifRepository;
import com.instinctools.giphyviper.domain.usecase.base.BaseAsyncUseCase;

import java.util.List;

import rx.Observable;

public class GetTrendingGifsUseCase extends BaseAsyncUseCase<List<Gif>> {

    private GifRepository gifRepository;

    public GetTrendingGifsUseCase(GifRepository gifRepository) {
        this.gifRepository = gifRepository;
    }

    @Override
    protected Observable<List<Gif>> doWork() {
        return gifRepository.getTrendingGifs();
    }
}
