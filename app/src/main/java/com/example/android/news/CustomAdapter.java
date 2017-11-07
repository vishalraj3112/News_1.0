package com.example.android.news;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VIshal on 04-11-2017.
 */

public class CustomAdapter extends ArrayAdapter {
    List list=new ArrayList();


    public CustomAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public void add(NewsFeed object){
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount(){
        return list.size();

    }

    @Override
    public Object getItem(int position){
        return list.get(position);

    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row=convertView;

        NewsHolder newsHolder;
        if (row==null){
            LayoutInflater layoutInflater=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row =layoutInflater.inflate(R.layout.row_layout,parent,false);
            newsHolder=new NewsHolder();
            newsHolder.tx_author=(TextView) row.findViewById(R.id.author);
            newsHolder.tx_title=(TextView) row.findViewById(R.id.title);

            row.setTag(newsHolder);
        }else{
            newsHolder=(NewsHolder) row.getTag();
        }
        NewsFeed newsFeed=(NewsFeed) this.getItem(position);
        newsHolder.tx_author.setText(newsFeed.getAuthor());
        newsHolder.tx_title.setText(newsFeed.getTitle());


        return row;
    }
    static class NewsHolder{
        TextView tx_author,tx_title;

    }
}
