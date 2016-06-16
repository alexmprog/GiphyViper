package com.instinctools.giphyviper.presentation.ui.trending;

import android.support.annotation.NonNull;

import com.instinctools.giphyviper.data.giphy.model.Gif;
import com.instinctools.giphyviper.domain.usecase.gif.GetTrendingGifsUseCase;
import com.instinctools.giphyviper.presentation.common.presenter.BasePresenter;

import java.util.List;

import rx.Subscription;
import rx.functions.Action1;
import timber.log.Timber;

public class TrendingPresenter extends BasePresenter<TrendingView, TrendingRouter> {

    private GetTrendingGifsUseCase getTrendingGifsUseCase;
    private Subscription subscription;

    public TrendingPresenter(GetTrendingGifsUseCase getTrendingGifsUseCase) {
        this.getTrendingGifsUseCase = getTrendingGifsUseCase;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    public void onGifClicked(@NonNull Gif gif) {
        getViperRouter().goToGifDetails(gif);
    }

    public void getTrendingGifs() {
        checkViewAttached();
        getViperView().showProgress();

        subscription = getTrendingGifsUseCase.perform().subscribe(new Action1<List<Gif>>() {
            @Override
            public void call(List<Gif> gifList) {
                getViperView().hideProgress();
                getViperView().showContent();
                getViperView().showData(new TrendingModel(gifList));
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Timber.e(throwable, "There was an error retrieving the shots");
                getViperView().hideProgress();
                getViperView().showError(new TrendingError());
            }
        });
    }
}
