package com.fstyle.androidtrainning.screen;

/**
 * Created by Administrator on 12/13/17.
 */

public interface BasePresenter<T> {

    void setView(T view);

    void onStart();

    void onStop();
}
