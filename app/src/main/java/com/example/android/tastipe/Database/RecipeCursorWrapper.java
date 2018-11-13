package com.example.android.tastipe.Database;
/**
 * Created by Kevin on 9/21/2018
 */

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.Database.TastipeDbSchema.TastipeTable;

public class RecipeCursorWrapper extends CursorWrapper {
    private static final String TAG = "com.example.android.tastipe.Database.RecipeCursorWrapper";

    public RecipeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Recipe getRecipe() {
        String recipeId = getString(getColumnIndex(TastipeTable.Cols.ID));
        String title = getString(getColumnIndex(TastipeTable.Cols.TITLE));
        String minutes = getString(getColumnIndex(TastipeTable.Cols.MINUTES));
        String servings = getString(getColumnIndex(TastipeTable.Cols.SERVINGS));
        String image = getString(getColumnIndex(TastipeTable.Cols.IMAGE));
        String instruction = getString(getColumnIndex(TastipeTable.Cols.INSTRUCTION));
        String ingredients = getString(getColumnIndex(TastipeTable.Cols.INGREDIENTS));

        Recipe recipe = new Recipe(recipeId);
        recipe.setTitle(title);
        recipe.setMinutes(minutes);
        recipe.setServings(servings);
        recipe.setImage(image);
        recipe.setInstructions(instruction);
        recipe.setIngredients(ingredients);

        return recipe;
    }
}
