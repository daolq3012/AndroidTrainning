package com.fstyle.androidtrainning.screen.detailsmovie;

import android.os.Bundle;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.screen.BaseActivity;

/**
 * DetailsMovie Screen.
 */
public class DetailsMovieActivity extends BaseActivity
        implements DetailsMovieContract.DetailsMovieView {

    DetailsMovieContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsmovie);

        mPresenter = new DetailsMoviePresenter();
        mPresenter.setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
    }
}
