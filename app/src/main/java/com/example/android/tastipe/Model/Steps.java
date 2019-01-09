package com.example.android.tastipe.Model;
/**
 * Created by kevin on 11/2/18.
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * TODO: Add a class header comment!
 */
public class Steps implements Serializable {

    @SerializedName("number")
    private String stepNumber;
    @SerializedName("step")
    private String instruction;
    @SerializedName("ingredients")
    private ArrayList<Ingredients> ingredients;

    private String id;

    public Steps(String id) {
        this.id = id;
    }

    public Steps() {
    }

    public Steps(String stepNumber, String instruction) {
        this.stepNumber = stepNumber;
        this.instruction = instruction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(String stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return stepNumber + ". " + instruction;
    }
}
