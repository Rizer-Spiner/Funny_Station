package com.example.session7.persistence.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.session7.model.Article;
import com.example.session7.model.DBSlideModel;
import com.example.session7.model.Folder;
import com.example.session7.model.JokeWithoutStatus2;
import com.example.session7.model.SavedJoke2;

import java.util.concurrent.Executors;

@Database(entities = {Folder.class, Article.class, DBSlideModel.class, JokeWithoutStatus2.class, SavedJoke2.class}, version = 1)
public abstract class JokesDatabase extends RoomDatabase {

    private static JokesDatabase instance;

    public static synchronized JokesDatabase getInstance(final Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), JokesDatabase.class, "localStorage")
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    getInstance(context.getApplicationContext()).jokesDao().insertDummyFolders(Folder.populateFolder());
                                }
                            });
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    getInstance(context.getApplicationContext()).jokesDao().insertDummyArticles(Article.prepopulateArticle());
                                }
                            });
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    getInstance(context.getApplicationContext()).jokesDao().insertDummySlideModels(DBSlideModel.prepopulateDBSlideModel());
                                }
                            });
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract JokesDao jokesDao();
}
