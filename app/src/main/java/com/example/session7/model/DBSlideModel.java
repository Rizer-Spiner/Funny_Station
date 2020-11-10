package com.example.session7.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.session7.R;

@Entity(tableName = "slideModel_table")
public class DBSlideModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "imageResource")
    private int imageResource;
    @ColumnInfo(name = "title")
    private String title;

    public DBSlideModel(){

    }

    public DBSlideModel( int imageResource, String title) {

        this.imageResource = imageResource;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public static DBSlideModel[] prepopulateDBSlideModel()
    {
        return new DBSlideModel[]{
                new DBSlideModel(R.mipmap.ic_cat_meme_foreground, ""),
                new DBSlideModel(R.mipmap.ic_2020be_like_foreground, ""),
                new DBSlideModel(R.mipmap.ic_cat_meme_foreground, ""),
                new DBSlideModel(R.mipmap.ic_corona_foreground, ""),
                new DBSlideModel(R.mipmap.ic_quarantine_foreground, ""),
                new DBSlideModel(R.mipmap.ic_microphone_foreground, "")
        };
    }
}