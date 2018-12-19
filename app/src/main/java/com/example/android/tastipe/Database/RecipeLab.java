package com.example.android.tastipe.Database;

/**
 * Created by Kevin on 9/21/2018
 */

import android.content.Context;

import com.example.android.tastipe.Model.Recipe;

import java.util.List;

public class RecipeLab {
    private static final String TAG = "RecipeLab";

    private static RecipeLab sRecipeLab;
    private Context mContext;

    public static RecipeLab get(Context context) {
        if (sRecipeLab == null) {
            sRecipeLab = new RecipeLab(context);
        }

        return sRecipeLab;
    }

    public RecipeLab(Context context) {
        mContext = context.getApplicationContext();
    }

    public void addFavorite(Recipe recipe) {

        DbHelper dbHelper = new DbHelper(mContext);

        dbHelper.createRecipe(recipe);
        dbHelper.createIngredient(recipe);
        dbHelper.createInstruction(recipe);
    }

    public void removeFromFavorites(String id) {

        DbHelper dbHelper = new DbHelper(mContext);
        dbHelper.deleteRecipe(id);
        dbHelper.deleteIngredient(id);
        dbHelper.deleteInstruction(id);
    }

    public List<Recipe> getRecipes() {

        DbHelper dbHelper = new DbHelper(mContext);

        return dbHelper.getAllRecipes();
    }

    public boolean isFavorite(Recipe checkItem) {
        RecipeLab recipeLab = new RecipeLab(mContext);

        boolean check = false;
        List<Recipe> recipeList = recipeLab.getRecipes();
        if (recipeList != null) {
            for (Recipe recipe : recipeList) {
                String id = recipe.getId();
                String id2 = checkItem.getId();
                if (id.equals(id2)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }
}
