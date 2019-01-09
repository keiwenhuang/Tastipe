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

import com.example.android.tastipe.Model.AnalyzedInstructions;
import com.example.android.tastipe.Model.Ingredients;
import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.Model.Steps;
import com.example.android.tastipe.R;
import com.example.android.tastipe.Utils.ListUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecipeActivity extends AppCompatActivity {
    private static final String TAG = "RecipeActivity";
    private static final String EXTRA_RECIPE = "EXTRA_RECIPE";

    private Recipe recipe;

    private TextView titleText;
    private TextView cookTimeText;
    private TextView servingText;
    private ImageView recipeImage;

    private ImageView btnBack;
    private ImageView btnEdit;
    private ImageView btnCart;

    private ListView ingredientListView, instructionListView;

    private List<Ingredients> mIngredientsList = new ArrayList<>();
    private List<Steps> mStepsList = new ArrayList<>();

//    private String [] itemList = {"Apple", "Pineapple", "Orange", "Apple"};

//    private Set<Ingredients> mIngredientsSet = new HashSet<>();
//    private Set<Steps> mStepsSet = new HashSet<>();


    public static Intent newIntent(Context context, Recipe recipe) {
        Intent intent = new Intent(context, RecipeActivity.class);
        intent.putExtra(EXTRA_RECIPE, recipe);
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

        if (recipe.getIngredients() == null) {

            for (AnalyzedInstructions analyzedInstructions : recipe.getAnalyzedInstructions()) {
                for (Steps steps : analyzedInstructions.getSteps()) {

                    String instruction = steps.getInstruction();

                    mStepsList.add(new Steps(steps.getStepNumber(), instruction));

                    for (Ingredients ingredients : steps.getIngredients()) {

                        Log.d(TAG, "onCreate: list, " + mIngredientsList.toString());

                        Ingredients item = new Ingredients(ingredients.getItemName().trim());
                        Log.d(TAG, "onCreate: item, " + item);

                        boolean isContained = mIngredientsList.contains(item);
                        Log.d(TAG, "onCreate: isContained: " + isContained);

                        if (isContained) {
                            Log.d(TAG, "onCreate: already has item in the list: " + item);
                        } else {
                            mIngredientsList.add(item);
                        }
//
//                        Log.d(TAG, "onCreate: item, " + item);
//                        Log.d(TAG, "onCreate: list contains: " + mIngredientsList.toString());
//
//                        if (!mIngredientsList.contains(item)) {
//
//                            Log.d(TAG, "onCreate: " + mIngredientsList.contains(item));
//                            mIngredientsList.add(item);
//
//                        } else {
//                            Log.d(TAG, "onCreate: item duplicated: " + item);
//                        }
                    }

                }
            }
        }

        setupToolbar();
        bindRecipeData(recipe);

        ingredientListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mIngredientsList));
        instructionListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStepsList));

        ListUtils.setDynamicHeight(ingredientListView);
        ListUtils.setDynamicHeight(instructionListView);
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
