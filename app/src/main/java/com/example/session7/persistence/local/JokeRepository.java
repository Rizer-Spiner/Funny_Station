package com.example.session7.persistence.local;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import com.example.session7.model.Article;
import com.example.session7.model.DBSlideModel;
import com.example.session7.model.Folder;
import com.example.session7.model.JokeWithoutStatus2;
import com.example.session7.model.SavedJoke2;

import java.util.List;

public class JokeRepository {

    private static JokeRepository instance;
    private JokesDao jokesDao;
    private Application application;

    private JokeRepository(Application application) {
        this.application = application;
        JokesDatabase database = JokesDatabase.getInstance(application);
        jokesDao = database.jokesDao();

    }

    public static synchronized JokeRepository getInstance(Application application) {
        if (instance == null)
            instance = new JokeRepository(application);

        return instance;
    }

    public LiveData<List<Folder>> getNotes() {
        return jokesDao.getAllNotes();
    }



    public void insert(Folder folder) {
        new InsertNoteAsync(jokesDao).execute(folder);
    }

    public void deleteNotes() {
        new DeleteNotesAsync(jokesDao).execute();

    }

    public void deleteFolder(Folder folder)
    {
        new DeleteFolderAsync(jokesDao).execute(folder);
    }

    public void saveJoke(SavedJoke2 savedJoke) {
        new InsertSavedJokeAsync(jokesDao).execute(savedJoke);
    }

    public void saveJoke(JokeWithoutStatus2 joke) {
        new InsertNewJokeAsync(jokesDao).execute(joke);
    }

    public LiveData<List<DBSlideModel>> getSlideModels() {
        return jokesDao.getSlideModels();
    }

    public LiveData<List<Article>> getArticles() {
        return jokesDao.getArticles();
    }

    public LiveData<List<JokeWithoutStatus2>> getJokes(int folderId) {
        return jokesDao.getJokes(folderId);
    }

    private static class InsertNoteAsync extends AsyncTask<Folder, Void, Void> {
        private JokesDao jokesDao;

        private InsertNoteAsync(JokesDao jokesDao) {
            this.jokesDao = jokesDao;
        }

        @Override
        protected Void doInBackground(Folder... folders) {
            jokesDao.insert(folders[0]);
            return null;
        }
    }

    private static class InsertNewJokeAsync extends AsyncTask<JokeWithoutStatus2, Void, Void> {
        private JokesDao jokesDao;

        private InsertNewJokeAsync(JokesDao jokesDao) {
            this.jokesDao = jokesDao;
        }

        @Override
        protected Void doInBackground(JokeWithoutStatus2... jokes) {
            jokesDao.insert(jokes[0]);
            return null;
        }
    }

    private static class InsertSavedJokeAsync extends AsyncTask<SavedJoke2, Void, Void> {
        private JokesDao jokesDao;

        private InsertSavedJokeAsync(JokesDao jokesDao) {
            this.jokesDao = jokesDao;
        }

        @Override
        protected Void doInBackground(SavedJoke2... jokes) {
            jokesDao.insert(jokes[0]);
            return null;
        }
    }


    private static class DeleteNotesAsync extends AsyncTask<Folder, Void, Void> {
        private JokesDao jokesDao;

        private DeleteNotesAsync(JokesDao jokesDao) {
            this.jokesDao = jokesDao;
        }

        @Override
        protected Void doInBackground(Folder... folders) {
            jokesDao.deleteAllNotes();
            return null;
        }
    }


    private static class DeleteFolderAsync extends AsyncTask<Folder, Void, Void> {
        private JokesDao jokesDao;
        private DeleteFolderAsync(JokesDao jokesDao) {
            this.jokesDao = jokesDao;
        }

        @Override
        protected Void doInBackground(Folder... folders) {
            jokesDao.delete(folders[0]);
            return null;
        }
    }


}

