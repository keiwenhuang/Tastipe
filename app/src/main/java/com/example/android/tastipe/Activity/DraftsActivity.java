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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.tastipe.Adapter.PagerAdapter;
import com.example.android.tastipe.Database.DbHelper;
import com.example.android.tastipe.Database.RecipeLab;
import com.example.android.tastipe.Fragment.DraftInfoFragment;
import com.example.android.tastipe.Fragment.DraftIngredientFragment;
import com.example.android.tastipe.Fragment.DraftInstructionFragment;
import com.example.android.tastipe.Model.Ingredients;
import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.Model.Steps;
import com.example.android.tastipe.R;

import java.util.List;
import java.util.Objects;

/**
 * TODO: Add a class header comment!
 */
public class DraftsActivity extends AppCompatActivity implements DraftInfoFragment.RecipeInfoCallback, DraftIngredientFragment.DraftIngredientCallback, DraftInstructionFragment.DraftInstructionCallback {
    private static final String TAG = "DraftsActivity";
    private static final String EXTRA_RECIPE = "EXTRA_RECIPE";

    private Recipe mRecipe, recipeDraft;

    private ImageView backBtn, saveBtn;

    private DraftInfoFragment mInfoFragment = new DraftInfoFragment();
    private DraftIngredientFragment mIngredientFragment  = new DraftIngredientFragment();
    private DraftInstructionFragment mInstructionFragment = new DraftInstructionFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_drafts);

        backBtn = findViewById(R.id.back_arrow);
        saveBtn = findViewById(R.id.save_button);

        Intent intent = getIntent();
        mRecipe = (Recipe) intent.getSerializableExtra(EXTRA_RECIPE);

        mInfoFragment.putArguments(mRecipe);
        mIngredientFragment.putArguments(mRecipe);
        mInstructionFragment.putArguments(mRecipe);

        setupToolbar();
        setupViewPager();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.draft_toolbar);
        setSupportActionBar(toolbar);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DraftsActivity.this, recipeDraft.getTitle() + " saved!", Toast.LENGTH_SHORT).show();
                saveRecipe();
                finish();
            }
        });
    }

    private void saveRecipe() {
        RecipeLab recipeLab = new RecipeLab(getApplicationContext());

        Log.d(TAG, "saveRecipe: " +
                recipeDraft.getTitle() +
                recipeDraft.getInstructionList().toString() +
                recipeDraft.getIngredientsList().toString());
        recipeLab.addCookBook(recipeDraft, DbHelper.TableType.COOKBOOK_TABLE);
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

        Objects.requireNonNull(tabLayout.getTabAt(0)).setText("1");
        Objects.requireNonNull(tabLayout.getTabAt(1)).setText("2");
        Objects.requireNonNull(tabLayout.getTabAt(2)).setText("3");
    }

    @Override
    public void onRecipeInfo(Recipe recipe) {
        recipeDraft = recipe;
    }

    @Override
    public void sendIngredients(List<Ingredients> items) {
        recipeDraft.setIngredientsList(items);
    }

    @Override
    public void sendInstruction(List<Steps> instructions) {
        recipeDraft.setInstructionList(instructions);
    }
}
