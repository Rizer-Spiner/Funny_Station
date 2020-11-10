package com.example.session7.ui.getMeAJoke;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.session7.model.Folder;
import com.example.session7.model.JokeWithoutStatus2;
import com.example.session7.persistence.ApplicationRepository;
import com.example.session7.persistence.GetMeAJokeRepository;

import java.util.List;

public class GetMeAJokeFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<JokeWithoutStatus2> joke;
    private GetMeAJokeRepository repository;

    public GetMeAJokeFragmentViewModel(Application application) {
        super(application);
        joke = new MutableLiveData<>();
        repository = ApplicationRepository.getInstance(application);
    }

    public LiveData<JokeWithoutStatus2> getJoke() {
        return joke;
    }

    public LiveData<JokeWithoutStatus2> getNewJoke() {
        joke.setValue(repository.getJoke().getValue());
        return joke;
    }

    public void saveJoke(Folder folder, JokeWithoutStatus2 jokeToSave) {
        repository.saveJoke(folder, jokeToSave);
    }

    public LiveData<List<Folder>> getFolders()
    {
       return repository.getFolders();
    }
}