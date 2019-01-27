package com.example.android.tastipe.Fragment;
/**
 * Created by kevin on 11/10/18.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.android.tastipe.Adapter.DraftInstructionAdapter;
import com.example.android.tastipe.Database.DbHelper;
import com.example.android.tastipe.Database.RecipeLab;
import com.example.android.tastipe.Model.Recipe;
import com.example.android.tastipe.Model.Steps;
import com.example.android.tastipe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class DraftInstructionFragment extends DefaultFragment {

    private Recipe mRecipe;
    private RecipeLab mRecipeLab;

    private RecyclerView mRecyclerView;
    private List<Steps> mInstructionList = new ArrayList<>();
    private DraftInstructionAdapter mAdapter;
//    private LinearLayout mLinearLayout;

    private DraftInstructionCallback mCallback;

    private ImageView btnAddNewItem;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAddNewItem = view.findViewById(R.id.btn_add);

        mRecipeLab = new RecipeLab(getContext());
        mInstructionList = mRecipeLab.getInstructions(mRecipe.getId(), DbHelper.TableType.FAVORITE_TABLE);

//        mLinearLayout = view.findViewById(R.id.container);

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new DraftInstructionAdapter(mInstructionList);
        mRecyclerView.setAdapter(mAdapter);

        btnAddNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInstructionList.add(new Steps());
                mAdapter.notifyItemInserted(mInstructionList.size() - 1);
            }
        });

        mCallback.sendInstruction(mInstructionList);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mCallback = (DraftInstructionCallback) context;

    }

    public void putArguments(Recipe recipe) {
        mRecipe = recipe;
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_draft_instruction;
    }

    public interface DraftInstructionCallback {
        void sendInstruction(List<Steps> instructions);
    }
}
