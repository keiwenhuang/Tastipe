package com.example.android.tastipe.Database;

/**
 * Created by Kevin on 9/21/2018
 */

import android.content.Context;

import com.example.android.tastipe.Model.Ingredients;
import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.Model.Steps;

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

    public void addFavorite(Recipe recipe, String database) {

        DbHelper dbHelper = new DbHelper(mContext, database);

        dbHelper.createRecipe(recipe);
        dbHelper.createIngredient(recipe);
        dbHelper.createInstruction(recipe);
    }

    public void removeFromFavorites(String id, String database) {

        DbHelper dbHelper = new DbHelper(mContext, database);
        dbHelper.deleteRecipe(id);
        dbHelper.deleteIngredient(id);
        dbHelper.deleteInstruction(id);
    }

    public Recipe getRecipe(String recipeId) {

        DbHelper dbHelper = new DbHelper(mContext, DbHelper.TableType.FAVORITE_TABLE);

        return dbHelper.getRecipe(recipeId);
    }

    public List<Recipe> getRecipes(String database) {

        DbHelper dbHelper = new DbHelper(mContext, database);

        return dbHelper.getAllRecipes();
    }

    public List<Ingredients> getIngredients(String recipeId, String database) {

        DbHelper dbHelper = new DbHelper(mContext, database);

        return dbHelper.getAllIngredients(recipeId);
    }

    public List<Steps> getInstructions(String recipeId, String database) {

        DbHelper dbHelper = new DbHelper(mContext, database);

        return dbHelper.getAllSteps(recipeId);
    }

    public boolean isFavorite(Recipe checkItem) {
        RecipeLab recipeLab = new RecipeLab(mContext);

        boolean check = false;
        List<Recipe> recipeList = recipeLab.getRecipes(DbHelper.TableType.FAVORITE_TABLE);
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
