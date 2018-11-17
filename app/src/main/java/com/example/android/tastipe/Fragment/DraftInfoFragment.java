package com.example.android.tastipe.Fragment;
/**
 * Created by kevin on 11/10/18.
 */

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.R;
import com.squareup.picasso.Picasso;

/**
 * TODO: Add a class header comment!
 */
public class DraftInfoFragment extends DefaultFragment {
    private static final String TAG = "DraftInfoFragment";

    private Recipe mRecipe = null;

    private ImageView recipeImage;
    private EditText title;
    private EditText cookTime;
    private EditText servings;

    public void putArguments(Recipe recipe) {
        mRecipe = recipe;
        Log.d(TAG, "Recipe received: " + mRecipe.getTitle());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recipeImage = view.findViewById(R.id.iv_recipe);
        title = view.findViewById(R.id.et_title);
        cookTime = view.findViewById(R.id.et_cooking_time);
        servings = view.findViewById(R.id.et_servings);

        bindData();
    }

    private void bindData() {
        Log.d(TAG, "bindData: " + mRecipe.getId());

        Uri uri = Uri.parse(mRecipe.getImage());
        Picasso.get().load(uri).into(recipeImage);
        title.setText(mRecipe.getTitle());
        cookTime.setText(mRecipe.getMinutes());
        servings.setText(mRecipe.getServings());

    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_draft_info;
    }
}
