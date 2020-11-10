package com.example.session7.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.session7.R;
import com.example.session7.model.Folder;

import com.example.session7.ui.folders.FolderFragmentViewModel;

import java.util.List;

public class DeleteFolderDialog extends DialogFragment {
    FolderFragmentViewModel folderFragmentViewModel;
    List<Folder> folders;

    public DeleteFolderDialog(List<Folder> folders)
    {
        this.folders = folders;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        folderFragmentViewModel = new ViewModelProvider(this).get(FolderFragmentViewModel.class);

        String[] charSequence = new String[folders.size()];
        for (int i = 0; i<folders.size(); i++) {
            charSequence[i] = String.valueOf(folders.get(i).getFolderName());
        }
        final CharSequence[] choices = charSequence;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.pick_folder_singular)
                .setItems(choices, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Folder folderToDelete = folders.get(which);
                        folderFragmentViewModel.deleteFolder(folderToDelete);
                    }
                });
        return builder.create();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.create_folder_dialog, container, false);
        return root;
    }
}
