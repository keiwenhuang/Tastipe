package com.example.android.tastipe.Utils;

/**
 * Created by Kevin on 6/21/2018
 */

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.example.android.tastipe.Activity.CookbookListActivity;
import com.example.android.tastipe.Activity.HomeActivity;
import com.example.android.tastipe.Activity.SearchActivity;
import com.example.android.tastipe.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

/**
 * TODO: Add a class header comment!
 */
public class NavigationViewHelper {

    private static final String TAG = "NavigationViewHelper";

    public static void enableNavigation(final Context context, BottomNavigationViewEx view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_home:
                        Intent intent1 = new Intent(context, HomeActivity.class);
                        context.startActivity(intent1);
                        break;
                    case R.id.ic_search:
                        Intent intent2 = new Intent(context, SearchActivity.class);
                        context.startActivity(intent2);
                        break;
                    case R.id.ic_cookbook:
                        Intent intent3 = new Intent(context, CookbookListActivity.class);
                        context.startActivity(intent3);
                        break;
                }
                return false;
            }
        });
    }
}