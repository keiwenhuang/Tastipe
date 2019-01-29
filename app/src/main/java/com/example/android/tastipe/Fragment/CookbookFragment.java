package com.example.android.tastipe.Fragment;

/**
 * Created by Kevin on 9/26/2018
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.tastipe.Activity.RecipeActivity;
import com.example.android.tastipe.Adapter.CookbookListAdapter;
import com.example.android.tastipe.Adapter.ListAdapter;
import com.example.android.tastipe.Database.DbHelper;
import com.example.android.tastipe.Database.RecipeLab;
import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class CookbookFragment extends DefaultFragment {
    private static final String TAG = "CookbookFragment";

    private static final int REQUEST_CODE = 2;

    private RecyclerView mRecyclerView;
    private List<Recipe> mRecipeList = new ArrayList<>();
    private CookbookListAdapter mAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        generateRecipeList();

    }

    private void generateRecipeList() {
        RecipeLab recipeLab = RecipeLab.get(getActivity());

        mRecipeList = recipeLab.getRecipes(DbHelper.TableType.COOKBOOK_TABLE);

        mRecyclerView = getView().findViewById(R.id.recyclerView);

        mAdapter = new CookbookListAdapter(mRecipeList);
        mAdapter.setCallback(new CookbookListAdapter.Callback() {
            @Override
            public void onItemClick(Recipe recipe) {
                startActivity(RecipeActivity.newIntent(getActivity(), recipe, REQUEST_CODE));
            }
        });

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_cookbook;
    }
}
