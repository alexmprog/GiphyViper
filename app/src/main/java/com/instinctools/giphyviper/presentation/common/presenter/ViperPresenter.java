package com.instinctools.giphyviper.presentation.common.presenter;

import com.instinctools.giphyviper.presentation.common.router.ViperRouter;
import com.instinctools.giphyviper.presentation.common.view.ViperView;

public interface ViperPresenter<V extends ViperView, R extends ViperRouter> {

    void attachView(V viperView);

    void detachView();

    void setRouter(R viperRouter);
}
