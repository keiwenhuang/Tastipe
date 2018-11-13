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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tastipe.Model.AnalyzedInstructions;
import com.example.android.tastipe.Model.Ingredients;
import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.Model.Steps;
import com.example.android.tastipe.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {
    private static final String TAG = "RecipeActivity";
    private static final String EXTRA_RECIPE = "EXTRA_RECIPE";

    private Recipe recipe;

    private TextView titleText;
    private TextView cookTimeText;
    private TextView servingText;
    private ImageView recipeImage;
    private TextView ingredientText;
    private TextView instructionText;

    private ImageView btnBack;
    private ImageView btnEdit;
    private ImageView btnCart;


    public static Intent newIntent(Context context, Recipe recipe) {
        Intent intent = new Intent(context, RecipeActivity.class);
        intent.putExtra(EXTRA_RECIPE, recipe);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        titleText = findViewById(R.id.recipe_title);
        cookTimeText = findViewById(R.id.cooking_time);
        servingText = findViewById(R.id.servings);
        recipeImage = findViewById(R.id.recipe_image);
        ingredientText = findViewById(R.id.tvIngredients);
        instructionText = findViewById(R.id.tvInstructions);

        btnBack = findViewById(R.id.btn_back);
        btnEdit = findViewById(R.id.btn_edit);
        btnCart = findViewById(R.id.btn_cart);

        recipe = (Recipe) getIntent().getSerializableExtra(EXTRA_RECIPE);

        StringBuilder ingredientsBuilder = new StringBuilder();
        StringBuilder instructionBuilder = new StringBuilder();

        if (recipe.getIngredients() == null) {

            for (AnalyzedInstructions analyzedInstructions : recipe.getAnalyzedInstructions()) {
                for (Steps steps : analyzedInstructions.getSteps()) {

                    instructionBuilder.append(steps.getNumber() + ". " + steps.getStep() + "\n");

                    for (Ingredients ingredients : steps.getIngredients()) {

                        ingredientsBuilder.append(ingredients.getName() + "\n");

                    }
                }
            }

            recipe.setIngredients(String.valueOf(ingredientsBuilder));
            recipe.setInstructions(String.valueOf(instructionBuilder));
        }

        setupToolbar();
        bindRecipeData(recipe);
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
                intent.putExtra("recipe", recipe);
                startActivity(intent);
            }
        });
    }

    private void bindRecipeData(final Recipe recipe) {

        titleText.setText(recipe.getTitle());
        cookTimeText.setText(recipe.getMinutes() + " mins");
        servingText.setText(recipe.getServings() + " servings");
        Picasso.get().load(Uri.parse(recipe.getImage())).into(recipeImage);
        ingredientText.setText(recipe.getIngredients());
        instructionText.setText(recipe.getInstructions());

    }
}
