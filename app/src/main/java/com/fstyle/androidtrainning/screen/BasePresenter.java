package com.fstyle.androidtrainning.screen;

/**
 * Created by huynh on 12/12/2017.
 */

public interface BasePresenter<T> {
    void setView(T view);
    void onStart();
    void onStop();
}
