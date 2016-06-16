package com.instinctools.giphyviper.domain.di;

import com.instinctools.giphyviper.data.giphy.repository.GifRepository;
import com.instinctools.giphyviper.domain.usecase.gif.GetTrendingGifsUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    public GetTrendingGifsUseCase providesGetTrendingGifsUseCase(GifRepository gifRepository) {
        return new GetTrendingGifsUseCase(gifRepository);
    }
}
