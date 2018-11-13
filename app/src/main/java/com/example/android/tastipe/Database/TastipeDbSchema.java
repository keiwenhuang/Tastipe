package com.example.android.tastipe.Database;

/**
 * Created by Kevin on 9/20/2018
 */

public class TastipeDbSchema {
    public static final class TastipeTable {
        public static final String NAME = "tastipe";

        public static final class Cols {
            public static final String ID = "id";
            public static final String TITLE = "title";
            public static final String MINUTES = "minutes";
            public static final String SERVINGS = "servings";
            public static final String IMAGE = "image";
            public static final String INSTRUCTION = "instructions";
            public static final String INGREDIENTS = "ingredients";
        }
    }
}
