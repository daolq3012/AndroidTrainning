package com.fstyle.androidtrainning.screen.tabhome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.fstyle.androidtrainning.MainApplication;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.data.model.Movie;
import com.fstyle.androidtrainning.data.service.config.MoviesApi;
import com.fstyle.androidtrainning.screen.BaseFragment;
import java.util.List;

/**
 * TabHome Screen.
 */
public class TabHomeFragment extends BaseFragment
        implements TabHomeContract.HomeView, OnRecyclerViewItemListener, View.OnClickListener {

    TabHomeContract.Presenter mPresenter;
    private RecyclerView mRecyclerNowPlaying, mRecyclerUpComing, mRecyclerTopRated,
            mRecyclerPopular;
    private MovieAdapter mAdapterNowPlaying, mAdapterUpComing, mAdapterTopRated, mAdapterPopular;
    private Button mButtonMoreNowPlaying, mButtonMoreUpComing, mButtonMoreTopRated,
            mButtonMorePopular;
    private RecyclerView.LayoutManager mLayoutManagerNowPlaying, mLayoutManagerUpComing,
            mLayoutManagerTopRated, mLayoutManagerPopular;

    public static TabHomeFragment newInstance() {
        return new TabHomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new TabHomePresenter();
        mPresenter.setView(this);
        MoviesApi moviesApi = MainApplication.getMoviesApi();
        mPresenter.setMoviesApi(moviesApi);

        mPresenter.getListNowPlayingMovie();
        mPresenter.getListUpComingMovie();
        mPresenter.getListTopRatedMovie();
        mPresenter.getListPopularMovie();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabhome, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mRecyclerNowPlaying = view.findViewById(R.id.recycler_now_playing);
        mRecyclerUpComing = view.findViewById(R.id.recycler_up_coming);
        mRecyclerTopRated = view.findViewById(R.id.recycler_top_rated);
        mRecyclerPopular = view.findViewById(R.id.recycler_popuplar);

        mLayoutManagerNowPlaying =
                new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerUpComing =
                new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerTopRated =
                new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerPopular =
                new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);

        mAdapterNowPlaying = new MovieAdapter(getActivity());
        mAdapterNowPlaying.setOnRecyclerViewItemListener(this);
        mAdapterUpComing = new MovieAdapter(getActivity());
        mAdapterUpComing.setOnRecyclerViewItemListener(this);
        mAdapterTopRated = new MovieAdapter(getActivity());
        mAdapterTopRated.setOnRecyclerViewItemListener(this);
        mAdapterPopular = new MovieAdapter(getActivity());
        mAdapterPopular.setOnRecyclerViewItemListener(this);

        mRecyclerNowPlaying.setLayoutManager(mLayoutManagerNowPlaying);
        mRecyclerNowPlaying.setAdapter(mAdapterNowPlaying);
        mRecyclerNowPlaying.setHasFixedSize(true);
        mRecyclerUpComing.setLayoutManager(mLayoutManagerUpComing);
        mRecyclerUpComing.setAdapter(mAdapterUpComing);
        mRecyclerUpComing.setHasFixedSize(true);
        mRecyclerTopRated.setLayoutManager(mLayoutManagerTopRated);
        mRecyclerTopRated.setAdapter(mAdapterTopRated);
        mRecyclerTopRated.setHasFixedSize(true);
        mRecyclerPopular.setLayoutManager(mLayoutManagerPopular);
        mRecyclerPopular.setAdapter(mAdapterPopular);
        mRecyclerPopular.setHasFixedSize(true);

        mButtonMoreNowPlaying = view.findViewById(R.id.button_more_now_playing);
        mButtonMoreUpComing = view.findViewById(R.id.button_more_up_coming);
        mButtonMoreTopRated = view.findViewById(R.id.button_more_top_rated);
        mButtonMorePopular = view.findViewById(R.id.button_more_popular);

        mButtonMoreNowPlaying.setOnClickListener(this);
        mButtonMoreUpComing.setOnClickListener(this);
        mButtonMoreTopRated.setOnClickListener(this);
        mButtonMorePopular.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    public void onItemClick(Movie movie) {
        //TODO Chuyen sang DetailsMovieActivity
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onListNowPlayingMovieSuccess(List<Movie> listNowPlayingMovie) {
        if (listNowPlayingMovie == null) {
            return;
        }
        mAdapterNowPlaying.updateData(listNowPlayingMovie);
    }

    @Override
    public void onListUpComingMovieSuccess(List<Movie> listUpComingMovie) {
        if (listUpComingMovie == null) {
            return;
        }
        mAdapterUpComing.updateData(listUpComingMovie);
    }

    @Override
    public void onListTopRatedMovieSuccess(List<Movie> listTopRatedMovie) {
        if (listTopRatedMovie == null) {
            return;
        }
        mAdapterTopRated.updateData(listTopRatedMovie);
    }

    @Override
    public void onListPopularMoviesSuccess(List<Movie> listPopularMovie) {
        if (listPopularMovie == null) {
            return;
        }
        mAdapterPopular.updateData(listPopularMovie);
    }
}
