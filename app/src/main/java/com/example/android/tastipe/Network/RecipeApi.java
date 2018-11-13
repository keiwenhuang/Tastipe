package com.example.android.tastipe.Network;
/**
 * Created by kevin on 10/21/18.
 */

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * TODO: Add a class header comment!
 */
public interface RecipeApi {

    @Headers("X-Mashape-Key: 4l2VUZDPcFmshNG9ypRwW0gs8XFqp1rk4dajsnVnpEyMHEyIWU")
    @GET("random")
    Call<RecipeList> getRandomRecipes(@Query("number") int number);

    @Headers("X-Mashape-Key: 4l2VUZDPcFmshNG9ypRwW0gs8XFqp1rk4dajsnVnpEyMHEyIWU")
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

//    @Headers("X-Mashape-Key: 4l2VUZDPcFmshNG9ypRwW0gs8XFqp1rk4dajsnVnpEyMHEyIWU")
//    @GET("{recipeId}/analyzedInstructions?stepBreakdown=false")
}

/*

unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/324694/analyzedInstructions?stepBreakdown=false")
.header("X-Mashape-Key", "4l2VUZDPcFmshNG9ypRwW0gs8XFqp1rk4dajsnVnpEyMHEyIWU")
.header("X-Mashape-Host", "spoonacular-recipe-food-nutrition-v1.p.mashape.com")
.end(function (result) {
  console.log(result.status, result.headers, result.body);
});

 */
