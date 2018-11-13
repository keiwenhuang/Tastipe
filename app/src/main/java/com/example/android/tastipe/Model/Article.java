package com.example.android.tastipe.Model;

/**
 * Created by Kevin on 6/28/2018
 */

/**
 * TODO: Add a class header comment!
 */
public class Article {

    String title, author, imgUrl;
    boolean favorite, recipe;

    public Article(String title, String author, String imgUrl, boolean favorite, boolean recipe) {
        this.title = title;
        this.author = author;
        this.imgUrl = imgUrl;
        this.favorite = favorite;
        this.recipe = recipe;
    }

    public Article() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isRecipe() {
        return recipe;
    }

    public void setRecipe(boolean recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", favorite=" + favorite +
                ", recipe=" + recipe +
                '}';
    }
}
