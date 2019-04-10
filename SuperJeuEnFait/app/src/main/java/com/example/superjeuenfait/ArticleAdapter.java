package com.example.superjeuenfait;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ArticleAdapter extends BaseAdapter {

    List<Article> articles;
    LayoutInflater inflater;

    public ArticleAdapter(Context context, List<Article> articles){
        inflater = LayoutInflater.from(context);
        this.articles = articles;
    }

    public int getCount(){
        return this.articles.size();
    }

    public Object getItem(int position){
        return this.articles.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    private class ViewHolder {
        TextView textViewArticleName;
        TextView textViewArticlePrice;
        ImageView imageViewCoin;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_one_article, null);
            holder.textViewArticleName = (TextView)convertView.findViewById(R.id.textViewArticleName);
            holder.textViewArticlePrice = (TextView)convertView.findViewById(R.id.textViewArticlePrice);
            holder.imageViewCoin = (ImageView)convertView.findViewById(R.id.imageViewCoin);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.textViewArticleName.setText(articles.get(position).getName());
        if(articles.get(position).getPrice()>0){
            holder.textViewArticlePrice.setText(String.valueOf(articles.get(position).getPrice()));
            holder.imageViewCoin.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
}
