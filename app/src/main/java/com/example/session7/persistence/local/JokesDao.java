package com.example.session7.persistence.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.session7.model.Article;
import com.example.session7.model.DBSlideModel;
import com.example.session7.model.Folder;
import com.example.session7.model.JokeWithoutStatus2;
import com.example.session7.model.SavedJoke2;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface JokesDao {

    @Insert
    void insert(Folder folder);

    @Update
    void update(Folder folder);

    @Delete
    void delete(Folder folder);

    @Query("DELETE FROM folder_table;")
    void deleteAllNotes();

    @Query("SELECT * FROM folder_table ORDER BY id;")
    LiveData<List<Folder>> getAllNotes();

    @Insert
    void insertDummyFolders(Folder... folders);

    @Insert(onConflict = REPLACE)
    void insert(JokeWithoutStatus2 joke);

    @Insert
    void insert(SavedJoke2 joke);

    @Insert
    void insertDummyArticles(Article... prepopulateArticle);

    @Insert
    void insertDummySlideModels(DBSlideModel... prepopulateDBSlideModel);

    @Query("Select * From slideModel_table ORDER by id;")
    LiveData<List<DBSlideModel>> getSlideModels();

    @Query("Select * From article_table ORDER By id;")
    LiveData<List<Article>> getArticles();

    @Query("SELECT * FROM joke_table2 where id in (Select jokeid from folder_has_jokes2 where folderid = :arg0);")
    LiveData<List<JokeWithoutStatus2>> getJokes(int arg0);

}
