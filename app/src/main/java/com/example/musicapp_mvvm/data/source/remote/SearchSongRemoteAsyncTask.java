package com.example.musicapp_mvvm.data.source.remote;

import android.os.AsyncTask;

import com.example.musicapp_mvvm.data.model.Song;
import com.example.musicapp_mvvm.data.source.SongDataSource;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchSongRemoteAsyncTask extends AsyncTask<String, Void, List<Song>> {
    private SongDataSource.LoadSongCallback mCallBack;
    private Exception mException;
    private String mSearchKey;

    SearchSongRemoteAsyncTask(SongDataSource.LoadSongCallback callBack, String searchKey) {
        mCallBack = callBack;
        mSearchKey = searchKey;
    }

    @Override
    protected List<Song> doInBackground(String... strings) {
        List<Song> songs = new ArrayList<>();
        try {
            String json = SongLoaderUtils.getJSONFromAPI(strings[0]);
            songs = SongLoaderUtils.getSearchSongs(json);
        } catch (IOException e) {
            mException = e;
        } catch (JSONException e) {
            mException = e;
        }
        return songs;
    }

    @Override
    protected void onPostExecute(List<Song> songs) {
        super.onPostExecute(songs);
        if (mCallBack == null) {
            return;
        }
        if (mException == null) {
            mCallBack.onSongsLoaded(songs);
        } else {
            mCallBack.onDataNotAvailable(mException);
        }
    }
}
