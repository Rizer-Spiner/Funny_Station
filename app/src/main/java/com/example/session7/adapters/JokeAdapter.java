package com.example.session7.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.session7.R;
import com.example.session7.model.JokeWithoutStatus2;

import java.util.List;

public class JokeAdapter extends RecyclerView.Adapter {

    private List<JokeWithoutStatus2> jokes;

    public JokeAdapter(List<JokeWithoutStatus2> folderNames) {
        super();
        this.jokes = folderNames;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.joke_list_item, parent, false);

        return new JokeAdapter.ViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder2 holder1 = (ViewHolder2) holder;
        holder1.name.setText(jokes.get(position).getJoke());

    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }


    class ViewHolder2 extends RecyclerView.ViewHolder {

        TextView name;


        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.rv_text1);
        }

    }


}
