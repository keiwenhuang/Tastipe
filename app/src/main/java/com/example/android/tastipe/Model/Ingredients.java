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

    public Ingredients(String id) {
        this.id = id;
    }

    public Ingredients(String itemName, String quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
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
}
