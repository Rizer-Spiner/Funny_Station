package com.example.session7.persistence;

import androidx.lifecycle.LiveData;

import com.example.session7.model.Folder;

import java.util.List;

public interface FolderRepository {

    LiveData<List<Folder>> getFolders();
    void addFolder(Folder folder);
    void deleteFolder(Folder folder);
}
