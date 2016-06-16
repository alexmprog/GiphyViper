package com.instinctools.giphyviper.presentation.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.instinctools.giphyviper.R;
import com.instinctools.giphyviper.data.giphy.model.Gif;
import com.instinctools.giphyviper.presentation.GiphyApp;
import com.instinctools.giphyviper.presentation.common.di.module.ActivityModule;
import com.instinctools.giphyviper.presentation.common.ui.activity.BaseViperActivity;
import com.instinctools.giphyviper.presentation.ui.details.di.DaggerDetailsComponent;
import com.instinctools.giphyviper.presentation.ui.details.di.DetailsComponent;
import com.instinctools.giphyviper.presentation.ui.details.di.DetailsModule;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends BaseViperActivity<DetailsView, DetailsRouter, DetailsPresenter, DetailsComponent> implements DetailsView {

    public static final String EXTRA_GIF = "com.instinctools.giphyviper.details.EXTRA_GIF";

    public static Intent getStartIntent(Context context, Gif gif) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_GIF, gif);
        return intent;
    }

    @Bind(R.id.image_view)
    ImageView imageView;

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    //TODO: about request listener - it's part of atomic operation - showImage or showGifImage
    private RequestListener<String, GlideDrawable> glideDrawableRequestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            progressBar.setVisibility(View.GONE);
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            progressBar.setVisibility(View.GONE);
            return false;
        }
    };

    private RequestListener<String, GifDrawable> glideGifDrawableRequestListener = new RequestListener<String, GifDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GifDrawable> target, boolean isFirstResource) {
            progressBar.setVisibility(View.GONE);
            return false;
        }

        @Override
        public boolean onResourceReady(GifDrawable resource, String model, Target<GifDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            progressBar.setVisibility(View.GONE);
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        getViperPresenter().attachView(this);

        Gif gif = getIntent().getParcelableExtra(EXTRA_GIF);
        if (gif == null) {
            throw new IllegalArgumentException("Details activity requires a gif instance!");
        }

        getViperPresenter().checkData(gif);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public DetailsComponent createActivityComponent() {
        return DaggerDetailsComponent.builder()
                .applicationComponent(GiphyApp.get(this).getComponent())
                .activityModule(new ActivityModule(this))
                .detailsModule(new DetailsModule())
                .build();
    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showImage(@NonNull String imageUrl) {
        progressBar.setVisibility(View.VISIBLE);
        Glide.with(this).load(imageUrl).centerCrop().listener(glideDrawableRequestListener).into(imageView);
    }

    @Override
    public void showGifImage(@NonNull String imageUrl) {
        progressBar.setVisibility(View.VISIBLE);
        Glide.with(this).load(imageUrl).asGif().listener(glideGifDrawableRequestListener).into(imageView);
    }
}
