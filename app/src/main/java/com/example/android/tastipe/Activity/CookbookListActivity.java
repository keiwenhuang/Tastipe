package com.example.android.tastipe.Activity;

import android.support.v4.app.Fragment;

import com.example.android.tastipe.Fragment.CookbookFragment;
import com.example.android.tastipe.R;
import com.example.android.tastipe.Utils.AppConfig;

public class CookbookListActivity extends DefaultActivity {
    private static final String TAG = "CookbookListActivity";

    @Override
    protected Fragment createInitialFragment() {
        return new CookbookFragment();
    }

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_cookbook;
    }

    @Override
    protected int getActivityNum() {
        return AppConfig.ActivityType.COOKBOOKLIST;
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
