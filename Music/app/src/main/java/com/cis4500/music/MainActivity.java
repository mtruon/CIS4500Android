package com.cis4500.music;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cis4500.music.fragments.PlaybackBarFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;
import androidx.transition.ChangeBounds;
import androidx.transition.TransitionManager;

import com.cis4500.music.models.Album;
import com.cis4500.music.models.Artist;
import com.cis4500.music.models.Song;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;


public class MainActivity extends AppCompatActivity implements PlaybackBarFragment.PlaybackBarDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.closeButton).setOnClickListener(v -> collapseNowPlaying());
    }

    public void setBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        // Expand the search view and request focus
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here
/*
                // Dummy data
                List albums = new ArrayList<>();
                albums.add(new Album("OK Computer", "Radiohead", "", 1991, 12));
                albums.add(new Album("Fate/Kaleid", "Various Artists", "", 2012, 12));
                albums.add(new Album("Guardians of the Galaxy Original Score", "Tyler Bates", "", 2013, 12));

                List artists = new ArrayList<>();
                artists.add(new Artist("Daft Punk"));
                artists.add(new Artist("ClariS"));
                artists.add(new Artist("Queen"));

                List songs = new ArrayList<>();
                songs.add(new Song("Exit Music", "OK Computer", "Radiohead", "", -1, -1, -1));
                songs.add(new Song("Starlog", "Fate/Kaleid", "ChouCho", "", -1, -1, -1));
                songs.add(new Song("TWO BY TWO", "Fate/Kaleid", "", "", -1, -1, -1));

                List results = new ArrayList<>();
                results.add(albums);
                results.add(artists);
                results.add(songs);*/


                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

//                NavController.navigate(R.id.action_albumFragment_to_songsInAlbumFragment);
                return false;
            }

        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final SearchView searchView = (SearchView) item.getActionView();
        NavController nav = Navigation.findNavController(findViewById(R.id.fragmentContainer));
        nav.navigate(R.id.searchFragment);
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void didTapPlaybackBar() {
        expandNowPlaying();
    }

    private void expandNowPlaying() {
        ConstraintSet set = new ConstraintSet();
        ConstraintLayout layout = findViewById(R.id.baseLayout);
        set.clone(layout);
        //  TransitionManager.beginDelayedTransition(layout);
        TransitionManager.beginDelayedTransition(layout, new ChangeBounds());
        set.constrainHeight(R.id.playbackBarContainer, layout.getHeight() + Math.round(64*getResources().getDisplayMetrics().density));
        set.applyTo(layout);
    }

    private void collapseNowPlaying() {
        ConstraintSet set = new ConstraintSet();
        ConstraintLayout layout = findViewById(R.id.baseLayout);
        set.clone(layout);
        //  TransitionManager.beginDelayedTransition(layout);
        TransitionManager.beginDelayedTransition(layout, new ChangeBounds());
        set.constrainHeight(R.id.playbackBarContainer, Math.round(64*getResources().getDisplayMetrics().density));
        set.applyTo(layout);
    }
}
