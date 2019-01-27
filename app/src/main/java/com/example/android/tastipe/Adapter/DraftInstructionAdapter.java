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
import android.widget.ImageView;

import com.example.android.tastipe.Model.Steps;
import com.example.android.tastipe.R;

import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class DraftInstructionAdapter extends RecyclerView.Adapter<DraftInstructionAdapter.DraftInstructionViewHolder> {
    private static final String TAG = "DraftIngredientItemAdapter";

    private List<Steps> mInstructionList;

    public DraftInstructionAdapter(List<Steps> instructionList) {
        mInstructionList = instructionList;
    }

    @NonNull
    @Override
    public DraftInstructionAdapter.DraftInstructionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_instruction, parent, false);
        DraftInstructionViewHolder viewHolder = new DraftInstructionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DraftInstructionAdapter.DraftInstructionViewHolder holder, final int position) {
        final Steps instructions = mInstructionList.get(position);

        holder.editTextInstruction.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int stepNumber = position + 1;

                instructions.setStepNumber(String.valueOf(stepNumber));
                instructions.setInstruction(s.toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mInstructionList.size();
    }

    public class DraftInstructionViewHolder extends RecyclerView.ViewHolder {

//        public LinearLayout viewForeground;
//        public RelativeLayout viewBackground;
//        public EditText itemIngredient, quantity;

        ImageView ivInstruction, btnCamera;
        EditText editTextInstruction;

        public DraftInstructionViewHolder(View itemView) {
            super(itemView);

//            viewBackground = itemView.findViewById(R.id.view_background);
//            viewForeground = itemView.findViewById(R.id.view_foreground);
//            itemIngredient = itemView.findViewById(R.id.edit_text_item);
//            quantity = itemView.findViewById(R.id.edit_text_quantity);

            ivInstruction = itemView.findViewById(R.id.iv_instruction);
            btnCamera = itemView.findViewById(R.id.btn_camera);
            editTextInstruction = itemView.findViewById(R.id.et_instruction);
        }
    }

//    public void removeItem(int position) {
//        mIngredientsList.remove(position);
//        notifyItemRemoved(position);
//    }
//
//    public void restoreItem(Ingredients ingredients, int position) {
//        mIngredientsList.add(position, ingredients);
//        notifyItemInserted(position);
//    }
}
