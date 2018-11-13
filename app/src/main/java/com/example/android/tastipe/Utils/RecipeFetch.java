package com.example.android.tastipe.Utils;

/**
 * Created by Kevin on 8/15/2018
 */

import android.net.Uri;
import android.util.Log;

import com.example.android.tastipe.Model.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */

/*
public class RecipeFetch {
    private static final String TAG = "RecipeFetch";

    private static final String API_KEY = "4l2VUZDPcFmshNG9ypRwW0gs8XFqp1rk4dajsnVnpEyMHEyIWU";
    private static final String API_HOST = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/menuItems/";

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("X-Mashape-Key", API_KEY);
//        connection.setRequestProperty("Accept", "application/json");

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<Recipe> fetchItem() {

        List<Recipe> recipeList = new ArrayList<>();

        try {
            String url = Uri.parse("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?number=10").buildUpon()
                    .build().toString();
//            String url = Uri.parse("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/search?query=burger").buildUpon()
//                    .build().toString();

            String jsonString = getUrlString(url);
            Log.i(TAG, "fetchItem: Received JSON" + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItem(recipeList, jsonBody);
        } catch (IOException e) {
            Log.e(TAG, "fetchItem: Failed to fetch items", e);
        } catch (JSONException je) {
            Log.e(TAG, "fetchItem: Failed to parse items", je);
        }
        return recipeList;
    }

    private void parseItem(List<Recipe> recipeList, JSONObject baseJsonBody) throws JSONException {

        JSONArray recipeJsonArray = baseJsonBody.getJSONArray("recipes");

        for (int i = 0; i < recipeJsonArray.length(); i++) {

            JSONObject recipeJsonObject = recipeJsonArray.getJSONObject(i);

            Recipe recipe = new Recipe();

            List<String> steps = new ArrayList<>();
            List<String> ingredients = new ArrayList<>();

            recipe.setId(recipeJsonObject.getString("id"));
            recipe.setTitle(recipeJsonObject.getString("title"));
            recipe.setMinutes(recipeJsonObject.getString("readyInMinutes"));
            recipe.setServings(recipeJsonObject.getString("servings"));
            recipe.setImage(recipeJsonObject.getString("image"));
            Log.d(TAG, "parseItem: " + recipe.getImage());
            recipe.setInstruction(recipeJsonObject.getString("instructions"));

            JSONArray analyzedInstructionsJsonArray = recipeJsonObject.getJSONArray("analyzedInstructions");
            for (int j = 0; j < analyzedInstructionsJsonArray.length(); j++) {

                JSONObject analyzedInstructionsJsonObject = analyzedInstructionsJsonArray.getJSONObject(j);
                JSONArray stepsJsonArray = analyzedInstructionsJsonObject.getJSONArray("steps");

                for (int k = 0; k < stepsJsonArray.length(); k++) {
                    JSONObject stepsJsonObject = stepsJsonArray.getJSONObject(k);
                    steps.add(stepsJsonObject.getString("step"));

                    JSONArray ingredientsJsonArray = stepsJsonObject.getJSONArray("ingredients");


                    for (int l = 0; l < ingredientsJsonArray.length(); l++) {
                        JSONObject ingredientsJsonObject = ingredientsJsonArray.getJSONObject(l);
                        ingredients.add(ingredientsJsonObject.getString("name"));

                    }
                }
            }
//            recipe.setSteps(steps);
//            recipe.setIngredients(ingredients);
            recipeList.add(recipe);
        }
    }
}
*/
