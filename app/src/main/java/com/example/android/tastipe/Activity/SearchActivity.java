package com.example.android.tastipe.Activity;

import android.support.v4.app.Fragment;

import com.example.android.tastipe.Fragment.SearchFragment;
import com.example.android.tastipe.R;

public class SearchActivity extends DefaultActivity {
    private static final String TAG = "SearchActivity";

    private final int ACTIVITY_NUM = 1;

    @Override
    protected Fragment createInitialFragment() {
        return new SearchFragment();
    }

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected int getActivityNum() {
        return ACTIVITY_NUM;
    }

    @Override
    protected boolean setupNavigation() {
        return true;
    }

    @Override
    protected boolean setupFragment() {
        return true;
    }
}
