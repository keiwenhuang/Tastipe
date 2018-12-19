package com.example.android.tastipe.Database;
/**
 * Created by kevin on 12/18/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.tastipe.Model.AnalyzedInstructions;
import com.example.android.tastipe.Model.Ingredients;
import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.Model.Steps;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = "DbHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "tastipeApp";

    // Table Names
    private static final String TABLE_RECIPE = "recipe";
    private static final String TABLE_INGREDIENT = "ingredient";
    private static final String TABLE_INSTRUCTION = "instruction";

    // Common column names
    private static final String KEY_ID = "_id";
    private static final String KEY_RECIPE_ID = "recipe_id";

    // RECIPE Table - column names
    private static final String KEY_TITLE = "title";
    private static final String KEY_SERVINGS = "servings";
    private static final String KEY_COOKTIME = "cooktime";
    private static final String KEY_IMG_URL = "img_url";

    // INGREDIENT Table - column names
    private static final String KEY_ITEM = "item";
    private static final String KEY_QUANTITY = "quantity";

    // INSTRUCTION Table - column names
    private static final String KEY_STEP_NUMBER = "step_number";
    private static final String KEY_INSTRUCTION = "instruction";

    // Table Create Statements
    // Recipe table create statement
    private static final String CREATE_TABLE_RECIPE =
            "CREATE TABLE " + TABLE_RECIPE + "(" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_RECIPE_ID + " TEXT," +
                    KEY_TITLE + " TEXT," +
                    KEY_SERVINGS + " TEXT," +
                    KEY_COOKTIME + " TEXT," +
                    KEY_IMG_URL + " TEXT" +
                    ")";

    // Ingredient table create statement
    private static final String CREATE_TABLE_INGREDIENT =
            "CREATE TABLE " + TABLE_INGREDIENT + "(" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_RECIPE_ID + " TEXT," +
                    KEY_ITEM + " TEXT," +
                    KEY_QUANTITY + " TEXT" +
                    ")";

    // todo_tag table create statement
    private static final String CREATE_TABLE_INSTRUCTION =
            "CREATE TABLE " + TABLE_INSTRUCTION + "(" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_RECIPE_ID + " TEXT," +
                    KEY_STEP_NUMBER + " TEXT," +
                    KEY_INSTRUCTION + " TEXT" +
                    ")";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_RECIPE);
        db.execSQL(CREATE_TABLE_INGREDIENT);
        db.execSQL(CREATE_TABLE_INSTRUCTION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSTRUCTION);

        // create new tables
        onCreate(db);
    }

    /**
     * CRUD for RECIPE TABLE
     */
    /*
     * Creating a recipe
     */
    public long createRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RECIPE_ID, recipe.getId());
        values.put(KEY_TITLE, recipe.getTitle());
        values.put(KEY_SERVINGS, recipe.getServings());
        values.put(KEY_COOKTIME, recipe.getMinutes());
        values.put(KEY_IMG_URL, recipe.getImage());

        // insert row
        return db.insert(TABLE_RECIPE, null, values);
    }

    /*
     * get single recipe
     */
    public Recipe getRecipe(long recipe_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_RECIPE + " WHERE "
                + KEY_RECIPE_ID + " = " + recipe_id;

        Log.e(TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Recipe recipe = new Recipe();
        recipe.setId(c.getString(c.getColumnIndex(KEY_RECIPE_ID)));
        recipe.setTitle((c.getString(c.getColumnIndex(KEY_TITLE))));
        recipe.setServings(c.getString(c.getColumnIndex(KEY_SERVINGS)));
        recipe.setMinutes(c.getString(c.getColumnIndex(KEY_COOKTIME)));
        recipe.setImage(c.getString(c.getColumnIndex(KEY_IMG_URL)));

        return recipe;
    }

    /*
     * getting all recipes
     * */
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_RECIPE;

        Log.e(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setId(c.getString(c.getColumnIndex(KEY_RECIPE_ID)));
                recipe.setTitle((c.getString(c.getColumnIndex(KEY_TITLE))));
                recipe.setServings(c.getString(c.getColumnIndex(KEY_SERVINGS)));
                recipe.setMinutes(c.getString(c.getColumnIndex(KEY_COOKTIME)));
                recipe.setImage(c.getString(c.getColumnIndex(KEY_IMG_URL)));

                // adding to recipe list
                recipes.add(recipe);
            } while (c.moveToNext());
        }

        return recipes;
    }

    /*
     * Updating a recipe
     */
    public int updateRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, recipe.getTitle());
        values.put(KEY_SERVINGS, recipe.getServings());
        values.put(KEY_COOKTIME, recipe.getMinutes());
        values.put(KEY_IMG_URL, recipe.getImage());

        // updating row
        return db.update(TABLE_RECIPE, values, KEY_RECIPE_ID + " = ?",
                new String[]{String.valueOf(recipe.getId())});
    }

    /*
     * Deleting a recipe
     */
    public void deleteRecipe(String recipe_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECIPE, KEY_RECIPE_ID + " = ?",
                new String[]{String.valueOf(recipe_id)});
    }

    /**
     * CRUD for INGREDIENT TABLE
     */
    /*
     * Creating a ingredient item
     */
    public void createIngredient(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RECIPE_ID, recipe.getId());

        for (AnalyzedInstructions analyzedInstructions : recipe.getAnalyzedInstructions()) {
            for (Steps steps : analyzedInstructions.getSteps()) {
                for (Ingredients ingredients : steps.getIngredients()) {

                    values.put(KEY_ITEM, ingredients.getItemName());
                    db.insert(TABLE_INGREDIENT, null, values);

                }
            }
        }
    }

    /*
     * getting all ingredients
     * */
    public List<Ingredients> getAllIngredients() {
        List<Ingredients> ingredientsList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_INGREDIENT;

        Log.e(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Ingredients item = new Ingredients();
                item.setId(c.getString(c.getColumnIndex(KEY_RECIPE_ID)));
                item.setItemName((c.getString(c.getColumnIndex(KEY_ITEM))));
                item.setQuantity(c.getString(c.getColumnIndex(KEY_QUANTITY)));

                // adding to ingredient list
                ingredientsList.add(item);
            } while (c.moveToNext());
        }

        return ingredientsList;
    }

    /*
     * Updating an ingredient
     */
    public int updateIngredient(Ingredients ingredients) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM, ingredients.getItemName());
        values.put(KEY_QUANTITY, ingredients.getQuantity());

        // updating row
        return db.update(TABLE_INGREDIENT, values, KEY_RECIPE_ID + " = ?",
                new String[]{String.valueOf(ingredients.getId())});
    }

    /*
     * Deleting an ingredient
     */
    public void deleteIngredient(String item_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INGREDIENT, KEY_ID + " = ?",
                new String[]{String.valueOf(item_id)});
    }

    /**
     * CRUD for INSTRUCTION TABLE
     */
    /*
     * Creating instruction
     */
    public void createInstruction(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RECIPE_ID, recipe.getId());

        for (AnalyzedInstructions analyzedInstructions : recipe.getAnalyzedInstructions()) {
            for (Steps steps : analyzedInstructions.getSteps()) {
                values.put(KEY_STEP_NUMBER, steps.getStepNumber());
                values.put(KEY_INSTRUCTION, steps.getInstruction());
                db.insert(TABLE_INSTRUCTION, null, values);
            }
        }
    }

    /*
     * getting all ingredients
     * */
    public List<Steps> getAllSteps() {
        List<Steps> steps = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_INSTRUCTION;

        Log.e(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Steps instruction = new Steps();
                instruction.setId(c.getString(c.getColumnIndex(KEY_RECIPE_ID)));
                instruction.setStepNumber((c.getString(c.getColumnIndex(KEY_STEP_NUMBER))));
                instruction.setInstruction(c.getString(c.getColumnIndex(KEY_INSTRUCTION)));

                // adding to ingredient list
                steps.add(instruction);
            } while (c.moveToNext());
        }

        return steps;
    }

    /*
     * Updating an instruction
     */
    public int updateInstruction(Steps instruction) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_INSTRUCTION, instruction.getInstruction());

        // updating row
        return db.update(TABLE_INSTRUCTION, values, KEY_RECIPE_ID + " = ?",
                new String[]{String.valueOf(instruction.getId())});
    }

    /*
     * Deleting an instruction
     */
    public void deleteInstruction(String instruction_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INSTRUCTION, KEY_ID + " = ?",
                new String[]{String.valueOf(instruction_id)});
    }
}
