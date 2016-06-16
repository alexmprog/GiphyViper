package com.instinctools.giphyviper.presentation.common.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.instinctools.giphyviper.presentation.common.presenter.PresenterHolder;
import com.instinctools.giphyviper.presentation.common.presenter.ViperPresenter;
import com.instinctools.giphyviper.presentation.common.router.ViperRouter;
import com.instinctools.giphyviper.presentation.common.view.ViperView;

import javax.inject.Inject;

import dagger.Lazy;

public class ViperActivityDelegate<V extends ViperView, R extends ViperRouter, P extends ViperPresenter<V, R>> {

    @Inject
    protected PresenterHolder presenterHolder;

    @Inject
    protected Lazy<P> mvpPresenterRef;

    private P viperPresenter;

    @Inject
    public ViperActivityDelegate() {
    }

    public void initPresenter(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            // first creation
            viperPresenter = mvpPresenterRef.get();
        } else {
            // get from cache
            viperPresenter = presenterHolder.restorePresenter(savedInstanceState);
            if (viperPresenter == null) {
                // something wrong with cache
                viperPresenter = mvpPresenterRef.get();
            }
        }
    }

    public P getViperPresenter() {
        return viperPresenter;
    }

    public void onSaveInstanceState(Bundle outState) {
        presenterHolder.savePresenter(viperPresenter, outState);
    }

    public void onDestroy() {
        if (viperPresenter != null) {
            viperPresenter.detachView();
            mvpPresenterRef = null;
        }
    }
}
