package com.fstyle.androidtrainning.screen.tabfavorite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fstyle.androidtrainning.MainApplication;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.data.local.MovieDatabase;
import com.fstyle.androidtrainning.data.local.OnSelectDataListener;
import com.fstyle.androidtrainning.data.local.SelectListMovieFromDatabase;
import com.fstyle.androidtrainning.data.local.entity.MovieEntity;
import com.fstyle.androidtrainning.data.service.config.MoviesApi;
import com.fstyle.androidtrainning.screen.BaseFragment;
import com.fstyle.androidtrainning.screen.detailsmovie.DetailsMovieActivity;
import com.fstyle.androidtrainning.utils.Constant;
import java.util.List;

/**
 * TabFavorite Screen.
 */
public class TabFavoriteFragment extends BaseFragment
        implements TabFavoriteContract.FavoriteView, OnFavoriteItemListener,
        OnSelectDataListener {

    private static final int NUMBER_COLUMNS = 2;
    TabFavoriteContract.Presenter mPresenter;
    private MovieDatabase mMovieDatabase;
    private RecyclerView mRecyclerView;
    private FavoriteAdapter mFavoriteAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static TabFavoriteFragment newInstance() {
        return new TabFavoriteFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new TabFavoritePresenter();
        mPresenter.setView(this);
        MoviesApi moviesApi = MainApplication.getMoviesApi();
        mPresenter.setMovieApi(moviesApi);

        mMovieDatabase = MainApplication.getMovieDatabase();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabfavorite, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_fravorite);
        mLayoutManager = new GridLayoutManager(getActivity(), NUMBER_COLUMNS);
        mFavoriteAdapter = new FavoriteAdapter(getActivity());
        mFavoriteAdapter.setOnFavoriteItemListener(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mFavoriteAdapter);
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
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        new SelectListMovieFromDatabase(mMovieDatabase, this).execute();
        super.onResume();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            new SelectListMovieFromDatabase(mMovieDatabase, this).execute();
        }
    }

    @Override
    public void onSelectDataSuccess(List<MovieEntity> movieEntityList) {
        mFavoriteAdapter.updateData(movieEntityList);
    }

    @Override
    public void onClickItem(MovieEntity movieEntity) {
        Intent intent = new Intent(getActivity(), DetailsMovieActivity.class);
        intent.putExtra(Constant.EXTRA_MOVIE_ID, movieEntity.getId());
        startActivity(intent);
    }
}
