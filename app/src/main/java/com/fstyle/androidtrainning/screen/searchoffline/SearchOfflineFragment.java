package com.fstyle.androidtrainning.screen.searchoffline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.screen.BaseFragment;

/**
 * Searchoffline Screen.
 */
public class SearchOfflineFragment extends BaseFragment implements SearchOfflineContract.Viewer {

    SearchOfflineContract.Presenter mPresenter;

    public static SearchOfflineFragment newInstance() {
        return new SearchOfflineFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new SearchOfflinePresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_searchoffline, container, false);
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
}
