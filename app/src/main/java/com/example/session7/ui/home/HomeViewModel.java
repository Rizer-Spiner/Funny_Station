package com.example.session7.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.denzcoskun.imageslider.models.SlideModel;
import com.example.session7.model.Article;
import com.example.session7.persistence.ApplicationRepository;
import com.example.session7.persistence.HomeRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private HomeRepository repository;

    public HomeViewModel(Application application) {
        super(application);
        repository = ApplicationRepository.getInstance(application);
    }
    public LiveData<List<SlideModel>> getSlideModels() { return repository.getSlideModels(); }
    public LiveData<List<Article>> getArticle() { return repository.getArticles(); }

}