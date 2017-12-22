package com.fstyle.androidtrainning.screen.main.mainfragments.mysong.subfragment.favoritesong;

import android.os.AsyncTask;
import com.fstyle.androidtrainning.application.MainApplication;
import com.fstyle.androidtrainning.data.local.roomdb.database.Database;
import com.fstyle.androidtrainning.data.local.roomdb.entity.TrackEntity;
import java.util.List;

/**
 * Created by Administrator on 12/18/17.
 */

public class GetListAsyncTask extends AsyncTask<Void, Void, List<TrackEntity>> {

    private Database mDatabase;
    private OnGetListTrack mOnGetListTrack;

    public void setOnGetListTrack(OnGetListTrack onGetListTrack) {
        mOnGetListTrack = onGetListTrack;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDatabase = MainApplication.getDatabase();
    }

    @Override
    protected List<TrackEntity> doInBackground(Void... voids) {
        return mDatabase.getTrackDAO().getListTrack();
    }

    @Override
    protected void onPostExecute(List<TrackEntity> tracks) {
        super.onPostExecute(tracks);
        mOnGetListTrack.onGetListSuccess(tracks);
    }
}
