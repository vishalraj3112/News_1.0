package com.example.android.news;

/**
 * Created by VIshal on 04-11-2017.
 */

public class NewsFeed {

    private String author,title,des;
    public NewsFeed(String author,String title){
        this.setAuthor(author);
        this.setTitle(title);

    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
