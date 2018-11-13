package com.example.android.tastipe.Activity;

/**
 * Created by Kevin on 9/12/2018
 */

import android.support.v4.app.Fragment;

import com.example.android.tastipe.Fragment.CartFragment;
import com.example.android.tastipe.R;

/**
 * TODO: Add a class header comment!
 */
public class CartActivity extends DefaultActivity {
    private static final String TAG = "CartActivity";

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_grocery_cart;
    }

    @Override
    protected boolean setupFragment() {
        return true;
    }

    @Override
    protected Fragment createInitialFragment() {
        return new CartFragment();
    }

    @Override
    protected boolean setupNavigation() {
        return false;
    }

    @Override
    protected int getActivityNum() {
        return 0;
    }


}
