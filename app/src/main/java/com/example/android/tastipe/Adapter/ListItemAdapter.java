package com.example.android.tastipe.Adapter;
/**
 * Created by kevin on 12/6/18.
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.android.tastipe.Model.Ingredients;
import com.example.android.tastipe.R;

import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder> {
    private static final String TAG = "ListItemAdapter";

    private List<Ingredients> mIngredientsList;

    public ListItemAdapter(List<Ingredients> ingredientsList) {
        mIngredientsList = ingredientsList;
    }

    @NonNull
    @Override
    public ListItemAdapter.ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_ingredient, parent, false);
        ListItemViewHolder viewHolder = new ListItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemAdapter.ListItemViewHolder holder, int position) {
        Ingredients ingredients = mIngredientsList.get(position);
        holder.bind(ingredients);
    }

    @Override
    public int getItemCount() {
        return mIngredientsList.size();
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout viewForeground;
        public RelativeLayout viewBackground;
        public EditText itemIngredient, quantity;

        public ListItemViewHolder(View itemView) {
            super(itemView);

            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
            itemIngredient = itemView.findViewById(R.id.edit_text_item);
            quantity = itemView.findViewById(R.id.edit_text_quantity);
        }

        public void bind(Ingredients ingredients) {
            itemIngredient.setText(ingredients.getName());

            // TODO: set quantity
        }
    }

    public void removeItem(int position) {
        mIngredientsList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Ingredients ingredients, int position) {
        mIngredientsList.add(position, ingredients);
        notifyItemInserted(position);
    }
}
