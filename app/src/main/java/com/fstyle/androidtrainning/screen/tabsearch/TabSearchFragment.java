package com.fstyle.androidtrainning.screen.tabsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fstyle.androidtrainning.MainApplication;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.data.model.Movie;
import com.fstyle.androidtrainning.data.service.config.MoviesApi;
import com.fstyle.androidtrainning.screen.BaseFragment;
import com.fstyle.androidtrainning.screen.detailsmovie.DetailsMovieActivity;
import com.fstyle.androidtrainning.screen.OnRecyclerViewItemListener;
import com.fstyle.androidtrainning.utils.Constant;

import java.util.List;

/**
 * TabSearch Screen.
 */
public class TabSearchFragment extends BaseFragment implements TabSearchContract.SearchView,
        OnRecyclerViewItemListener, SearchView.OnQueryTextListener {

    TabSearchContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private SearchAdapter mSearchAdapter;
    private SearchView mSearchMovieView;
    private RecyclerView.LayoutManager mLayoutManager;

    public static TabSearchFragment newInstance() {
        return new TabSearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new TabSearchPresenter();
        mPresenter.setView(this);
        MoviesApi moviesApi = MainApplication.getMoviesApi();
        mPresenter.setMoviesApi(moviesApi);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabsearch, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_search);
        mSearchMovieView = view.findViewById(R.id.search_view_movie);

        mSearchMovieView.setOnQueryTextListener(this);
        mLayoutManager = new GridLayoutManager(view.getContext(), 3);
        mSearchAdapter = new SearchAdapter(getActivity());
        mSearchAdapter.setOnRecyclerViewItemListener(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mSearchAdapter);
        mRecyclerView.setHasFixedSize(true);
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
    public void onListSearchMovieSuccess(List<Movie> listSearchMovie) {
        if (listSearchMovie == null) {
            return;
        }
        mSearchAdapter.updateData(listSearchMovie);
    }

    @Override
    public void onItemClick(Movie movie) {
        Intent intent = new Intent(getActivity(), DetailsMovieActivity.class);
        intent.putExtra(Constant.EXTRA_MOVIE_ID, movie.getId());
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String keyWord) {
        if (keyWord == null) {
            return false;
        }
        mPresenter.getListSearchMovie(keyWord);
        return true;
    }
}
