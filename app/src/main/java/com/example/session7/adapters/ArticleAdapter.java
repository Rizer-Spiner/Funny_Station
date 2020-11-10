package com.example.session7.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.session7.R;
import com.example.session7.model.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter {
    final private ArticleAdapter.OnListItemClickListener3 onListItemClickListener;
    private List<Article> articles;

    public ArticleAdapter(List<Article> articles, ArticleAdapter.OnListItemClickListener3 onListItemClickListener) {
        super();
        this.articles = articles;
        this.onListItemClickListener = onListItemClickListener;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.article_list_item, parent, false);

        return new ViewHolder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ArticleAdapter.ViewHolder3 holder1 = (ArticleAdapter.ViewHolder3) holder;

        holder1.name.setText(articles.get(position).getArticleText());
        holder1.icon.setImageResource(articles.get(position).getImage());

    }

    @Override
    public int getItemCount() { return articles.size(); }

    public Article getArticle(int position) {
        return articles.get(position);
    }


    public interface OnListItemClickListener3 {

        void onListItemClick(int adapterPosition);
    }

    class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        ImageView icon;

        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.article_text);
            icon = itemView.findViewById(R.id.article_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
}
