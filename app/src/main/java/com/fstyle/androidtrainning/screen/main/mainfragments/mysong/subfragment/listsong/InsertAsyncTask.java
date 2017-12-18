package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.listsong;

import android.os.AsyncTask;
import com.fstyle.androidtrainning.application.MainApplication;
import com.fstyle.androidtrainning.data.local.roomdb.database.Database;
import com.fstyle.androidtrainning.data.local.roomdb.entity.TrackEntity;

/**
 * Created by Administrator on 12/18/17.
 */

public class InsertAsyncTask extends AsyncTask<TrackEntity, Void, Void> {

    private Database mDatabase;
    private OnTrackInsert mOnTrackInsert;

    public void setOnTrackInsert(OnTrackInsert onTrackInsert) {
        mOnTrackInsert = onTrackInsert;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDatabase = MainApplication.getDatabase();
    }

    @Override
    protected Void doInBackground(TrackEntity... tracks) {
        insertTrackToDB(tracks[0]);
        return null;
    }

    private void insertTrackToDB(TrackEntity track) {
        mDatabase.getTrackDAO().doInsertTrack(track);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mOnTrackInsert.onInsertSuccess();
    }
}
