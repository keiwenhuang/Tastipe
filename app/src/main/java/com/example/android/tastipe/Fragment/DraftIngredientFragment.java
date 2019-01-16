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
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.android.tastipe.Adapter.DraftIngredientItemAdapter;
import com.example.android.tastipe.Database.RecipeLab;
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
    private RecipeLab mRecipeLab;

    private List<Ingredients> mIngredientsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private DraftIngredientItemAdapter mAdapter;
    private LinearLayout mLinearLayout;

    private ImageView btnAddNewItem;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAddNewItem = view.findViewById(R.id.btn_add);

        mRecipeLab = new RecipeLab(getContext());

        mIngredientsList = mRecipeLab.getIngredients(mRecipe.getId());

        mLinearLayout = view.findViewById(R.id.container);

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new DraftIngredientItemAdapter(mIngredientsList);
        mRecyclerView.setAdapter(mAdapter);

        btnAddNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIngredientsList.add(new Ingredients());
                mAdapter.notifyItemInserted(mIngredientsList.size() - 1);
            }
        });

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
 }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof DraftIngredientItemAdapter.ListItemViewHolder) {

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