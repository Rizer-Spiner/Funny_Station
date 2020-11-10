package com.example.session7.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.session7.R;
import com.example.session7.model.Folder;

import java.util.List;

public class FolderAdapter extends RecyclerView.Adapter {

    final private OnListItemClickListener onListItemClickListener;
    private List<Folder> folderNames;

    public FolderAdapter(List<Folder> folderNames, OnListItemClickListener onListItemClickListener) {
        super();
        this.folderNames = folderNames;
        this.onListItemClickListener = onListItemClickListener;
    }

    public List<Folder> getFolderNames() {
        return folderNames;
    }

    public void setFolderNames(List<Folder> folderNames) {
        this.folderNames = folderNames;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.folder_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder holder1 = (ViewHolder) holder;

        holder1.name.setText(folderNames.get(position).getFolderName());
        holder1.icon.setImageResource(R.drawable.ic_folder);
    }

    @Override
    public int getItemCount() {
        return folderNames.size();
    }

    public Folder getFolder(int adapterPosition) {
        return folderNames.get(adapterPosition);
    }


    public interface OnListItemClickListener {

        void onListItemClick(int adapterPosition);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.rv_text);
            icon = itemView.findViewById(R.id.rv_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
}
