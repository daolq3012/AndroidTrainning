package com.fstyle.androidtrainning.screen.detailsmovie;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.fstyle.androidtrainning.MainApplication;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.data.model.Movie;
import com.fstyle.androidtrainning.data.service.config.MoviesApi;
import com.fstyle.androidtrainning.screen.BaseActivity;
import com.fstyle.androidtrainning.utils.Constant;
import com.fstyle.androidtrainning.utils.DateTimeUtils;
import com.fstyle.androidtrainning.utils.StringUtils;
import java.util.Date;
import java.util.List;

/**
 * DetailsMovie Screen.
 */
public class DetailsMovieActivity extends BaseActivity
        implements DetailsMovieContract.DetailsMovieView, View.OnClickListener {

    public static final String DATE_FORMATT_DD_MM_YYYY = "yyyy-MM-dd";
    public static final String DATE_FORMATT_DD_MMMMM_YYYY = "dd MMM yyyy";
    private static final String MINUTE = " min";
    private static final String MAX_POINT = "/10";
    private ImageView mImageBackdrop, mImagePoster;
    private TextView mTextReleaseDate, mTextRunTime, mTextGenreMovie, mTextOverview, mTextRateMovie;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private FloatingActionButton mFloatingFavoriteButton;
    DetailsMovieContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsmovie);

        mPresenter = new DetailsMoviePresenter();
        mPresenter.setView(this);
        MoviesApi moviesApi = MainApplication.getMoviesApi();
        mPresenter.setMovieApi(moviesApi);
        mPresenter.getDetailsMovie();

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
        mFloatingFavoriteButton.setBackgroundTintList(
                ColorStateList.valueOf(getResources().getColor(R.color.colorOrange)));
        mFloatingFavoriteButton.setOnClickListener(this);

        mCollapsingToolbarLayout = findViewById(R.id.collapsing_detail_movie);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsedAppBar);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        mToolbar = findViewById(R.id.toolbar_detail_movie);
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
        String releaseDate =
                DateTimeUtils.getStrDateTimeFormatted(date, DATE_FORMATT_DD_MMMMM_YYYY);

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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.floating_favorite:
                //TODO Add movie to favorite list
                break;
            default:
                break;
        }
    }
}
