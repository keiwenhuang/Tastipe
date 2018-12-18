package com.example.android.tastipe.Model;

/**
 * Created by Kevin on 9/21/2018
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.tastipe.Database.DatabaseHelper;
import com.example.android.tastipe.Database.RecipeCursorWrapper;
import com.example.android.tastipe.Database.TastipeDbSchema.TastipeTable;

import java.util.ArrayList;
import java.util.List;

public class RecipeLab {
    private static final String TAG = "RecipeLab";

    private static RecipeLab sRecipeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private List<Recipe> mRecipes;

    public static RecipeLab get(Context context) {
        if (sRecipeLab == null) {
            sRecipeLab = new RecipeLab(context);
        }

        return sRecipeLab;
    }

    public RecipeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DatabaseHelper(mContext).getWritableDatabase();
    }

    public void addFavorite(Recipe recipe) {
        ContentValues values = getContentValues(recipe);
        mDatabase.insert(TastipeTable.NAME, null, values);
    }

    public void removeFromRecipe(String id) {
        mDatabase.delete(TastipeTable.NAME, "ID = ?",
                new String[]{String.valueOf(id)});
    }

    public Recipe getRecipe(String id) {

        RecipeCursorWrapper cursor = queryRecipes(
                TastipeTable.Cols.ID + " = ?",
                new String[]{id}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getRecipe();
        } finally {
            cursor.close();
        }
    }

    public List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>();
        RecipeCursorWrapper cursor = queryRecipes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                recipes.add(cursor.getRecipe());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return recipes;
    }

    public void updateRecipe(Recipe recipe) {
        String recipeId = recipe.getId();
        ContentValues values = getContentValues(recipe);

        mDatabase.update(TastipeTable.NAME, values,
                TastipeTable.Cols.ID + " = ?",
                new String[]{recipeId}
        );
    }

    private RecipeCursorWrapper queryRecipes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                TastipeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new RecipeCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Recipe recipe) {
        ContentValues values = new ContentValues();

        StringBuilder ingredientsBuilder = new StringBuilder();
        StringBuilder instructionBuilder = new StringBuilder();

        for (AnalyzedInstructions analyzedInstructions : recipe.getAnalyzedInstructions()) {
            for (Steps steps : analyzedInstructions.getSteps()) {
                instructionBuilder.append(steps.getStepNumber() + ". " + steps.getInstruction() + "\n");
                for (Ingredients ingredients : steps.getIngredients()) {
                    ingredientsBuilder.append(ingredients.getItemName() + "\n");
                }
            }
        }

        values.put(TastipeTable.Cols.ID, recipe.getId());
        values.put(TastipeTable.Cols.TITLE, recipe.getTitle());
        values.put(TastipeTable.Cols.MINUTES, recipe.getMinutes());
        values.put(TastipeTable.Cols.SERVINGS, recipe.getServings());
        values.put(TastipeTable.Cols.IMAGE_URL, recipe.getImage());
        values.put(TastipeTable.Cols.INSTRUCTION, instructionBuilder.toString());
        values.put(TastipeTable.Cols.INGREDIENTS, ingredientsBuilder.toString());

        return values;
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
