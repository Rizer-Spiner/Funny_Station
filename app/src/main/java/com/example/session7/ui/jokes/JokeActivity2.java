package com.example.session7.ui.jokes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.session7.R;
import com.example.session7.adapters.JokeAdapter;
import com.example.session7.model.JokeWithoutStatus2;

import java.util.ArrayList;
import java.util.List;

public class JokeActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    private JokesViewModel viewModel;
    JokeAdapter adapter;
    int folder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        recyclerView = findViewById(R.id.recycleView2);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(JokesViewModel.class);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("Folder")) {
            int folderId = bundle.getInt("Folder");
            Log.wtf("FOLDER ID", ""+folderId);
            folder = folderId;
            viewModel.getJokes(folderId).observe(this, new Observer<List<JokeWithoutStatus2>>() {
                @Override
                public void onChanged(List<JokeWithoutStatus2> jokeWithoutStatuses) {
                    if (!jokeWithoutStatuses.isEmpty())
                    {
                        adapter = new JokeAdapter(jokeWithoutStatuses);
                    }
                    else adapter = new JokeAdapter(new ArrayList<>());
                    recyclerView.setAdapter(adapter);
                }
            });

        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true; // must return true to consume it here
        }
        return super.onOptionsItemSelected(item);
    }


}