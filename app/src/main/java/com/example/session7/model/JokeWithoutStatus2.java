package com.example.session7.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.session7.ui.jokes.JokeActivity2;

@Entity(tableName = "joke_table2")
public class JokeWithoutStatus2 {

    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    private String id;
    @ColumnInfo(name = "joke")
    private String joke;

    public JokeWithoutStatus2()
    {

    }

    public JokeWithoutStatus2(String id, String joke)
    {
        this.id = id;
        this.joke = joke;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
