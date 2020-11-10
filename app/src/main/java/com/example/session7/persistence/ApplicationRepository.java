package com.example.session7.persistence;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.denzcoskun.imageslider.models.SlideModel;
import com.example.session7.R;
import com.example.session7.model.Article;
import com.example.session7.model.Folder;
import com.example.session7.model.JokeWithoutStatus2;
import com.example.session7.model.SavedJoke2;
import com.example.session7.persistence.local.JokeRepository;
import com.example.session7.persistence.remote.RemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class ApplicationRepository implements FolderRepository, GetMeAJokeRepository, HomeRepository, com.example.session7.persistence.JokeRepository {

    private JokeRepository localStorage;
    private RemoteDataSource remoteDataSource;
    private static ApplicationRepository instance;

    private ApplicationRepository(Application application)
    {
        remoteDataSource = new RemoteDataSource();
        localStorage = JokeRepository.getInstance(application);
    }

    public synchronized static ApplicationRepository getInstance(Application application)
    {
        if (instance == null)
        {
            instance = new  ApplicationRepository(application);
        }
        return instance;
    }

    @Override
    public LiveData<List<Folder>> getFolders() {
        return localStorage.getNotes();
    }

    @Override
    public void addFolder(Folder folder) {
        localStorage.insert(folder);
    }

    @Override
    public void deleteFolder(Folder folder) {
        localStorage.deleteFolder(folder);
    }

    @Override
    public LiveData<JokeWithoutStatus2> getJoke() {
        return remoteDataSource.getRandomJoke();
    }

    @Override
    public void saveJoke(Folder folder, JokeWithoutStatus2 joke) {
        localStorage.saveJoke(joke);
        localStorage.saveJoke(new SavedJoke2(folder.getId(), joke.getId()));
    }

    @Override
    public LiveData<List<SlideModel>> getSlideModels() {
        MutableLiveData<List<SlideModel>> data = new MutableLiveData<>();
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add( new SlideModel(R.mipmap.ic_cat_meme_foreground, ""));
        slideModels.add( new SlideModel(R.mipmap.ic_2020be_like_foreground, ""));
        slideModels.add( new SlideModel(R.mipmap.ic_microphone_foreground, ""));
        slideModels.add( new SlideModel(R.mipmap.ic_corona_foreground, ""));
        slideModels.add( new SlideModel(R.mipmap.ic_quarantine_foreground, ""));
        data.setValue(slideModels);
        return data;
    }

    @Override
    public LiveData<List<Article>> getArticles() {
        return localStorage.getArticles();
    }

    @Override
    public LiveData<List<JokeWithoutStatus2>> getJokes(int folderId) {
        return localStorage.getJokes(folderId);
    }
}
