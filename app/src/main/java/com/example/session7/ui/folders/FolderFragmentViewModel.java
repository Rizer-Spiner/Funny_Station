package com.example.session7.ui.folders;

import android.app.Application;
import android.widget.MultiAutoCompleteTextView;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.session7.model.Folder;
import com.example.session7.model.JokeWithoutStatus2;
import com.example.session7.persistence.ApplicationRepository;
import com.example.session7.persistence.FolderRepository;
import java.util.List;

public class FolderFragmentViewModel extends AndroidViewModel {



    private FolderRepository repository;

    public FolderFragmentViewModel(Application application) {
        super(application);
        this.repository = ApplicationRepository.getInstance(application);
    }


    public LiveData<List<Folder>> getFolders(){return repository.getFolders();}
    public void deleteFolder(Folder folder){repository.deleteFolder(folder);}
    public void addFolder(Folder folder){repository.addFolder(folder);}

}
