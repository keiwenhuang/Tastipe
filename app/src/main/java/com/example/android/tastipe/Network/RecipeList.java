package com.example.android.tastipe.Network;
/**
 * Created by kevin on 10/21/18.
 */

import com.example.android.tastipe.Model.Recipe;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class RecipeList {

    @SerializedName("recipes")
    private List<Recipe> recipeList;

    public List<Recipe> getRecipeArrayList() {
        return recipeList;
    }

    public void setRecipeArrayList(List<Recipe> recipeArrayList) {
        this.recipeList = recipeArrayList;
    }
}
