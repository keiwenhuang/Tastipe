package com.example.android.tastipe.Model;
/**
 * Created by kevin on 11/2/18.
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * TODO: Add a class header comment!
 */
public class Ingredients implements Serializable {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
