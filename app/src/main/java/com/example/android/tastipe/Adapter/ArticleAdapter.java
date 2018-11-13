package com.example.android.tastipe.Adapter;

/**
 * Created by Kevin on 7/3/2018
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tastipe.Model.Article;
import com.example.android.tastipe.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * TODO: Add a class header comment!
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Article> articles;

    private static final int RECIPE = 0, ARTICLE = 1;


    public ArticleAdapter(Context context, ArrayList<Article> articles) {
        mContext = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case ARTICLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_article, parent, false);
                break;

            case RECIPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_recipe, parent, false);
                break;
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {
        Article article = articles.get(position);

        holder.author.setText(article.getAuthor());
        holder.title.setText(article.getTitle());

        String imgUrl = articles.get(position).getImgUrl();
        Picasso.get().load(imgUrl).into(holder.articleImage);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (articles.get(position).isRecipe()) {
            return RECIPE;
        } else {
            return ARTICLE;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, author;
        private ImageView articleImage, favoriteBtn;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            author = (TextView) itemView.findViewById(R.id.author);
            articleImage = (ImageView) itemView.findViewById(R.id.article_image);
//            favoriteBtn = (ImageView) itemView.findViewById(R.id.image_heart);
        }
    }
}
