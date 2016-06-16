package com.instinctools.giphyviper.presentation.common.view;

import android.support.annotation.NonNull;

import com.instinctools.giphyviper.presentation.common.view.error.ViewError;
import com.instinctools.giphyviper.presentation.common.view.model.ViewModel;

public interface ViperLceView<V extends ViewModel, E extends ViewError> extends ViperView {

    void showProgress();

    void hideProgress();

    void showContent();

    void showData(@NonNull V data);

    void showError(@NonNull E error);

}
