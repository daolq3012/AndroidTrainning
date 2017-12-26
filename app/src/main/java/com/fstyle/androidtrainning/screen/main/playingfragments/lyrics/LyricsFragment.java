package com.fstyle.androidtrainning.screen.main.playingfragments.lyrics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.data.local.storage.LyricSdCard;
import com.fstyle.androidtrainning.model.Track;
import com.fstyle.androidtrainning.screen.BaseFragment;
import com.fstyle.androidtrainning.screen.main.MainActivity;
import com.fstyle.androidtrainning.screen.main.OnTrackChangeListener;
import com.fstyle.androidtrainning.utils.Constant;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import me.zhengken.lyricview.LyricView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LyricsFragment extends BaseFragment
        implements LyricsContract.Viewer, OnTrackChangeListener {

    private LyricsPresenter mPresenter;
    private List<File> fileArrayList = new ArrayList<>();
    private static final String TAG = "LyricsFragment";
    private LyricView mLyricView;
    private Track mTrack = new Track();
    private BroadcastReceiver mReceiver;
    private IntentFilter currentTimeFilter;
    private static final int SDK_DEFAULT = 21;

    public static LyricsFragment newInstance() {
        return new LyricsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentTimeFilter = new IntentFilter(Constant.ACTION_MEDIA_TIME);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lyrics, container, false);
        initViews(v);

        ((MainActivity) getActivity()).setOnTrackChangeListener(this);
//        if (Build.VERSION.SDK_INT >= SDK_DEFAULT) {
//            File sdCard2 = new File(Constant.STR_PATH_LOWER_4);
//            fileArrayList = LyricSdCard.findLyrics(sdCard2);
//        } else {
//            File sdCard = new File(Constant.STR_PATH_UPPER_4);
//            fileArrayList = LyricSdCard.findLyrics(sdCard);
//        }

        initReceiver();
        return v;
    }

    private void initReceiver() {
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case Constant.ACTION_MEDIA_TIME:
                        mLyricView.setCurrentTimeMillis(
                                intent.getIntExtra(Constant.EXTRA_CURRENT_TIME, 0));
                        break;
                    default:
                        break;
                }
            }
        };
        getActivity().registerReceiver(mReceiver, currentTimeFilter);
    }

    private void initViews(View v) {
        mPresenter = new LyricsPresenter();
        mPresenter.setView(this);
        mLyricView = v.findViewById(R.id.lyricView);
    }

    private void loadLyric(Track track) {

        String songPath = track.getTrackData();
        int indexSongData = songPath.lastIndexOf('/');
        String subSongData = songPath.substring(indexSongData + 1).replace(Constant.TYPE_MP3, "");
        for (int i = 0; i < fileArrayList.size(); i++) {
            int indexFileArrayList = fileArrayList.get(i).toString().lastIndexOf('/');
            String file = fileArrayList.get(i)
                    .toString()
                    .substring(indexFileArrayList + 1)
                    .replace(Constant.TYPE_LYRIC, "");
            if (subSongData.equalsIgnoreCase(file)) {
                mLyricView.setLyricFile(fileArrayList.get(i));
                mLyricView.invalidate();
                return;
            } else {
                mLyricView.setLyricFile(null);
            }
        }
        mLyricView.setLyricFile(null);
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    public void onTrackChanged() {
        if (getActivity() instanceof MainActivity) {
            mTrack = ((MainActivity) getActivity()).getTrack();
            loadLyric(mTrack);
        }
    }
}
