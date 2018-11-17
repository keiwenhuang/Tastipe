package com.example.android.tastipe.Fragment;
/**
 * Created by kevin on 11/10/18.
 */

import android.support.v7.widget.RecyclerView;

import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.R;

/**
 * TODO: Add a class header comment!
 */
public class DraftIngredientFragment extends DefaultFragment {

    private Recipe mRecipe;

    private RecyclerView mRecyclerView;

    public void putArguments(Recipe recipe) {
        mRecipe = recipe;
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_draft_ingredient;
    }
}