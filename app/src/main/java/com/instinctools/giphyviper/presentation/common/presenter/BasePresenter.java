package com.instinctools.giphyviper.presentation.common.presenter;

import com.instinctools.giphyviper.presentation.common.router.ViperRouter;
import com.instinctools.giphyviper.presentation.common.view.ViperView;

import java.lang.ref.WeakReference;

public class BasePresenter<V extends ViperView, R extends ViperRouter> implements ViperPresenter<V, R> {

    private WeakReference<V> viperView;
    private WeakReference<R> viperRouter;

    @Override
    public void attachView(V viperView) {
        this.viperView = new WeakReference<>(viperView);
    }

    @Override
    public void detachView() {
        if (viperView != null) {
            viperView.clear();
        }
    }

    @Override
    public void setRouter(R viperRouter) {
        this.viperRouter = new WeakReference<>(viperRouter);
    }

    public boolean isViewAttached() {
        return viperView != null && viperView.get() != null;
    }

    public V getViperView() {
        if (viperView != null) {
            return viperView.get();
        }
        return null;
    }

    public R getViperRouter() {
        if (viperRouter != null) {
            return viperRouter.get();
        }
        return null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new ViperViewNotAttachedException();
    }

    public static class ViperViewNotAttachedException extends RuntimeException {
        public ViperViewNotAttachedException() {
            super("Please call ViperPresenter.attachView(ViperView) before" +
                    " requesting data to the ViperPresenter");
        }
    }
}
