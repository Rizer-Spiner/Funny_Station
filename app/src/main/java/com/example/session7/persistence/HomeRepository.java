package com.example.session7.persistence;

import androidx.lifecycle.LiveData;

import com.denzcoskun.imageslider.models.SlideModel;
import com.example.session7.model.Article;

import java.util.List;

public interface HomeRepository {
    LiveData<List<SlideModel>> getSlideModels();
    LiveData<List<Article>> getArticles();
}
