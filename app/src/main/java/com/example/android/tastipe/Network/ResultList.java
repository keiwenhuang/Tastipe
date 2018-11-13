package com.example.android.tastipe.Network;
/**
 * Created by kevin on 10/21/18.
 */

import com.example.android.tastipe.Model.Recipe;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * TODO: Add a class header comment!
 */
public class ResultList {

    @SerializedName("results")
    private ArrayList<Recipe> resultList;

    public ArrayList<Recipe> getResultArrayList() {
        return resultList;
    }

    public void setRecipeArrayList(ArrayList<Recipe> resultArrayList) {
        this.resultList = resultArrayList;
    }
}
