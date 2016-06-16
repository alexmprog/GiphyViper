package com.instinctools.giphyviper.presentation.ui.details;

import android.text.TextUtils;

import com.instinctools.giphyviper.data.giphy.model.Gif;
import com.instinctools.giphyviper.presentation.common.presenter.BasePresenter;

public class DetailsPresenter extends BasePresenter<DetailsView, DetailsRouter> {

    public void checkData(Gif gif) {
        String gifUrl = gif.getGifUrl();
        if (!TextUtils.isEmpty(gifUrl)) {
            getViperView().showGifImage(gifUrl);
        } else {
            getViperView().showImage(gif.getImageUrl());
        }
    }
}
