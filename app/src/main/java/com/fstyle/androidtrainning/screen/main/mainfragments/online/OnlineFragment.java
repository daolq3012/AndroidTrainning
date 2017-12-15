package com.fstyle.androidtrainning.screen.main.mainfragments.online;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fstyle.androidtrainning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnlineFragment extends Fragment {

    private OnlineViewPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private static final int PAGE_LIMIT = 2;

    public OnlineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_online, container, false);
        initViews(v);

        return v;
    }

    private void initViews(View v) {
        mViewPager = v.findViewById(R.id.viewPager);
        mTabLayout = v.findViewById(R.id.tabLayout);
        mPagerAdapter = new OnlineViewPagerAdapter(getChildFragmentManager(), getActivity());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(PAGE_LIMIT);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
