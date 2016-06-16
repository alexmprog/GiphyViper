package com.instinctools.giphyviper.presentation.ui.trending.di;


import android.app.Activity;
import android.support.annotation.NonNull;

import com.instinctools.giphyviper.domain.usecase.gif.GetTrendingGifsUseCase;
import com.instinctools.giphyviper.presentation.ui.trending.TrendingAdapter;
import com.instinctools.giphyviper.presentation.ui.trending.TrendingPresenter;
import com.instinctools.giphyviper.presentation.ui.trending.TrendingRouter;

import dagger.Module;
import dagger.Provides;

@Module
public class TrendingModule {

    @Provides
    public TrendingPresenter providesTrendingPresenter(GetTrendingGifsUseCase getTrendingGifsUseCase) {
        return new TrendingPresenter(getTrendingGifsUseCase);
    }

    @Provides
    public TrendingAdapter providesTrendingAdapter() {
        return new TrendingAdapter();
    }

    @Provides
    public TrendingRouter providesTrendingRouter(@NonNull Activity activity) {
        return new TrendingRouter(activity);
    }
}
