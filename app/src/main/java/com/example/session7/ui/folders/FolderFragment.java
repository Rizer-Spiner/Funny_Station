package com.example.session7.ui.folders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.session7.R;
import com.example.session7.adapters.FolderAdapter;
import com.example.session7.model.Folder;
import com.example.session7.ui.dialog.CreateFolderDialog;
import com.example.session7.ui.dialog.DeleteFolderDialog;
import com.example.session7.ui.jokes.JokeActivity2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FolderFragment extends Fragment implements FolderAdapter.OnListItemClickListener {

    static FolderFragment instance;
    RecyclerView recyclerView;
    FolderAdapter folderAdapter;
    FloatingActionButton addFolder;
    FloatingActionButton removeFolder;
    private FolderFragmentViewModel folderFragmentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_folders, container, false);


        recyclerView = root.findViewById(R.id.recycleView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        addFolder = root.findViewById(R.id.fab);
        removeFolder = root.findViewById(R.id.fab2);

        folderFragmentViewModel = new ViewModelProvider(this).get(FolderFragmentViewModel.class);


        addFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFolder();
            }
        });

        removeFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFolder();
            }
        });

        instance = this;

        folderFragmentViewModel.getFolders().observe(getViewLifecycleOwner(), new Observer<List<Folder>>() {
            @Override
            public void onChanged(List<Folder> folders) {
                if (!folders.isEmpty()) {
                    folderAdapter = new FolderAdapter(folders, instance);
                } else {
                    folderAdapter = new FolderAdapter(new ArrayList<Folder>(), instance);
                }
                recyclerView.setAdapter(folderAdapter);

            }
        });
        return root;
    }

    private void removeFolder() {

        FragmentManager fm = getParentFragmentManager();
        DeleteFolderDialog deleteFolderDialog = new DeleteFolderDialog(folderAdapter.getFolderNames());
        deleteFolderDialog.show(fm, deleteFolderDialog.getTag());
    }

    private void addFolder() {

        FragmentManager fm = getParentFragmentManager();
        CreateFolderDialog createFolderDialog = new CreateFolderDialog();
        createFolderDialog.show(fm, createFolderDialog.getTag());
    }

    @Override
    public void onListItemClick(int adapterPosition) {

        Folder contact = folderAdapter.getFolder(adapterPosition);

        Intent intent = new Intent(getActivity(), JokeActivity2.class);
        intent.putExtra("Folder",contact.getId());
        startActivity(intent);
    }
}