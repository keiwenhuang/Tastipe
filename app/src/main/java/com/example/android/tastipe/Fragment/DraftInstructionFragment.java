package com.example.android.tastipe.Fragment;
/**
 * Created by kevin on 11/10/18.
 */

import android.support.v4.app.Fragment;

import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.R;

/**
 * TODO: Add a class header comment!
 */
public class DraftInstructionFragment extends DefaultFragment {

    private Recipe mRecipe;

    public void putArguments(Recipe recipe) {
        mRecipe = recipe;
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_draft_instruction;
    }
}
