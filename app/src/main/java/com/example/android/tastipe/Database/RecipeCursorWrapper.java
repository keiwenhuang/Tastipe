package com.example.android.tastipe.Database;
/**
 * Created by Kevin on 9/21/2018
 */

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.android.tastipe.Database.TastipeDbSchema.TastipeTable;
import com.example.android.tastipe.Model.Ingredients;
import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.Model.Steps;

import static com.example.android.tastipe.Database.TastipeDbSchema.IngredientTable;
import static com.example.android.tastipe.Database.TastipeDbSchema.InstructionTable;

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
        String image = getString(getColumnIndex(TastipeTable.Cols.IMAGE_URL));
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

    public Ingredients getIngredients() {

        String recipeId = getString(getColumnIndex(IngredientTable.Cols.ID));
        String item = getString(getColumnIndex(IngredientTable.Cols.ITEM));
        String quantity = getString(getColumnIndex(IngredientTable.Cols.QUANTITY));

        Ingredients ingredients = new Ingredients(recipeId);

        ingredients.setItemName(item);
        ingredients.setQuantity(quantity);

        return ingredients;
    }

    public Steps getSteps() {

        String recipeId = getString(getColumnIndex(InstructionTable.Cols.ID));
        String step = getString(getColumnIndex(InstructionTable.Cols.STEP));
        String instruction = getString(getColumnIndex(InstructionTable.Cols.INSTRUCTION));

        Steps instructions = new Steps(recipeId);

        instructions.setStepNumber(step);
        instructions.setInstruction(instruction);

        return instructions;
    }
}
