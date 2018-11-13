package com.example.android.tastipe.Fragment;

/**
 * Created by Kevin on 6/14/2018
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.tastipe.Model.Article;
import com.example.android.tastipe.R;
import com.example.android.tastipe.Adapter.ArticleAdapter;

import java.util.ArrayList;

/**
 * TODO: Setup a list of Article object
 * TODO: ArticleAdapter
 */
public class ArticleListFragment extends DefaultFragment {

    private static final String TAG = "ArticleListFragment";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected int layoutRes() {
        return R.layout.recycler_list_search;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<Article> articles = new ArrayList<>();

        articles.add(new Article("Hell's Kitchen", "Gordon Ramsey", "https://i.redd.it/vbsj3xzji0811.jpg", false, false));
        articles.add(new Article("Hell's Kitchen", "Gordon Ramsey", "https://i.redd.it/vbsj3xzji0811.jpg", false, false));
        articles.add(new Article("Hell's Kitchen", "Gordon Ramsey", "https://i.redd.it/vbsj3xzji0811.jpg", false, false));
        articles.add(new Article("Hell's Kitchen", "Gordon Ramsey", "https://i.redd.it/vbsj3xzji0811.jpg", false, false));
        articles.add(new Article("Hell's Kitchen", "Gordon Ramsey", "https://i.redd.it/vbsj3xzji0811.jpg", false, false));
        articles.add(new Article("Hell's Kitchen", "Gordon Ramsey", "https://i.redd.it/vbsj3xzji0811.jpg", false, false));

        mAdapter = new ArticleAdapter(getActivity(), articles);
        mRecyclerView.setAdapter(mAdapter);
    }
}
