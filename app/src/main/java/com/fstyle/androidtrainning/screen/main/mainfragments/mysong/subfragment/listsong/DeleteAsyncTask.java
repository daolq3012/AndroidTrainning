package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.listsong;

import android.os.AsyncTask;
import com.fstyle.androidtrainning.application.MainApplication;
import com.fstyle.androidtrainning.data.local.roomdb.database.Database;
import com.fstyle.androidtrainning.data.local.roomdb.entity.TrackEntity;

/**
 * Created by Administrator on 12/19/17.
 */

public class DeleteAsyncTask extends AsyncTask<TrackEntity, Void, Void> {

    private Database mDatabase;
    private OnTrackDelete mOnTrackDelete;

    public void setOnTrackDelete(OnTrackDelete onTrackDelete) {
        mOnTrackDelete = onTrackDelete;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDatabase = MainApplication.getDatabase();
    }

    @Override
    protected Void doInBackground(TrackEntity... trackEntities) {
        doDelete(trackEntities);
        return null;
    }

    private void doDelete(TrackEntity... trackEntities) {
        String name = trackEntities[0].getNameSong();
        String artist = trackEntities[0].getNameArtist();
        mDatabase.getTrackDAO().doDeleteTrack(name, artist);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mOnTrackDelete.onDeleteSuccess();
    }
}
