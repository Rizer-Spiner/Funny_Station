package com.example.session7.persistence;

import androidx.lifecycle.LiveData;

import com.example.session7.model.JokeWithoutStatus2;

import java.util.List;

public interface JokeRepository {
    LiveData<List<JokeWithoutStatus2>> getJokes(int folderId);
}
