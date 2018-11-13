package com.example.android.tastipe.Activity;

/**
 * Created by Kevin on 9/12/2018
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.example.android.tastipe.Fragment.FavoriteListFragment;
import com.example.android.tastipe.R;

/**
 * TODO: Add a class header comment!
 */
public class FavoriteListActivity extends DefaultActivity {
    private static final String TAG = "FavoriteListActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected Fragment createInitialFragment() {
        return new FavoriteListFragment();
    }

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_favorites_list;
    }

    @Override
    protected int getActivityNum() {
        return 0;
    }

    @Override
    protected boolean setupNavigation() {
        return false;
    }

    @Override
    protected boolean setupFragment() {
        return true;
    }
}
