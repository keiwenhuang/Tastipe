package com.example.android.tastipe.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tastipe.Adapter.PagerAdapter;
import com.example.android.tastipe.Fragment.ArticleListFragment;
import com.example.android.tastipe.Fragment.RecipeListFragment;
import com.example.android.tastipe.Fragment.TipListFragment;
import com.example.android.tastipe.Database.RecipeLab;
import com.example.android.tastipe.R;
import com.example.android.tastipe.Utils.NavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private Context mContext = HomeActivity.this;
    private final int ACTIVITY_NUM = 0;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_drawer);

        setupViewPager();
        setupNavigationView();
        setupToolbar();
        setupDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager() {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new RecipeListFragment()); //index 0
        adapter.addFragment(new ArticleListFragment()); //index 1
        adapter.addFragment(new TipListFragment()); //index 2

        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText(R.string.recipes);
        tabLayout.getTabAt(1).setText(R.string.articles);
        tabLayout.getTabAt(2).setText(R.string.cooking_tips);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    private void setupNavigationView() {
        Log.d(TAG, "setupNavigationView: setting up navigation view");

        BottomNavigationViewEx navigationViewEx = (BottomNavigationViewEx) findViewById(R.id.navigation_view_bar);
        NavigationViewHelper.enableNavigation(mContext, navigationViewEx);
        Menu menu = navigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    private void setupDrawer() {
        mDrawerLayout = findViewById(R.id.drawer_layout);

        final TextView about = findViewById(R.id.about);
        final TextView contact = findViewById(R.id.contact);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.getBackground().setAlpha(215);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        selectDrawerItem(menuItem);

                        about.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext, "about", Toast.LENGTH_SHORT).show();
                                mDrawerLayout.closeDrawers();
                            }
                        });

                        contact.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext, "contact", Toast.LENGTH_SHORT).show();
                                mDrawerLayout.closeDrawers();
                            }
                        });

                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                }
        );

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(@NonNull View view, float v) {

                    }

                    @Override
                    public void onDrawerOpened(@NonNull View view) {

                    }

                    @Override
                    public void onDrawerClosed(@NonNull View view) {

                    }

                    @Override
                    public void onDrawerStateChanged(int i) {

                    }
                }
        );
    }

    private void selectDrawerItem(MenuItem menuItem) {

        RecipeLab recipeLab = new RecipeLab(getApplicationContext());

        switch (menuItem.getItemId()) {
            case R.id.hearts:
                Toast.makeText(mContext, "hearts", Toast.LENGTH_SHORT).show();
                Intent heartsIntent = new Intent(this, FavoriteListActivity.class);
//                Log.d(TAG, "selectDrawerItem: " + recipeLab.getRecipes());
                startActivity(heartsIntent);
                break;
            case R.id.recipe_drafts:
                Toast.makeText(mContext, "drafts", Toast.LENGTH_SHORT).show();
                Intent draftIntent = new Intent(this, DraftsActivity.class);
                startActivity(draftIntent);
                break;
            case R.id.grocery_cart:
                Toast.makeText(mContext, "cart", Toast.LENGTH_SHORT).show();
                Intent cartIntent = new Intent(this, CartActivity.class);
                startActivity(cartIntent);
                break;
            default:
        }
    }
}
