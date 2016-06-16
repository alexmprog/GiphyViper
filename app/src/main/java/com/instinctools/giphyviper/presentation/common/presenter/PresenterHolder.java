package com.instinctools.giphyviper.presentation.common.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.instinctools.giphyviper.presentation.common.cache.Cache;

import java.util.concurrent.atomic.AtomicLong;

public class PresenterHolder {

    private static final String SIS_KEY_PRESENTER_ID = "presenter_id";

    private final AtomicLong currentId;

    private final Cache<Long, ViperPresenter<?, ?>> presenters;

    // should be value in milliseconds
    public PresenterHolder(long expirationValue) {
        currentId = new AtomicLong();
        presenters = new Cache<>(expirationValue);
    }

    public <P extends ViperPresenter<?, ?>> P restorePresenter(@NonNull Bundle savedInstanceState) {
        Long presenterId = savedInstanceState.getLong(SIS_KEY_PRESENTER_ID);
        P presenter = (P) presenters.getIfPresent(presenterId);
        if (presenter != null) {
            presenters.invalidate(presenterId);
        }
        return presenter;
    }

    public <P extends ViperPresenter<?, ?>> void savePresenter(@NonNull P presenter, @NonNull Bundle outState) {
        long presenterId = currentId.incrementAndGet();
        presenters.put(presenterId, presenter);
        outState.putLong(SIS_KEY_PRESENTER_ID, presenterId);
    }
}
