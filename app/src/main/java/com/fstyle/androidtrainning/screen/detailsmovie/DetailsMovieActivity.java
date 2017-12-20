package com.fstyle.androidtrainning.screen.detailsmovie;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.fstyle.androidtrainning.MainApplication;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.data.model.Cast;
import com.fstyle.androidtrainning.data.model.Movie;
import com.fstyle.androidtrainning.data.model.Trailer;
import com.fstyle.androidtrainning.data.service.config.MoviesApi;
import com.fstyle.androidtrainning.screen.BaseActivity;
import com.fstyle.androidtrainning.utils.Constant;
import com.fstyle.androidtrainning.utils.DateTimeUtils;
import com.fstyle.androidtrainning.utils.StringUtils;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import java.util.Date;
import java.util.List;

/**
 * DetailsMovie Screen.
 */
public class DetailsMovieActivity extends BaseActivity
        implements DetailsMovieContract.DetailsMovieView, View.OnClickListener,
        YouTubePlayer.OnInitializedListener, YouTubeThumbnailView.OnInitializedListener {
    private static final String TAG = "DetailsMovieActivity";
    public static final String DATE_FORMATT_DD_MM_YYYY = "yyyy-MM-dd";
    public static final String DATE_FORMATT_DD_MMM_YYYY = "dd MMM yyyy";
    private static final String MINUTE = " min";
    private static final String MAX_POINT = "/10";
    private static final String OFFICIAL = "Official";
    private static final int FIRST_TRAILER = 0;
    private ImageView mImageBackdrop, mImagePoster, mImageThumnail;
    private TextView mTextReleaseDate, mTextRunTime, mTextGenreMovie, mTextOverview, mTextRateMovie;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private FloatingActionButton mFloatingFavoriteButton;
    private RelativeLayout mRelativeLayout;
    private YouTubePlayerFragment mYouTubePlayer;
    private YouTubePlayer.OnInitializedListener mOnPlayerInitListener;
    private YouTubeThumbnailView mThumbnailView;
    private RecyclerView mRecyclerCast;
    private RecyclerView.LayoutManager mLayoutManager;
    private CastAdapter mAdapter;
    DetailsMovieContract.Presenter mPresenter;
    private String mYoutubeKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsmovie);

        mPresenter = new DetailsMoviePresenter();
        mPresenter.setView(this);
        MoviesApi moviesApi = MainApplication.getMoviesApi();
        mPresenter.setMovieApi(moviesApi);
        mPresenter.getDetailsMovie();
        mPresenter.getMovieTrailers();
        mPresenter.getCastsMovie();

        initViews();
        setHomeButtonToolbar();
    }

    private void initViews() {
        mImageBackdrop = findViewById(R.id.image_backdrop);
        mImagePoster = findViewById(R.id.image_poster);
        mTextReleaseDate = findViewById(R.id.text_release_date);
        mTextRunTime = findViewById(R.id.text_run_time);
        mTextGenreMovie = findViewById(R.id.text_kind_movie);
        mTextOverview = findViewById(R.id.text_describe_movie);
        mTextRateMovie = findViewById(R.id.text_rate);
        mFloatingFavoriteButton = findViewById(R.id.floating_favorite);
        mRelativeLayout = findViewById(R.id.relative_play);
        mRelativeLayout.setOnClickListener(this);
        mThumbnailView = findViewById(R.id.youtube_thumnail);

        mYouTubePlayer = YouTubePlayerFragment.newInstance();
        mYouTubePlayer.initialize(Constant.GOOGLE_API_KEY, mOnPlayerInitListener);
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_fragment, mYouTubePlayer)
                .commit();

        mFloatingFavoriteButton.setBackgroundTintList(
                ColorStateList.valueOf(getResources().getColor(R.color.colorOrange)));
        mFloatingFavoriteButton.setOnClickListener(this);

        mCollapsingToolbarLayout = findViewById(R.id.collapsing_detail_movie);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsedAppBar);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        mToolbar = findViewById(R.id.toolbar_detail_movie);

        mRecyclerCast = findViewById(R.id.recycler_cast);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter = new CastAdapter(this);
        mRecyclerCast.setLayoutManager(mLayoutManager);
        mRecyclerCast.setAdapter(mAdapter);
    }

    private void setHomeButtonToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    public Integer getMovieId() {
        return getIntent().getIntExtra(Constant.EXTRA_MOVIE_ID, 0);
    }

    @Override
    public void onDetailsMovieSuccess(Movie movie, List<String> listGenre) {
        String urlBackdrop = StringUtils.convertPosterPathToUrlPoster(movie.getBackdropPath());
        String urlPoster = StringUtils.convertPosterPathToUrlPoster(movie.getPosterPath());
        String genresCommaSeparated = StringUtils.convertListToStringCommaSeparated(listGenre);
        String rateMovie = movie.getVoteAverage() + MAX_POINT;
        String runTimeMovie = movie.getRuntime() + MINUTE;
        Date date =
                DateTimeUtils.convertStringToDate(movie.getReleaseDate(), DATE_FORMATT_DD_MM_YYYY);
        String releaseDate = DateTimeUtils.getStrDateTimeFormatted(date, DATE_FORMATT_DD_MMM_YYYY);

        Glide.with(this).load(urlBackdrop).into(mImageBackdrop);
        Glide.with(this).load(urlPoster).into(mImagePoster);
        mTextReleaseDate.setText(releaseDate);
        mTextGenreMovie.setText(genresCommaSeparated);
        mTextRateMovie.setText(rateMovie);
        mTextRunTime.setText(runTimeMovie);
        mTextOverview.setText(movie.getOverview());
        mCollapsingToolbarLayout.setTitle(movie.getTitle());
    }

    @Override
    public void onListTrailerSuccess(List<Trailer> listTrailer) {
        if (listTrailer.size() == 0) {
            mRelativeLayout.setVisibility(View.INVISIBLE);
            mRelativeLayout.setVisibility(View.GONE);
        }
        for (Trailer trailer : listTrailer) {
            if (trailer.getName().contains(OFFICIAL)) {
                mYoutubeKey = trailer.getKey();
                break;
            } else {
                mYoutubeKey = listTrailer.get(FIRST_TRAILER).getKey();
            }
        }
        mThumbnailView.initialize(Constant.GOOGLE_API_KEY, this);
    }

    @Override
    public void onListCastMovieSuccess(List<Cast> listCast) {
        if (listCast.size() == 0) {
            return;
        }
        mAdapter.updateData(listCast);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.floating_favorite:
                //TODO Add movie to favorite list
                break;
            case R.id.relative_play:
                mRelativeLayout.setVisibility(View.INVISIBLE);
                mRelativeLayout.setVisibility(View.GONE);
                mYouTubePlayer.initialize(Constant.GOOGLE_API_KEY, this);
                break;
            default:
                break;
        }
    }

    //Initialize YoutubePlayer
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
            YouTubePlayer youTubePlayer, boolean b) {
        mThumbnailView.setVisibility(View.GONE);
        youTubePlayer.loadVideo(mYoutubeKey);
        youTubePlayer.setShowFullscreenButton(false);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
            YouTubeInitializationResult youTubeInitializationResult) {
        Log.e(TAG, "onInitializationFailure: ");
    }

    //Initialize YoutubeThumnailView
    @Override
    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView,
            final YouTubeThumbnailLoader youTubeThumbnailLoader) {
        youTubeThumbnailLoader.setVideo(mYoutubeKey);
        youTubeThumbnailLoader.setOnThumbnailLoadedListener(
                new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView,
                            String s) {
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView,
                            YouTubeThumbnailLoader.ErrorReason errorReason) {
                        Log.e(TAG, "onThumbnailError: ");
                    }
                });
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView,
            YouTubeInitializationResult youTubeInitializationResult) {
        Log.e(TAG, "onInitializationFailure: ");
    }
}
