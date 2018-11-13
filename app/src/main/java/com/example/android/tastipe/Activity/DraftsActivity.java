package com.example.android.tastipe.Activity;

/**
 * Created by Kevin on 9/12/2018
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.android.tastipe.Adapter.PagerAdapter;
import com.example.android.tastipe.Fragment.DraftInfoFragment;
import com.example.android.tastipe.Fragment.DraftIngredientFragment;
import com.example.android.tastipe.Fragment.DraftInstructionFragment;
import com.example.android.tastipe.Fragment.DraftsFragment;
import com.example.android.tastipe.R;

/**
 * TODO: Add a class header comment!
 */
public class DraftsActivity extends DefaultActivity {
    private static final String TAG = "DraftsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupViewPager();
    }

    private void setupViewPager() {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new DraftInfoFragment()); //index 0
        adapter.addFragment(new DraftIngredientFragment()); //index 1
        adapter.addFragment(new DraftInstructionFragment()); //index 2

        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("1");
        tabLayout.getTabAt(1).setText("2");
        tabLayout.getTabAt(2).setText("3");
    }

    @Override
    protected Fragment createInitialFragment() {
        return new DraftsFragment();
    }

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_recipe_drafts;
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
        return false;
    }
}
