package com.example.android.tastipe.Activity;

/**
 * Created by Kevin on 8/30/2018
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.tastipe.Database.DbHelper;
import com.example.android.tastipe.Database.RecipeLab;
import com.example.android.tastipe.Model.AnalyzedInstructions;
import com.example.android.tastipe.Model.Ingredients;
import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.Model.Steps;
import com.example.android.tastipe.R;
import com.example.android.tastipe.Utils.AppConfig;
import com.example.android.tastipe.Utils.ListUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {
    private static final String TAG = "RecipeActivity";
    private static final String EXTRA_RECIPE = "EXTRA_RECIPE";
    private static final String REQUEST_CODE = "REQUEST_CODE";

    private Recipe recipe;
    private RecipeLab mRecipeLab;

    private TextView titleText, cookTimeText, servingText;
    private ImageView recipeImage;

    private ImageView btnBack, btnEdit, btnCart;

    private ListView ingredientListView, instructionListView;

    private List<Ingredients> mIngredientsList = new ArrayList<>();
    private List<Steps> mStepsList = new ArrayList<>();

    public static Intent newIntent(Context context, Recipe recipe, int requestCode) {
        Intent intent = new Intent(context, RecipeActivity.class);
        intent.putExtra(EXTRA_RECIPE, recipe);
        intent.putExtra(REQUEST_CODE, requestCode);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        ingredientListView = findViewById(R.id.list_ingredients);
        instructionListView = findViewById(R.id.list_instructions);

        titleText = findViewById(R.id.recipe_title);
        cookTimeText = findViewById(R.id.cooking_time);
        servingText = findViewById(R.id.servings);
        recipeImage = findViewById(R.id.recipe_image);

        btnBack = findViewById(R.id.btn_back);
        btnEdit = findViewById(R.id.btn_edit);
        btnCart = findViewById(R.id.btn_cart);

        recipe = (Recipe) getIntent().getSerializableExtra(EXTRA_RECIPE);
        String recipeId = recipe.getId();

        if (getIntent().getExtras().getInt(REQUEST_CODE) == AppConfig.ListType.RECIPE_LIST) { // intent from RecipeListFragment

            fetchListFromApi(recipe);

        } else if (getIntent().getExtras().getInt(REQUEST_CODE) == AppConfig.ListType.FAVORITE_LIST) { // intent from FavoriteListFragment

            fetchListFromDatabase(recipeId);

        } else if (getIntent().getExtras().getInt(REQUEST_CODE) == AppConfig.ListType.COOKBOOK_LIST) { // intent from CookbookFragment

            fetchListFromCookbook(recipeId);

        }
        else {

            fetchListFromSearch(recipe);

        }

        setupToolbar();
        bindRecipeData(recipe);

        ingredientListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mIngredientsList));
        instructionListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStepsList));

        ListUtils.setDynamicHeight(ingredientListView);
        ListUtils.setDynamicHeight(instructionListView);
    }

    private void fetchListFromSearch(Recipe recipe) {
    }


    private void fetchListFromCookbook(String recipeId) {

        mRecipeLab = new RecipeLab(getApplicationContext());

        mIngredientsList = mRecipeLab.getIngredients(recipeId, DbHelper.TableType.COOKBOOK_TABLE);
        mStepsList = mRecipeLab.getInstructions(recipeId, DbHelper.TableType.COOKBOOK_TABLE);

    }

    private void fetchListFromDatabase(String recipeId) {
        mRecipeLab = new RecipeLab(getApplicationContext());

        mIngredientsList = mRecipeLab.getIngredients(recipeId, DbHelper.TableType.FAVORITE_TABLE);
        mStepsList = mRecipeLab.getInstructions(recipeId, DbHelper.TableType.FAVORITE_TABLE);
    }

    private void fetchListFromApi(Recipe recipe) {
        for (AnalyzedInstructions analyzedInstructions : recipe.getAnalyzedInstructions()) {
            for (Steps steps : analyzedInstructions.getSteps()) {

                String instruction = steps.getInstruction();
                mStepsList.add(new Steps(steps.getStepNumber(), instruction));

                for (Ingredients ingredients : steps.getIngredients()) {

                    Ingredients item = new Ingredients(ingredients.getItemName().trim());

                    if (!mIngredientsList.contains(item)) {
                        mIngredientsList.add(item);
                    } else {
                        Log.d(TAG, "onCreate: already has item in the list: " + item);
                    }
                }
            }
        }
    }

    private void setupToolbar() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeActivity.this, DraftsActivity.class);
                intent.putExtra(EXTRA_RECIPE, recipe);
                startActivity(intent);
            }
        });
    }

    private void bindRecipeData(final Recipe recipe) {

        titleText.setText(recipe.getTitle());
        cookTimeText.setText(recipe.getMinutes() + " mins");
        servingText.setText(recipe.getServings() + " servings");
        Picasso.get().load(Uri.parse(recipe.getImage())).into(recipeImage);

    }
}
