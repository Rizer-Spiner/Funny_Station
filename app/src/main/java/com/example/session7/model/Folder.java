package com.example.session7.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "folder_table")
public class Folder {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "folderName")
    private String folderName;

    public Folder()
    {

    }

    public Folder(String content)
    {
        this.folderName = content;
    }


    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String content) {
        this.folderName = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public static Folder[] populateFolder() {
        return new Folder[]{
                new Folder("Favorites"),
                new Folder("Demo")
        };
    }
}
