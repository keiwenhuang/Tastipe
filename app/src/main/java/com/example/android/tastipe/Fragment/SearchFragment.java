package com.example.android.tastipe.Fragment;

/**
 * Created by Kevin on 9/26/2018
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tastipe.Activity.RecipeActivity;
import com.example.android.tastipe.Adapter.ListAdapter;
import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.Network.RecipeApi;
import com.example.android.tastipe.Network.ResultList;
import com.example.android.tastipe.Network.RetrofitInstance;
import com.example.android.tastipe.R;
import com.example.android.tastipe.Utils.AppConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * TODO: Add a class header comment!
 */
public class SearchFragment extends DefaultFragment {
    private static final String TAG = "SearchFragment";
    public static final int RECIPE_GENERATED = 10;
    private static final int REQUEST_CODE = 2;

//    private RecyclerView mCuisineRecyclerView, mDietRecyclerView, mAllergyRecyclerView;
//    private RecyclerView.Adapter mCuisineAdapter, mDietAdapter, mAllergyAdapter;
//    private RecyclerView.LayoutManager mCuisineLayoutManager, mDietLayoutManager, mAllergyLayoutManager;

    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private ProgressBar mProgressBar;
    private TextView btnSearch;
    private EditText queryText;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_search;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mCuisineRecyclerView = (RecyclerView) view.findViewById(R.id.cuisines_recycler_view);
//        mDietRecyclerView = (RecyclerView) view.findViewById(R.id.diets_recycler_view);
//        mAllergyRecyclerView = (RecyclerView) view.findViewById(R.id.allergies_recycler_view);
//
//        mCuisineLayoutManager = new GridLayoutManager(mContext, 2);
//        mDietLayoutManager = new GridLayoutManager(mContext, 2);
//        mAllergyLayoutManager = new GridLayoutManager(mContext, 2);
//
//        mCuisineRecyclerView.setLayoutManager(mCuisineLayoutManager);
//        mDietRecyclerView.setLayoutManager(mDietLayoutManager);
//        mAllergyRecyclerView.setLayoutManager(mAllergyLayoutManager);
//
//        String[] cuisines = {"African", "American", "British", "Cajun", "Caribbean",
//                "Chinese", "Eastern European", "French", "German", "Greek", "Indian", "Irish", "Italian",
//                "Japanese", "Jewish", "Korean", "Latin American", "Mexican", "Middle Eastern", "Nordic",
//                "Southern", "Spanish", "Thai", "Vietnamese"};
//        String[] diets = {"Ketogenic", "Paleo", "Vegetarian", "Primal", "Vegan", "Whole 30"};
//        String[] allergies = {"Dairy Free", "Egg Free", "Gluten Free", "Grain Free",
//                "Peanut Free", "Seafood Free", "Sesame Free", "Shellfish Free", "Soy Free", "Sulfite Free",
//                "Tree Nut Free", "Wheat Free"};
//
//        mCuisineAdapter = new FilterAdapter(cuisines);
//        mDietAdapter = new FilterAdapter(diets);
//        mAllergyAdapter = new FilterAdapter(allergies);
//
//        mCuisineRecyclerView.setAdapter(mCuisineAdapter);
//        mDietRecyclerView.setAdapter(mDietAdapter);
//        mAllergyRecyclerView.setAdapter(mAllergyAdapter);

        recyclerView = view.findViewById(R.id.recyclerView);
        mProgressBar = view.findViewById(R.id.progressBar);
        queryText = view.findViewById(R.id.searchQuery);
        btnSearch = view.findViewById(R.id.buttonSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchRecipe(queryText.getText().toString());
            }
        });

        queryText.addTextChangedListener(mHideShowButtonTextWatcher);
        queryText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String query = queryText.getText().toString();
                    if (query.length() > 0) searchRecipe(query);
                    return true;
                }
                return false;
            }
        });

    }

    private void searchRecipe(String query) {
        Log.d(TAG, "searchRecipe: " + query);

        mProgressBar.setVisibility(View.VISIBLE);

        RecipeApi service = RetrofitInstance.getRetrofitInstance().create(RecipeApi.class);
        Call<ResultList> call = service.searchRecipes(
                null,
                null,
                null,
                RECIPE_GENERATED,
                0,
                query,
                "main+course"
        );

        call.enqueue(new Callback<ResultList>() {
            @Override
            public void onResponse(Call<ResultList> call, Response<ResultList> response) {
                Log.d(TAG, "onResponse: ResultList generated.");
                mProgressBar.setVisibility(View.GONE);
                generateRecipeList(response.body().getResultArrayList());
                hideSoftKeyboard();
            }

            @Override
            public void onFailure(Call<ResultList> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateRecipeList(List<Recipe> recipeArrayList) {

        adapter = new ListAdapter(recipeArrayList, AppConfig.ListType.FAVORITE_LIST);
        adapter.setCallback(new ListAdapter.Callback() {
            @Override
            public void onItemClick(Recipe recipe) {
                Log.d(TAG, "onItemClick: " + recipe);
                startActivity(RecipeActivity.newIntent(getActivity(), recipe, REQUEST_CODE));
            }
        });

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "generateRecipeList: ");
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(queryText.getWindowToken(), 0);
    }

    private TextWatcher mHideShowButtonTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            btnSearch.setVisibility(charSequence.length() > 0 ? View.VISIBLE : View.GONE);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
