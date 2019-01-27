package com.example.android.tastipe.Fragment;
/**
 * Created by kevin on 11/10/18.
 */

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
    private EditText editTextTitle, editTextCookTime, editTextServings;

    private RecipeInfoCallback mCallback;

    public void putArguments(Recipe recipe) {
        mRecipe = recipe;
        Log.d(TAG, "Recipe received: " + mRecipe.getTitle());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recipeImage = view.findViewById(R.id.iv_recipe);
        editTextTitle = view.findViewById(R.id.et_title);
        editTextCookTime = view.findViewById(R.id.et_cooking_time);
        editTextServings = view.findViewById(R.id.et_servings);

        bindData();

        editTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mRecipe.setTitle(s.toString());
            }
        });

        editTextCookTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mRecipe.setMinutes(s.toString());
            }
        });

        editTextServings.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mRecipe.setServings(s.toString());
            }
        });

        mCallback.onRecipeInfo(mRecipe);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mCallback = (RecipeInfoCallback) context;

    }

    private void bindData() {
        Log.d(TAG, "bindData: " + mRecipe.getId());

        Uri uri = Uri.parse(mRecipe.getImage());
        Picasso.get().load(uri).into(recipeImage);
        editTextTitle.setText(mRecipe.getTitle());
        editTextCookTime.setText(mRecipe.getMinutes());
        editTextServings.setText(mRecipe.getServings());

    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_draft_info;
    }

    public interface RecipeInfoCallback {
        void onRecipeInfo(Recipe recipe);
    }
}
