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
import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.Database.RecipeLab;
import com.example.android.tastipe.R;
import com.example.android.tastipe.Utils.AppConfig;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private static final String TAG = "ListAdapter";

    private List<Recipe> mRecipeList;
    private int fragment;
    private Callback callback;

    public ListAdapter() {
        mRecipeList = Collections.emptyList();
    }

    public ListAdapter(List<Recipe> recipeList, int fragment) {
        mRecipeList = recipeList;
        this.fragment = fragment;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        mRecipeList = recipeList;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (fragment == AppConfig.ListType.RECIPE_LIST) {
            view = inflater.inflate(R.layout.card_recipe, parent, false);
        } else if (fragment == AppConfig.ListType.FAVORITE_LIST) {
            view = inflater.inflate(R.layout.card_favorite, parent, false);
        }

        final ListViewHolder viewHolder = new ListViewHolder(view);
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
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Recipe recipe = mRecipeList.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        private Recipe mRecipe;
        private TextView recipeTitle;
        private ImageView recipeImage, favButton;
        private View contentLayout;

        public ListViewHolder(View itemView) {
            super(itemView);

            recipeTitle = itemView.findViewById(R.id.recipe_title);
            recipeImage = itemView.findViewById(R.id.recipe_image);
            favButton = itemView.findViewById(R.id.fav_button);
            contentLayout = itemView.findViewById(R.id.layout_content);
        }

        public void bind(final Recipe recipe) {
            final RecipeLab recipeLab = new RecipeLab(itemView.getContext());

            mRecipe = recipe;
            recipeTitle.setText(recipe.getTitle());

            Uri uri = Uri.parse(recipe.getImage());
            Picasso.get().load(uri).into(recipeImage);

            favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!recipeLab.isFavorite(recipe)) {

                        Log.d(TAG, "onClick: save" + recipe.getId());

                        recipeLab.addFavorite(recipe, DbHelper.TableType.FAVORITE_TABLE);
                        favButton.setImageResource(R.drawable.ic_heart_red);
                        Toast.makeText(view.getContext(), mRecipe.getTitle() + " saved to favorite!", Toast.LENGTH_SHORT).show();


                    } else {
                        recipeLab.removeFromFavorites(recipe.getId(), DbHelper.TableType.FAVORITE_TABLE);
                        favButton.setImageResource(R.drawable.ic_heart_white);
                        Toast.makeText(view.getContext(), mRecipe.getTitle() + " was removed from favorites.", Toast.LENGTH_SHORT).show();

                        Log.d(TAG, "onClick: " + recipe.getId());
                    }
                }
            });
        }
    }

    public interface Callback {
        void onItemClick(Recipe recipe);
    }
}