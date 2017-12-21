package com.fstyle.androidtrainning.data.local;

import android.os.AsyncTask;
import com.fstyle.androidtrainning.data.local.entity.MovieEntity;

/**
 * Created by huynh on 19/12/2017.
 */

public class InsertMovieToDatabase extends AsyncTask<MovieEntity, Void, Void> {
    private MovieDatabase mMovieDatabase;
    private OnInsertDataListener mOnInsertDataListener;

    public InsertMovieToDatabase(MovieDatabase movieDatabase,
            OnInsertDataListener onInsertDataListener) {
        mMovieDatabase = movieDatabase;
        mOnInsertDataListener = onInsertDataListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(MovieEntity... movieEntities) {
        mMovieDatabase.getMovieDAO().insertMovie(movieEntities[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mOnInsertDataListener.onInsertDataSuccess();
    }
}
