package com.example.musicapp_mvvm.data.source.local;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.example.musicapp_mvvm.data.model.Song;
import com.example.musicapp_mvvm.data.source.SongDataSource;
import com.example.musicapp_mvvm.data.model.SongType;

import java.util.ArrayList;
import java.util.List;

public class LocalSongAsyncTask extends AsyncTask<Void, Void, List<Song>> {
    private static String message = "DATA NOT AVAILABLE";
    private SongDataSource.LoadSongCallback mCallback;
    private ContentResolver mContentResolver;

    public LocalSongAsyncTask(ContentResolver contentResolver,
                              SongDataSource.LoadSongCallback callback) {
        mCallback = callback;
        mContentResolver = contentResolver;
    }

    @Override
    protected List<Song> doInBackground(Void... voids) {
        return getSongs();
    }

    @Override
    protected void onPostExecute(List<Song> songs) {
        super.onPostExecute(songs);
        if (songs != null && songs.size() != 0) {
            mCallback.onSongsLoaded(songs);
        } else {
            mCallback.onDataNotAvailable(new Exception(message));
        }
    }

    public List<Song> getSongs() {
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM,
        };
        Cursor cursor = mContentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection, selection, null, null);
        List<Song> songs = new ArrayList<>();
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
            String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String duraion = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            int intDuration = Integer.parseInt(duraion);
            int intId = Integer.parseInt(id);
            Song song = new Song();
            song.setId(intId);
            song.setDuration(intDuration);
            song.setTitle(title);
            song.setArtist(artist);
            song.setSongType(SongType.TYPE_LOCAL);
            song.setAlbum(album);
            song.setDownloadURL(data);
            song.setDownloadable(false);
            song.setDownloaded(true);
            song.setArtworkUrl(null);
            song.setFavorite(false);
            song.setGenre(null);
            songs.add(song);
        }
        cursor.close();
        return songs;
    }
}
