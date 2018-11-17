package com.example.android.tastipe.Activity;

/**
 * Created by Kevin on 9/12/2018
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.tastipe.Adapter.PagerAdapter;
import com.example.android.tastipe.Fragment.DraftInfoFragment;
import com.example.android.tastipe.Fragment.DraftIngredientFragment;
import com.example.android.tastipe.Fragment.DraftInstructionFragment;
import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.R;

/**
 * TODO: Add a class header comment!
 */
public class DraftsActivity extends AppCompatActivity {
    private static final String TAG = "DraftsActivity";
    private static final String EXTRA_RECIPE = "EXTRA_RECIPE";

    private Recipe mRecipe;

    private DraftInfoFragment mInfoFragment = new DraftInfoFragment();
    private DraftIngredientFragment mIngredientFragment  = new DraftIngredientFragment();
    private DraftInstructionFragment mInstructionFragment = new DraftInstructionFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_drafts);

        Intent intent = getIntent();
        mRecipe = (Recipe) intent.getSerializableExtra(EXTRA_RECIPE);

        mInfoFragment.putArguments(mRecipe);
        mIngredientFragment.putArguments(mRecipe);
        mInstructionFragment.putArguments(mRecipe);

        setupViewPager();
    }

    private void setupViewPager() {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());

        adapter.addFragment(mInfoFragment); //index 0
        adapter.addFragment(mIngredientFragment); //index 1
        adapter.addFragment(mInstructionFragment); //index 2

        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("1");
        tabLayout.getTabAt(1).setText("2");
        tabLayout.getTabAt(2).setText("3");
    }
}
