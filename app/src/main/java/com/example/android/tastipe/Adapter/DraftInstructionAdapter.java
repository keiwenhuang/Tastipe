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
    public void onBindViewHolder(@NonNull DraftInstructionAdapter.DraftInstructionViewHolder holder, int position) {
        Steps instructions = mInstructionList.get(position);
        holder.bind(instructions);
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
        EditText etInstruction;

        public DraftInstructionViewHolder(View itemView) {
            super(itemView);

//            viewBackground = itemView.findViewById(R.id.view_background);
//            viewForeground = itemView.findViewById(R.id.view_foreground);
//            itemIngredient = itemView.findViewById(R.id.edit_text_item);
//            quantity = itemView.findViewById(R.id.edit_text_quantity);

            ivInstruction = itemView.findViewById(R.id.iv_instruction);
            btnCamera = itemView.findViewById(R.id.btn_camera);
            etInstruction = itemView.findViewById(R.id.et_instruction);
        }

        public void bind(Steps instruction) {
            etInstruction.setText(instruction.getStepNumber() + ". " + instruction.getInstruction());
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
