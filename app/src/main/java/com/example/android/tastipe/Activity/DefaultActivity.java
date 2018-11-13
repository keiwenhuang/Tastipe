package com.example.android.tastipe.Activity;

/**
 * Created by Kevin on 9/25/2018
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.tastipe.R;
import com.example.android.tastipe.Utils.NavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

/**
 * TODO: Add a class header comment!
 */
public abstract class DefaultActivity extends AppCompatActivity {
    private static final String TAG = "DefaultActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentViewLayoutRes());
        if (setupFragment()) {
            setupInitialFragment(savedInstanceState);
        }
        if (setupNavigation()) {
            setupNavigationView(getActivityNum());
        }
    }

    private void setupInitialFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, createInitialFragment())
                    .commit();
        }
    }

    private void setupNavigationView(int activity_num) {
        Log.d(TAG, "setupNavigationView: setting up navigation view");

        BottomNavigationViewEx navigationViewEx = (BottomNavigationViewEx) findViewById(R.id.navigation_view_bar);
        NavigationViewHelper.enableNavigation(getApplicationContext(), navigationViewEx);
        Menu menu = navigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(activity_num);
        menuItem.setChecked(true);
    }

    protected abstract Fragment createInitialFragment();

    protected abstract int getActivityNum();

    protected abstract boolean setupNavigation();

    protected abstract boolean setupFragment();

    protected abstract int contentViewLayoutRes();
}
