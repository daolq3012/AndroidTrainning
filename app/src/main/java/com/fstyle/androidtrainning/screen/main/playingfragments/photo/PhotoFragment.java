package com.fstyle.androidtrainning.screen.main.playingfragments.photo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.screen.BaseFragment;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends BaseFragment implements PhotoContract.Viewer {

    private PhotoPresenter mPresenter;
    private CircleImageView mCircleImageView;

    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_photo, container, false);
        initViews(v);
        doAnimation(container);
        return v;
    }

    private void initViews(View v) {
        mPresenter = new PhotoPresenter();
        mPresenter.setView(this);
        mCircleImageView = v.findViewById(R.id.circleImage);
    }

    private void doAnimation(ViewGroup viewGroup) {
        Animation animRotate = AnimationUtils.loadAnimation(viewGroup.getContext(), R.anim.rotate);
        mCircleImageView.setAnimation(animRotate);
    }
}
