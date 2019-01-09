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
    private String itemName;

    private String id;
    private String quantity;

    public Ingredients(String itemName, String quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public Ingredients(String itemName) {
        this.itemName = itemName;
    }

    public Ingredients() {

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return itemName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() == this.getClass()) {
            Ingredients ingredients = (Ingredients) obj;
            return  ingredients.getItemName().equals((getItemName()));
        } else {
            return false;
        }
    }
}
