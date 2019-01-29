package com.example.android.tastipe.Adapter;
/**
 * Created by kevin on 10/11/18.
 */

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tastipe.Database.DbHelper;
import com.example.android.tastipe.Database.RecipeLab;
import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class CookbookListAdapter extends RecyclerView.Adapter<CookbookListAdapter.CookbookListViewHolder> {
    private static final String TAG = "CookbookListAdapter";

    private List<Recipe> mRecipeList;
    private Callback callback;

    public CookbookListAdapter() {
        mRecipeList = Collections.emptyList();
    }

    public CookbookListAdapter(List<Recipe> recipeList) {
        mRecipeList = recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        mRecipeList = recipeList;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public CookbookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_cookbook, parent, false);

        final CookbookListViewHolder viewHolder = new CookbookListViewHolder(view);
        viewHolder.contentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onItemClick(viewHolder.mRecipe);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CookbookListViewHolder holder, int position) {
        Recipe recipe = mRecipeList.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public class CookbookListViewHolder extends RecyclerView.ViewHolder {

        private Recipe mRecipe;
        private TextView textViewTitle, editedTime;
        private ImageView deleteButton, imageRecipe;
        private View contentLayout;

        public CookbookListViewHolder(View itemView) {
            super(itemView);

            contentLayout = itemView.findViewById(R.id.layout_content);
            textViewTitle = itemView.findViewById(R.id.recipe_title);
            editedTime = itemView.findViewById(R.id.last_edited_time);
            deleteButton = itemView.findViewById(R.id.delete_button);
            imageRecipe = itemView.findViewById(R.id.recipe_image);
            contentLayout = itemView.findViewById(R.id.layout_content);
        }

        public void bind(final Recipe recipe) {
            final RecipeLab recipeLab = new RecipeLab(itemView.getContext());

            mRecipe = recipe;
            textViewTitle.setText(recipe.getTitle());

            Uri uri = Uri.parse(recipe.getImage());
            Picasso.get().load(uri).into(imageRecipe);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Cookbook Deleted.", Toast.LENGTH_SHORT).show();
                    recipeLab.removeFromFavorites(recipe.getId(), DbHelper.TableType.COOKBOOK_TABLE);
                }
            });
        }
    }

    public interface Callback {
        void onItemClick(Recipe recipe);
    }
}