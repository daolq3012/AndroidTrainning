package com.fstyle.androidtrainning.screen.search;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.fstyle.androidtrainning.R;
import com.fstyle.androidtrainning.screen.BaseActivity;

/**
 * Search Screen.
 */
public class SearchActivity extends BaseActivity
        implements SearchContract.Viewer, View.OnClickListener {

    private SearchContract.Presenter mPresenter;
    private ViewPager mViewPager;
    private SearchPagerAdapter mSearchPagerAdapter;
    private TabLayout mTabLayout;
    private ImageView mImageViewBack, mImageViewCancel;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
    }

    private void initViews() {
        mPresenter = new SearchPresenter();
        mPresenter.setView(this);

        mViewPager = findViewById(R.id.viewPager);
        mTabLayout = findViewById(R.id.tabLayout);
        mImageViewBack = findViewById(R.id.image_back);
        mImageViewCancel = findViewById(R.id.image_cancel);
        mEditText = findViewById(R.id.edit_search);

        mSearchPagerAdapter = new SearchPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mSearchPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mImageViewBack.setOnClickListener(this);
        mImageViewCancel.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.image_cancel:
                mEditText.setText("");
                break;
            default:
                break;
        }
    }
}
