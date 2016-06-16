package com.instinctools.giphyviper.presentation.ui.trending;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.instinctools.giphyviper.data.giphy.model.Gif;
import com.instinctools.giphyviper.presentation.common.router.ViperRouter;
import com.instinctools.giphyviper.presentation.ui.details.DetailsActivity;

public class TrendingRouter implements ViperRouter {

    private Activity activity;

    public TrendingRouter(@NonNull Activity activity) {
        this.activity = activity;
    }

    public void goToGifDetails(@NonNull Gif gif) {
        // implement gif clicked
        activity.startActivity(DetailsActivity.getStartIntent(activity, gif));
    }
}
