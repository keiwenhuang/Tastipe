package com.example.android.tastipe.Network;
/**
 * Created by kevin on 10/21/18.
 */

import com.example.android.tastipe.BuildConfig;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * TODO: Add a class header comment!
 */
public interface RecipeApi {

    String apiKey = BuildConfig.ApiKey;

    @Headers("X-Mashape-Key: " + apiKey)
    @GET("random")
    Call<RecipeList> getRandomRecipes(@Query("number") int number);

    @Headers("X-Mashape-Key: " + apiKey)
    @GET("search")
    Call<ResultList> searchRecipes(
            @Query(value = "diet", encoded = true) String diet,
            @Query(value = "excludeIngredients", encoded = true) String excludeIngredients,
            @Query("intolerances") String intolerances,
            @Query("number") int number,
            @Query("offset") int offset,
            @Query(value = "query", encoded = true) String query,
            @Query(value = "type", encoded = true) String type
    );
}