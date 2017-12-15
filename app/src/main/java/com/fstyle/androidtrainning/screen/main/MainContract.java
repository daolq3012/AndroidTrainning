package com.fstyle.androidtrainning.screen.main;

import com.fstyle.androidtrainning.screen.BasePresenter;
import com.fstyle.androidtrainning.screen.BaseView;

/**
 * Created by Administrator on 12/10/17.
 */

public interface MainContract {

    interface Viewer extends BaseView {

    }

    interface Presenter extends BasePresenter<Viewer> {

    }
}
