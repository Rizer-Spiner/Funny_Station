package com.example.session7.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.session7.R;
import com.example.session7.model.Folder;
import com.example.session7.ui.folders.FolderFragmentViewModel;

public class CreateFolderDialog extends DialogFragment {
    EditText newFolderName;
    FolderFragmentViewModel folderFragmentViewModel;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        folderFragmentViewModel = new ViewModelProvider(this).get(FolderFragmentViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();



        builder.setMessage(R.string.input_folder);
        final View view = inflater.inflate(R.layout.create_folder_dialog, null);
        builder.setView(view);
        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        newFolderName = view.findViewById(R.id.newFolderName);
                        folderFragmentViewModel.addFolder(new Folder(newFolderName.getText().toString()));
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Create folder aborted", Toast.LENGTH_SHORT).show();
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
