package com.example.android.tastipe.Fragment;
/**
 * Created by kevin on 11/10/18.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.android.tastipe.Adapter.ListItemAdapter;
import com.example.android.tastipe.Model.Ingredients;
import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.R;
import com.example.android.tastipe.Utils.RecyclerItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class DraftIngredientFragment extends DefaultFragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private Recipe mRecipe;

    private RecyclerView mRecyclerView;
    private List<Ingredients> mIngredientsList = new ArrayList<>();
    private ListItemAdapter mAdapter;
    private LinearLayout mLinearLayout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mIngredientsList.add(new Ingredients("Apple", "2"));
        mIngredientsList.add(new Ingredients("Onion", "0.5"));
        mIngredientsList.add(new Ingredients("Bay Leaf", "5"));
        mIngredientsList.add(new Ingredients("Beef", "1"));

        mLinearLayout = view.findViewById(R.id.container);

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new ListItemAdapter(mIngredientsList);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
 }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof ListItemAdapter.ListItemViewHolder) {

            String name = mIngredientsList.get(viewHolder.getAdapterPosition()).getItemName();

            final Ingredients deletedItem = mIngredientsList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            mAdapter.removeItem(viewHolder.getAdapterPosition());

            Snackbar snackbar = Snackbar.make(mLinearLayout, name + " remove from list!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
    public void putArguments(Recipe recipe) {
        mRecipe = recipe;
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_draft_ingredient;
    }


}