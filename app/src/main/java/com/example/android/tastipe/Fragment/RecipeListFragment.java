package com.example.android.tastipe.Fragment;

/**
 * Created by Kevin on 6/14/2018
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.tastipe.Activity.RecipeActivity;
import com.example.android.tastipe.Adapter.ListAdapter;
import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.Network.RecipeApi;
import com.example.android.tastipe.Network.RecipeList;
import com.example.android.tastipe.Network.RetrofitInstance;
import com.example.android.tastipe.R;
import com.example.android.tastipe.Utils.AppConfig;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * TODO: Add a class header comment!
 */
public class RecipeListFragment extends DefaultFragment {
    private static final String TAG = "RecipeListFragment";

    private static final int RECIPE_GENERATED = 10;
    private static final int REQUEST_CODE = 0;

    private RecyclerView mRecyclerView;
    private ListAdapter adapter;
    private ProgressBar mProgressBar;

    private List<Recipe> recipeList = new ArrayList<>();

    @Override
    protected int layoutRes() {
        return R.layout.recycler_list_search;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");

        mProgressBar = view.findViewById(R.id.progressBar);
        getRandomRecipes();
    }

    private void getRandomRecipes() {
        Log.d(TAG, "randomRecipe: generating random recipes");

        mProgressBar.setVisibility(View.VISIBLE);
        Log.d(TAG, "randomRecipe: show progressBar");

        RecipeApi service = RetrofitInstance.getRetrofitInstance().create(RecipeApi.class);
        Log.d(TAG, "randomRecipe: service created");

        Call<RecipeList> call = service.getRandomRecipes(RECIPE_GENERATED);
        Log.d(TAG, "randomRecipe: call " + call);

        call.enqueue(new Callback<RecipeList>() {
            @Override
            public void onResponse(Call<RecipeList> call, Response<RecipeList> response) {
                Log.d(TAG, "onResponse: " + response.body().getRecipeArrayList());
                Log.d("getFeed: ", new GsonBuilder().setPrettyPrinting().create().toJson(response));
                mProgressBar.setVisibility(View.GONE);

                recipeList = response.body().getRecipeArrayList();
                generateRecipeList(recipeList);
            }

            @Override
            public void onFailure(Call<RecipeList> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateRecipeList(List<Recipe> recipeArrayList) {
        Log.d(TAG, "generateRecipeList: ");
        mRecyclerView = getView().findViewById(R.id.recyclerView);

        adapter = new ListAdapter(recipeArrayList, AppConfig.ListType.RECIPE_LIST);
        adapter.setCallback(new ListAdapter.Callback() {
            @Override
            public void onItemClick(Recipe recipe) {
                startActivity(RecipeActivity.newIntent(getActivity(), recipe, AppConfig.ListType.RECIPE_LIST));
            }
        });

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: Background thread destroyed.");
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
