package com.example.android.tastipe.Network;
/**
 * Created by kevin on 10/21/18.
 */

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * TODO: Add a class header comment!
 */
public class RetrofitInstance {

    private static Retrofit retrofit;
    public static final String BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
