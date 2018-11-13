package com.example.android.tastipe.Adapter;

/**
 * Created by Kevin on 7/19/2018
 */

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.tastipe.R;

/**
 * TODO: Add a class header comment!
 */
public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {

    private String[] filters;

    public FilterAdapter(String[] filters) {
        this.filters = filters;
    }

    @NonNull
    @Override
    public FilterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_filter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterAdapter.ViewHolder holder, int position) {
        holder.filter.setText(filters[position]);
    }

    @Override
    public int getItemCount() {
        return filters.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView filter;

        public ViewHolder(View textView) {
            super(textView);

            filter = (TextView) textView.findViewById(R.id.filter);
        }
    }
}
