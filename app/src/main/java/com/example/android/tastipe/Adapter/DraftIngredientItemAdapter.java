package com.example.android.tastipe.Adapter;
/**
 * Created by kevin on 12/6/18.
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
public class DraftIngredientItemAdapter extends RecyclerView.Adapter<DraftIngredientItemAdapter.ListItemViewHolder> {
    private static final String TAG = "DraftIngredientItemAdapter";

    private List<Ingredients> mIngredientsList;

    public DraftIngredientItemAdapter(List<Ingredients> ingredientsList) {
        mIngredientsList = ingredientsList;
    }

    @NonNull
    @Override
    public DraftIngredientItemAdapter.ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_ingredient, parent, false);
        ListItemViewHolder viewHolder = new ListItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DraftIngredientItemAdapter.ListItemViewHolder holder, final int position) {
        final Ingredients ingredient = mIngredientsList.get(position);

        holder.editTextItem.setText(ingredient.getItemName());
        holder.editTextQuantity.setText(ingredient.getQuantity());

        holder.editTextItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ingredient.setItemName(s.toString());
            }
        });
        holder.editTextQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ingredient.setQuantity(s.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mIngredientsList.size();
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout viewForeground;
        public RelativeLayout viewBackground;
        public EditText editTextItem, editTextQuantity;

        public ListItemViewHolder(View itemView) {
            super(itemView);

            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
            editTextItem = itemView.findViewById(R.id.edit_text_item);
            editTextQuantity = itemView.findViewById(R.id.edit_text_quantity);
        }

//        public void bind(Ingredients ingredients) {
//            editTextItem.setText(ingredients.getItemName());
//
//            // TODO: set quantity
//        }
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
