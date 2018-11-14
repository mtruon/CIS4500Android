package com.cis4500.music.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cis4500.music.adapters.SearchRecyclerViewAdapter;
import com.cis4500.music.models.Song;
import com.cis4500.music.models.Album;
import com.cis4500.music.models.Artist;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SearchFragment extends ListFragment implements SearchRecyclerViewAdapter.SearchRecyclerViewDelegate {

    private List<String> results;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        results = new ArrayList<>();
        results.add("NOOB");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        recyclerView.setAdapter(new SearchRecyclerViewAdapter(results, this));
        return view;
    }

//    @Override
//    public void didSelectSong(Song song) {
//
//    }

    @Override
    public int numberOfColumns() {
        return 1;
    }

    @Override
    public String getTitle() {
        return "Songs";
    }

}
