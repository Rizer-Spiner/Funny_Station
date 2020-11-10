package com.example.session7.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "folder_has_jokes2")
public class SavedJoke2 {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "folderId")
    @ForeignKey(entity = Folder.class, parentColumns = "id", childColumns = "folderId", onDelete = CASCADE)
    private int folderId;

    @ColumnInfo(name = "jokeId")
    @ForeignKey(entity = JokeWithoutStatus2.class, parentColumns = "id", childColumns = "jokes", onDelete = CASCADE)
    private String jokeId;

    public SavedJoke2()
    {

    }

    public SavedJoke2(int folderId, String jokeId)
    {
        this.folderId = folderId;
        this.jokeId = jokeId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public String getJokeId() {
        return jokeId;
    }

    public void setJokeId(String jokeId) {
        this.jokeId = jokeId;
    }
}
