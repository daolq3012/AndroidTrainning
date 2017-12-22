package com.fstyle.androidtrainning.data.local;

import android.os.AsyncTask;
import com.fstyle.androidtrainning.data.local.entity.MovieEntity;
import java.util.List;

/**
 * Created by huynh on 19/12/2017.
 */

public class SelectListMovieFromDatabase extends AsyncTask<Void, Void, List<MovieEntity>> {
    private MovieDatabase mMovieDatabase;
    private OnSelectDataListener mOnSelectDataListener;

    public SelectListMovieFromDatabase(MovieDatabase movieDatabase,
            OnSelectDataListener onSelectDataListener) {
        mMovieDatabase = movieDatabase;
        mOnSelectDataListener = onSelectDataListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<MovieEntity> doInBackground(Void... voids) {
        List<MovieEntity> movieEntityList;
        movieEntityList = mMovieDatabase.getMovieDAO().getMovie();
        return movieEntityList;
    }

    @Override
    protected void onPostExecute(List<MovieEntity> movieEntityList) {
        super.onPostExecute(movieEntityList);
        mOnSelectDataListener.onSelectDataSuccess(movieEntityList);
    }
}
