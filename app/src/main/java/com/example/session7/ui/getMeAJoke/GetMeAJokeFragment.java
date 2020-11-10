package com.example.session7.ui.getMeAJoke;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.session7.R;
import com.example.session7.model.Folder;
import com.example.session7.model.JokeWithoutStatus2;
import com.example.session7.ui.dialog.SaveJokeDialog;

import java.util.List;

public class GetMeAJokeFragment extends Fragment implements View.OnClickListener {

    Button getMeAJoke;
    Button saveJoke;
    TextView joke;
    List<Folder> foldersData;
    JokeWithoutStatus2 jokeData;
    private GetMeAJokeFragmentViewModel getMeAJokeFragmentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getMeAJokeFragmentViewModel = new ViewModelProvider(this).get(GetMeAJokeFragmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_get_me_a_joke, container, false);

        joke = root.findViewById(R.id.textView8);
        getMeAJoke = root.findViewById(R.id.button6);
        saveJoke = root.findViewById(R.id.button8);

        getMeAJokeFragmentViewModel.getNewJoke().observe(getViewLifecycleOwner(), new Observer<JokeWithoutStatus2>() {
            @Override
            public void onChanged(@Nullable JokeWithoutStatus2 s) {
                jokeData = s;
                joke.setText(s.getJoke());
            }
        });

        getMeAJokeFragmentViewModel.getFolders().observe(getViewLifecycleOwner(), new Observer<List<Folder>>() {
            @Override
            public void onChanged(List<Folder> folders) {
                foldersData = folders;
            }
        });

        getMeAJoke.setOnClickListener(this);
        saveJoke.setOnClickListener(this);

        return root;
    }

    private void saveJoke() {

        if (jokeData.getId()==null)
        {
            Toast.makeText(getContext(), "Get your joke first!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            FragmentManager fm = getParentFragmentManager();
            SaveJokeDialog saveInFolderDialog = new SaveJokeDialog(foldersData, jokeData);
            saveInFolderDialog.show(fm, saveInFolderDialog.getTag());
        }


    }


    public void getJoke() {
        getMeAJokeFragmentViewModel.getNewJoke();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button6: {
                getJoke();
                saveJoke.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.button8: {
                saveJoke();
                break;
            }
            default:
                break;
        }
    }

}