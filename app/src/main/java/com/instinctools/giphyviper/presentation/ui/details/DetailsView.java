package com.instinctools.giphyviper.presentation.ui.details;

import android.support.annotation.NonNull;

import com.instinctools.giphyviper.presentation.common.view.ViperView;

public interface DetailsView extends ViperView {

    void showImage(@NonNull String imageUrl);

    void showGifImage(@NonNull String imageUrl);
}
