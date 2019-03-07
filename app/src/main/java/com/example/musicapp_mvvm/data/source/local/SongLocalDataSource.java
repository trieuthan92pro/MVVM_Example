package com.example.musicapp_mvvm.data.source.local;

import android.content.ContentResolver;

import com.example.musicapp_mvvm.SoundCloudApplication;
import com.example.musicapp_mvvm.data.source.SongDataSource;

public class SongLocalDataSource implements SongDataSource.LocalDataSource {
    private static SongLocalDataSource sInstance;
    private ContentResolver mContentResolver;

    private SongLocalDataSource() {
        mContentResolver = SoundCloudApplication.getInstance().getContentResolver();
    }

    public static SongLocalDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new SongLocalDataSource();
        }
        return sInstance;
    }

    @Override
    public void getSongs(SongDataSource.LoadSongCallback callback) {
        new LocalSongAsyncTask(mContentResolver, callback).execute();
    }
}
