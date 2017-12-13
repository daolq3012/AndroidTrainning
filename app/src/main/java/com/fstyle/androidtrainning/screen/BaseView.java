package com.fstyle.androidtrainning.screen;

/**
 * Created by Administrator on 12/13/17.
 */

public interface BaseView<T extends BasePresenter> {

    void onStart();

    void onStop();

    void setPresenter(T presenter);
}
