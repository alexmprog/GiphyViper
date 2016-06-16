package com.instinctools.giphyviper.presentation.ui.trending;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.instinctools.giphyviper.R;
import com.instinctools.giphyviper.data.giphy.model.Gif;
import com.instinctools.giphyviper.presentation.GiphyApp;
import com.instinctools.giphyviper.presentation.common.di.module.ActivityModule;
import com.instinctools.giphyviper.presentation.common.ui.activity.BaseViperActivity;
import com.instinctools.giphyviper.presentation.ui.trending.di.DaggerTrendingComponent;
import com.instinctools.giphyviper.presentation.ui.trending.di.TrendingComponent;
import com.instinctools.giphyviper.presentation.ui.trending.di.TrendingModule;
import com.instinctools.giphyviper.presentation.util.DisplayMetricsUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrendingActivity extends BaseViperActivity<TrendingView, TrendingRouter, TrendingPresenter, TrendingComponent> implements TrendingView, TrendingAdapter.ClickListener {

    @Bind(R.id.button_message)
    Button messageButton;

    @Bind(R.id.image_message)
    ImageView messageImage;

    @Bind(R.id.progress)
    ProgressBar recyclerProgress;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerGif;

    @Bind(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.text_message)
    TextView messageText;

    @Bind(R.id.toolbar_browse)
    Toolbar toolbar;

    @Bind(R.id.layout_message)
    View messageLayout;

    private boolean isTabletLayout;

    @Inject
    TrendingAdapter trendingAdapter;

    @Inject
    TrendingRouter trendingRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);
        ButterKnife.bind(this);

        getViperPresenter().attachView(this);
        getViperPresenter().setRouter(trendingRouter);

        setSupportActionBar(toolbar);
        isTabletLayout = DisplayMetricsUtil.isScreenW(600);

        setupViews();
        getViperPresenter().getTrendingGifs();
    }

    private void setupViews() {
        trendingAdapter.setClickListener(this);

        recyclerGif.setLayoutManager(setLayoutManager());
        recyclerGif.setHasFixedSize(true);
        recyclerGif.setAdapter(trendingAdapter);

        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorAccent);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getViperPresenter().getTrendingGifs();
            }
        });
    }

    private RecyclerView.LayoutManager setLayoutManager() {
        RecyclerView.LayoutManager layoutManager;
        if (!isTabletLayout) {
            layoutManager = new GridLayoutManager(this, 2);
        } else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 6);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 3;
                }
            });
            layoutManager = gridLayoutManager;
        }
        return layoutManager;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.button_message)
    public void onReloadButtonClick() {
        getViperPresenter().getTrendingGifs();
    }

    @Override
    public void showProgress() {
        if (recyclerGif.getVisibility() == View.VISIBLE && trendingAdapter.getItemCount() > 0) {
            swipeRefreshLayout.setRefreshing(true);
        } else {
            recyclerProgress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
        recyclerProgress.setVisibility(View.GONE);
    }

    @Override
    public void showContent() {
        recyclerGif.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(@NonNull TrendingModel data) {
        List<Gif> gifList = data.getGifList();
        if (!gifList.isEmpty()) {
            recyclerGif.setVisibility(View.VISIBLE);
            trendingAdapter.setGifs(gifList);
        } else {
            showEmpty();
        }
    }

    @Override
    public void showError(@NonNull TrendingError error) {
        // TODO: can use error as you want
        recyclerGif.setVisibility(View.GONE);
        messageImage.setImageResource(R.drawable.ic_sentiment_data);
        messageText.setText(getString(R.string.text_error_loading_gifs));
        messageButton.setText(getString(R.string.text_reload));
        showMessageLayout(true);
    }

    private void showEmpty() {
        recyclerGif.setVisibility(View.GONE);
        messageImage.setImageResource(R.drawable.ic_empty_data);
        messageText.setText(getString(R.string.text_no_shots));
        messageButton.setText(getString(R.string.text_check_again));
        showMessageLayout(true);
    }

    private void showMessageLayout(boolean show) {
        messageLayout.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public TrendingComponent createActivityComponent() {
        return DaggerTrendingComponent
                .builder()
                .applicationComponent(GiphyApp.get(this).getComponent())
                .activityModule(new ActivityModule(this))
                .trendingModule(new TrendingModule())
                .build();
    }

    @Override
    public void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onGifClick(Gif gif) {
        getViperPresenter().onGifClicked(gif);
    }
}
