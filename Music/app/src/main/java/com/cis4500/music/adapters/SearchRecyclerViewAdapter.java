package com.cis4500.music.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cis4500.music.R;
import com.cis4500.music.models.Song;
import com.cis4500.music.views.TitleDetailImageViewHolder;
import com.cis4500.music.views.TitleImageViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder> {
    private List<String> results;
    private final SearchRecyclerViewAdapter.SearchRecyclerViewDelegate delegate;

    public SearchRecyclerViewAdapter(List<String> results, SearchRecyclerViewAdapter.SearchRecyclerViewDelegate delegate) {
        this.results = results;
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.header_list_item, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.titleView.setText(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public interface SearchRecyclerViewDelegate {
//        void didSelectSong(Song song);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView titleView;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            titleView = view.findViewById(R.id.title);
        }
    }
}
