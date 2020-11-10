package com.example.session7.ui.jokes;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.session7.model.JokeWithoutStatus2;
import com.example.session7.persistence.ApplicationRepository;
import com.example.session7.persistence.JokeRepository;

import java.util.List;

public class JokesViewModel extends AndroidViewModel {


    private JokeRepository repository;

    public JokesViewModel(Application application) {
        super(application);

        repository = ApplicationRepository.getInstance(application);

    }


    public LiveData<List<JokeWithoutStatus2>> getJokes(int folderId) {

        return repository.getJokes(folderId);
    }


}
