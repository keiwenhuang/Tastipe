package com.example.android.tastipe.Fragment;

/**
 * Created by Kevin on 9/25/2018
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.tastipe.Activity.RecipeActivity;
import com.example.android.tastipe.Adapter.ListAdapter;
import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.Database.RecipeLab;
import com.example.android.tastipe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class FavoriteListFragment extends DefaultFragment {
    private static final String TAG = "FavoriteListFragment";

    private static final int ACTIVITY_NUM = 2;

    private RecyclerView mRecyclerView;
    private List<Recipe> recipeList = new ArrayList<>();
    private ListAdapter adapter;

    @Override
    protected int layoutRes() {
        return R.layout.recycler_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        generateRecipeList();

    }

    private void generateRecipeList() {
        RecipeLab recipeLab = RecipeLab.get(getActivity());

        recipeList = recipeLab.getRecipes();

        mRecyclerView = getView().findViewById(R.id.recyclerView);

        adapter = new ListAdapter(recipeList, ACTIVITY_NUM);
        adapter.setCallback(new ListAdapter.Callback() {
            @Override
            public void onItemClick(Recipe recipe) {
                startActivity(RecipeActivity.newIntent(getActivity(), recipe));
            }
        });

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }
}
