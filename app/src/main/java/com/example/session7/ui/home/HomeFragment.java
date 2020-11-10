package com.example.session7.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.session7.R;
import com.example.session7.adapters.ArticleAdapter;
import com.example.session7.model.Article;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ArticleAdapter.OnListItemClickListener3 {

    static HomeFragment instance;
    ImageSlider imageSlider;
    RecyclerView recyclerView;
    ArticleAdapter articleAdapter;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider = root.findViewById(R.id.slider);
        recyclerView = root.findViewById(R.id.recycleView3);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        instance = this;

        homeViewModel.getArticle().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                if (!articles.isEmpty()) {
                    List<Article> update = new ArrayList<>();
                    update = articles;
                    articleAdapter = new ArticleAdapter(update, instance);
                } else {
                    articleAdapter = new ArticleAdapter(new ArrayList<Article>(), instance);
                }
                recyclerView.setAdapter(articleAdapter);
            }
        });

        homeViewModel.getSlideModels().observe(getViewLifecycleOwner(), new Observer<List<SlideModel>>() {
            @Override
            public void onChanged(List<SlideModel> slideModels) {
                if (!slideModels.isEmpty()) {
                    imageSlider.setImageList(slideModels, true);

                }

            }
        });


        imageSlider.setClickable(true);
        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                Toast.makeText(getActivity(), "Hello from here", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onListItemClick(int adapterPosition) {

        Article article = articleAdapter.getArticle(adapterPosition);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
        startActivity(intent);
    }


}