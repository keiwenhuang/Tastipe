package com.example.android.tastipe.Model;

/**
 * Created by Kevin on 8/3/2018
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {

    /**
     * json
     */
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("readyInMinutes")
    private String minutes;
    @SerializedName("servings")
    private String servings;
    @SerializedName("image")
    private String image;
    // "Fabulous-Un-fried-Falafel-Burgers-681837.jpg" -----> search
    // "https://spoonacular.com/recipeImages/701874-556x370." -----> random
    @SerializedName("analyzedInstructions")
    private ArrayList<AnalyzedInstructions> analyzedInstructions;

    /**
     * fetch from api and save to the following
     */
    private String instructions;
    private String ingredients;

    public Recipe() {
    }

    public Recipe(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<AnalyzedInstructions> getAnalyzedInstructions() {
        return analyzedInstructions;
    }

    public void setAnalyzedInstructions(ArrayList<AnalyzedInstructions> analyzedInstructions) {
        this.analyzedInstructions = analyzedInstructions;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    //    public List<String> getSteps() {
//        return steps;
//    }
//
//    public void setSteps(List<String> steps) {
//        this.steps = steps;
//    }
//
//    public List<String> getIngredients() {
//        return ingredients;
//    }
//
//    public void setIngredients(List<String> ingredients) {
//        this.ingredients = ingredients;
//    }

/*
    id:589724
    title:"Red Lentil, Smashed Chickpea and Millet Burgers"
    readyInMinutes:80
    servings:7
    image:"Red-Lentil--Smashed-Chickpea-and-Millet-Burgers-589724.jpg"

    "steps": [
        {
          "number": 1,
          "step": "In a small saucepan, combine the lentils with 1 cup water. Bring to a boil, reduce to a simmer, and cook for 10-15 minutes, until softened.",
          "ingredients": [
            {
              "id": 16069,

              "name": "lentils",
              "image": "lentils-brown.jpg"
            }
          ],
          "equipment": [
            {
              "id": 404669,
              "name": "sauce pan",
              "image": "sauce-pan.jpg"
            }
          ],
          "length": {
            "number": 15,
            "unit": "minutes"
          }
        },
     */
}