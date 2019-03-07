package com.example.musicapp_mvvm.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.musicapp_mvvm.R;
import com.example.musicapp_mvvm.SoundCloudApplication;
import com.example.musicapp_mvvm.data.model.Genre;
import com.example.musicapp_mvvm.data.model.GenreType;
import com.example.musicapp_mvvm.data.model.Song;
import com.example.musicapp_mvvm.data.source.SongDataSource;
import com.example.musicapp_mvvm.data.source.SongRepository;

import java.util.ArrayList;
import java.util.List;

public class DiscoverViewModel extends AndroidViewModel {
    private static final int LIMIT = 20;
    private static final String[] GENRES = {
            GenreType.ALL_MUSIC, GenreType.ALL_AUDIO, GenreType.ALTERNATIVE_ROCK,
            GenreType.AMBIENT, GenreType.CLASSICAL, GenreType.COUNTRY
    };
    private SongRepository mSongRepository;
    private String[] mGenreTitles;
    private MutableLiveData<List<Genre>> mGenresMutableLiveData;
    private MutableLiveData<String> mOnGetSongEvent;

    public DiscoverViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadSongs() {
        final List<Genre> genres = new ArrayList<>();
        for (int i = 0; i < mGenreTitles.length; i++) {
            final String genreTitle = mGenreTitles[i];
            final String genreKey = GENRES[i];
            mSongRepository.getSongsByGenre(genreKey, LIMIT, new SongDataSource.LoadSongCallback() {
                @Override
                public void onSongsLoaded(List<Song> songs) {
                    Genre genre = new Genre(genreTitle, songs);
                    genres.add(genre);
                    if (genres.size() == mGenreTitles.length) {
                        mGenresMutableLiveData.setValue(genres);
                    }
                }

                @Override
                public void onDataNotAvailable(Exception e) {
                    mOnGetSongEvent.setValue(e.getMessage());
//                    mGenresMutableLiveData.setValue(new ArrayList<Genre>());
                }
            });
        }
    }

    public void start() {
        mGenreTitles = SoundCloudApplication.getInstance().getResources().getStringArray(R.array.array_genre_titles);
        mSongRepository = SongRepository.getInstance();
        loadSongs();
    }

    public MutableLiveData<List<Genre>> getGenresMutableLiveData() {
        if (mGenresMutableLiveData == null) {
            mGenresMutableLiveData = new MutableLiveData<>();
        }
        return mGenresMutableLiveData;
    }

    public MutableLiveData<String> getOnGetSongEvent() {
        if (mOnGetSongEvent == null) {
            mOnGetSongEvent = new MutableLiveData<>();
        }
        return mOnGetSongEvent;
    }
}
