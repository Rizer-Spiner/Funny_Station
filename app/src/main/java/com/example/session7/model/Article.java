package com.example.session7.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.session7.R;

import java.io.Serializable;

@Entity(tableName = "article_table")
public class Article implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "articleText")
    private String articleText;

    @ColumnInfo(name = "imageSource")
    private int imageSource;
    @ColumnInfo(name = "url")
    private String url;

    public Article() {

    }

    public Article(String articleText, int image, String url) {
        this.articleText = articleText;
        this.imageSource = image;
        this.url = url;
    }

    public Article(String articleText, int image, int id, String url) {
        this.articleText = articleText;
        this.imageSource = image;
        this.id = id;
        this.url = url;
    }


    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public int getImage() {
        return imageSource;
    }

    public void setImage(int image) {
        this.imageSource = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getImageSource() {
        return imageSource;
    }

    public void setImageSource(int imageSource) {
        this.imageSource = imageSource;
    }

    public static Article[] prepopulateArticle()
    {
        return new Article[]{
                new Article("The most beloved Terrorist, Ahmed, is coming to Denmark. Watch some of his most funny moments. ", R.mipmap.ic_ahmed_foreground, "https://www.youtube.com/watch?v=IL357BrwK7c"),
        new Article("Kevin Hart, the most well paid comedian of 2019", R.mipmap.ic_kevin_foreground, "https://www.forbes.com/sites/arielshapiro/2019/08/16/the-highest-earning-stand-up-comedians-of-2019/#283b8c6742fa"),
        new Article("Curious who is the most influential comedian of century? Check this top 50! ", R.mipmap.ic_top_foreground, "https://www.theguardian.com/stage/2019/sep/18/best-comedians-of-the-21st-century-comics-standup-comedy"),
        new Article("You asked for it to happen and it will! Fluffy is rescheduled to come to Denmark next year!", R.mipmap.ic_fluffy_foreground, "https://www.royalarena.dk/event/gabriel-iglesias"),
        new Article("He made us laugh as children. Find out about the true story of our beloved Jim Carrey!", R.mipmap.ic_jim_foreground, "https://ro.wikipedia.org/wiki/Jim_Carrey")
        };
    }

}