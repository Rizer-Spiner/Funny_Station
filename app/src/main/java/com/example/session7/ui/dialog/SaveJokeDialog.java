package com.example.session7.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.session7.R;
import com.example.session7.model.Folder;
import com.example.session7.model.JokeWithoutStatus2;
import com.example.session7.ui.getMeAJoke.GetMeAJokeFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class SaveJokeDialog extends DialogFragment {

    GetMeAJokeFragmentViewModel getMeAJokeFragmentViewModel;

    List<Folder> foldersData;
    JokeWithoutStatus2 jokeData;

    public SaveJokeDialog(List<Folder> folders, JokeWithoutStatus2 joke)
    {
        foldersData = folders;
        jokeData = joke;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        getMeAJokeFragmentViewModel = new ViewModelProvider(this).get(GetMeAJokeFragmentViewModel.class);
        final List<Integer> selectedItems = new ArrayList();

        String[] charSequence = new String[foldersData.size()];
        for (int i = 0; i < foldersData.size(); i++) {
            charSequence[i] = String.valueOf(foldersData.get(i).getFolderName());
        }
        final CharSequence[] choices = charSequence;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the dialog title
        builder.setTitle(R.string.pick_folder)
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(choices, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    selectedItems.add(which);
                                } else if (selectedItems.contains(which)) {
                                    selectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        List<Folder> foldersInWhichToSave = new ArrayList<>();

                        for (int i : selectedItems) {
                            foldersInWhichToSave.add(foldersData.get(i));
                        }

                        for (Folder folder : foldersInWhichToSave) {
                            getMeAJokeFragmentViewModel.saveJoke(folder, jokeData);
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getContext(), "Save Joke aborted", Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();


    }
}
