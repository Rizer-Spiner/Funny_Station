package com.example.session7.persistence;

import androidx.lifecycle.LiveData;

import com.example.session7.model.Folder;
import com.example.session7.model.JokeWithoutStatus2;

import java.util.List;

public interface GetMeAJokeRepository {
    LiveData<JokeWithoutStatus2> getJoke();
    void saveJoke(Folder folder, JokeWithoutStatus2 joke);
    LiveData<List<Folder>> getFolders();
}
