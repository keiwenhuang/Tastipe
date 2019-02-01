package com.example.android.tastipe.Activity;

import android.support.v4.app.Fragment;

import com.example.android.tastipe.Fragment.SearchFragment;
import com.example.android.tastipe.R;
import com.example.android.tastipe.Utils.AppConfig;

public class SearchActivity extends DefaultActivity {
    private static final String TAG = "SearchActivity";

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
        return AppConfig.ActivityType.SEARCH;
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
